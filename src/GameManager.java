import basicgraphics.BasicFrame;
import basicgraphics.Card;

public class GameManager {
    private final BasicFrame frame;
    private final Menu splashScreen;
    private final GameScreen gameScreen;

    public GameManager(BasicFrame frame) {
        this.frame = frame;

        this.gameScreen = new GameScreen(frame);
        this.splashScreen = new Menu(frame, gameScreen);
        splashScreen.card.showCard();

    }

    public void switchCard(Card card) {

    }

}