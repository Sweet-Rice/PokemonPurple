package generic;

import basicgraphics.BasicFrame;
import basicgraphics.ClockWorker;
import basicgraphics.Task;

public class Main {
    public static void main(String[] args) {
        BasicFrame bf = new BasicFrame("Flyer");
        GameManager gameManager = new GameManager(bf);
        bf.setResize();
        //FontLoader.loadAllFonts();
        bf.show();
        ClockWorker.addTask(new Task() {
            @Override
            public void run() {
                System.gc();
            }
        });
    }
}