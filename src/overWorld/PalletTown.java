package overWorld;

import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;

import java.awt.*;

public class PalletTown implements GlobalFunc{

    private SpriteComponent sc;

    public Scene outside;
    private Scene playerHouse;
    private Scene oakLab;
    private Scene garyHouse;

    public PalletTown(SpriteComponent sc) {

        this.outside = sc.createScene();
        this.playerHouse = sc.createScene();
        this.oakLab = sc.createScene();
        this.garyHouse = sc.createScene();

        outside.setBackgroundSize(new Dimension(800,400));

        //everything below is just testing stuff
        TileHandler tileHandler = new TileHandler(outside) {
            @Override
            void initTile(int y, int x) {
                grid[y][x] = new OutsideTile(outside,1, true, y,x);
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
