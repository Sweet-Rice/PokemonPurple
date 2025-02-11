package overWorld;

import basicgraphics.BasicFrame;
import basicgraphics.Card;
import basicgraphics.Scene;
import basicgraphics.SpriteComponent;
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
        palletTown = new PalletTown(sc);
        //GlobalFunc global = new PalletTown(sc);





    }

}
