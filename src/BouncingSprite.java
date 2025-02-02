/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import basicgraphics.*;
import basicgraphics.images.Picture;

import java.awt.*;

/**
 *
 * @author sbrandt
 */
public class BouncingSprite extends Sprite {
    public Picture initialPic;
    public final static double FAC = .9;
    public SpriteComponent scw;
    /**
     * Initializes the sprite, setting its picture,
     * position, and speed. It also adds it to the
     * SpriteComponent.
     *
     * @param sc
     */
    public BouncingSprite(Scene sc, SpriteComponent scw) {
        super(sc);
        initialPic = new Picture("title.png");
        setPicture(initialPic);
        Dimension d = sc.getSize();
        setVelY(10);
        setX(150);
        setY(-200);



        this.scw = scw;
    }

    private void vibrate() {
        final int steps = 10;
        ClockWorker.addTask(new Task(steps) {
            @Override
            public void run() {
                if(iteration() %2 ==0) {
                    setVelY(getVelY()*-1);

                }
                if(iteration() == steps-1) {
                    setVelY(0);
                    setY(50);


                }

            }
        });
    }

    /**
     * This sprite only reacts to collisions with the
     * borders of the display region. When it does, it
     * wraps to the other side.
     * @param se
     */
    @Override
    public void processEvent(SpriteCollisionEvent se) {
        SpriteComponent sc = getSpriteComponent();

        if (se.yhi) {
            setVelY(-20);
            setY(0);

                vibrate();


        }


    }
}
