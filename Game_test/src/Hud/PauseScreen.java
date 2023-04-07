package Hud;

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PauseScreen {

    BufferedImage pause, continue_button, exit, idle, select, main_background, control, control_title;

    int current_slot=0;

    int press_delay=50;

    int current_state=0;

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
            control = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/controls.png"));
            control_title = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/Controls_title.png"));

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
            else if(current_slot==2)
                current_slot=1;
            else if(current_slot==0)
                current_slot=2;

            press_delay=10;
        }
        else if(gp.keyH.downPressed==true && press_delay==0)
        {
            if(current_slot==1)
                current_slot=2;
            else if(current_slot==2)
                current_slot=0;
            else if(current_slot==0)
                current_slot=1;

            press_delay=10;
        }
        else if(gp.keyH.enterPressed==true && press_delay==0)
        {
            if(current_slot==0)
                gp.game_state=gp.playState;
            else if(current_slot==2)
                {
                    gp.titleScreen.press_delay=60;
                    gp.next_selected="title";
                }
            else if(current_slot==1)
            {
                current_state=1;
            }
        }
        else if(gp.keyH.escapePressed==true && press_delay==0)
        {
            current_state=0;
        }

        if(press_delay>0)
            press_delay--;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(main_background, 0, 0, gp.screenWidth, gp.screenHeight, null);

        if(current_state==0) {
            g2.drawImage(pause, 550, 150, 444, 118, null);


            if (current_slot == 0)
                g2.drawImage(select, 570, 370, 384, 97, null);
            else
                g2.drawImage(idle, 570, 370, 384, 97, null);
            g2.drawImage(continue_button, 570, 370, 384, 97, null);

            if (current_slot == 1)
                g2.drawImage(select, 570, 520, 384, 97, null);
            else
                g2.drawImage(idle, 570, 520, 384, 97, null);

            g2.drawImage(control, 570, 520, 384, 97, null);

            if (current_slot == 2)
                g2.drawImage(select, 570, 670, 384, 97, null);
            else
                g2.drawImage(idle, 570, 670, 384, 97, null);

            g2.drawImage(exit, 570, 670, 384, 97, null);

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
