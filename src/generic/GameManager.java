package generic;

import basicgraphics.BasicFrame;
import menuWorld.Menu;
import overWorld.Overworld;

public class GameManager {
    private final BasicFrame frame;
    private final Menu splashScreen;
    private final GameScreen gameScreen;
    private final Overworld overworld;

    public GameManager(BasicFrame frame) {
        this.frame = frame;

        this.gameScreen = new GameScreen(frame, this);
        this.splashScreen = new Menu(frame, this);
        this.overworld = new Overworld(frame, this);
        switchSplash();

    }

    public void switchSplash() {
        splashScreen.card.showCard();
        splashScreen.card.requestFocus();
    }
    public void switchGame() {
        //gameScreen.card.showCard();
        //gameScreen.card.requestFocus();
        overworld.card.showCard();
        overworld.card.requestFocus();
    }

}