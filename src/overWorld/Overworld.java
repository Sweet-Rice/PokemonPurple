package overWorld;

import basicgraphics.*;
import generic.GameManager;
import generic.GameScreen;
import menuWorld.Gary;

import java.awt.*;

public class Overworld {


    public final Card card;

    private SpriteComponent sc;



    //private Scene outside;
    GameManager gm;

    public final PalletTown palletTown;

    public Overworld(BasicFrame frame, GameManager gm) {
        this.gm = gm;
        card = frame.getCard();
        this.sc = new SpriteComponent();
        sc.setPreferredSize(new Dimension(800,400));
        card.createSingletonLayout(sc);

        palletTown = new PalletTown(sc);
        //GlobalFunc global = new PalletTown(sc);
        //global.switchScene(outside);
        //palletTown.outside = sc.getScene();
        //Gary gary = new Gary(palletTown.outside, sc);


        sc.swapScene(palletTown.outside);
        ClockWorker.initialize(33);
        ClockWorker.addTask(sc.moveSprites());






    }

}
