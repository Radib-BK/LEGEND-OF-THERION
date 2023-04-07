package Entity;

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Old_man extends  Entity{

    public BufferedImage current_image;

    public String state="not_active";

    public String old_man_house= "closed";
    public int dialogue_delay=0;
    public Old_man(GamePanel gp) {
        super(gp);


        x=1355;
        y=630;
        current_health=100;
        max_health=100;
        getEnemyImage();
        current_image=left0;



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


            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPC/Old_man/left.png"));





        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {


        //------------------------------------------------
        //DRAW PLAYER
        // --------------------------------------------------





            g2.drawImage(left1, x, y, 69, 100, null);
    }
}
