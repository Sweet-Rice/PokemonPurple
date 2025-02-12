package overWorld;

import basicgraphics.Scene;
import basicgraphics.images.Picture;

import java.awt.*;

public class TileHandler {
    private final int SIZE = 16;

    private Picture picture;
    private int x, y;

    protected NotSoAbstractTile[][] grid;
    private int gridWidth, gridHeight;
    private Scene scene;
    Dimension d;

    public TileHandler( Scene scene) {
        d = scene.getBackgroundSize();
        this.gridWidth = d.width / SIZE;
        this.gridHeight = d.height / SIZE;
        System.out.println("tilehandler created");
        this.scene = scene;

        initGrid();

    }

    public void initGrid() {
        grid = new NotSoAbstractTile[gridHeight][gridWidth];
        for (int y = 0; y < gridHeight; y++) {
            grid[y] = new NotSoAbstractTile[gridWidth];
            for (int x = 0; x < gridWidth; x++) {
                grid[y][x] = new NotSoAbstractTile(scene, 2, true, y, x){

                };
            }
        }
    }

    public void initTile(Picture picture) {

    }
}
