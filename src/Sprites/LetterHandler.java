
package Sprites;

import basicgraphics.ClockWorker;
import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.Task;
import basicgraphics.images.Picture;

import java.awt.*;

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


        //organizes the grid in an array of rows. Should be easier management.
        //tldr y,x
        initGrid();
        //setText("ee");
    }
    public void initGrid(){
        grid = new Letter[(int)gridHeight][(int)gridWidth];
        for (int i = 0; i < gridHeight; i++) {
                grid[i] = new Letter[gridWidth];
            for (int j = 0; j < gridWidth; j++) {
                grid[i][j] = new Letter(sc.getScene(),j,i);

                //grid[i][j].setX(xLoc);
               // grid[i][j].setY(yLoc);
                //xLoc += 24;

            }
        }
    }
    public void setText(String textIn) {
        //this.remainingText = textIn;
        Picture white = new Picture("capA.png");
        int length = textIn.length();

        int emptyTiles = 0;
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                if (grid[i][j].empty) {
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

        //currently working for all text except \n
        text = textIn.substring(0, max);
        remainingText = textIn.substring(max);

        System.out.println(text);
        System.out.println(remainingText);

        showString();
    }
    public void showString(){
        int index = 0;
        int lineindex = 0;
        String line = "";
        for (int i = 0; i < gridHeight; i++) {
            String fromPrevLine = "";

            //this block sh
            if (lineindex < text.length()) {


                //this block retrieves the string that needs to be sent to the next line.
                //finalSpaceindex is the index of line that is the last space.
                line = text.substring(lineindex, gridWidth);
                //debug.
                System.out.println(line);
                //boolean Sent = false;
                String wrap = line;
                int spaceindex = 0;
                int loopblocker = -1;
                int finalSpaceindex = 0;
                while (spaceindex != -1) {
                    loopblocker = spaceindex;
                    spaceindex = wrap.indexOf(" ");

                    System.out.println(spaceindex);
                    if (spaceindex != -1) {
                        finalSpaceindex += spaceindex + 1;
                        wrap = wrap.substring(spaceindex + 1);

                        System.out.println(wrap + finalSpaceindex);
                    }


                }
                System.out.println(line.substring(finalSpaceindex));
                fromPrevLine = line.substring(finalSpaceindex);
            }


                //text = fromPrevLine + text;
                //prints literally everything with no line wrapping
                for (int j = 0; j < gridWidth&&j<text.length(); j++) {
                    if (index < text.length()) {
                        grid[i][j].setLetter(Character.toString(text.charAt(index++)));
                    }

                }
            }

            lineindex+=gridWidth;
        }
    }



