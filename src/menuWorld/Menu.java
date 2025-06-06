package menuWorld;

import basicgraphics.*;
import basicgraphics.images.Picture;
import basicgraphics.sounds.ReusableClip;
import generic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;

public class Menu {

    public final Card card;

    private SpriteComponent sc;

    private Red red;
    private Gary gary;
    private BouncingSprite bouncingsprite;

    private Scene scene,beginSequenceScene;
    private LetterHandler topLh1, title, bottomLh1, play;

    private int beginProg = 0;

    final ReusableClip titleClip = new ReusableClip("title.zip");
    //final ReusableClip buttonClip = new ReusableClip("button1.wav");

    GameManager gm;
    private boolean begin = true, lecture = true;

    public Menu(BasicFrame frame, GameManager gr) {
        card = frame.getCard();
        this.sc = new SpriteComponent();
        sc.setPreferredSize(new Dimension(900, 600));
        System.out.println(sc.getPreferredSize().getWidth()+ " "+sc.getPreferredSize().getHeight());

        scene=sc.getScene();
        this.beginSequenceScene = sc.createScene();
        ClockWorker.initialize(33);
        ClockWorker.addTask(sc.moveSprites());
        //sc.swapScene(scene);


        card.addKeyListener(new KeyAdapter() {
            //int stage = 0;
            @Override
            public void keyPressed(KeyEvent a) {
                System.out.println("caught key");
                if (beginProg == 0) {
                    if (((a.getKeyCode()==KeyEvent.VK_ENTER||a.getKeyCode()==KeyEvent.VK_Z))&&begin) {
                        begin = false;
                        ClockWorker.addTask(new Task(50){

                            TransitionSprite spr = new TransitionSprite(sc.getScene(),1);
                            @Override
                            public void run() {
                                if (iteration()==0){
                                    spr.transition(true, 1);
                                }
                                if (iteration()==20){
                                    spr.destroy();

                                    initializeBeginSequence();
                                    //gm.switchGame();
                                    setFinished();
                                    beginProg = 1;
                                }
                            }
                        });
                    }
                }else if (beginProg == 1) {
                    if ((a.getKeyCode() == KeyEvent.VK_Z )&&lecture) {
                        lecture = false;
                        ClockWorker.addTask(new Task(40){
                            @Override
                            public void run() {
                                if (iteration()==0){
                                    topLh1.fadeOut();
                                    bottomLh1.fadeOut();

                                }
                                if (iteration()==20){
                                    topLh1.destroy();
                                    bottomLh1.destroy();
                                    TransitionSprite spr = new TransitionSprite(sc.getScene(),0);
                                    spr.transition(true, 1);
                                }
                                if (iteration()==40){
                                    titleClip.loop();
                                    titleClip.stop();
                                    gm.switchOak();}
                            }
                        });

                    }
                }
            }
        });
        initializeMenu();
        this.gm = gr;
    }

    private void initializeMenu() {

        titleClip.loop();
        titleClip.play();

        BasicLayout blayout = new BasicLayout();
        card.add(sc);
        sc.setLayout(blayout);
        //ClockWorker.addTask(sc.moveSprites());
        bouncingsprite = new BouncingSprite(scene, sc);
        red = new Red(scene, sc);
        gary = new Gary(scene, sc);

        InputStream fontStream = getClass().getResourceAsStream("/fonts/pokemon_fire_red.ttf");
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Font font1 = font.deriveFont(Font.BOLD, 60);
        Font font2 = font.deriveFont(Font.BOLD, 30);
        title = new LetterHandler(310, 300,400,100,sc, font1, new Color(138,43,226),"Purple Version", 9);
        title.shadow();
        title.showNow();

        play = new LetterHandler(355, 500, 400, 100, sc, font1, Color.BLACK,"Press Enter",10);
        //LetterHandler playShadow = new LetterHandler(358,502, 400, 100, sc, font1,Color.gray,"Press Enter",9);
        play.shadow();

        play.showNow();
        ClockWorker.addTask(new Task() {
            boolean hid = false;
            @Override
            public void run() {
                if (iteration()%20==0){
                    if (hid){
                        play.showNow();
                        hid=false;
                    } else {
                        play.hideNow();
                        hid=true;
                    }
                }
            }
        });
    }
    private void initializeBeginSequence() {
        ClockWorker.finish();
        ClockWorker.initialize(33);
        red.destroy();
        gary.destroy();
        bouncingsprite.destroy();
        title.destroy();
        play.destroy();

        sc.swapScene(beginSequenceScene);
        sc.setPreferredSize(new Dimension(900, 600));

        System.out.println(sc.getPreferredSize().width + " "+ sc.getPreferredSize().height);
       TransitionSprite transitionSprite = new TransitionSprite(beginSequenceScene,1);
        transitionSprite.transition(false, 1);
        Sprite introBox = new Sprite(beginSequenceScene);
        introBox.setDrawingPriority(1);

        introBox.setPicture(new Picture("introBox.png"));

        InputStream fontStream = getClass().getResourceAsStream("/fonts/pokemon_fire_red.ttf");
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        font = font.deriveFont(Font.PLAIN, 57);
        int grayOffset = 60;
        String string1 = "Welcome to Pokemon Purple! " +
                "Here are the controls: " +
                "";


        Color gray = new Color(90,90,90);
        topLh1 = new LetterHandler(40, 140, 820, 200,sc,font, gray,string1,50 );
        topLh1.shadow();
        //topLhShadow1 = new LetterHandler(43, 142, 800, 200,sc,font, Color.lightGray,string1,49);
        topLh1.fadeIn();

        String string2 = "The SELECT button is (SHIFT)." +
                " Use the arrow keys to move. " +
                "Use the A(Z) button to select, " +
                "or the B(X) button to go back." +
                " START(ENTER) will open your menu. " +
                "Use A(Z) to chat.";
        bottomLh1 = new LetterHandler(40, 250, 700, 400,sc,font, gray,string2,50);
        bottomLh1.shadow();

        bottomLh1.fadeIn();




    }
}