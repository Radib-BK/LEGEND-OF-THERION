package Enemy;

import Entity.Entity;
import MainPackage.GamePanel;
import Entity.Weapon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BlackMetal extends Entity {











    BufferedImage leftShoot, rightShoot;


    public int dying_animation= 0, dying_animation_delay=3;



    public int fall_height = 0, final_fall_height = 0;
    public int hit_delay = 0, shoot_animation_pause=0;




    public BufferedImage ani0, ani1, ani2, ani3, ani4, ani5, ani6, ani7, ani8, ani9, ani10, ani11;


    public BlackMetal(GamePanel gp) {
        super(gp);

        solidArea = new Rectangle(0, 0, 102, 118);
        //    player_bullet = new Weapon[10];


        //player width is 96 px which is equal to gp.tileSize(48) *2 =96


        setDefaultValues();
        getEnemyImage();
        getDeathAnimationImage();
    }


    public void setDefaultValues() {

        speed = 3;
        direction = "left";
        move_direction = "left";

        max_hit_delay = 80;
        height = 132;
        width = 98;
        can_shoot = true;


        max_health = 60;
        current_health = max_health;
        lifetime_max_charge = 9;
        hit_damage=30;


        setPlayerBulletValues();
    }


    public void getEnemyImage() {
        try {

            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Black Metal/walk/left/0.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Black Metal/walk/left/1.png"));
            left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Black Metal/walk/left/2.png"));
            left4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Black Metal/walk/left/3.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Black Metal/walk/right/0.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Black Metal/walk/right/1.png"));
            right3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Black Metal/walk/right/2.png"));
            right4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Black Metal/walk/right/3.png"));
            leftShoot=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Black Metal/attack/left.png"));
            rightShoot = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Black Metal/attack/right.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getDeathAnimationImage() {
        try {
            ani1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Dying Animation/1.png"));
            ani2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Dying Animation/2.png"));
            ani3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Dying Animation/3.png"));
            ani4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Dying Animation/4.png"));
            ani5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Dying Animation/5.png"));
            ani6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Dying Animation/6.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerBulletdefaultValues(int lifetime_max_charge) {


        for (int i = 0; i < lifetime_max_charge; i++)
            bullet[i].power = 8;

        for (int i = 0; i < lifetime_max_charge; i++)
            bullet[i].speed = 8;


        for (int i = 0; i < lifetime_max_charge; i++) {
            bullet[i].solidArea = new Rectangle(0, 15, 48, 29-15);

            max_shoot_delay = 50;
            current_shoot_delay = 0;


        }
    }

    public void setPlayerBulletValues() {


        try {
            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i] = new Weapon(gp);

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Black Metal/bullet/left/0.png"));

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Black Metal/bullet/left/1.png"));
            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Black Metal/bullet/left/2.png"));
            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].left4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Black Metal/bullet/left/3.png"));

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Black Metal/bullet/right/0.png"));

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Black Metal/bullet/right/1.png"));

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].right3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Black Metal/bullet/right/2.png"));

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].right4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Black Metal/bullet/right/3.png"));



        } catch (IOException e) {
            e.printStackTrace();
        }

        setPlayerBulletdefaultValues(lifetime_max_charge);


    }


    public void shoot_player_bullet() {


        if (current_visible_bullet < 10) {
            if (direction == "right") {
                bullet[current_visible_bullet].direction = "right";
                bullet[current_visible_bullet].move_direction = "right";
                bullet[current_visible_bullet].visibility = true;
                bullet[current_visible_bullet].x = x + gp.tileSize*2;
                bullet[current_visible_bullet].y = y+58;
            } else if (direction == "left") {
                bullet[current_visible_bullet].direction = "left";
                bullet[current_visible_bullet].move_direction = "left";
                bullet[current_visible_bullet].visibility = true;
                bullet[current_visible_bullet].x = x - gp.tileSize;
                bullet[current_visible_bullet].y = y+58;
            }


            current_visible_bullet++;
        }
    }

    public void move_player_bullet() {

        for (int i = 0; i < current_visible_bullet; i++) {
            if (bullet[i].visibility == true) {
                if (bullet[i].direction == "left")
                    bullet[i].x -= bullet[i].speed;
                else if (bullet[i].direction == "right")
                    bullet[i].x += bullet[i].speed;
            }

        }
    }

    public void check_player_bullet_collison_with_wall() {

        for (int i = 0; i < current_visible_bullet; i++) {
            bullet[i].collisonOn = false;


            gp.cChecker.checkTile(bullet[i]);

            if (bullet[i].collisonOn == true)
                bullet[i].visibility = false;

            if (bullet[i].x < 0 || bullet[i].y < 0 || bullet[i].x > gp.screenWidth - gp.tileSize * 2 || bullet[i].y > gp.screenHeight - gp.tileSize * 2)
                bullet[i].visibility = false;

        }

        int false_count = 0;


        for (int i = 0; i < current_visible_bullet; i++) {
            if (bullet[i].visibility == false) { // found a false value
                false_count++; // increment the count of false values found
            } else if (false_count > 0) { // shift the array to the left
                bullet[i - false_count].copy_weapon(bullet[i]);
            }
        }

        current_visible_bullet -= false_count;

        for (int i = current_visible_bullet + 1; i < lifetime_max_charge; i++)
            bullet[i].visibility = false;
    }

    public void do_action() {

        check_player_bullet_collison_with_wall();
        move_player_bullet();


        if(can_get_hit>0)
        can_get_hit--;

        can_get_hit=can_get_hit%1000;


        if (x < left_limit) {
            direction = "right";
            move_direction = "right";

        } else if (x > right_limit) {
            direction = "left";
            move_direction = "left";
        }

        if(dying_animation==0)
        {
            if (direction == "left")
                x -= speed;
            else if (direction == "right")
                x += speed;
        }

        collisonOn = false;

        gp.cChecker.checkTile(this);

        if (collisonOn == true) {
            switch (move_direction) {

                case "left":
                    direction = "right";
                    move_direction = "right";
                    break;
                case "right":
                    direction = "left";
                    move_direction = "left";
                    break;
            }
        }


        spriteCounter = (++spriteCounter) % 10;
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

        if(shoot_animation_pause==1)
            spriteNum=1;


        //Do that will always be done

        current_shoot_delay = (++current_shoot_delay) % max_shoot_delay;

        if (current_shoot_delay == 0)
        {
            shoot_player_bullet();
            shoot_animation_pause=15;
            spriteNum=5;
        }

        if(shoot_animation_pause>0)
            shoot_animation_pause--;



            hit_delay--;
            //System.out.println("Red hi");


        //hit_delay=hit_delay%1000;





        if(hp_bar_display_time>0)
            last_hit_time--;

        if(current_health <=0)
            state="dying";

        if(state=="dying")
            dying_animation++;

        if(dying_animation>6*dying_animation_delay)
            state="dead";

        if(dying_animation==5*dying_animation_delay)
        {
            gp.tileM.mapTileNum[gp.currentMap][x/gp.tileSize][y/gp.tileSize+1]=25;
            gp.tileM.mapTileNum[gp.currentMap][x/gp.tileSize+1][y/gp.tileSize+1]=25;
            gp.tileM.mapTileNum[gp.currentMap][x/gp.tileSize+1][y/gp.tileSize+2]=25;
            gp.tileM.mapTileNum[gp.currentMap][x/gp.tileSize][y/gp.tileSize+2]=25;
        }



        //------CHECK OBJECT COLLISON------//

    }


    public void draw(Graphics2D g2) {


        //------------------------------------------------
        //DRAW PLAYER
        // --------------------------------------------------
        BufferedImage image = null;

        if (direction == "left") {

            if (spriteNum == 1)
                current_image = left1;
            if (spriteNum == 2)
                current_image = left2;
            if (spriteNum == 3)
                current_image = left3;
            if (spriteNum == 4)
                current_image = left4;
            if (spriteNum == 5)
                current_image = leftShoot;


        } else if (direction == "right") {

            if (spriteNum == 1)
                current_image = right1;
            if (spriteNum == 2)
                current_image = right2;
            if (spriteNum == 3)
                current_image = right3;
            if (spriteNum == 4)
                current_image = right4;
            if (spriteNum == 5)
                current_image = rightShoot;

        }


        if(dying_animation<2)
        {
            if (spriteNum != 5 )
            {
                if ( can_get_hit % 2 == 0)
                    g2.drawImage(current_image, x, y, width, height, null);

            } else {
                if (direction == "left")
                    g2.drawImage(current_image, x - 20, y, width + 20, height, null);
                else if (direction == "right")
                    g2.drawImage(current_image, x, y, width + 20, height, null);
            }
        }

        for (int i = 0; i < current_visible_bullet; i++) {
            if (bullet[i].visibility == true) {
                if (bullet[i].direction == "left") {
                    if (bullet[i].spriteNum == 1)
                        image = bullet[i].left1;
                    else if (bullet[i].spriteNum == 2)
                        image = bullet[i].left2;
                    else if (bullet[i].spriteNum == 3)
                        image = bullet[i].left3;
                    else if (bullet[i].spriteNum == 4)
                        image = bullet[i].left4;


                } else if (bullet[i].direction == "right") {
                    if (bullet[i].spriteNum == 1)
                        image = bullet[i].right1;
                    else if (bullet[i].spriteNum == 2)
                        image = bullet[i].right2;
                    else if (bullet[i].spriteNum == 3)
                        image = bullet[i].right3;
                    else if (bullet[i].spriteNum == 4)
                        image = bullet[i].right4;


                }

                g2.drawImage(image, bullet[i].x, bullet[i].y, gp.tileSize, gp.tileSize, null);

                bullet[i].spriteCounter = (++bullet[i].spriteCounter) % 8;

                if (bullet[i].spriteCounter == 7) {
                    if (bullet[i].spriteNum == 1)
                        bullet[i].spriteNum = 2;
                    else if (bullet[i].spriteNum == 2)
                        bullet[i].spriteNum = 1;

                }
            }

        }


        //------------------------------------------------
        //DRAW HP BAR
        //--------------------------------------------------

        if(last_hit_time>0) {
            //just change following values for adjustment
            int outline_width = 2, hp_bar_width = 100, hp_bar_start = 0, hp_bar_y_offset = 25;

            double oneScale = (double) hp_bar_width / max_health;
            double hpBarValue = oneScale * current_health;

            if (current_health < 0)
                hpBarValue = 0;

            g2.setColor(new Color(35, 35, 35));
            g2.fillRect(x + hp_bar_start - outline_width, y - hp_bar_y_offset - outline_width, hp_bar_width + outline_width * 2, 10 + outline_width * 2);
            g2.setColor(new Color(255, 0, 30));
            g2.fillRect(x + hp_bar_start, y - hp_bar_y_offset, (int) hpBarValue, 10);
        }

        //DYING ANIMATION

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

