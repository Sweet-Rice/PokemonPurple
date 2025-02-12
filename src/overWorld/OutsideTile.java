package overWorld;

import basicgraphics.Scene;
import basicgraphics.images.Picture;

public class OutsideTile extends NotSoAbstractTile {

    public OutsideTile(Scene sc, int type, boolean walkable, int x, int y) {
        super(sc, type, walkable, x, y);
        System.out.println("created tile");
    }

    @Override
    public void setTile() {
        setPicture(new Picture("outside_02.png"));
    }
}
