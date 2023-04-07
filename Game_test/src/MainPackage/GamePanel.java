
package MainPackage;

import Hud.HudManager;
import Tile.TileManager;
import entity.Player;

import  javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;


public class GamePanel extends JPanel  implements Runnable
{
    final int originalTileSize=16; //individual block..may hold a whole player
    final int scale=3; //16 is too small..so we need to scale it

    public final int tileSize = originalTileSize * scale; //original tile that would be displayed
    public final int MaxScreenCol= 36; //no of tiles in a column
    public final int MaxScreenRow= 20; // no of tiles in row



    public final int screenWidth= tileSize * MaxScreenCol; //sets total screen column
    public final int screenHeight= tileSize * MaxScreenRow; //sets total screen row

    Thread gameThread; //for time management
    KeyHandler keyH = new KeyHandler();
    public CollisionChecker cChecker = new CollisionChecker(this);

    int FPS=60;

    public final int maxMap=10;
    public int currentMap=1;

    public TileManager tileM= new TileManager(this);

    public HudManager hud = new HudManager(this);

    public Player player= new Player(this, keyH);

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start(); //automatically gonna call run method
    }

    public void run()
    {

        double drawInterval = 1000000000/ (double) FPS;
        double nextDrawTime= System.nanoTime() + drawInterval;
        //our gameloop

        while(gameThread != null) {


            update();

            repaint(); //we are calling paintComponent option

            try
            {
                double remainingTime = nextDrawTime - System.nanoTime();

                remainingTime= remainingTime/1000000; //the sleep function only takes mili, so we convert to it

                if(remainingTime<0)
                    remainingTime=0;

                Thread.sleep((long) remainingTime);

                nextDrawTime+= drawInterval; //set next interval
             }
            catch(InterruptedException e)
            {
                //convention
            }
        }

    }

    public void update()
    {
        //will change the player or other position

    player.update();


        //tileSize is basically player height and width
    }

    int count=0;

    public void paintComponent(Graphics g) //this is built in method..name can't be changed
    {
        super.paintComponent((g));
        //this is a format...this super class is parent of jpanel

        Graphics2D g2= (Graphics2D)g;
        //graphics 2D has more option than graphics

        tileM.draw(g2);
        player.draw(g2);
        tileM.draw_border(g2);
        hud.draw_hud(g2);

        count=(++count)%60;

        if(count==0) {
            player.player_current_health++;
            player.player_current_health %= player.player_max_health;
        }

        System.out.println(player.player_current_health);
        g2.dispose();
    }



    public GamePanel()
    {
        this.setPreferredSize((new Dimension(screenWidth, screenHeight)));
        this.setBackground((Color.BLUE));
        this.setDoubleBuffered((true));
        this.addKeyListener((keyH));
        this.setFocusable((true));
    }
}
