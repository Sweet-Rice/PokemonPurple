package overWorld.PalletTown;

import basicgraphics.Scene;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import overWorld.GlobalFunc;
import overWorld.NotSoAbstractTile;
import overWorld.Overworld;
import overWorld.TileHandler;

import java.awt.*;

public class PalletTown implements GlobalFunc {

    private SpriteComponent sc;

    public Scene outside;
    private Scene playerHouse;
    private Scene oakLab;
    private Scene garyHouse;
    private Overworld overworld;

    public TileHandler outsideTileHandler;

    public PalletTown(SpriteComponent sc, Overworld o) {
        this.overworld = o;
        this.outside = sc.getScene();
        this.playerHouse = sc.createScene();
        this.oakLab = sc.createScene();
        this.garyHouse = sc.createScene();

        initOutside();




    }
    private void initOutside() {
        outside.setBackgroundSize(new Dimension(48*25,48*15));


        outsideTileHandler = new TileHandler(outside) {

            @Override
            public NotSoAbstractTile initTile(NotSoAbstractTile tile) {
                tile = new OutsideTile(outside, 1, true, this.x, this.y);
                return tile;
            }

            @Override
            public void setTilePicture(Picture picture, int x, int y) {
                grid[y][x].setPicture(picture);
            }
        };


        //Below is how
        System.out.println("Pallet Town");
        outsideTileHandler.setTilePicture(new Picture("outsidefloor_00.png"), 0,8);

        /*
        outside.setFocus(overworld.player);
        outside.periodic_x = true;
        outside.periodic_y = true;

         */
    }

    public void enterPalletTown() {
        sc.swapScene(outside);
        //migrate player sprite and put bro in the top
    }

    @Override
    public void fly() {
        //tp on pokecenter
    }

    @Override
    public void respawn() {
        //spawn in pokecenter, automatically interact with nurse joy. Consider
        //making healing a static method, since its the same everywhere
    }

    @Override
    public void switchScene(Scene scene) {

    }
}
