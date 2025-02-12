package overWorld;

import basicgraphics.Scene;
import basicgraphics.images.Picture;

public class OutsideTile extends AbstractTile {

    public OutsideTile(Scene sc, int type, boolean walkable, int x, int y) {
        super(sc, type, walkable, x, y);
        System.out.println("created tile");
    }

    @Override
    void setTile() {
        setPicture(new Picture("outside_001.png"));
    }
}
