package Hud;

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PauseScreen {

    BufferedImage pause, continue_button, exit, idle, select, main_background;

    int current_slot=0;

    int press_delay=50;

    GamePanel gp;
    public PauseScreen(GamePanel gp)
    {
        this.gp=gp;

        load_image();
    }

    public void load_image()
    {
        try {
            main_background= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/red_background.png"));

            exit=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/exit.png"));
            continue_button=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/continue.png"));
            pause=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/Paused.png"));
            idle=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/button-idle.png"));
            select=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/button-select.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update()
    {
        if(gp.keyH.upPressed==true && press_delay==0)
        {
            if(current_slot==1)
                current_slot=0;
            else
                current_slot=1;

            press_delay=10;
        }
        else if(gp.keyH.downPressed==true && press_delay==0)
        {
            if(current_slot==1)
                current_slot=0;
            else
                current_slot=1;
            press_delay=10;
        }
        else if(gp.keyH.enterPressed==true && press_delay==0)
        {
            if(current_slot==0)
                gp.game_state=gp.playState;
            else
                if(current_slot==1)
                {
                    gp.titleScreen.press_delay=60;
                    gp.next_selected="title";
                }
        }

        if(press_delay>0)
            press_delay--;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(main_background, 0, 0, gp.screenWidth, gp.screenHeight, null);

        g2.drawImage(pause, 550, 150, 444, 118, null);

        if (current_slot==0)
            g2.drawImage(select, 560, 400, 384, 97, null);
        else
            g2.drawImage(idle, 560, 400, 384, 97, null);
        g2.drawImage(continue_button, 560, 400, 384, 97, null);

        if(current_slot==1)
            g2.drawImage(select, 560, 550, 384, 97, null);
        else
            g2.drawImage(idle, 560, 550, 384, 97, null);

        g2.drawImage(exit, 560, 550, 384, 97, null);
    }


}
