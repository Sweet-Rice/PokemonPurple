package menuWorld;

import basicgraphics.*;
import basicgraphics.images.Picture;
import basicgraphics.sounds.ReusableClip;
import generic.GameManager;
import generic.GameScreen;
import generic.TransitionSprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Menu {
    //private GameScreen gs;

    public final Card card;

    private SpriteComponent sc;
    private SpriteComponent r;
    private SpriteComponent g;

//menu part
    private Red red;
    private Gary gary;
    private BouncingSprite bouncingsprite;

//save part
    private SaveSprite saveSprite;
    //issue inside the sprite

    private Scene scene;
    private Scene saveScene;
    private Scene beginSequenceScene;


    final ReusableClip titleClip = new ReusableClip("title3.wav");
    //final ReusableClip buttonClip = new ReusableClip("button1.wav");

    GameManager gm;


    public Menu(BasicFrame frame, GameManager gr) {
        card = frame.getCard();
        this.sc = new SpriteComponent(){
        @Override
            public void paintBackground(Graphics g) {
                Dimension d = getSize();
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, d.width, d.height);
            }
        };
        this.r = new SpriteComponent();
        this.g = new SpriteComponent();

        scene=sc.getScene();
        this.beginSequenceScene = sc.getScene();
        saveScene= sc.createScene();

        //sc.swapScene(scene);

        initializeMenu();
        //this.gs = gs;

        this.gm = gr;

    }

    private void initializeMenu() {

        ClockWorker.initialize(33);
        titleClip.loop();

        BasicLayout blayout = new BasicLayout();
        card.add(sc);
        sc.setLayout(blayout);



        //sc.add("x=0,y=0,w=4,h=3",sc);
        sc.add("x=0,y=3,w=1,h=3",r);
        sc.add("x=3,y=3,w=1,h=3",g);



        ClockWorker.addTask(sc.moveSprites());
        ClockWorker.addTask(r.moveSprites());
        ClockWorker.addTask(g.moveSprites());

        bouncingsprite = new BouncingSprite(sc.getScene(), sc);

        red = new Red(r.getScene(), r);
        gary = new Gary(g.getScene(), g);
        //letter = new Letter(sc.getScene(),0,0);


        //test
        InputStream fontStream = getClass().getResourceAsStream("/fonts/pokemon_fire_red.ttf");
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JLabel title = new JLabel("Purple Version");

        title.setFont(font.deriveFont(Font.BOLD, 50));
        title.setForeground(Color.MAGENTA);
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setHorizontalAlignment(0);

        sc.add( "x=1,y=3,w=2,h=1", title);

        //card.add("m", title);

        JButton startButton = new JButton("Start");
        sc.add("x=1,y=5,w=2,h=1", startButton);


        startButton.setFont(font.deriveFont(Font.BOLD, 100));

        startButton.setForeground(Color.BLACK);
        startButton.setBackground(Color.white);
        startButton.setBorderPainted(false);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //  buttonClip.playOverlapping();

                //titleClip.stop();
                //optimally shouldn't need to do all this. will figure out eventually
                ///*

                //*/
                sc.remove(startButton);
                sc.remove(g);
                sc.remove(r);
                sc.remove(title);

                ClockWorker.addTask(new Task(50){

                    @Override
                    public void run() {
                        if (iteration()==0){
                            TransitionSprite spr = new TransitionSprite(sc.getScene(),1, true);
                        }
                        if (iteration()==50){

                            initializeSaveSc();
                            //initializeBeginSequence();
                        }
                    }
                });
                //gm.switchGame();

            }
        });


    }
    private void initializeBeginSequence() {
        sc.swapScene(beginSequenceScene);
        TransitionSprite transitionSprite = new TransitionSprite(saveScene,1, false);
        System.out.println(sc.getSize().getWidth()+" "+sc.getSize().getHeight());
    }
    private void initializeSaveSc(){
        //Picture white = new Picture("white.jpg");
        //white.setBackground(Color.white);
        //saveScene.setPainter(new BackgroundPainter( white));
        sc.swapScene(saveScene);
        saveSprite = new SaveSprite(saveScene, sc);

        TransitionSprite transitionSprite = new TransitionSprite(saveScene,1, false);
        ClockWorker.initialize(33);
        ClockWorker.addTask(sc.moveSprites());
        BasicLayout blayout1 = new BasicLayout();



    }

}