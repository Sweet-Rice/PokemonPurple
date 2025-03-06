package menuWorld;

import basicgraphics.BasicFrame;
import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.images.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Letter extends Sprite {
    public boolean empty = true;
    private Color color;
    private Font font;

    public Letter(Scene sc, int x, int y, Color color, Font font, String letter) {
        super(sc);
        //font should be declared outside of letter and outside of letterhandler
        this.font = font;
        this.color = color;
        setDrawingPriority(10);
        is_visible = false;
        setPicture(new Picture(setLetter(letter)));

    }


    private BufferedImage setLetter(String character ) {

        String letter = Character.toString(character.charAt(0));

// Create temporary image for FontMetrics
        BufferedImage tempImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D tempG = tempImage.createGraphics();
        tempG.setFont(font);
        FontMetrics fm = tempG.getFontMetrics();
        tempG.dispose();

// Calculate dimensions using only ascent/descent (ignore leading)
        int textWidth = fm.stringWidth("w");
        int textVerticalSpace = fm.getAscent()+ fm.getDescent();

// Minimal padding (adjust if glyphs get clipped)
        int padding = 0; // Reduced from 2
        int imgWidth = textWidth + padding * 2;
        int imgHeight = textVerticalSpace + padding * 2;

        BufferedImage realImage = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = realImage.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(font);

// Set background and text color
        g2.setColor(color);


// Calculate position (centered horizontally, baseline at padding + ascent)
        int textX = (imgWidth - textWidth) / 2;
        int baselineY = padding + fm.getAscent();
        if (letter.equals("i")) {
            g2.drawString("i", textWidth/3, baselineY);
        }else if (letter.equals("l")) {
            g2.drawString("l", textWidth/5, baselineY);
        } else
        g2.drawString(letter, textX, baselineY);
        g2.dispose();

        return realImage;

    }


}
