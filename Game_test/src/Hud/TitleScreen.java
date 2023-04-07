package Hud;

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TitleScreen {

    public BufferedImage main_background, loading, loading_title;
    public BufferedImage castle_background, forest_background, winter_background, cave_background;
public BufferedImage play, exit, continue_button, load, idle, select, title, control, control_title;

public BufferedImage map[]= new BufferedImage[11];

public int press_delay=50;

    String current_selected="play";

    boolean is_control_state=false;



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
            control = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/controls.png"));
            control_title = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/Controls_title.png"));

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
        else if(gp.keyH.escapePressed==true && press_delay==0)
        {
            press_delay=10;

            is_control_state=false;

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
        else if(current_selected=="controls")
        {
            is_control_state=true;
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
            current_selected="controls";
        else if(current_selected=="continue")
            current_selected="exit";
        else if(current_selected=="controls")
            current_selected="load";

    }

    public void downlogic()
    {
        if(current_selected=="play")
            current_selected="load";

        else if(current_selected=="exit")
        {
            if(gp.currentMap>0)
                current_selected="continue";
            else
                current_selected="play";

        }
        else if(current_selected=="continue")
            current_selected="play";
        else if(current_selected=="load")
            current_selected="controls";


    }



    public void draw(Graphics2D g2) {


        g2.drawImage(main_background, 0, 0, gp.screenWidth, gp.screenHeight, null);


        if (is_control_state == false) {
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

            if (current_selected == "controls")
                g2.drawImage(select, 640, 650 - factor, 288, 72, null);
            else
                g2.drawImage(idle, 640, 650 - factor, 288, 72, null);
            g2.drawImage(control, 640, 650 - factor, 288, 72, null);

            if (current_selected == "exit")
                g2.drawImage(select, 640, 750 - factor, 288, 72, null);
            else
                g2.drawImage(idle, 640, 750 - factor, 288, 72, null);
            g2.drawImage(exit, 640, 750 - factor, 288, 72, null);
        }
        else
        {

            g2.drawImage(control_title, 560, 100, 400, 100, null);

            g2.setColor(Color.white);
            g2.setFont(gp.hud.purisaB);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 18F));
            g2.drawString("LEFT-ARROW: Move left (all state)", 550, 250);
            g2.drawString("RIGHT-ARROW: Move right (all state)", 550, 300);
            g2.drawString("UP-ARROW: Fly Up (Fly state)", 550, 350);
            g2.drawString("DOWN-ARROW: Fly down (Fly state)", 550, 400);
            g2.drawString("1, 2, 3, 4: Transform to states (if unlocked)", 550, 450);
            g2.drawString("Q BUTTON: Open Upgrade Screen", 550, 500);
            g2.drawString("H BUTTON: Use red potions", 550, 550);
            g2.drawString("T BUTTON: Use blue potions", 550, 600);
            g2.drawString("ESC BUTTON: Pause or get back", 550, 650);
            g2.drawString("ENTER BUTTON: Interact & skip dialogue", 550, 700);
            g2.drawString("Other keys are usual like other games", 550, 750);
        }

    }




}
