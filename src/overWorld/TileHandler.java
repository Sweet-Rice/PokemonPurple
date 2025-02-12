package overWorld;

import basicgraphics.Scene;

import java.awt.*;

public abstract class TileHandler {
    private final int SIZE = 16;


    private int x, y;

    protected AbstractTile[][] grid;
    private int gridWidth, gridHeight;
    Dimension d;

    public TileHandler( Scene scene) {
        d = scene.getSize();
        this.gridWidth = d.width / SIZE;
        this.gridHeight = d.height / SIZE;
        System.out.println("tilehandler created");

        initGrid();

    }

    public void initGrid() {
        grid = new AbstractTile[gridHeight][gridWidth];
        for (int y = 0; y < gridHeight; y++) {
            grid[y] = new AbstractTile[gridWidth];
            for (int x = 0; x < gridWidth; x++) {
                initTile(y, x);
            }
        }
    }
    abstract void initTile(int y, int x);
}
