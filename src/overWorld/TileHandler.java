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
        this.scene = scene;

        initGrid();



    }

    public void initGrid() {
        grid = new NotSoAbstractTile[gridHeight][gridWidth];
        for ( y = 0; y < gridHeight; y++) {
            //potentially not necessary
            //grid[y] = new NotSoAbstractTile[gridWidth];
            for ( x = 0; x < gridWidth; x++) {

                grid[y][x] = initTile(grid[y][x]);

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
