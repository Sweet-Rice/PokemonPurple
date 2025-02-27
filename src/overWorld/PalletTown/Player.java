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
        Picture idle = new Picture("player_01.png");
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

            walk(direction);
            final int steps = 16;
            ClockWorker.addTask(new Task(steps) {
                int x = 1;
                @Override
                public void run() {
                    if (iteration()%5==0){


                    }




                    if (iteration()==maxIteration()-1){
                        setX(tile.getX());
                        setY(tile.getY());
                        currentTile = tile;
                        setVel(0,0);
                        tile.behaviorAction();
                        busy = false;


                    }
                }
            });


        } else{
            double currentX = getX();
            double currentY = getY();
            switch (direction){
                case 0-> setPicture(new Picture("player_01.png"));
                case 1-> setPicture(new Picture("player_37.png"));
                case 2-> setPicture(new Picture("player_13.png"));
                case 3-> setPicture(new Picture("player_25.png"));
            }


            setX(currentX+12);
            setY(currentY+45);

            busy = false;}

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
                    move(tileHandler.playerGetTile(thisX,thisY+1),direction);
                    //walk(0);
                }

            }
            case 1->{
                if ((thisY-1)>=0){
                    busy = true;
                    move(tileHandler.playerGetTile(thisX,thisY-1),direction);
                }
            }
            case 2->{
                if ((thisX-1)>=0){
                    busy = true;
                    move(tileHandler.playerGetTile(thisX-1,thisY),direction);
                }
            }
            case 3->{
                if ((thisX+1)<x){
                    busy = true;
                    move(tileHandler.playerGetTile(thisX+1,thisY),direction);
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
        final int steps = 15;
        ClockWorker.addTask(new Task(steps) {
            int x = 1;
            double currentX = getX();
            double currentY = getY();
            @Override
            public void run() {
                if (iteration() % 5 == 0) {
                    //down
                    if (direction == 0) {
                        switch (x){
                            case 1->{setPicture(new Picture("player_00.png"));x++;}
                            case 2->{setPicture(new Picture("player_01.png"));x++;}
                            case 3->{setPicture(new Picture("player_02.png"));x++;}
                            case 4->{setPicture(new Picture("player_01.png"));x=1;}
                        }
                        currentY+=12;

                    }
                    //up
                    if (direction == 1) {
                        switch (x){
                            case 1->{setPicture(new Picture("player_36.png"));x++;}
                            case 2->{setPicture(new Picture("player_37.png"));x++;}
                            case 3->{setPicture(new Picture("player_38.png"));x++;}
                            case 4->{setPicture(new Picture("player_37.png"));x=1;}
                        }

                        currentY-=12;
                    }
                    //left
                    if (direction == 2) {
                        switch (x){
                            case 1->{setPicture(new Picture("player_12.png"));x++;}
                            case 2->{setPicture(new Picture("player_13.png"));x++;}
                            case 3->{setPicture(new Picture("player_14.png"));x++;}
                            case 4->{setPicture(new Picture("player_13.png"));x=1;}

                        }
                        currentX-=12;
                    }
                    //right
                    if (direction == 3) {
                        switch (x){
                            case 1->{setPicture(new Picture("player_24.png"));x++;}
                            case 2->{setPicture(new Picture("player_25.png"));x++;}
                            case 3->{setPicture(new Picture("player_26.png"));x++;}
                            case 4->{setPicture(new Picture("player_25.png"));x=1;}

                        }
                        currentX+=12;
                    }
                    setX(currentX+12);
                    setY(currentY+45);
                }
            }
        });
    }

}
