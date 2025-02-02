

import basicgraphics.BasicFrame;

public class Main {
    public static void main(String[] args) {
        BasicFrame bf = new BasicFrame("Flyer");
        GameManager gameManager = new GameManager(bf);
        //FontLoader.loadAllFonts();
        bf.show();
    }
}