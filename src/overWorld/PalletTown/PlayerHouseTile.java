package overWorld.PalletTown;

import basicgraphics.Scene;
import basicgraphics.images.Picture;
import overWorld.NotSoAbstractTile;
import overWorld.TileHandler;

public class PlayerHouseTile extends NotSoAbstractTile {
    public PlayerHouseTile(Scene sc, int type, boolean walkable, int x, int y, TileHandler tileHandler) {
        super(sc, type, walkable, x, y, tileHandler);
        //System.out.println("created playerHouseTile");
        if (walkable) {
            System.out.print("walkable");

        }
        System.out.println(sc.toString());
        setTile();
    }

    @Override
    public void setTile() {
        setPicture(new Picture("outsidefloor_01.png"));
    }
}
