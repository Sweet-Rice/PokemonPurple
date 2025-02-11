package overWorld;

import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.images.Picture;

import java.awt.*;

public abstract class AbstractTile extends Sprite {
    protected static Picture initPic = new Picture("outside_00.png");
    protected Picture picture;
    public boolean walkable = true;
    protected Scene sc;
    protected Dimension d;

    protected int type;

    public AbstractTile(Scene sc, int type, boolean walkable, int x, int y) {
        super (sc);
        this.walkable = walkable;
        this.type = type;
        d = sc.getSize();
        setY(y*16);
        setX(x*16);
        setTile();
    }

    //should take in the int type and set a picture for tile
    abstract void setTile ();


}
