package OakLectureWorld;

import basicgraphics.ClockWorker;
import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.Task;
import basicgraphics.images.Picture;

public class RedArrow extends Sprite {

    private boolean which = false;
    public RedArrow(Scene sc) {
        super(sc);
        setDrawingPriority(20);
        setPicture(new Picture("redArrow1.png"));


        ClockWorker.addTask(new Task(){
            @Override
            public void run() {
                if (iteration()%20==0){
                    setDrawingPriority(20);
                    if (which) {
                        setPicture(new Picture("redArrow2.png"));
                        which = false;
                    } else {
                        setPicture(new Picture("redArrow1.png"));
                        which = true;
                    }
                }
            }
        });
    }
}
