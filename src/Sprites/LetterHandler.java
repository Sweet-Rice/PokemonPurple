
package Sprites;

import basicgraphics.ClockWorker;
import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.Task;
import basicgraphics.images.Picture;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

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
        this.x = (int) x + XYDISPLACEMENT;
        this.y = (int) y + XYDISPLACEMENT;
        this.width = (int) width;
        this.height = (int) height;

        gridWidth = ((int) width - (2 * XYDISPLACEMENT)) / 24;
        gridHeight = ((int) height - (2 * XYDISPLACEMENT)) / 27;


        //organizes the grid in an array of rows. Should be easier management.
        //tldr y,x
        initGrid();
        //setText("ee");
    }

    public void initGrid() {
        grid = new Letter[(int) gridHeight][(int) gridWidth];
        for (int i = 0; i < gridHeight; i++) {
            grid[i] = new Letter[gridWidth];
            for (int j = 0; j < gridWidth; j++) {
                grid[i][j] = new Letter(sc.getScene(), j, i);

                //grid[i][j].setX(xLoc);
                // grid[i][j].setY(yLoc);
                //xLoc += 24;

            }
        }
    }
    private ArrayList<String> wordQueue;
    public void setText(String textIn) {
        String[] parsed = textIn.split("\\s+");
        this.wordQueue = new ArrayList<>(Arrays.asList(parsed));
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
/*
    public void showString() {
        System.out.println(wordQueue.toString());
        for (int i = 0; i < wordQueue.size(); i++) {
            String word = extractFirst();
            System.out.println(word);
           for (int j = 0; j < gridHeight; j++) {
               int empty = checkSpace(j);
               if (empty >= word.length()) {
                   int l = 0;
                   for (int k = 0; k < gridWidth; k++) {
                       if (grid[j][k].empty && l < word.length()) {
                           grid[j][k].setLetter(Character.toString(word.charAt(l)));
                           grid[j][k].empty = false;
                           l++;
                       }
                       if (l == word.length() && grid[j][k + 1].empty) {
                           grid[j][k + 1].setLetter(" ");
                           System.out.println(wordQueue.toString());
                           break;
                       }
                   }
               }
           }
            System.out.println("In word: " + word + ", removing " + wordQueue.get(0));
            wordQueue.remove(0);
//        }

    }

 */
    public void showString(){
        while (!wordQueue.isEmpty()) {
            printWord();
        }
    }

   public void printWord(){
       String word = wordQueue.getFirst();
       System.out.println(word);
       int count = 0;
       if((gridWidth-1)-getEmptyCol()<word.length()){
           for (int i = getEmptyCol(); i < gridWidth; i++) {
               grid[getEmptyRow()][i].setLetter(" ");
           }
       }else{
           int space = getEmptyCol();
           for (int i = getEmptyCol(); i < gridWidth; i++) {
               if (grid[getEmptyRow()][i].empty&&count<word.length()){

                   grid[getEmptyRow()][i].setLetter(Character.toString(word.charAt(count++)));

                    space++;

               }
           }
           if (checkSpace(getEmptyRow())>0){
               grid[getEmptyRow()][getEmptyCol()].setLetter(" ");
           }
           wordQueue.removeFirst();
       }

   }


    public int getEmptyRow(){
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                if (grid[i][j].empty){
                    return i;
                }
            }
        }
        return -1;

    }
    public int getEmptyCol(){
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                if (grid[i][j].empty){
                    return j;
                }
            }
        }
        return -1;
    }
}