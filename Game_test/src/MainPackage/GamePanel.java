
package MainPackage;

import Hud.*;
import Tile.TileManager;
import Entity.Player;
import Entity.Entity;
import Entity.Old_man;

import  javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
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

    boolean loading_screen=false;




    int FPS=60;

    public AssetSetter asset= new AssetSetter(this);

    public final int maxMap=11;
    public int currentMap=0;
    public Entity[][] enemy = new Entity[maxMap][5];
    Entity [][] obstacle = new Entity[maxMap][8];
    public Old_man old_man= new Old_man(this);
    int old_man_map=1;

    public TileManager tileM= new TileManager(this);

    public HudManager hud = new HudManager(this);

    public UpgradeScreenManager upgradeScreen= new UpgradeScreenManager(this);

    public Player player= new Player(this, keyH);


    public Dialogue dialogue= new Dialogue(this);
    public TitleScreen titleScreen = new TitleScreen(this);

    public LoadScreen loadScreen= new LoadScreen(this);

    public PauseScreen pauseScreen= new PauseScreen(this);

    public String next_selected= "";




    public final int playState=0;
    public final int gameOverState=1;
    public final int dialogueState=2;
    public final int upgradeState=3;
    public final int titlestate=4;
    public final int load_game_state=5;
    public final int pause_game_state=6;

    public int game_state=titlestate;



    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start(); //automatically gonna call run method
    }

    public void setupGame()
    {

        if(next_selected!="load")
        try
        {
            currentMap=db.getSavedGame();


        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


        player=new Player(this, keyH);
        asset.set_enemy();
        asset.set_obstacle();
        player.set_default_x_y();
        tileM= new TileManager(this);

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

    public void cutscene_manager()
    {
        if(currentMap==0 && dialogue.level0_dialogue_state=="not_started")
        {
            game_state=dialogueState;
            dialogue.level0_dialogue_state="started";
            dialogue.change_level0_dialogue();
        }


        if(currentMap==0 && dialogue.level0_dialogue_state=="started")
        {

            dialogue.change_level0_dialogue();
        }

        if(currentMap==1 && player.x>1255 && dialogue.level1_dialogue_state=="not_started")
        {
            game_state=dialogueState;
            player.spriteNum=0;

           dialogue.level1_dialogue_state="started";
            dialogue.change_level1_dialogue();
        }

        if(currentMap==1 && dialogue.level1_dialogue_state=="started" )
        {
            dialogue.change_level1_dialogue();

            if(dialogue.dialogue_index==1)
            {
                old_man.old_man_house="open";
                old_man.state="active";
            }

            if(dialogue.dialogue_index==10)
            {
                old_man.old_man_house="closed";
                old_man.state="not_active";
                player.display=false;

            }

            if(dialogue.dialogue_index==12)
            {
                old_man.old_man_house="open";
                old_man.state="active";
                player.display=true;
            }

            if(dialogue.dialogue_index==17)
            {
                old_man.old_man_house="closed";
                old_man.state="not_active";
            }


        }
    }

    public void update() {
        //will change the player or other position

        if(loading_screen==true)
        {
            if(next_selected=="play")
            {




                currentMap=0;

                db.setSavedGame(this);
                setupGame();
                game_state=playState;
                next_selected=" ";
                loading_screen=false;
                player.set_default_x_y();
            }
            else if(next_selected=="continue")
            {
                loading_screen=false;


                next_selected=" ";
                setupGame();


                game_state=playState;
            }
            else if(next_selected=="load")
            {
                setupGame();
                next_selected=" ";
                loading_screen=false;
                game_state=playState;
            }
            else if(next_selected=="title")
            {
                setupGame();
                next_selected=" ";
                loading_screen=false;
                game_state=titlestate;
            }
        }
        else {

            if (game_state != titlestate && game_state != load_game_state && game_state!=pause_game_state)
                cutscene_manager();

            if (game_state == playState) {


                    player.update();


            }
            else if(game_state==gameOverState)
                hud.upgradeGameOverScreen();

            if (game_state != upgradeState && game_state != titlestate && game_state!=load_game_state && game_state!=pause_game_state) {

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

                if (game_state == playState) {
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


            } else if (game_state == upgradeState) {
                upgradeScreen.update();
            } else if (game_state == titlestate) {
                titleScreen.update();
            }
            else if(game_state==load_game_state)
            {
                loadScreen.update();
            }
            else if(game_state==pause_game_state)
            {
                pauseScreen.update();
            }

//        for (int i = 0; i < 5; i++) {
//            if (enemy[currentMap][i] != null)
//                System.out.print(enemy[currentMap][i].can_get_hit+" ");
//        }
//
//        System.out.println();


        }

    }

    public void paintComponent(Graphics g) //this is built in method..name can't be changed
    {

        super.paintComponent((g));
        //this is a format...this super class is parent of jpanel

        Graphics2D g2= (Graphics2D)g;
        //graphics 2D has more option than graphics


        if(next_selected=="play")
        {

            g2.drawImage(titleScreen.loading, 0, 0, screenWidth, screenHeight, null);
            g2.drawImage(titleScreen.loading_title, 1300, 800,  386, 97, null );
            loading_screen=true;
        }
        else if(next_selected=="continue")
        {
            g2.drawImage(titleScreen.loading, 0, 0, screenWidth, screenHeight, null);
            g2.drawImage(titleScreen.loading_title, 1300, 800,  386, 97, null );
            loading_screen=true;
        }
        else if(next_selected=="load" || next_selected=="title")
        {
            g2.drawImage(titleScreen.loading, 0, 0, screenWidth, screenHeight, null);
            g2.drawImage(titleScreen.loading_title, 1300, 800,  386, 97, null );
            loading_screen=true;
        }
        else
        {
            if(game_state!=upgradeState && game_state!=titlestate && game_state!=load_game_state && game_state!=pause_game_state)
            {
                tileM.draw_background(g2);

                if(currentMap==6 && enemy[6][0]!=null)
                    enemy[6][0].draw_laser(g2);

                tileM.draw(g2);


                if (currentMap == old_man_map && old_man.state=="active")
                    old_man.draw(g2);

                if(player.display==true)
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
                    {
                        if(currentMap==1 && dialogue.dialogue_index==11)
                            hud.draw_mid_screen_dialogue(g2);
                        else
                            hud.drawDialogueScreen(g2);
                    }
                }

            }
            else if(game_state==upgradeState)
            {
                upgradeScreen.draw(g2);
            }
            else if(game_state==titlestate)
            {
                titleScreen.draw(g2);
            }
            else if(game_state==load_game_state)
            {
                loadScreen.draw(g2);
            }
            else if(game_state==pause_game_state)
            {
                pauseScreen.draw(g2);
            }
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
