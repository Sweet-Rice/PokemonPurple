package overWorld.PalletTown;

import basicgraphics.ClockWorker;
import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.Task;
import basicgraphics.images.Picture;
import overWorld.NotSoAbstractTile;
import overWorld.TileHandler;

public class Player extends Sprite {
    public Scene scene;
    public Scene scene2;
    public TileHandler tileHandler;
    public boolean busy = false;
    private int iteration = 1;

    private NotSoAbstractTile currentTile;
    public Player(Scene scene, TileHandler tileHandler) {
        super(scene);
        this.tileHandler = tileHandler;
        this.scene = scene;
        freezeOrientation = true;
        Picture idle = new Picture("player_00.png");
        setDrawingPriority(2);
        setPicture(idle);




    }
    public void setPosition (NotSoAbstractTile tile){
        if (tile.requestMoveHere()){
            setX(tile.getX());
            setY(tile.getY());
            currentTile = tile;
        }
        else {
            throw new IllegalStateException("Player cannot be positioned on an unwalkable tile");
        }
    }
    public void move(NotSoAbstractTile tile, int direction){
        if (tile.requestMoveHere()){


            final int steps = 10;
            ClockWorker.addTask(new Task(steps) {
                int x = 1;
                @Override
                public void run() {
                    if (iteration()%5==0){

                        if (direction==0){
                            if (x==1){
                                setPicture(new Picture("player_00.png"));

                                x++;
                            } else if (x==2){
                                setPicture(new Picture("player_02.png"));
                                x=1;
                            }

                        }
                        setX(tile.getX());
                        setY(tile.getY());
                    }
                    /*
                    if (iteration() == 2)
                        setPicture(new Picture("player_00.png"));
                    if (iteration() == 4)
                        setPicture(new Picture("player_02.png"));

                     */


                    if (iteration()==maxIteration()-1){
                        //setX(tile.getX());
                        //setY(tile.getY());
                        setVel(0,0);
                        busy = false;


                    }
                }
            });

        }
    }

    //0 is down, 1 is up, 2 is left, 3 is right
    public void moveDirection(int direction){
        int y = tileHandler.getGridHeight();
        int x = tileHandler.getGridWidth();
        int thisX = (12+(int)getX())/48;
        int thisY = (45+(int)getY())/48;
        switch (direction){
            case 0->{
                if ((thisY+1)<y){
                    //setVelY(5);
                    busy = true;
                    move(tileHandler.grid[thisY+1][thisX],direction);
                    //walk(0);
                }

            }
            case 1->{
                if ((thisY-1)>=0){
                    busy = true;
                    move(tileHandler.grid[thisY-1][thisX],direction);
                }
            }
            case 2->{
                if ((thisX-1)>=0){
                    busy = true;
                    move(tileHandler.grid[thisY][thisX-1],direction);
                }
            }
            case 3->{
                if ((thisX+1)<x){
                    busy = true;
                    move(tileHandler.grid[thisY][thisX+1],direction);
                }
            }
        }
    }

    @Override
    public void setX(double x) {
        super.setX(x-12);
    }
    @Override
    public void setY(double y) {
        super.setY(y-45);

    }
    private void walk(int direction){
        int steps = 10;
        ClockWorker.addTask(new Task(steps) {


            @Override
            public void run() {

            }
        });
    }

}
