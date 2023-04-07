package Enemy;

import Entity.Entity;
import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RedSlime extends Entity
{

















    public int dying_animation=0, dying_animation_delay=3;

    public int fall_height=0, final_fall_height=0;






    public BufferedImage ani1, ani2, ani3, ani4, ani5, ani6;


    public RedSlime(GamePanel gp) {
        super(gp);

        solidArea = new Rectangle(0,3,gp.tileSize, gp.tileSize-3);
    //    player_bullet = new Weapon[10];



        //player width is 96 px which is equal to gp.tileSize(48) *2 =96


        setDefaultValues();
        getEnemyImage();
        getDeathAnimationImage();
    }



    public void setDefaultValues() {

        speed = 1;
        direction = "right";
        move_direction="right";

        max_hit_delay=40;
        height=gp.tileSize;
        width=gp.tileSize;
        can_shoot=false;


        max_health =20;
        current_health =20;
        hit_damage=8;


        setPlayerBulletValues();
    }




    public void getEnemyImage() {
        try {

            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Red Slime/walk/left/0.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Red Slime/walk/left/1.png"));
            left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Red Slime/walk/left/2.png"));
            left4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Red Slime/walk/left/3.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Red Slime/walk/right/0.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Red Slime/walk/right/1.png"));
            right3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Red Slime/walk/right/2.png"));
            right4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Red Slime/walk/right/3.png"));




        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getDeathAnimationImage()
    {
        try{

            ani1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Dying Animation/1.png"));
            ani2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Dying Animation/2.png"));
            ani3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Dying Animation/3.png"));
            ani4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Dying Animation/4.png"));
            ani5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Dying Animation/5.png"));
            ani6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Dying Animation/6.png"));




        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setPlayerBulletdefaultValues(int lifetime_max_charge)
    {}

    public void setPlayerBulletValues()
    {}



    public void shoot_player_bullet()
    {}

    public void move_player_bullet()
    {}

    public void check_player_bullet_collison_with_wall()
    {}

    public void do_action() {

        check_player_bullet_collison_with_wall();
        move_player_bullet();




            if ( x < left_limit) {
                direction = "right";
                move_direction="right";

            } else if (x > right_limit){
                direction = "left";
                move_direction="left";
            }


                if(direction=="left")
                    x-=speed;
                else if(direction=="right")
                    x+=speed;



            spriteCounter = (++spriteCounter) % 18;
            if (spriteCounter == 0) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 4;
                } else if (spriteNum == 4) {
                    spriteNum = 1;
                }



            }






            //Do steps that will always be done if even no key is pressed

            //if not in other state, increase transformation duration


            //player always goes down



            //Do that will always be done

        if(can_get_hit>0)
            can_get_hit--;

        can_get_hit=can_get_hit%1000;


        if(hp_bar_display_time>0)
            last_hit_time--;

        if(current_health <=0)
            state="dying";

        if(state=="dying")
            dying_animation++;

        if(dying_animation==5*dying_animation_delay)
        {
            gp.tileM.mapTileNum[gp.currentMap][x/gp.tileSize+1][y/gp.tileSize]=25;

        }

        if(dying_animation>6*dying_animation_delay)
            state="dead";





        //------CHECK OBJECT COLLISON------//


    }






    public void draw(Graphics2D g2) {





        //------------------------------------------------
        //DRAW PLAYER
        // --------------------------------------------------


        if (direction == "left") {

            if (spriteNum == 1)
                current_image = left1;
            if (spriteNum == 2)
                current_image = left2;
            if (spriteNum == 3)
                current_image = left3;
            if (spriteNum == 4)
                current_image = left4;


        } else if (direction == "right") {

            if (spriteNum == 1)
                current_image = right1;
            if (spriteNum == 2)
                current_image = right2;
            if (spriteNum == 3)
                current_image = right3;
            if (spriteNum == 4)
                current_image = right4;

        }

        if(can_get_hit%2==0 && dying_animation<2 )
        g2.drawImage(current_image, x, y, width, height, null);















        //------------------------------------------------
        //DRAW HP BAR
        //--------------------------------------------------
        if(last_hit_time>0)
        {
            int outline_width = 2, hp_bar_width = 35, hp_bar_start = 7;

            double oneScale = (double) hp_bar_width / max_health;
            double hpBarValue = oneScale * current_health;

            if (current_health < 0)
                hpBarValue = 0;

            g2.setColor(new Color(35, 35, 35));
            g2.fillRect(x + hp_bar_start - outline_width, y - 15 - outline_width, hp_bar_width + outline_width * 2, 10 + outline_width * 2);
            g2.setColor(new Color(255, 0, 30));
            g2.fillRect(x + hp_bar_start, y - 15, (int) hpBarValue, 10);

        }

        //------------------------------------------------
        //DRAW Dying ANIMATION
        //--------------------------------------------------

        if(dying_animation>0)
        {
            if(dying_animation/dying_animation_delay==1)
                current_image=ani1;
            else if(dying_animation/dying_animation_delay==2)
                current_image=ani2;
            else if(dying_animation/dying_animation_delay==3)
                current_image=ani3;
            else if(dying_animation/dying_animation_delay==4)
                current_image=ani4;
            else if(dying_animation/dying_animation_delay==5)
                current_image=ani5;
            else if(dying_animation/dying_animation_delay==6)
                current_image=ani6;
            else
                current_image=null;

            g2.drawImage(current_image, x, y, width, height, null);
        }






    }
}
