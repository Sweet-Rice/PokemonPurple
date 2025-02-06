package Sprites;

import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;

import java.awt.*;

public class SaveSprite extends Sprite {
    public Picture initialPic;
    private LetterHandler lh;
    private SpriteComponent scw;


    public SaveSprite(Scene sc, SpriteComponent scw) {
        super(sc);
        this.scw = scw;
        initialPic = new Picture("save.png");
        setPicture(initialPic);
        Dimension d = sc.getSize();
        setY(d.getHeight()/15);
        setX(d.getWidth()/2);

        //lh = new LetterHandler(getX(), getY(), getWidth(), getHeight(), scw);


    }
}