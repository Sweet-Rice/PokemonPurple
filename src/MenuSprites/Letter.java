package MenuSprites;

import basicgraphics.BasicFrame;
import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.images.Picture;

import java.awt.*;



public class Letter extends Sprite {
    Letter[][] grid;
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

        switch (character) {
            case "!" ->picture = new Picture("!.png");
            case "#" ->picture =new Picture("#.png");
            case "$" ->picture =new Picture("$png");
            case "&" ->picture =new Picture("&.png");
            case "*" ->picture =new Picture("*.png");
            case "," ->picture =new Picture(",.png");
            case "." ->picture =new Picture("..png");
            case "0" ->picture =new Picture("0.png");
            case "1" ->picture =new Picture("1.png");
            case "2" ->picture =new Picture("2.png");
            case "3" ->picture =new Picture("3.png");
            case "4" ->picture =new Picture("4.png");
            case "5" ->picture =new Picture("5.png");
            case "6" ->picture =new Picture("6.png");
            case "7" ->picture =new Picture("7.png");
            case "8" ->picture =new Picture("8.png");
            case "9" ->picture =new Picture("9.png");
            case ":" ->picture =new Picture(":.png");
            case ";" ->picture =new Picture(";.png");
            case "?" ->picture =new Picture("?.png");
            case "@" ->picture =new Picture("@.png");
            case "^" ->picture =new Picture("^.png");
            case "_" ->picture =new Picture("_.png");
            case "A" ->picture =new Picture("capA.png");
            case "B" ->picture =new Picture("capB.png");
            case "C" ->picture =new Picture("capC.png");
            case "D" ->picture =new Picture("capD.png");
            case "E" ->picture =new Picture("capE.png");
            case "F" ->picture =new Picture("capF.png");
            case "G" ->picture =new Picture("capG.png");
            case "H" ->picture =new Picture("capH.png");
            case "I" ->picture =new Picture("capI.png");
            case "J" ->picture =new Picture("capJ.png");
            case "K" ->picture =new Picture("capK.png");
            case "L" ->picture =new Picture("capL.png");
            case "M" ->picture =new Picture("capM.png");
            case "N" ->picture =new Picture("capN.png");
            case "O" ->picture =new Picture("capO.png");
            case "P" ->picture =new Picture("capP.png");
            case "Q" ->picture =new Picture("capQ.png");
            case "R" ->picture =new Picture("capR.png");
            case "S" ->picture =new Picture("capS.png");
            case "T" ->picture =new Picture("capT.png");
            case "U" ->picture =new Picture("capU.png");
            case "V" ->picture =new Picture("capV.png");
            case "W" ->picture =new Picture("capW.png");
            case "X" ->picture =new Picture("capX.png");
            case "Y" ->picture =new Picture("capY.png");
            case "Z" ->picture =new Picture("capZ.png");
            case "a" ->picture =new Picture( "a.png");
            case "b" ->picture =new Picture( "b.png");
            case "c" ->picture =new Picture( "c.png");
            case "d" ->picture =new Picture( "d.png");
            case "e" ->picture =new Picture( "e.png");
            case "f" ->picture =new Picture( "f.png");
            case "g" ->picture =new Picture( "g.png");
            case "h" ->picture =new Picture( "h.png");
            case "i" ->picture =new Picture( "i.png");
            case "j" ->picture =new Picture( "j.png");
            case "k" ->picture =new Picture( "k.png");
            case "l" ->picture =new Picture( "l.png");
            case "m" ->picture =new Picture( "m.png");
            case "n" ->picture =new Picture( "n.png");
            case "o" ->picture =new Picture( "o.png");
            case "p" ->picture =new Picture( "p.png");
            case "q" ->picture =new Picture( "q.png");
            case "r" ->picture =new Picture( "r.png");
            case "s" ->picture =new Picture( "s.png");
            case "t" ->picture =new Picture( "t.png");
            case "u" ->picture =new Picture( "u.png");
            case "v" ->picture =new Picture( "v.png");
            case "w" ->picture =new Picture( "w.png");
            case "x" ->picture =new Picture( "x.png");
            case "y" ->picture =new Picture( "y.png");
            case "z" ->picture =new Picture( "z.png");
            case "|" ->picture =new Picture( "abs.png");
            case "'" ->picture =new Picture( "apostrophe.png");
            //case "!" ->new Picture("closeapostrophe.png");
            case "]" ->picture =new Picture("closebracket.png");
            case "}" ->picture =new Picture( "closecurly.png");
            case ")" ->picture =new Picture("closeparenth.png");
            case "" ->picture =new Picture("closequote.png");
            case "=" ->picture =new Picture("equal.png");
            case "`" ->picture =new Picture("evil.png");
            case ">" ->picture =new Picture("greaterthan.png");
            case "<" ->picture =new Picture("lessthan.png");
            case "-" ->picture =new Picture("minus.png");
            case "[" ->picture =new Picture("openbracket.png");
            case "{" ->picture =new Picture("opencurly.png");
            case "(" ->picture =new Picture("openparenth.png");
            //case "" ->new Picture("openquote.png");
            case "%" ->picture =new Picture("percent.png");
            case "+" ->picture =new Picture("plus.png");
            //case "\\" ->new Picture("slashback.png");
            case "/" ->picture =new Picture("slashfor.png");
            case " " ->picture =new Picture("space.png");
            case "~" ->picture =new Picture("tilda.png");


        }

        setPicture(picture);
        empty = false;
    }


}
