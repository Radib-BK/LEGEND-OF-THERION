package Entity;

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Old_man extends  Entity{

    public BufferedImage current_image;
    public int dialogue_delay=0;
    public Old_man(GamePanel gp) {
        super(gp);
        setDialogue();

        x=600;
        y=650;
        current_health=100;
        max_health=100;
        getEnemyImage();
        current_image=left0;



    }

    public void setDialogue()
    {
        dialogues[0]="Who are you, my child?";
        dialogues[1]="[Tells his story]";
        dialogues[2]="You story makes me sad...[A moment of silence]";
        dialogues[3]="I used to be the greatest wizard. I have visited a lots\n of countries.  Once your father invited me to his \ncountry. What a beautiful country it was.";
        dialogues[4]="You have such a great potential and I want to help you.";
        dialogues[5]="I will give you my blessings and tell you the path of \nachieving the greatest power";
        dialogues[6]="You can now shoot a special projectile with your hand.";
        dialogues[7]="But that is not all. Go ahead along this path and you will \nSget a special mana that lets you transform into powerful form";
        dialogues[8]="However, that is not all. Take the portal and it will take you to \nanother world where you can get more transformation abilities";
        dialogues[9]="I hope one day you strong enough to beat the demon and get back your country";

    }

    public void speak()
    {
        gp.hud.currentDialogue=dialogues[dialogue_index];
       if(gp.keyH.dialogue_enter_pressed==true &&dialogue_delay==0)
       {
           dialogue_index++;
       dialogue_delay=100;
       }

       if(dialogue_delay>0)
           dialogue_delay--;
    }

    public void getEnemyImage() {
        try {

            left0=ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPC/Old_man/oldman_down_1.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPC/Old_man/oldman_left_1.png"));





        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {


        //------------------------------------------------
        //DRAW PLAYER
        // --------------------------------------------------





            g2.drawImage(current_image, x, y, 80, 80, null);
    }
}
