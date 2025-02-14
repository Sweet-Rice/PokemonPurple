package menuWorld;

import basicgraphics.BasicFrame;
import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.images.Picture;

import java.awt.*;



public class Letter extends Sprite {
    private static Picture initPic = new Picture("a.png");
    private Picture picture;
    public boolean empty = true;

    public Letter(Scene sc, int x, int y) {
        super(sc);

        Dimension d = sc.getSize();
        setY(40+(d.getHeight()/15)+(y*27));
        setX(40+(d.getWidth()/2)+(x*24));
        setDrawingPriority(10);
        picture = initPic;
        setPicture(picture);
        //setPicture(makeTile(true));

    }



    private static Picture makeTile(boolean lit) {
        Image im = BasicFrame.createImage(24, 27);
        Graphics g = im.getGraphics();

        g.setColor(Color.RED);
        g.fillRect(0, 0, 24, 27);

        g.setColor(lit ? Color.WHITE : Color.BLACK);
        g.fillRect(1, 1, 24 - 2, 27 - 2);
        return new Picture(im);
    }

    public void setLetter(String character) {

        char c = character.charAt(0);
        String fname = "";
        if(c >= 'A' && c <= 'Z') {
            fname = "cap"+c+".png";
        } else if(c>='a' &&c<='z'){
            fname = ""+c+".png";
        } else if (c>='0'&&c<='9'){
            fname = ""+c+".png";
        }else {
            System.out.printf("%02x.png",(int)c);
            fname = String.format("%02x.png", (int)c);
        }
        picture = new Picture(fname);

        setPicture(picture);
        empty = false;
    }


}
