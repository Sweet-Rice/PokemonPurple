package overWorld;

import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;

public class PalletTown implements GlobalFunc{

    private SpriteComponent sc;

    private Scene outside;
    private Scene playerHouse;
    private Scene oakLab;
    private Scene garyHouse;

    public PalletTown(SpriteComponent sc) {

        this.outside = sc.createScene();
        this.playerHouse = sc.createScene();
        this.oakLab = sc.createScene();
        this.garyHouse = sc.createScene();


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
