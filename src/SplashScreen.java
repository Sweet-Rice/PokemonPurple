import basicgraphics.*;
import basicgraphics.images.BackgroundPainter;
import basicgraphics.images.Picture;
import basicgraphics.sounds.ReusableClip;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class SplashScreen {
    public final Card card;
    private GameScreen gs;
    private BouncingSprite bouncingsprite;
    SpriteComponent sc;

    final ReusableClip titleClip = new ReusableClip("title.wav");

    public SplashScreen(BasicFrame frame, GameScreen gs) {
        card = frame.getCard();
        this.sc = new SpriteComponent();
        initializeUI();
        this.gs = gs;

    }

    private void initializeUI() {
        //card.setPainter(new BackgroundPainter(new Picture("freespace.png")));
        ClockWorker.initialize(33);
        titleClip.playOverlapping();

        String[][] layout = {
                {"o"}
        };
        //ByNameLayout bnl = new ByNameLayout(layout, card);
        BasicLayout blayout = new BasicLayout();
        card.setLayout(blayout);
        //card.setStringLayout(layout);

        card.add("x=0,y=0,w=4,h=3",sc);

        ClockWorker.addTask(sc.moveSprites());



        //card.add("b", sc);




        bouncingsprite = new BouncingSprite(sc.getScene(), sc);


        //test
        InputStream fontStream = getClass().getResourceAsStream("/fonts/chary.ttf");
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JLabel title = new JLabel("Purple Version");

        title.setFont(font.deriveFont(Font.BOLD, 25));

        //card.add("m", title);

        JButton startButton = new JButton("Start");
        card.add("x=1,y=5,w=2,h=1", startButton);
        startButton.setFont(font.deriveFont(Font.BOLD, 100));

        startButton.setForeground(Color.BLACK);
        startButton.setBackground(Color.white);
        startButton.setBorderPainted(false);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){



                gs.show();
                ClockWorker.initialize(7);
            }
        });
    }


}