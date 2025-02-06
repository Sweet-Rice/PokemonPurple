package Sprites;

import basicgraphics.ClockWorker;
import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.Task;
import basicgraphics.images.Picture;

public class LetterHandler {
    private final int WIDTH = 24;
    private final int HEIGHT = 27;
//width and height of letters


    private int x;
    private int y;
    //location of box

    private final int XYDISPLACEMENT = 40;
    //displacement from the walls of box

    private int width;
    private int height;
    //width and height of box

    private Letter[] letters;

    private String text;
    //will serve as the displayable text.
    private String remainingText;
    //will act as a buffer for text that cannot be displayed.

    private Letter[][] grid;
    private int gridWidth;
    private int gridHeight;

    //should serve as the layout of the locations of the letters

    private SpriteComponent sc;
    public LetterHandler(double x, double y, double width, double height, SpriteComponent sc) {
        this.sc = sc;
        this.x = (int)x + XYDISPLACEMENT;
        this.y = (int)y + XYDISPLACEMENT;
        this.width = (int)width;
        this.height = (int)height;

        gridWidth=((int)width-(2*XYDISPLACEMENT))/24;
        gridHeight=((int)height-(2*XYDISPLACEMENT))/27;

        grid = new Letter[(int)gridHeight][(int)gridWidth];
        //organizes the grid in an array of rows. Should be easier management.
        //tldr y,x
        initGrid();
        setText("ee");
    }
    public void initGrid(){
        double xLoc = x;
        double yLoc = y;

        for (int i = 0; i < gridHeight; i++) {

            for (int j = 0; j < gridWidth; j++) {
                grid[i][j] = new Letter(sc.getScene());
                grid[i][j].setX(xLoc);
                grid[i][j].setY(yLoc);
                xLoc += 24;

            }
            xLoc = x;
            yLoc += 27;
        }
    }
    public void setText(String textIn) {
        //this.remainingText = textIn;
        Picture white = new Picture("A.png");
        int length = textIn.length();

        int emptyTiles = 0;
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                if (grid[i][j].isreal==false) {
                    emptyTiles++;
                    System.out.println(emptyTiles);
                }
            }

        }
        int max = 0;
        if (emptyTiles > textIn.length()) {
            max = textIn.length();
        }
        else { max = emptyTiles; }
        text = textIn.substring(0, max);
        remainingText = textIn.substring(max);

        showString();
    }
    public void showString() {
        int left = text.length();
        int counter = 0;
       for (int i = 0; i < gridHeight; i++) {
           for (int j = 0; j < gridWidth; j++) {
               if (grid[i][j].isreal==false) {
                   grid[i][j].setLetter(Character.toString(text.indexOf(counter++)));
               }
           }
       }
    }

}
