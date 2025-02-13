package overWorld;

import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.images.Picture;

import java.awt.*;

public abstract class NotSoAbstractTile extends Sprite {
    protected static Picture initPic = new Picture("outside_01.png");
    protected Picture picture;
    public boolean walkable = true;
    protected Scene sc;
    protected Dimension d;



    protected int type;

    public NotSoAbstractTile(Scene sc, int type, boolean walkable, int x, int y) {

        super (sc);
        this.sc = sc;
        this.walkable = walkable;
        this.type = type;
        d = sc.getBackgroundSize();
        setY(y*48);
        setX(x*48);
        setDrawingPriority(1);
        setTile();
        setTile();
        //^^^this needs to be here and I have no idea why
        System.out.println("created tile");
        setPicture(initPic);
    }

    //should take in the int type and set a picture for tile
    public void setTile() {

    }

    //modify this if you want to implement ledges
    public boolean requestMoveHere(){
        if (walkable) {
            return true;
        }return  false;
    }
    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }
    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

}
