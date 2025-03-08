package generic;

import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.images.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

public class LetterBox extends Sprite {
    Scene sc;
    public LetterBox(Scene sc, int x, int y, int width, int height) {
        super(sc);
        this.sc = sc;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //temp graphics for debug
        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        setDrawingPriority(0);
        setPicture(new Picture(image));
        setX(x);
        setY(y);

    }
    public LetterHandler setLetterHandler(String string, Color color, int fontsize){
        InputStream fontStream = getClass().getResourceAsStream("/fonts/pokemon_fire_red.ttf");
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            font = font.deriveFont( Font.BOLD, fontsize );

        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new LetterHandler(getX(),getY(),getWidth(),getHeight(),sc.getSpriteComponent(),font,color,string);
    }
}
