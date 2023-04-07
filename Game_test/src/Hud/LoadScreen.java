package Hud;

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LoadScreen {
    public BufferedImage main_background, loading, loading_title;
    public BufferedImage castle_background, forest_background, winter_background, cave_background;
    public BufferedImage play, exit, continue_button, load, idle, select, title;

    public BufferedImage map[]= new BufferedImage[11];

    int press_delay=50;

    int current_slot=0;



    GamePanel gp;
    public LoadScreen(GamePanel gp)
    {
        this.gp=gp;
        load_image();




    }

    public void load_image(){
        try {
            loading_title= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/loading_title.png"));
            loading= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/background.png"));
            main_background= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/red_background.png"));
            castle_background= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/castle.png"));
            forest_background=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/background.png"));
            cave_background= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/cave-background.jpg"));
            winter_background=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/mountain.jpg"));


            for(int i=0; i<=10; i++)
                map[i]= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/map ("+i+").png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update()
    {
        if(gp.keyH.downPressed==true && press_delay==0)
        {
            press_delay=10;
            downlogic();
        }
        else if(gp.keyH.upPressed==true && press_delay==0)
        {
            press_delay=10;
            uplogic();
        }
        else if(gp.keyH.rightPressed==true && press_delay==0)
        {
            press_delay=10;
            rightlogic();
        }
        else if(gp.keyH.leftPressed==true && press_delay==0)
        {
            press_delay=10;
            leftlogic();
        }
        else if(gp.keyH.enterPressed==true && press_delay==0)
        {
            press_delay=10;
            enterlogic();
        }
        else if(gp.keyH.escapePressed==true && press_delay==0)
        {
            press_delay=10;
            gp.game_state=gp.titlestate;
            gp.titleScreen.current_selected="load";
        }

        if(press_delay>0)
            press_delay--;


    }

    public void enterlogic()
    {
        gp.next_selected="load";
        gp.currentMap=current_slot;

    }

    public void leftlogic()
    {
        if(current_slot>=1 && current_slot<=6)
        {
            current_slot--;
        }
        else if(current_slot==0)
        {
            current_slot=6;
        }
        else if(current_slot>=8 && current_slot<=10)
        {
            current_slot--;
        }
        else if(current_slot==7)
        {
            current_slot=10;
        }

    }
    public void rightlogic()
    {
        if(current_slot>=0 && current_slot<=6)
        {
            current_slot= (++current_slot)%7;
        }
        else if(current_slot>=6 && current_slot<=9)
        {
            current_slot++;
        }
        else if(current_slot==10)
        {
            current_slot=7;
        }

    }

    public void uplogic()
    {
       if(current_slot==7)
           current_slot=1;
       else if(current_slot==8)
           current_slot=2;
       else if(current_slot==9)
           current_slot=4;
       else if(current_slot==10)
           current_slot=6;


    }

    public void downlogic()
    {
        if(current_slot==0 || current_slot==1)
            current_slot=7;
        else if(current_slot==2 || current_slot==3)
            current_slot=8;
        else if(current_slot==4|| current_slot==5)
            current_slot=9;
        else if(current_slot==6)
            current_slot=10;
    }



    public void draw(Graphics2D g2) {


        g2.drawImage(main_background, 0, 0, gp.screenWidth, gp.screenHeight, null);

        g2.drawImage(winter_background, 100, 60, 660, 330, null);
        g2.drawImage(forest_background, 950, 60, 660, 330, null);
        g2.drawImage(cave_background, 100, 500, 660, 330, null);
        g2.drawImage(castle_background, 950, 500, 660, 330, null);

        g2.setColor(new Color(204, 204, 204, 200));
        g2.fillRect(110, 400, 140, 50);
        g2.drawImage(map[0], 110, 405,130, 50, null );

        if(current_slot==0)
            g2.drawRect(105, 395, 150, 60);

        g2.setColor(new Color(204, 204, 204, 200));
        g2.fillRect(270, 400, 140, 50);
        g2.drawImage(map[1], 270, 405,130, 50, null );

        if(current_slot==1)
            g2.drawRect(265, 395, 150, 60);

        g2.setColor(new Color(204, 204, 204, 200));
        g2.fillRect(430, 400, 140, 50);
        g2.drawImage(map[2], 430, 405,130, 50, null );

        if(current_slot==2)
            g2.drawRect(425, 395, 150, 60);


        g2.setColor(new Color(204, 204, 204, 200));
        g2.fillRect(590, 400, 140, 50);
        g2.drawImage(map[3], 590, 405,130, 50, null );

        if(current_slot==3)
            g2.drawRect(585, 395, 150, 60);


        g2.setColor(new Color(204, 204, 204, 200));
        g2.fillRect(1000, 400, 140, 50);
        g2.drawImage(map[4], 1000, 405,130, 50, null );

        if(current_slot==4)
            g2.drawRect(995, 395, 150, 60);


        g2.setColor(new Color(204, 204, 204, 200));
        g2.fillRect(1200, 400, 140, 50);
        g2.drawImage(map[5], 1200, 405,130, 50, null );


        if(current_slot==5)
            g2.drawRect(1195, 395, 150, 60);

        g2.setColor(new Color(204, 204, 204, 200));
        g2.fillRect(1400, 400, 140, 50);
        g2.drawImage(map[6], 1400, 405,130, 50, null );

        if(current_slot==6)
            g2.drawRect(1395, 395, 150, 60);

        g2.setColor(new Color(204, 204, 204, 200));
        g2.fillRect(250, 840, 140, 50);
        g2.drawImage(map[7], 250, 845,130, 50, null );

        if(current_slot==7)
            g2.drawRect(245, 835, 150, 60);


        g2.setColor(new Color(204, 204, 204, 200));
        g2.fillRect(450, 840, 140, 50);
        g2.drawImage(map[8], 450, 845,130, 50, null );

        if(current_slot==8)
            g2.drawRect(445, 835, 150, 60);

        g2.setColor(new Color(204, 204, 204, 200));
        g2.fillRect(1050, 840, 140, 50);
        g2.drawImage(map[9], 1050, 845,130, 50, null );

        if(current_slot==9)
            g2.drawRect(1045, 835, 150, 60);

        g2.setColor(new Color(204, 204, 204, 200));
        g2.fillRect(1250, 840, 140, 50);
        g2.drawImage(map[10], 1250, 845,130, 50, null );


        if(current_slot==10)
            g2.drawRect(1245, 835, 150, 60);

    }
}
