package generic;

import basicgraphics.ClockWorker;
import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.Task;
import basicgraphics.images.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TransitionSprite extends Sprite {
private int goal;
private int r, g, b;
private Graphics2D graphics;
private BufferedImage image;
private boolean fadeOut;
    public TransitionSprite(Scene sc, int goal) {

        super(sc);

        //gaol 0 is black goal 1 is white
        this.goal = goal;

        this.image = new BufferedImage(sc.getSpriteComponent().getPreferredSize().width+200, sc.getSpriteComponent().getPreferredSize().height, BufferedImage.TYPE_INT_ARGB);
        this.graphics = image.createGraphics();
    }
    private void setTransition(int opacity) {
        if (goal == 1) {
            this.r = 255;
            this.g = 255;
            this.b = 255;
            graphics.setColor(new java.awt.Color(this.r, this.g, this.b,opacity));
        }else{
            this.r = 0;
            this.g = 0;
            this.b = 0;
            graphics.setColor(new java.awt.Color(this.r, this.g, this.b,opacity));
        }
    }
    private void perform(double velocity){
        ClockWorker.addTask(new Task(255) {
            double speed = 0;
            @Override
            public void run() {
                //System.out.println(iteration());
                BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D graphics = newImage.createGraphics();
                speed = iteration()*(iteration()/velocity);
                if (speed>255)speed=255;
                graphics.setColor(new Color(r,g,b,maxIteration()-(int)speed));
                graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
                setDrawingPriority(100);
                setPicture(new Picture(newImage));
                if (speed==255){setFinished();}
            }
        });
    }
    private void perform1(double velocity){
        ClockWorker.addTask(new Task(255) {
            double speed = 0;
            @Override
            public void run() {
                //System.out.println(iteration());
                BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D graphics = newImage.createGraphics();
                speed = iteration()*(iteration()/velocity);
                if (speed>255)speed=255;
                graphics.setColor(new Color(r,g,b,(int)speed));
                graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
                setDrawingPriority(100);
                setPicture(new Picture(newImage));
                if (speed == 255){
                    setFinished();
                    waitfordeath();
                }
            }
        });
    }
    public void transition(boolean fade, int velocity){
        fadeOut = fade;
        if (fadeOut) {
            setTransition(0);
        }else { setTransition(255);}


        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        Picture squarePicture = new Picture(image);
        setDrawingPriority(100);
        setPicture(squarePicture);
        if (fadeOut) {
            perform1(velocity);
        } else {perform(velocity);}
    }
    private void waitfordeath(){
        ClockWorker.addTask(new Task(100) {
            @Override
            public void run() {
                if( iteration()==maxIteration())destroy();
            }
        });
    }
}
