package overWorld;

import basicgraphics.Scene;
import basicgraphics.images.Picture;

import java.awt.*;

public abstract class TileHandler {
    private final int SIZE = 48;

    protected Picture picture;
    protected int x, y;

    private NotSoAbstractTile[][] grid;
    private int gridWidth, gridHeight;
    public Scene scene;
    public Scene[] otherScenes = new Scene[10];
    private Dimension d;


    public TileHandler( Scene scene, int width, int height) {
        this.scene = scene;

        //this.d = this.scene.getBackgroundSize();
        //this.gridWidth = d.width / SIZE;
        //this.gridHeight = d.height / SIZE;
        this.gridWidth = width + (2*paddingX);
        this.gridHeight = height + (2*paddingY);

        scene.setBackgroundSize(new Dimension(gridWidth*SIZE, gridHeight*SIZE));


        initGrid();
        additionalInit();


    }

    public void initGrid() {
        grid = new NotSoAbstractTile[gridHeight][gridWidth];
        for ( y = 0; y < gridHeight; y++) {
            //potentially not necessary
            //grid[y] = new NotSoAbstractTile[gridWidth];
            for ( x = 0; x < gridWidth; x++) {

                grid[y][x] = initTile(grid[y][x]);
                if (y<paddingY||y>=gridHeight-paddingY||x<paddingX||x>gridWidth-paddingX) {
                    grid[y][x].walkable = false;


                    //placeholder till i figure smt out
                    grid[y][x].setPicture(new Picture("outsidefloor_39.png"));
                }

            }
        }

    }
    public abstract NotSoAbstractTile initTile(NotSoAbstractTile tile);
    public abstract void setTilePicture(Picture picture, int x, int y);


    private static final  int paddingX = 9, paddingY = 6;

    //unfortunately, this method is necessary given how I handled the player movement method.
    public NotSoAbstractTile playerGetTile(int x, int y) {
        //
        return grid[y][x];
    }
    public NotSoAbstractTile getTile(int x, int y) {

        return grid[y+paddingY][x+paddingX];
    }
    public int getGridHeight() {
        return gridHeight;
    }
    public int getGridWidth() {
        return gridWidth;
    }

    public void setTile(int x, int y, NotSoAbstractTile tile) {
        grid[y+paddingY][x+paddingX] = tile;
    }
    public static int getX(int x){
        return x+paddingX;

    }
    public static int getY(int y){
        return y+paddingY;
    }
    public void additionalInit(){

    }
    //this is gonna be hard to code. will leave notes
    public void migrate(){

    }
    public NotSoAbstractTile walkableTile(){
        System.out.println("debug only");
        for (y = 0; y < gridHeight; y++) {
            for (x = 0; x < gridWidth; x++) {
                if (grid[y][x].walkable) {
                    return grid[y][x];
                }
            }
        }
        System.out.println("No walkable tile found");
        return null;
    }
}


