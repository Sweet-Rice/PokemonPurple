package OakLectureWorld;

import basicgraphics.*;
import basicgraphics.images.BackgroundPainter;
import basicgraphics.images.Picture;
import basicgraphics.sounds.ReusableClip;
import generic.GameManager;
import generic.LetterHandler;
import generic.TransitionSprite;
import menuWorld.Red;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;

public class OakLecture {

    public final Card card;
    private Scene scene;
    private int state;
    private boolean wait = true;
    private RedArrow redArrow;

    private String player, rival;

    private LetterHandler lh1, lh1one, lh2, lh3, lh4, lh5, lh6, lh7, lh8, lh9, lh10;
    private final ReusableClip clip;
    private SpriteComponent sc;
    GameManager gm;
    private Font font;
    private int x, y;
    private Color gray = new Color(90,90,90);

    public OakLecture(BasicFrame frame, GameManager gm) {
        this.card = frame.getCard();
        this.gm = gm;
        clip = new ReusableClip("lab.zip");
        clip.loop();
        this.sc = new SpriteComponent();
        sc.setPreferredSize(new Dimension(900,600));
        card.createSingletonLayout(sc);


        scene = sc.createScene();
        sc.swapScene(scene);
        System.out.println(scene.getSize().getWidth());

        ClockWorker.initialize(33);
        ClockWorker.addTask(sc.moveSprites());
        Sprite bg = new Sprite(scene);
        bg.setDrawingPriority(0);
        bg.setPicture(new Picture("oakIntro.png"));
        Sprite oak = new Sprite(scene);
        oak.setDrawingPriority(1);
        oak.setPicture(new Picture("oakSprite.png"));
        oak.setX(350);oak.setY(90);

        TransitionSprite spr = new TransitionSprite(scene, 0);
        spr.transition(false,5);

        ClockWorker.addTask(new Task(50){

            @Override
            public void run() {
                if (iteration()==maxIteration()){
                    state = 1;
                    initLecture();

                    clip.play();
                }
            }
        });
        redArrow = new RedArrow(scene);
        redArrow.setX(800);redArrow.setY(540);
        redArrow.is_visible = false;
        card.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e ){
                boolean uh = false;
                if (e.getKeyCode() == KeyEvent.VK_Z&&!wait){
                    switch (state){
                        case 1->{
                            lh1.hideNow();lh1one.hideNow();
                            lh1.destroy();lh1one.destroy();
                            init2();
                            lh2.showStrings();
                            redArrow.is_visible = false;
                            await(50);
                        }
                        case 2->{
                            lh2.hideNow();
                            lh2.destroy();
                            init3();
                            lh3.showStrings();
                            redArrow.is_visible = false;
                            await(30);
                        }
                        case 3 ->{
                            lh3.hideNow();
                            lh3.destroy();
                            init4();
                            lh4.showStrings();
                            redArrow.is_visible = false;
                            await(60);
                        }
                        case 4 ->{
                            lh4.hideNow();
                            lh4.destroy();
                            init5();
                            lh5.showStrings();
                            redArrow.is_visible = false;
                            await(40);
                        }
                        case 5 ->{
                            lh5.hideNow();
                            lh5.destroy();
                            init6();
                            lh6.showStrings();
                            redArrow.is_visible = false;
                            await(80);
                        }
                        case 6 ->{
                            lh6.hideNow();
                            lh6.destroy();
                            init7();
                            lh7.showStrings();
                            redArrow.is_visible = false;
                            await(90);
                        }
                        case 7 ->{
                            lh7.hideNow();
                            lh7.destroy();
                            init8();
                            lh8.showStrings();
                            redArrow.is_visible = false;
                            await(40);
                        }
                        case 8 ->{
                            lh8.hideNow();
                            lh8.destroy();
                            init9();
                            lh9.showStrings();
                            redArrow.is_visible = false;
                            await(40);
                        }
                        case 9 ->{
                            lh9.hideNow();
                            lh9.destroy();
                            init10();
                            lh10.showStrings();
                            redArrow.is_visible = false;
                            await(80);
                        }
                        case 10 ->{
                            await(100);
                            TransitionSprite spr = new TransitionSprite(scene, 0);
                            spr.transition(true,5);
                            ClockWorker.addTask(new Task(50){

                                @Override
                                public void run() {
                                    if (iteration()==maxIteration()){
                                        lh10.destroy();
                                        redArrow.destroy();
                                        clip.stop();
                                        gm.switchGame();
                                    }
                                }
                            });

                        }

                    }
                }
            }
        });

    }
    public void initLecture(){
        redArrow.is_visible= false;
        Sprite textBox = new Sprite(sc.getScene());
        textBox.setDrawingPriority(2);
        textBox.setPicture(new Picture("textBoxLarge.png"));
        textBox.setX(20);textBox.setY(440);


        //clip.play();


        x = (int)textBox.getX()+50;
        y = (int)textBox.getY()+20;

        InputStream fontStream = getClass().getResourceAsStream("/fonts/pokemon_fire_red.ttf");
        font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        font = font.deriveFont(Font.PLAIN, 57);


        String one, one1, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fifteen, sixteen, seventeen, eighteen;
        //Group 1
        one = "Hello there!";
        one1 = "Glad to meet you!";


        lh1 = new LetterHandler(x,y,400, 100, sc, font, gray,one,10);
        lh1.shadow();
        lh1one = new LetterHandler(x,y+50,400, 100, sc, font, gray,one1,10);
        lh1one.shadow();
        lh1.showStrings();
        ClockWorker.addTask(new Task(20){

            @Override
            public void run() {
                if(iteration()==maxIteration()){
                    lh1one.showStrings();
                    ClockWorker.addTask(new Task(50){

                        @Override
                        public void run() {

                            if (iteration()==maxIteration()){
                                wait = false;
                                redArrow.is_visible= true;

                            }
                        }
                    });
                }
            }
        });
















    }
    //These init methods are required to reduce lag generated from an insane amount of letter sprites
    private void init2(){

        String two = "Welcome to the world of Pokemon!";
        lh2 = new LetterHandler(x,y,800, 100, sc, font, gray,two,10);
        lh2.shadow();
    }
    private void init3(){
        String three = "My name is Oak.";
        lh3 = new LetterHandler(x,y,800, 200, sc, font, gray,three,10);
        lh3.shadow();
    }
    private void init4(){
        String four = "People affectionately refer to me as the Pokemon Professor.";
        lh4 = new LetterHandler(x,y,800, 200, sc, font, gray,four,10);
        lh4.shadow();
    }
    private void init5(){
        String five = "This world...";
        lh5 = new LetterHandler(x,y,800, 100, sc, font, gray,five,10);
        lh5.shadow();
    }
    private void init6(){
        String six = "...is inhabited far and wide by creatures called Pokemon.";
        lh6 = new LetterHandler(x,y,800, 200, sc, font, gray,six,10);
        lh6.shadow();
    }
    private void init7(){
        String seven = "For some people, Pokemon are pets. Others use them for battling.";
        lh7 = new LetterHandler( x,y,800, 200, sc, font, gray,seven,10);
        lh7.shadow();
    }
    private void init8(){
        String eight = "As for myself...";
        lh8 = new LetterHandler( x,y,800, 100, sc, font, gray,eight,10);
        lh8.shadow();
    }
    private void init9(){
        String nine = "I study Pokemon as a profession.";
        lh9 = new LetterHandler( x,y,800, 100, sc, font, gray,nine,10);
        lh9.shadow();
    }
    private void init10(){
        String ten = "Red, your very own Pokemon legend is about to unfold! Let's go!";
        lh10 = new LetterHandler( x,y,800, 200, sc, font, gray,ten,10);
        lh10.shadow();
    }
    public void await(int waitTime){
        state++;
        wait = true;
        ClockWorker.addTask(new Task(waitTime){

            @Override
            public void run() {
                if (iteration()==maxIteration()){
                    wait = false;
                    redArrow.is_visible= true;
                }

            }
        });
    }

    public String getRival() {
        return rival;
    }
    public String getPlayer(){
        return player;
    }
}
