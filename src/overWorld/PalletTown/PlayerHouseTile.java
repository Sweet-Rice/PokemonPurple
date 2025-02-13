package overWorld.PalletTown;

import basicgraphics.Scene;
import basicgraphics.images.Picture;
import overWorld.NotSoAbstractTile;

public class PlayerHouseTile extends NotSoAbstractTile {
    public PlayerHouseTile(Scene sc, int type, boolean walkable, int x, int y) {
        super(sc, type, walkable, x, y);
        System.out.println("created playerHouseTile");

        setTile();
    }

    @Override
    public void setTile() {
        setPicture(new Picture("outsidefloor_01.png"));
    }
}
