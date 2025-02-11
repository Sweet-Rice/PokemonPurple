import basicgraphics.BasicFrame;
import basicgraphics.Card;

public class GameManager {
    private final BasicFrame frame;
    private final Menu splashScreen;
    private final GameScreen gameScreen;

    public GameManager(BasicFrame frame) {
        this.frame = frame;

        this.gameScreen = new GameScreen(frame, this);
        this.splashScreen = new Menu(frame, this);
        switchSplash();

    }

    public void switchSplash() {
        splashScreen.card.showCard();
        splashScreen.card.requestFocus();
    }
    public void switchGame() {
        gameScreen.card.showCard();
        gameScreen.card.requestFocus();
    }

}