package overWorld.PalletTown;

import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.images.Picture;
import overWorld.NotSoAbstractTile;
import overWorld.TileHandler;

public class Player extends Sprite {
    public Scene scene;
    public Scene scene2;
    public TileHandler tileHandler;

    private NotSoAbstractTile currentTile;
    public Player(Scene scene, TileHandler tileHandler) {
        super(scene);
        this.tileHandler = tileHandler;
        this.scene = scene;
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
    public void move(NotSoAbstractTile tile){
        if (tile.requestMoveHere()){
            setX(tile.getX());
            setY(tile.getY());

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
                    move(tileHandler.grid[thisY+1][thisX]);
                }

            }
            case 1->{
                if ((thisY-1)>=0){
                    move(tileHandler.grid[thisY-1][thisX]);
                }
            }
            case 2->{
                if ((thisX-1)>=0){
                    move(tileHandler.grid[thisY][thisX-1]);
                }
            }
            case 3->{
                if ((thisX+1)<x){
                    move(tileHandler.grid[thisY][thisX+1]);
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
}
