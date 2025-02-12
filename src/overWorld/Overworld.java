package overWorld;

import basicgraphics.*;
import generic.GameManager;
import generic.GameScreen;

public class Overworld {


    public final Card card;

    private SpriteComponent sc;



    private Scene outside;
    GameManager gm;

    public final PalletTown palletTown;

    public Overworld(BasicFrame frame, GameManager gm) {
        this.gm = gm;
        card = frame.getCard();
        this.sc = new SpriteComponent();
        card.createSingletonLayout(sc);

        palletTown = new PalletTown(sc);
        //GlobalFunc global = new PalletTown(sc);
        //global.switchScene(outside);

        sc.swapScene(palletTown.outside);
        ClockWorker.initialize(33);
        ClockWorker.addTask(sc.moveSprites());






    }

}
