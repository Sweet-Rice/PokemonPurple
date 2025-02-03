

import Sprites.Falcon;
import basicgraphics.*;
import basicgraphics.images.Painter;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameScreen {
    private final Card card;
    private final SpriteComponent sc;
    InputHandler inputHandler;


    public GameScreen(BasicFrame frame) {
        card = frame.getCard();
        sc = new SpriteComponent();
        final Falcon falcon = new Falcon(sc.getScene(), sc);

        initializeBackground();
        setupLayout();
        inputHandler = new InputHandler(falcon, card);
        card.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke){
                inputHandler.keyPressed(ke);
            }
        });
    }

    private void initializeBackground() {
        sc.getScene().setPainter(new StarfieldPainter());
        card.add(sc);
        ClockWorker.addTask(sc.moveSprites());
    }

    private void setupLayout() {
        card.setLayout(new BasicLayout());
        card.add("x=0,y=0,w=1,h=1", sc);
    }

    public void show() {
        card.showCard();
        card.requestFocus();
    }

    private static class StarfieldPainter implements Painter {
        // Original starfield painting code here

        @Override
        public void paint(Graphics g, Dimension d) {
            final int BORDER_SZ=10;
            // 0 . . . . . 0
            // . 1 . . . 1 0
            // . . . . . . .
            // . 1 . . . 1 .
            // 0 . . . . . 0
            g.setColor(Color.blue);
            g.fillRect(0, 0, d.width-BORDER_SZ, BORDER_SZ);
            g.setColor(Color.pink);
            g.fillRect(d.width-BORDER_SZ, 0, BORDER_SZ, d.height-BORDER_SZ);
            g.setColor(Color.cyan);
            g.fillRect(0, d.height-BORDER_SZ, d.width, BORDER_SZ);
            g.setColor(Color.orange);
            g.fillRect(0, BORDER_SZ, BORDER_SZ, d.height);
            g.setColor(Color.black);
            g.fillRect(BORDER_SZ, BORDER_SZ, d.width-2*BORDER_SZ, d.height-2*BORDER_SZ);
            final int NUM_STARS = d.width*d.height/500;
            Random rand = new Random();
            rand.setSeed(0);
            g.setColor(Color.white);
            for(int i=0;i<NUM_STARS;i++) {
                int diameter = rand.nextInt(5)+1;
                int xpos = (int)(rand.nextDouble()*d.width);
                int ypos = (int)(rand.nextDouble()*d.height);
                g.fillOval(xpos, ypos, diameter, diameter);
            }

        }
    }
}