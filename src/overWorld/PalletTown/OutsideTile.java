package overWorld.PalletTown;

import basicgraphics.Scene;
import basicgraphics.images.Picture;
import overWorld.NotSoAbstractTile;
import overWorld.TileHandler;

import java.util.Random;

public class OutsideTile extends NotSoAbstractTile {


    private TileHandler tileHandler;
    public OutsideTile(Scene sc, int type, boolean walkable, int x, int y, TileHandler tileHandler) {
        super(sc, type, walkable, x, y, tileHandler);
        //System.out.println("created outside tile");
        //setDrawingPriority(0);
        setTile();
        this.tileHandler = tileHandler;
    }

    @Override
    public void setTile() {
        Random rand = new Random();
        int x = rand.nextInt(3);
        //System.out.println("x: " + x);
        switch (x) {

            case 0->setPicture(new Picture("outsidefloor_13.png"));
            case 1->setPicture(new Picture("outsidefloor_14.png"));
            case 2 -> setPicture(new Picture("outsidefloor_15.png"));

            default -> throw new IllegalStateException("Unexpected value: " + x);
        }

    }

    @Override
    public void specifiedBehaviorAction(){
        switch (behavior){
            case 1->{
                //System.out.println("behavior 1");
                //sc.getSpriteComponent().swapScene(this.tileHandler.otherScenes[1]);
                this.tileHandler.migrate();
            }
        }
    }
}
