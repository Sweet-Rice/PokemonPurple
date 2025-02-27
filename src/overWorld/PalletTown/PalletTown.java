package overWorld.PalletTown;

import basicgraphics.*;
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

        initOutside();
        initPlayerHouse();



    }
    private void initOutside() {
        outside.setBackgroundSize(new Dimension(48*25,48*15));


        outsideTileHandler = new TileHandler(outside,25,15) {
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

                //playerHouseTileHandler.initGrid();
                //Unsure why, but it isn't migrating correctly? Or playerHouseTileHandler is in the wrong place?
                playerHouse.setFocus(overworld.player);
                overworld.player.setPosition(playerHouseTileHandler.walkableTile());

            }


        };

        // the below commented code is deprecated and can no longer perform its job correctly.

        // edit 1: the current idea to fix migration is to select the special tile and change its behavior to something different. Then,
        //override the specifiedBehaviorAction
        /*
        sc.addSpriteSpriteCollisionListener(Player.class, OutsideTile.class, new SpriteSpriteCollisionListener<Player, OutsideTile>() {
            @Override
            public void collision(Player sp1, OutsideTile sp2) {
                if (sp2.getType()==2){
                    ClockWorker.addTask(new Task(25) {
                        @Override
                        public void run() {
                            if (iteration()>maxIteration()-1){
                                sp1.migrate(playerHouse, 0,0,0,0);
                                //sp1.setPosition(playerHouseTileHandler.grid[0][0]);
                                sp1.move(playerHouseTileHandler.getTile(0,0), 1);
                            }
                        }
                    });

                }
            }
        });

        */


        //Below is setup.
        System.out.println("Pallet Town");
        //these next few lines in particular are how to set up special event tiles.
        //deprecated ....
        /*
        outsideTileHandler.setTile(1,1,new OutsideTile(outside,1,true,TileHandler.getX(1),TileHandler.getY(1)){
            @Override
            public void specifiedBehaviorAction(){
                System.out.println("Pallet Town specifiedBehaviorAction");
                sc.getSpriteComponent().swapScene(playerHouse);
            }
            @Override
            public void setTile(){
                setPicture(new Picture("outsidefloor_01.png"));
            }
        }
        );
        */


        //modify the outsideTile class's specifiedBehaviorAction.
        outsideTileHandler.setTilePicture(new Picture("outsidefloor_00.png"), 0,1);
        //outsideTileHandler.grid[8][0].setWalkable(false);
        outsideTileHandler.getTile(0,1).setType(2);
        outsideTileHandler.getTile(0,1).setBehavior(1);


    }
    private void initPlayerHouse() {
        //playerHouse.setBackgroundSize(new Dimension(48*20,48*12));
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
