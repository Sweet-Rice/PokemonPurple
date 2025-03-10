package menuWorld;

import basicgraphics.*;
import basicgraphics.images.Picture;

import java.awt.*;

public class Red extends Sprite {
    public Picture initialPic;

    /**
     * Initializes the sprite, setting its picture,
     * position, and speed. It also adds it to the
     * SpriteComponent.
     *
     * @param sc
     */
    public Red(Scene sc, SpriteComponent scw) {
        super(sc);
        initialPic = new Picture("red.png");
        setPicture(initialPic);
        Dimension d = sc.getSize();
        setX(-200);
        setY(325);
        setVel(10, 0);

        ClockWorker.addTask(new Task() {
            @Override
            public void run() {
                if (getX()>=0){
                    setX(0);
                    setVel(0,0);
                    setFinished();
                }
            }
        });
    }


}