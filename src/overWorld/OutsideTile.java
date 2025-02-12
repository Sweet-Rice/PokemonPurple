package overWorld;

import basicgraphics.Scene;
import basicgraphics.images.Picture;

import java.util.Random;

public class OutsideTile extends NotSoAbstractTile {

    public OutsideTile(Scene sc, int type, boolean walkable, int x, int y) {
        super(sc, type, walkable, x, y);
        System.out.println("created tile");
    }

    @Override
    public void setTile() {
        Random rand = new Random();
        int x = rand.nextInt(3);
        System.out.println("x: " + x);
        switch (x) {

            case 0->setPicture(new Picture("outsidefloor_13.png"));
            case 1->setPicture(new Picture("outsidefloor_14.png"));
            case 2 -> setPicture(new Picture("outsidefloor_15.png"));

            default -> throw new IllegalStateException("Unexpected value: " + x);
        }

    }
}
