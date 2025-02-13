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
        this.playerHouse = sc.createScene();
        this.oakLab = sc.createScene();
        this.garyHouse = sc.createScene();

        initOutside();
        initPlayerHouse();



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
                                sp1.move(playerHouseTileHandler.grid[0][0], 1);
                            }
                        }
                    });

                }
            }
        });


        //Below is how
        System.out.println("Pallet Town");
        outsideTileHandler.setTilePicture(new Picture("outsidefloor_00.png"), 0,8);
        //outsideTileHandler.grid[8][0].setWalkable(false);
        outsideTileHandler.grid[8][0].setType(2);


    }
    private void initPlayerHouse() {
        playerHouse.setBackgroundSize(new Dimension(48*20,48*12));
        playerHouseTileHandler = new TileHandler(playerHouse) {

            @Override
            public NotSoAbstractTile initTile(NotSoAbstractTile tile) {
                tile = new PlayerHouseTile(playerHouse, 1, true, this.x, this.y);
                return tile;
            }

            @Override
            public void setTilePicture(Picture picture, int x, int y) {
                grid[y][x].setPicture(picture);
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
