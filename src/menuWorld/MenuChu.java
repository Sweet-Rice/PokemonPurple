package menuWorld;

import basicgraphics.ClockWorker;
import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.Task;
import basicgraphics.images.Picture;

public class MenuChu extends Sprite {
    public MenuChu(Scene scene) {
        super(scene);
        setX(0);
        setY(-10);
        setDrawingPriority(10);
        setPicture(new Picture("pikachu1.png"));
        ClockWorker.addTask(new Task() {
            boolean swap = false;
            @Override
            public void run() {
                if (iteration()%20==0){
                    if (swap){
                        setPicture(new Picture("pikachu2.png"));
                        swap = false;
                    } else {
                        setPicture(new Picture("pikachu1.png"));
                        swap = true;
                    }
                }
            }
        });
    }
}
