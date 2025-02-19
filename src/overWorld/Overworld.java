package overWorld;

import basicgraphics.*;
import generic.GameManager;
import overWorld.PalletTown.PalletTown;
import overWorld.PalletTown.Player;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Overworld {


    public final Card card;

    private SpriteComponent sc;
    public Player player;


    //private Scene outside;
    GameManager gm;

    public final PalletTown palletTown;

    public Overworld(BasicFrame frame, GameManager gm) {
        this.gm = gm;
        card = frame.getCard();
        this.sc = new SpriteComponent();
        sc.setPreferredSize(new Dimension(48*16,48*12));

        card.createSingletonLayout(sc);

        palletTown = new PalletTown(sc, this);
        //GlobalFunc global = new PalletTown(sc);
        //global.switchScene(outside);
        //palletTown.outside = sc.getScene();
        //Gary gary = new Gary(palletTown.outside, sc);


        sc.swapScene(palletTown.outside);
        player = new Player(palletTown.outside, palletTown.outsideTileHandler);

        player.setPosition(palletTown.outsideTileHandler.grid[7][9]);

        sc.getScene().setFocus(player);
        /*
        sc.getScene().periodic_x = true;
        sc.getScene().periodic_y = true;
*/

        ClockWorker.initialize(33);
        ClockWorker.addTask(sc.moveSprites());


        card.addKeyListener(new KeyAdapter() {
            int stage = 0;
            //0 = player
            //-1 = task in progress
            // 1 = dialogue click
            @Override
            public void keyPressed(KeyEvent e) {
                if (stage == 0&&!player.busy) {

                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        player.moveDirection(0);

                    }
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        player.moveDirection(1);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        player.moveDirection(2);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        player.moveDirection(3);
                    }
                }
                if (stage == 1) {

                    System.out.println("attempted");
                }
            }
        });



    }

}
