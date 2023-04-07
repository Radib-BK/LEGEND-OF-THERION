
package MainPackage;

import Hud.HudManager;
import Hud.UpgradeScreenManager;
import Tile.TileManager;
import Entity.Player;
import Entity.Entity;
import Entity.Old_man;

import  javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;


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
    public KeyHandler keyH = new KeyHandler(this);
    public Database db = new Database();
    public CollisionChecker cChecker = new CollisionChecker(this);



    int FPS=60;

    public AssetSetter asset= new AssetSetter(this);

    public final int maxMap=10;
    public int currentMap=3;
    Entity[][] enemy = new Entity[maxMap][5];
    Entity [][] obstacle = new Entity[maxMap][8];
    public Old_man old_man= new Old_man(this);
    int old_man_map=1;

    public TileManager tileM= new TileManager(this);

    public HudManager hud = new HudManager(this);

    public UpgradeScreenManager upgradeScreen= new UpgradeScreenManager(this);

    public Player player= new Player(this, keyH);





    public int game_state;
    public final int playState=0;
    public final int gameOverState=1;
    public final int dialogueState=2;
    public final int upgradeState=3;


    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start(); //automatically gonna call run method
    }

    public void setupGame()
    {
        asset.set_enemy();
        asset.set_obstacle();
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

    public void update() {
        //will change the player or other position



        if(game_state==playState) {
            player.update();

        }
        if(game_state!=upgradeState) {

            for (int i = 0; i < 5; i++) {
                if (enemy[currentMap][i] != null)
                    enemy[currentMap][i].update();
            }

            for (int i = 0; i < 5; i++) {
                if (obstacle[currentMap][i] != null)
                    obstacle[currentMap][i].update();
            }


            //tileSize is basically player height and width


            //check common collison

            if (game_state == playState)
            {
                for (int i = 0; i < 5; i++) {
                    if (enemy[currentMap][i] != null && enemy[currentMap][i].can_shoot == true) {
                        cChecker.bullet_bullet_collison_check(player.bullet, enemy[currentMap][i].bullet, enemy[currentMap][i]);
                    }
                }

                for (int i = 0; i < 5; i++) {
                    if (enemy[currentMap][i] != null)
                        cChecker.entity_entity_collison_check(player, enemy[currentMap][i]);
                }



                //check player_bullet_collison_with enemies

                for (int i = 0; i < 5; i++) {
                    if (enemy[currentMap][i] != null)
                        cChecker.entity_bullet_collison_check(enemy[currentMap][i], player);
                }

                //check enemy_bullet_collison_with player

                for (int i = 0; i < 5; i++) {
                    if (enemy[currentMap][i] != null && enemy[currentMap][i].can_shoot == true)
                        cChecker.entity_bullet_collison_check(player, enemy[currentMap][i]);
                }

                for (int i = 0; i < 5; i++) {
                    if (enemy[currentMap][i] != null && enemy[currentMap][i].state == "dead")
                        enemy[currentMap][i] = null;
                }

            }

            if (currentMap == old_man_map && old_man != null) {
                if (player.x >= 520) {
                    player.spriteNum = 0;
                    game_state = dialogueState;
                    old_man.speak();
                    old_man.current_image = old_man.left1;
                }
            }
        }
        else if(game_state==upgradeState)
        {
        upgradeScreen.update();
        }

//        for (int i = 0; i < 5; i++) {
//            if (enemy[currentMap][i] != null)
//                System.out.print(enemy[currentMap][i].can_get_hit+" ");
//        }
//
//        System.out.println();




    }

    public void paintComponent(Graphics g) //this is built in method..name can't be changed
    {

        super.paintComponent((g));
        //this is a format...this super class is parent of jpanel

        Graphics2D g2= (Graphics2D)g;
        //graphics 2D has more option than graphics


        if(game_state!=upgradeState)
        {
            tileM.draw_background(g2);

            if(currentMap==6 && enemy[6][0]!=null)
                enemy[6][0].draw_laser(g2);

            tileM.draw(g2);


            if (currentMap == old_man_map)
                old_man.draw(g2);

            player.draw(g2);
            // tileM.draw_border(g2);


            for (int i = 0; i < 5; i++) {
                if (enemy[currentMap][i] != null)
                    enemy[currentMap][i].draw_common(g2);
            }

            for (int i = 0; i < 5; i++) {
                if (obstacle[currentMap][i] != null)
                    obstacle[currentMap][i].draw_common(g2);
            }

            //tileM.draw_border(g2);

                hud.draw_hud(g2);

            if (game_state == gameOverState) {

                player.player_fall_below();
                hud.drawGameOverScreen(g2);
            }

            if (game_state == dialogueState) {
                hud.drawDialogueScreen(g2);
            }

        }
        else if(game_state==upgradeState)
        {
            upgradeScreen.draw(g2);
        }








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
