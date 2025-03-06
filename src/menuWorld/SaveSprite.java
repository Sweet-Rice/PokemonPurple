package menuWorld;

import basicgraphics.Scene;
import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class SaveSprite extends Sprite {
    public Picture initialPic;
    private LetterHandler lh;
    private SpriteComponent scw;


    public SaveSprite(Scene sc, SpriteComponent scw) {
        super(sc);
        this.scw = scw;
        initialPic = new Picture("frame500x500.png");
        setPicture(initialPic);
        Dimension d = sc.getSize();
        setY(d.getHeight()/15);
        setX(d.getWidth()/2);

        InputStream fontStream = getClass().getResourceAsStream("/fonts/pokemon_fire_red.ttf");
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

         //font = new Font("Arial", Font.BOLD, 20);
        String s = "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?";
        lh = new LetterHandler(getX(), getY(), getWidth(), getHeight(), scw, font.deriveFont(Font.BOLD, 20), Color.BLACK, s);
        lh.showNow();
    }

    public LetterHandler getLh() {
        return lh;
    }
}