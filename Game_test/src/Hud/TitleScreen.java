package Hud;

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TitleScreen {

    public BufferedImage main_background, loading, loading_title;
    public BufferedImage castle_background, forest_background, winter_background, cave_background;
public BufferedImage play, exit, continue_button, load, idle, select, title;

public BufferedImage map[]= new BufferedImage[11];

int press_delay=50;

String current_selected="play";



    GamePanel gp;
    public TitleScreen(GamePanel gp)
    {
        this.gp=gp;
        load_image();

        if(gp.currentMap>0)
            current_selected="continue";


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

            play=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/play.png"));
            exit=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/exit.png"));
            continue_button=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/continue.png"));
            load=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/load.png"));
            idle=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/button-idle.png"));
            select=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/button-select.png"));
            title=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/title.png"));

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
        else if(gp.keyH.enterPressed==true && press_delay==0)
        {
            press_delay=10;
            enterlogic();
        }

        if(press_delay>0)
            press_delay--;


    }

    public void enterlogic()
    {
        if(current_selected=="continue")
        {



            gp.next_selected="continue";

        }
        else if(current_selected=="play")
        {

            gp.next_selected="play";



        }
        else if(current_selected=="exit")
        {
            System.exit(0);
        }
        else if(current_selected=="load")
        {
            gp.game_state=gp.load_game_state;
        }
    }

    public void uplogic()
    {
        if(current_selected=="play")
        {
            if(gp.currentMap>0)
                current_selected="continue";
            else
                current_selected="exit";
        }
        else if(current_selected=="load")
            current_selected="play";
        else if(current_selected=="exit")
            current_selected="load";
        else if(current_selected=="continue")
            current_selected="exit";

    }

    public void downlogic()
    {
        if(current_selected=="play")
            current_selected="load";
        else if(current_selected=="load")
            current_selected="exit";
        else if(current_selected=="exit")
        {
            if(gp.currentMap>0)
                current_selected="continue";
            else
                current_selected="play";

        }
        else if(current_selected=="continue")
            current_selected="play";

    }



    public void draw(Graphics2D g2) {


        g2.drawImage(main_background, 0, 0, gp.screenWidth, gp.screenHeight, null);



            g2.drawImage(title, 270, -100, 1024, 512, null);

            int factor = 0;

            if (gp.currentMap > 0) {
                if (current_selected == "continue")
                    g2.drawImage(select, 640, 350, 288, 72, null);
                else
                    g2.drawImage(idle, 640, 350, 288, 72, null);
                g2.drawImage(continue_button, 640, 350, 288, 72, null);
            } else
                factor = 50;


            if (current_selected == "play")
                g2.drawImage(select, 640, 450 - factor, 288, 72, null);
            else
                g2.drawImage(idle, 640, 450 - factor, 288, 72, null);
            g2.drawImage(play, 640, 450 - factor, 288, 72, null);

            if (current_selected == "load")
                g2.drawImage(select, 640, 550 - factor, 288, 72, null);
            else
                g2.drawImage(idle, 640, 550 - factor, 288, 72, null);
            g2.drawImage(load, 640, 550 - factor, 288, 72, null);

            if (current_selected == "exit")
                g2.drawImage(select, 640, 650 - factor, 288, 72, null);
            else
                g2.drawImage(idle, 640, 650 - factor, 288, 72, null);
            g2.drawImage(exit, 640, 650 - factor, 288, 72, null);
        }



}
