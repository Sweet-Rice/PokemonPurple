
package generic;

import basicgraphics.ClockWorker;
import basicgraphics.Scene;
import basicgraphics.SpriteComponent;
import basicgraphics.Task;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class LetterHandler {

    private int x;
    private int y;
    //location of box

    private int width;
    private int height;
    //width and height of box

    private final int XDISPLACEMENT;
    private final int YDISPLACEMENT;

    private Letter[][] grid;
    private int gridWidth;
    private int gridHeight;

    //should serve as the layout of the locations of the letters

    private SpriteComponent sc;
    private Font font;
    private Color color;
    private String string;
    private int prio;
    int letterHeight;
    int letterWidth;

    public LetterHandler(double x, double y, double width, double height, SpriteComponent sc, Font font, Color color, String string, int prio) {
        this.width = (int) width;
        this.height = (int) height;
        this.XDISPLACEMENT = 0;//this.width/15;
        this.YDISPLACEMENT = 0;//this.height/15;
        this.sc = sc;
        this.x = (int) x + XDISPLACEMENT;
        this.y = (int) y + YDISPLACEMENT;
        this.prio = prio;

        this.string = string;
        setText(string);

        Scene tempsc =  sc.createScene();
        Letter temp = new Letter(tempsc, 0,0, color, font, "s", prio);
        //reference letter to create grid before actually generating grid

        letterHeight = (int)temp.getHeight();
        letterWidth = (int) temp.getWidth();

        gridWidth = ((int) width - (2 * XDISPLACEMENT)) / letterWidth;
        gridHeight = ((int) height - (2 * YDISPLACEMENT)) / letterHeight;

        this.font = font;
        this.color = color;

        initGrid();
        //showStrings();
    }

    public void initGrid() {
        grid = new Letter[(int) gridHeight][(int) gridWidth];
        int incr = 0;


        for (int i = 0; i < gridHeight; i++) {
            grid[i] = new Letter[gridWidth];
            int wordX = 0;
            for (int j = 0; j < gridWidth; j++) {

                String temp = extractFirst();
                System.out.println("temp word: " + temp);
                String s = " ";
                if(!temp.isEmpty()) {

                    if (temp.substring(wordX).length()<gridWidth-j) {
                        if (wordX>=temp.length()) {
                            s = " ";
                            wordQueue.removeFirst();
                            System.out.println("removed");
                            wordX = 0;
                        } else {
                            s  = Character.toString(temp.charAt(wordX));
                            wordX++;
                        }
                        System.out.println(s);
                    } else {
                        System.out.println("reached else");
                        if (wordX>=temp.length())
                            wordQueue.removeFirst();
                        wordX = 0;
                        s = " ";
                    }
                }

                System.out.println("creating: " + s);
                grid[i][j] = new Letter(sc.getScene(), j, i, color, font, s, prio);
                grid[i][j].setX(x + j*grid[i][j].getWidth());
                grid[i][j].setY(y + i*grid[i][j].getHeight());

            }
        }

    }
    private ArrayList<String> wordQueue;
    public void setText(String textIn) {
        String[] parsed = textIn.split("\\s+");
        //for (int i = 0; i < parsed.length; i++) {
        //    parsed[i] += " ";
        //}
        this.wordQueue = new ArrayList<>(Arrays.asList(parsed));

        System.out.println(wordQueue);
    }

    public String extractFirst(){
        if (wordQueue.isEmpty()) return "";

        return wordQueue.get(0);
    }
    public void appendText(String textIn) {
        String[] parsed = textIn.split("\\s+");
        wordQueue.addAll(Arrays.asList(parsed));

    }
    public int checkSpace(int i) {
        int count = 0;
        for (int j = 0; j < gridWidth; j++) {
            if (grid[i][j].empty){
                count++;
            }

        }
       return count;

    }
    public void showNow(){
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                grid[i][j].is_visible = true;
            }
        }
    }
    public void hideNow(){
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                grid[i][j].is_visible = false;
            }
        }
    }
    public void showStrings(){
        ClockWorker.addTask(new Task() {
            int iteratedX = 0;
            int iteratedY = 0;
            @Override
            public void run() {

                if (iteratedY<gridHeight) {
                    if (iteratedX<gridWidth) {
                        grid[iteratedY][iteratedX].is_visible = true;
                        //System.out.println(iteratedX + " " + iteratedY);
                        iteratedX++;

                    } else {
                        iteratedX = 0;
                        iteratedY++;
                    }
                }
            }
        });
    }
    public void fadeIn(){
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                grid[i][j].fadeIn();
            }
        }
    }
    public void fadeOut(){
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                grid[i][j].fadeOut();
            }
        }
    }
}