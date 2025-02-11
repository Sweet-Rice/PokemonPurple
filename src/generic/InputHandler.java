package generic;

import menuWorld.Falcon;
import menuWorld.Plasma;
import basicgraphics.Card;
import basicgraphics.ClockWorker;
import basicgraphics.SpriteComponent;
import basicgraphics.Task;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class InputHandler extends KeyAdapter {
    private final Falcon falcon;
    private final Card gameCard;
    final double INCR =  0.06283185307179587;
    private SpriteComponent sc;

    public InputHandler(Falcon falcon, Card gameCard) {
        this.falcon = falcon;
        sc = falcon.scw;
        this.gameCard = gameCard;
        gameCard.addKeyListener(this);


    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            falcon.rotate( INCR);
        } else if(ke.getKeyCode() == KeyEvent.VK_LEFT) {
            falcon.rotate(-INCR);
        } else if(ke.getKeyChar() == ' ') {
            firePlasma();
        }

    }

    private void firePlasma() {
        // Sprites.Plasma firing logic
        Plasma pl = new Plasma(sc.getScene());
        pl.setVel(falcon.getVelX()*2, falcon.getVelY()*2);
        pl.setCenterX(falcon.centerX());
        pl.setCenterY(falcon.centerY());
        final int steps = 225, bloom = 30;
        ClockWorker.addTask(new Task(steps) {
            @Override
            public void run() {
                if(iteration() + bloom >= maxIteration()) {
                    Color c = Color.white;
                    if(iteration() + bloom/4 < maxIteration())
                        c = Color.yellow;
                    pl.setPicture(
                            Plasma.makeBall(c,10 + iteration()-steps+bloom));
                }
                if(iteration() == maxIteration()) {
                    pl.destroy();

                }
            }
        });


    }
}