package menuWorld;

import basicgraphics.*;
import basicgraphics.images.Picture;

import java.awt.*;

public class Gary extends Sprite {
    public Picture initialPic;

    /**
     * Initializes the sprite, setting its picture,
     * position, and speed. It also adds it to the
     * SpriteComponent.
     *
     * @param sc
     */
    public Gary(Scene sc, SpriteComponent scw) {
        super(sc);
        initialPic = new Picture("gary.png");
        setPicture(initialPic);
        Dimension d = sc.getSize();

        setX(1000);
        setY(325);
        setVel(-10,0);
        freezeOrientation = true;
        ClockWorker.addTask(new Task() {
            @Override
            public void run() {
                if (getX()<=775){
                    setX(775);
                    setVel(0,0);
                }
            }
        });
        //setVel(getVelX()*-1,0);
    }


}