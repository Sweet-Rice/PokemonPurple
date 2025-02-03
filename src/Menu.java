import Sprites.BouncingSprite;
import basicgraphics.*;
import basicgraphics.sounds.ReusableClip;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class Menu {

    private GameScreen gs;

    public final Card card;

    private SpriteComponent sc;
    private SpriteComponent r;
    private SpriteComponent g;


    private Red red;
    private Gary gary;
    private BouncingSprite bouncingsprite;

    private Card saveCard;

    private SpriteComponent savedsc;







    final ReusableClip titleClip = new ReusableClip("title.wav");

    public Menu(BasicFrame frame, GameScreen gs) {
        card = frame.getCard();
        this.sc = new SpriteComponent();
        this.r = new SpriteComponent();
        this.g = new SpriteComponent();


        saveCard = frame.getCard();
        this.savedsc = new SpriteComponent();


        initializeMenu();
        this.gs = gs;




    }

    private void initializeMenu() {

        ClockWorker.initialize(33);
        titleClip.playOverlapping();


        BasicLayout blayout = new BasicLayout();
        card.add(sc);
        sc.setLayout(blayout);



        //sc.add("x=0,y=0,w=4,h=3",sc);
        sc.add("x=0,y=3,w=1,h=3",r);
        sc.add("x=3,y=3,w=1,h=3",g);



        ClockWorker.addTask(sc.moveSprites());
        ClockWorker.addTask(r.moveSprites());
        ClockWorker.addTask(g.moveSprites());

        bouncingsprite = new BouncingSprite(sc.getScene(), sc);

        red = new Red(r.getScene(), r);
        gary = new Gary(g.getScene(), g);
        




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

        title.setFont(font.deriveFont(Font.BOLD, 50));
        title.setForeground(Color.MAGENTA);
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setHorizontalAlignment(0);

        sc.add( "x=1,y=3,w=2,h=1", title);

        //card.add("m", title);

        JButton startButton = new JButton("Start");
        sc.add("x=1,y=5,w=2,h=1", startButton);
        startButton.setFont(font.deriveFont(Font.BOLD, 100));

        startButton.setForeground(Color.BLACK);
        startButton.setBackground(Color.white);
        startButton.setBorderPainted(false);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                BasicLayout blayout1 = new BasicLayout();
                card.remove(sc);
                card.add(savedsc);
                savedsc.setLayout(blayout1);
                card.showCard();
                savedsc.requestFocus();
                ClockWorker.addTask(savedsc.moveSprites());


                //gs.show();
                ClockWorker.initialize(7);
            }
        });
    }

    private void initializeSelSave(){
        ClockWorker.initialize(33);
        BasicLayout blayout1 = new BasicLayout();
        saveCard.setLayout(blayout1);


    }

}