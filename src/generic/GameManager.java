package generic;

import basicgraphics.BasicFrame;
import basicgraphics.ClockWorker;
import basicgraphics.Task;
import menuWorld.Menu;
import overWorld.Overworld;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameManager {
    private final BasicFrame frame;
    private final Menu splashScreen;
    //private final Overworld overworld;
    private int currentCard;

    public GameManager(BasicFrame frame) {
        this.frame = frame;
        //this.overworld = new Overworld(frame, this);
        this.splashScreen = new Menu(frame, this);




        splashScreen.card.requestFocusInWindow();
        switchSplash();

/*
        frame.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("caught key");
                if (currentCard==0){
                    splashScreen.keyAction(e);
                }else if (currentCard==1){
                    overworld.keyAction(e);
                }
            }
        });

 */
    }

    public void switchSplash() {
        currentCard = 0;
        ClockWorker.addTask(new Task(20) {
            @Override
            public void run() {
                if (iteration()%10==0){
                    System.out.println(splashScreen.card.isFocusable());
                    if (splashScreen.card.isFocusOwner()) {
                        System.out.println("is owner");
                        setFinished();
                    }else{
                        System.out.println("is not owner");
                    }
                } if (splashScreen.card.isFocusable()) {
                    splashScreen.card.requestFocus();
                }else System.out.println("is not focusable");


            }
        });

    }
    public void switchGame() {
        currentCard = 1;
        //gameScreen.card.showCard();
        //gameScreen.card.requestFocus();
        Overworld overworld = new Overworld(frame,this);

        overworld.card.showCard();
        overworld.card.requestFocusInWindow();

        ClockWorker.addTask(new Task() {
            @Override
            public void run() {
                if (iteration()%10==0){
                    System.out.println(overworld.card.isFocusable());
                    if (overworld.card.isFocusOwner()) {
                        System.out.println("is owner");
                        setFinished();
                    }else{
                        System.out.println("is not owner");
                    }
                } if (overworld.card.isFocusable()) {
                    overworld.card.requestFocus();
                }else {
                    System.out.println("is not focusable, trying again");
                    overworld.card.requestFocus();
                }


            }
        });

    }

}