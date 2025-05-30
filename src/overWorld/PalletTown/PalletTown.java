package overWorld.PalletTown;

import basicgraphics.*;
import basicgraphics.images.Picture;
import basicgraphics.sounds.ReusableClip;

import overWorld.NotSoAbstractTile;
import overWorld.Overworld;
import overWorld.TileHandler;

import java.awt.*;

public class PalletTown {

    private SpriteComponent sc;

    public Scene outside;
    private Scene playerHouse;
    private Scene oakLab;
    private Scene garyHouse;
    private Overworld overworld;

    public TileHandler outsideTileHandler;
    public TileHandler playerHouseTileHandler;

    public PalletTown(SpriteComponent sc, Overworld o) {
        this.sc = sc;

        this.overworld = o;
        this.outside = sc.getScene();
        System.out.println("I am a outside."+outside.toString());
        this.playerHouse = sc.createScene();
        System.out.println("I am a playerHouse."+playerHouse.toString());
        this.oakLab = sc.createScene();
        this.garyHouse = sc.createScene();
        ReusableClip clip = new ReusableClip("town.zip");
        clip.loop();
        clip.play();
        initOutside();
        initPlayerHouse();



    }
    private void initOutside() {
        //outside.setBackgroundSize(new Dimension(48*25,48*15));




        outsideTileHandler = new TileHandler(outside,3,3) {
            @Override
            public void additionalInit() {
                System.out.println("I am outsideTileHandler."+this.scene.toString());
                otherScenes[1] = playerHouse;
            }
            @Override
            public NotSoAbstractTile initTile(NotSoAbstractTile tile) {
                tile = new OutsideTile(outside, 1, true, this.x, this.y, this);
                return tile;
            }

            @Override
            public void setTilePicture(Picture picture, int x, int y) {
                this.getTile(x,y).setPicture(picture);
            }
            @Override
            public void migrate() {
                System.out.println(overworld.player.scene.toString());
                overworld.player.migrate(playerHouse,0,0,0,0);
                overworld.player.tileHandler=playerHouseTileHandler;
                overworld.player.scene = playerHouse;
                System.out.println("migrated");
                System.out.println(overworld.player.scene.toString());


                playerHouse.setFocus(overworld.player);
                overworld.player.setPosition(playerHouseTileHandler.walkableTile());

            }


        };

        outsideTileHandler.updateGrid("generic/outside1.csv");
        System.out.println("Pallet Town");
        //these next few lines in particular are how to set up special event tiles.

        //modify the outsideTile class's specifiedBehaviorAction.
        outsideTileHandler.setTilePicture(new Picture("outsidefloor_00.png"), 0,1);
        //outsideTileHandler.grid[8][0].setWalkable(false);
        outsideTileHandler.getTile(0,1).setType(2);
        outsideTileHandler.getTile(0,1).setBehavior(1);


    }
    private void initPlayerHouse() {

        playerHouseTileHandler = new TileHandler(playerHouse,20,15) {
            @Override
            public void additionalInit(){
                System.out.println("I am playerHouseTileHandler."+this.scene.toString());
            }
            @Override
            public NotSoAbstractTile initTile(NotSoAbstractTile tile) {
                tile = new PlayerHouseTile(playerHouse, 1, true, this.x, this.y, playerHouseTileHandler);
                return tile;
            }

            @Override
            public void setTilePicture(Picture picture, int x, int y) {
                playerHouseTileHandler.getTile(x,y).setPicture(picture);
            }
        };
    }

    public void enterPalletTown() {
        sc.swapScene(outside);
        //migrate player sprite and put bro in the top
    }

}
