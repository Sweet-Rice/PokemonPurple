package overWorld;

import basicgraphics.Scene;
import basicgraphics.images.Picture;

import java.awt.*;

public abstract class TileHandler {
    private final int SIZE = 48;

    protected Picture picture;
    protected int x, y;

    public NotSoAbstractTile[][] grid;
    private int gridWidth, gridHeight;
    private Scene scene;
    Dimension d;
    Class c;

    public TileHandler( Scene scene) {
        d = scene.getBackgroundSize();
        this.gridWidth = d.width / SIZE;
        this.gridHeight = d.height / SIZE;
        //System.out.println("tilehandler created");
        this.scene = scene;

        initGrid();



    }

    public void initGrid() {
        grid = new NotSoAbstractTile[gridHeight][gridWidth];
        for ( y = 0; y < gridHeight; y++) {
            grid[y] = new NotSoAbstractTile[gridWidth];
            for ( x = 0; x < gridWidth; x++) {
                //grid[y][x] = new NotSoAbstractTile(scene, 2, true, y, x){
                grid[y][x] = initTile(grid[y][x]);
                //grid[y][x].setDrawingPriority(0);
                //grid[y][x].setPicture(new Picture("outsidefloor_02.png"));
                //grid[y][x].setPicture(picture);
                //};
            }
        }
    }
    public abstract NotSoAbstractTile initTile(NotSoAbstractTile tile);
    public abstract void setTilePicture(Picture picture, int x, int y);


    public int getGridHeight() {
        return gridHeight;
    }
    public int getGridWidth() {
        return gridWidth;
    }
}
