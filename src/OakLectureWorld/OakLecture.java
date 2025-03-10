package OakLectureWorld;

import basicgraphics.*;
import basicgraphics.images.BackgroundPainter;
import basicgraphics.images.Picture;
import generic.GameManager;
import generic.TransitionSprite;

import java.awt.*;

public class OakLecture {

    public final Card card;
    private Scene scene;

    private SpriteComponent sc;
    GameManager gm;

    public OakLecture(BasicFrame frame, GameManager gm) {
        this.card = frame.getCard();
        this.gm = gm;
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

        ClockWorker.addTask(new Task(100){

            @Override
            public void run() {
                if (iteration()==maxIteration()){
                    initLecture();
                }
            }
        });


    }
    public void initLecture(){
        Sprite textBox = new Sprite(sc.getScene());
        textBox.setDrawingPriority(2);
        textBox.setPicture(new Picture("textBoxLarge.png"));
        textBox.setX(20);textBox.setY(440);
    }


}
