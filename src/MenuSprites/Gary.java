package MenuSprites;

import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
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

        setX(200);
        setY(25);
        setVel(-10,0);
        freezeOrientation = true;

        //setVel(getVelX()*-1,0);
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

        if (se.xlo) {
            setVel(0,0);
        }

    }
}