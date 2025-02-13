package basicgraphics.flyer;

import basicgraphics.BasicFrame;
import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.images.Picture;

import java.awt.*;

public class Plasma extends Sprite {

    /**
     * Creates a picture of a ball with the given color and size.
     *
     * @param color
     * @param size
     * @return
     */
    public static Picture makeBall(Color color, int size) {
        Image im = BasicFrame.createImage(size, size);
        Graphics g = im.getGraphics();
        g.setColor(color);
        g.fillOval(0, 0, size, size);
        return new Picture(im);
    }

    /**
     * Just sets the picture.
     *
     * @param sc
     */
    public Plasma(Scene sc) {
        super(sc);
        setPicture(makeBall(Color.red, Flyer.PLASMA_SIZE));
    }

    /**
     * Disappears if it comes in contact with the display boundary.
     *
     * @param se
     */
    @Override
    public void processEvent(SpriteCollisionEvent se) {
        //setActive(false);
    }
}