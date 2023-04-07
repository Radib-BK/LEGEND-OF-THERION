package Enemy;

import Entity.Entity;
import Entity.Weapon;
import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class StoneGolem extends Entity {



    public int dying_animation = 0, dying_animation_delay = 3;

    public BufferedImage leftshoot0, leftshoot1, leftshoot2, leftshoot3, leftshoot4, leftshoot5, leftshoot6, leftshoot7, leftshoot8, leftshoot9, leftshoot10, leftshoot11, leftshoot12, leftshoot13, leftshoot14;
    public BufferedImage rightshoot0, rightshoot1, rightshoot2, rightshoot3, rightshoot4, rightshoot5, rightshoot6, rightshoot7, rightshoot8, rightshoot9, rightshoot10, rightshoot11, rightshoot12, rightshoot13, rightshoot14;
    public BufferedImage ani1, ani2, ani3, ani4, ani5, ani6;

    public BufferedImage leftlaser1, leftlaser2, leftlaser3, leftlaser4, leftlaser5, leftlaser6, leftlaser7, leftlaser8, leftlaser9, leftlaser10, leftlaser11, leftlaser12, leftlaser13, leftlaser14;

    public BufferedImage rightlaser1, rightlaser2, rightlaser3, rightlaser4, rightlaser5, rightlaser6, rightlaser7, rightlaser8, rightlaser9, rightlaser10, rightlaser11, rightlaser12, rightlaser13, rightlaser14;

    public BufferedImage leftcycle1, leftcycle2, leftcycle3, leftcycle4, leftcycle6, leftcycle5;

    public BufferedImage leftGround0,leftGround1, leftGround2, leftGround3, leftGround4, leftGround5, leftGround6,  leftGround7, leftGround8, leftGround9;
    public BufferedImage rightGround0, rightGround1, rightGround2, rightGround3, rightGround4, rightGround5, rightGround6, rightGround7, rightGround8, rightGround9;
    public BufferedImage leftLasershoot0, leftLasershoot1, leftLasershoot2, leftLasershoot3, leftLasershoot4, leftLasershoot5, leftLasershoot6, leftLasershoot7;

    public BufferedImage rightLasershoot0, rightLasershoot1, rightLasershoot2, rightLasershoot3, rightLasershoot4, rightLasershoot5, rightLasershoot6, rightLasershoot7;

    public BufferedImage title,  boss_health_bar;
    int max_laser_bullet = 7;

    public BufferedImage ground0, ground1, ground2, ground3, ground4, ground5, ground6, ground7;

    int laser_begin_sprite_num = 1, lazer_cycle_sprite_num = 1;

    Weapon laser[] = new Weapon[max_laser_bullet];

    Weapon ground= new Weapon(gp);

    String activity = "idle";

    int activity_delay = 120;
    int movement_delay = 100;

    int laser_shoot = 0;

    public StoneGolem(GamePanel gp) {
        super(gp);

        solidArea = new Rectangle(71, 80, 192-71, 180-80);
        //    player_bullet = new Weapon[10];


        //player width is 96 px which is equal to gp.tileSize(48) *2 =96


        setDefaultValues();
        getEnemyImage();
        getDeathAnimationImage();
    }


    public void setDefaultValues() {

        speed = 2;
        direction = "right";
        move_direction = "right";

        max_hit_delay = 40;
        height = 260;
        width = 260;
        can_shoot = false;

        lifetime_max_charge = 2;
        can_shoot=true;

        ground.solidArea= new Rectangle(31, 180, 176-41,  239-180);
        ground.hit_damage=20;

        max_health = 70;
        current_health = max_health;
        hit_damage = 25;


        setPlayerBulletValues();
        getLaserValues();
    }


    public void getEnemyImage() {
        try {

            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/idle/left/0.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/idle/left/1.png"));
            left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/idle/left/2.png"));
            left4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/idle/left/3.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/idle/right/0.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/idle/right/1.png"));
            right3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/idle/right/2.png"));
            right4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/idle/right/3.png"));

            leftshoot0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/left/0.png"));
            leftshoot1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/left/1.png"));
            leftshoot2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/left/2.png"));
            leftshoot3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/left/3.png"));
            leftshoot4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/left/4.png"));
            leftshoot5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/left/5.png"));
            leftshoot6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/left/6.png"));
            leftshoot7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/left/7.png"));
            leftshoot8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/left/8.png"));
            leftshoot9 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/left/9.png"));
            leftshoot10 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/left/10.png"));
            leftshoot11 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/left/11.png"));
            leftshoot12 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/left/12.png"));
            leftshoot13 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/left/13.png"));
            leftshoot14 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/left/14.png"));


            rightshoot0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/right/0.png"));
            rightshoot1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/right/1.png"));
            rightshoot2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/right/2.png"));
            rightshoot3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/right/3.png"));
            rightshoot4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/right/4.png"));
            rightshoot5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/right/5.png"));
            rightshoot6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/right/6.png"));
            rightshoot7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/right/7.png"));
            rightshoot8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/right/8.png"));
            rightshoot9 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/right/9.png"));
            rightshoot10 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/right/10.png"));
            rightshoot11 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/right/11.png"));
            rightshoot12 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/right/12.png"));
            rightshoot13 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/right/13.png"));
            rightshoot14 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/hand-shoot/right/14.png"));

            leftLasershoot0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/laser/left/0.png"));
            leftLasershoot1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/laser/left/1.png"));
            leftLasershoot2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/laser/left/2.png"));
            leftLasershoot3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/laser/left/3.png"));
            leftLasershoot4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/laser/left/4.png"));
            leftLasershoot5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/laser/left/5.png"));
            leftLasershoot6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/laser/left/6.png"));
            leftLasershoot7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/laser/left/7.png"));

            rightLasershoot0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/laser/right/0.png"));
            rightLasershoot1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/laser/right/1.png"));
            rightLasershoot2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/laser/right/2.png"));
            rightLasershoot3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/laser/right/3.png"));
            rightLasershoot4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/laser/right/4.png"));
            rightLasershoot5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/laser/right/5.png"));
            rightLasershoot6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/laser/right/6.png"));
            rightLasershoot7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/laser/right/7.png"));

            leftGround0= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/left/0.png"));
            leftGround1= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/left/1.png"));
            leftGround2= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/left/2.png"));
            leftGround3= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/left/3.png"));
            leftGround4= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/left/4.png"));
            leftGround5= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/left/5.png"));
            leftGround6= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/left/6.png"));
            leftGround7= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/left/7.png"));
            leftGround8= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/left/8.png"));
            leftGround9= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/left/9.png"));


            rightGround0= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/right/0.png"));
            rightGround1= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/right/1.png"));
            rightGround2= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/right/2.png"));
            rightGround3= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/right/3.png"));
            rightGround4= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/right/4.png"));
            rightGround5= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/right/5.png"));
            rightGround6= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/right/6.png"));
            rightGround7= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/right/7.png"));
            rightGround8= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/right/8.png"));
            rightGround9= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/attack/ground-burst/right/9.png"));

            ground0= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/ground-burst/0.png"));
            ground1= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/ground-burst/1.png"));
            ground2= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/ground-burst/2.png"));
            ground3= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/ground-burst/3.png"));
            ground4= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/ground-burst/4.png"));
            ground5= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/ground-burst/5.png"));
            ground6= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/ground-burst/6.png"));
            ground7= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/ground-burst/7.png"));

            boss_health_bar= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Boss Health Bar.png"));
            title= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/title.png"));



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
            bullet[i].speed = 12;

        for (int i = 0; i < lifetime_max_charge; i++)
            bullet[i].destroyable = false;


        for (int i = 0; i < lifetime_max_charge; i++) {
            bullet[i].solidArea = new Rectangle(0, 15, 48, 29 - 15);


        }
    }

    public void getLaserValues() {
        for (int i = 0; i < max_laser_bullet; i++)
            laser[i] = new Weapon(gp);

        try {


            leftlaser1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/1.png"));
            leftlaser2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/2.png"));
            leftlaser3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/3.png"));
            leftlaser4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/4.png"));
            leftlaser5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/5.png"));
            leftlaser6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/6.png"));
            leftlaser7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/7.png"));
            leftlaser8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/8.png"));
            leftlaser9 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/9.png"));
            leftlaser10 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/10.png"));
            leftlaser11 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/11.png"));
            leftlaser12 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/12.png"));
            leftlaser13 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/13.png"));
            leftlaser14 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/14.png"));

            rightlaser1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/right/1.png"));
            rightlaser2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/right/2.png"));
            rightlaser3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/right/3.png"));
            rightlaser4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/right/4.png"));
            rightlaser5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/right/5.png"));
            rightlaser6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/right/6.png"));
            rightlaser7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/right/7.png"));
            rightlaser8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/right/8.png"));
            rightlaser9 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/right/9.png"));
            rightlaser10 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/right/10.png"));
            rightlaser11 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/right/11.png"));
            rightlaser12 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/right/12.png"));
            rightlaser13 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/right/13.png"));
            rightlaser14 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/right/14.png"));

            leftcycle1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/cycle (1).png"));
            leftcycle2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/cycle (2).png"));
            leftcycle3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/cycle (3).png"));
            leftcycle4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/cycle (4).png"));
            leftcycle5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/cycle (5).png"));
            leftcycle6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/laser/left/cycle (6).png"));


        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < max_laser_bullet; i++)
            laser[i].power = 8;


    }

    public void setPlayerBulletValues() {

        for (int i = 0; i < lifetime_max_charge; i++)
            bullet[i] = new Weapon(gp);

        try {


            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/hand-shoot/left/0.png"));

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/hand-shoot/left/1.png"));

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/hand-shoot/left/2.png"));
            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/hand-shoot/left/3.png"));
            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].left4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/hand-shoot/left/4.png"));

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].right0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/hand-shoot/right/0.png"));

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/hand-shoot/right/1.png"));

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/hand-shoot/right/2.png"));

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].right3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/hand-shoot/right/3.png"));

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].right4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/stone golem/fx/hand-shoot/right/4.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }

        setPlayerBulletdefaultValues(lifetime_max_charge);


    }

    public void shoot_laser() {


        for (int i = 0; i < max_laser_bullet; i++) {
            Random random = new Random();

            int randomNumber = random.nextInt(70);

            laser[i].y = 40 + i * 110;

            laser[i].y += randomNumber;


            if (direction == "right") {
                laser[i].direction = "right";
                laser[i].move_direction = "right";
                laser[i].visibility = true;
                laser[i].x = x + 150;
                laser[i].width = 0;

            } else if (direction == "left") {
                laser[i].direction = "left";
                laser[i].move_direction = "left";
                laser[i].visibility = true;
                laser[i].x = x - 200;
                laser[i].width = 0;
            }

            laser[i].lifetime = 0;
        }
        laser_begin_sprite_num = 1;
        lazer_cycle_sprite_num = 1;
    }

    public void move_laser() {
        if (laser[0].visibility == true) {
            for (int i = 0; i < max_laser_bullet; i++) {

                if (laser[i].lifetime == 40) {
                    if (laser[i].direction == "left")
                        laser[i].width = laser[i].x;
                    else
                        laser[i].width = gp.screenWidth - laser[i].x;

                    laser_begin_sprite_num = 9;
                }


                laser[i].lifetime++;

                if (laser[i].lifetime > 90)
                    laser[i].visibility = false;
            }


            if (laser[0].width == 0) {
                if (laser[0].lifetime % 5 == 2)
                    laser_begin_sprite_num++;
            } else {


                if (laser[0].lifetime % 3 == 2) {
                    if (laser_begin_sprite_num >= 9 && laser_begin_sprite_num <= 13)
                        laser_begin_sprite_num++;
                    else if (laser_begin_sprite_num == 14)
                        laser_begin_sprite_num = 9;
                }

                if (lazer_cycle_sprite_num >= 1 && lazer_cycle_sprite_num <= 5)
                    lazer_cycle_sprite_num++;
                else
                    lazer_cycle_sprite_num = 1;

            }

        }


    }

    public void shoot_player_bullet() {


        if (current_visible_bullet < 10) {
            if (direction == "right") {
                bullet[current_visible_bullet].direction = "right";
                bullet[current_visible_bullet].move_direction = "right";
                bullet[current_visible_bullet].visibility = true;
                bullet[current_visible_bullet].x = x + gp.tileSize * 2;
                bullet[current_visible_bullet].y = y;
                bullet[current_visible_bullet].solidArea= new Rectangle(15, 85, 111-15,122-85);
            } else if (direction == "left") {
                bullet[current_visible_bullet].direction = "left";
                bullet[current_visible_bullet].move_direction = "left";
                bullet[current_visible_bullet].visibility = true;
                bullet[current_visible_bullet].x = x - gp.tileSize;
                bullet[current_visible_bullet].y = y;
                bullet[current_visible_bullet].solidArea= new Rectangle(169, 85, 265-169,122-83);
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

    public void hit_ground()
    {

        ground.x=gp.player.x-gp.tileSize;

        int y_value=gp.player.y/gp.tileSize;
        int x_value=( (gp.player.x*2+gp.tileSize*2)/2)/gp.tileSize;



        while(true)
        {
            if(gp.tileM.mapTileNum[gp.currentMap][x_value][y_value]!=0)
                break;
            else
                y_value++;



        }




        ground.y=y_value*gp.tileSize-gp.tileSize*5+10;

        ground.visibility=true;
        ground.lifetime=0;
        ground.spriteNum=0;

    }

    public void destroy_ground() {
        if (ground.visibility == true) {

            ground.lifetime++;



          if (ground.lifetime % 10 == 0)
           {
                if (ground.spriteNum <= 6)
                    ground.spriteNum++;
            }

            if (ground.lifetime > 90)
                ground.visibility = false;
        }
    }

    public void check_player_bullet_collison_with_wall() {

        for (int i = 0; i < current_visible_bullet; i++) {
            bullet[i].collisonOn = false;




            if (bullet[i].x < 0 || bullet[i].y < 0 || bullet[i].x > gp.screenWidth - gp.tileSize * 5 || bullet[i].y > gp.screenHeight - gp.tileSize * 2)
                bullet[i].visibility = false;

            gp.cChecker.checkTile(bullet[i]);

            if (bullet[i].collisonOn == true)
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

    void ground_player_collison_check()
    {
        Rectangle player = new Rectangle();

        player.x = gp.player.x + gp.player.solidArea.x;
        player.y = gp.player.y + gp.player.solidArea.y;
        player.height = gp.player.solidArea.height;
        player.width = gp.player.solidArea.width;


        Rectangle ground_burst = new Rectangle();

        ground_burst.x = ground.x + ground.solidArea.x;
        ground_burst.y = ground.y + ground.solidArea.y;
        ground_burst.height = ground.solidArea.height;
        ground_burst.width = ground.solidArea.width;



        if(gp.player.can_get_hit<=0 &&   player.intersects(ground_burst))
        {
            gp.player.current_health -=ground.hit_damage;
            gp.player.can_get_hit=gp.player.max_hit_delay;


        }

    }

    public void player_laser_collison_check()
    {
        for(int i=0; i<max_laser_bullet; i++)
        {
            Rectangle player = new Rectangle();

            player.x = gp.player.x + gp.player.solidArea.x;
            player.y = gp.player.y + gp.player.solidArea.y;
            player.height = gp.player.solidArea.height;
            player.width = gp.player.solidArea.width;


            Rectangle laser_area = new Rectangle();

            if(laser[i].direction=="right") {
                laser_area.x = laser[i].x;
                laser_area.y = laser[i].y + 25;
                laser_area.height = 42 - 25;
                laser_area.width = laser[i].width;
            }
            else if(laser[i].direction=="left")
            {
                laser_area.x=0;
                laser_area.y=laser[i].y+25;
                laser_area.height=42-25;

                if(laser[i].width>0)
                laser_area.width=laser[i].x;
                else
                    laser_area.width=0;
            }

            if(gp.player.can_get_hit<=0 &&   player.intersects(laser_area))
            {
                gp.player.current_health -=laser[i].power;
                gp.player.can_get_hit=gp.player.max_hit_delay;


            }

        }
    }

    public void do_action() {

        check_player_bullet_collison_with_wall();
        move_player_bullet();
        move_laser();
        destroy_ground();

        if(ground.visibility==true && ground.spriteNum>=6)
        ground_player_collison_check();

        if(laser[0].visibility==true)
            player_laser_collison_check();


        if (activity != "laser" && activity!="ground") {
            if (x - gp.player.x > 400) {
                direction = "left";

                x -= speed;
            } else if (gp.player.x - x > 500) {
                direction = "right";
                x += speed;
            } else if (x - gp.player.x > 0) {
                direction = "left";
            } else if (gp.player.x - x > 200)
                direction = "right";

        }


        if (y - gp.player.y > -50) {
            {
                if (movement_delay == 0)
                    y -= speed;
                else
                    movement_delay--;
            }
        } else if (gp.player.y - y > 50 && y < 640) {
            if (movement_delay == 0)
                y += speed;
            else
                movement_delay--;
        } else
            movement_delay = 30;


        if (activity_delay == 0) {
            Random random = new Random();

            int randomNumber = random.nextInt(3);

            if (randomNumber == 1) {
                activity = "shoot";
                activity_delay = 70;
                spriteNum=0;
            } else if (randomNumber == 0) {
                activity = "laser";
                activity_delay = 90;
                spriteNum=0;
            }
            else if(randomNumber==2)
            {
                if(gp.player.transformation_state=="normal") {
                    activity = "ground";
                    activity_delay = 90;
                    spriteNum = 0;
                }
                else
                {
                    randomNumber= random.nextInt(2);
                    if (randomNumber == 0) {
                        activity = "shoot";
                        activity_delay = 70;
                        spriteNum=0;
                    } else if (randomNumber == 1) {
                        activity = "laser";
                        activity_delay = 90;
                        spriteNum=0;
                    }

                }
                }

        } else
            activity_delay--;


        if (activity == "idle") {
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
        } else if (activity == "shoot") {
            spriteCounter = (++spriteCounter) % 4;
            if (spriteCounter == 0) {

                if (spriteNum >= 0 && spriteNum <= 13)
                    spriteNum++;
                else if (spriteNum == 14) {
                    activity = "idle";
                    spriteNum = 1;
                }


            }
        } else if (activity == "laser") {
            spriteCounter = (++spriteCounter) % 10;
            if (spriteCounter == 0) {

                if (spriteNum >= 0 && spriteNum <= 5)
                    spriteNum++;
                else if (spriteNum == 6) {
                    if (laser_shoot == 1)
                        spriteNum++;
                    else {
                        laser_shoot++;
                        spriteNum = 1;
                    }
                } else if (spriteNum == 7) {
                    laser_shoot = 0;
                    activity = "idle";
                    spriteNum = 1;
                }


            }
        }
        else if(activity=="ground")
        {
            spriteCounter = (++spriteCounter) % 11;
            if (spriteCounter == 0) {

                if (spriteNum >= 0 && spriteNum <= 8)
                    spriteNum++;

                } else if (spriteNum == 9) {

                    activity = "idle";
                    spriteNum = 1;
                }


            }




        //Do steps that will always be done if even no key is pressed

        //if not in other state, increase transformation duration


        //player always goes down

        if (activity == "shoot" && spriteNum == 8 && spriteCounter == 0)
            shoot_player_bullet();

        if (activity == "laser" && spriteNum == 5 && laser_shoot == 0 && spriteCounter == 0)
            shoot_laser();

        if(activity=="ground" && spriteNum==3 && spriteCounter==0)
            hit_ground();



        //Do that will always be done

        if (can_get_hit > 0)
            can_get_hit--;

        can_get_hit = can_get_hit % 1000;


        if (hp_bar_display_time > 0)
            last_hit_time--;

        if (current_health <= 0)
            state = "dying";

        if (state == "dying")
            dying_animation++;

        if (dying_animation == 5 * dying_animation_delay) {
            gp.tileM.mapTileNum[gp.currentMap][x / gp.tileSize + 1][y / gp.tileSize] = 25;

        }

        if (dying_animation > 6 * dying_animation_delay)
            state = "dead";


        //------CHECK OBJECT COLLISON------//


    }

    public void draw_stone_golem_laser(Graphics2D g2) {
        for (int i = 0; i < max_laser_bullet; i++) {
            if (laser[i].visibility == false)
                break;
            else {


                for (int k = 0; k <= laser[i].width * 2; k += 300) {
                    if (laser[i].direction == "left") {
                        if (k == 0) {
                            if (laser[i].width == 0) {
                                if (laser_begin_sprite_num == 1)
                                    current_image = leftlaser1;
                                if (laser_begin_sprite_num == 2)
                                    current_image = leftlaser2;
                                if (laser_begin_sprite_num == 3)
                                    current_image = leftlaser3;
                                if (laser_begin_sprite_num == 4)
                                    current_image = leftlaser4;
                                if (laser_begin_sprite_num == 5)
                                    current_image = leftlaser5;
                                if (laser_begin_sprite_num == 6)
                                    current_image = leftlaser6;
                                if (laser_begin_sprite_num == 7)
                                    current_image = leftlaser7;
                                if (laser_begin_sprite_num == 8)
                                    current_image = leftlaser8;

                            } else {
                                if (laser_begin_sprite_num == 9)
                                    current_image = leftlaser9;
                                if (laser_begin_sprite_num == 10)
                                    current_image = leftlaser10;
                                if (laser_begin_sprite_num == 11)
                                    current_image = leftlaser11;
                                if (laser_begin_sprite_num == 12)
                                    current_image = leftlaser12;
                                if (laser_begin_sprite_num == 13)
                                    current_image = leftlaser13;
                                if (laser_begin_sprite_num == 14)
                                    current_image = leftlaser14;
                            }
                        } else {
                            if (lazer_cycle_sprite_num == 1)
                                current_image = leftcycle1;
                            if (lazer_cycle_sprite_num == 2)
                                current_image = leftcycle2;
                            if (lazer_cycle_sprite_num == 3)
                                current_image = leftcycle3;
                            if (lazer_cycle_sprite_num == 4)
                                current_image = leftcycle4;
                            if (lazer_cycle_sprite_num == 5)
                                current_image = leftcycle5;
                            if (lazer_cycle_sprite_num == 6)
                                current_image = leftcycle6;

                        }

                        g2.drawImage(current_image, laser[i].x - k, laser[i].y, 300, 100, null);
                    } else if (laser[i].direction == "right") {
                        if (k == 0) {
                            if (laser[i].width == 0) {
                                if (laser_begin_sprite_num == 1)
                                    current_image = rightlaser1;
                                if (laser_begin_sprite_num == 2)
                                    current_image = rightlaser2;
                                if (laser_begin_sprite_num == 3)
                                    current_image = rightlaser3;
                                if (laser_begin_sprite_num == 4)
                                    current_image = rightlaser4;
                                if (laser_begin_sprite_num == 5)
                                    current_image = rightlaser5;
                                if (laser_begin_sprite_num == 6)
                                    current_image = rightlaser6;
                                if (laser_begin_sprite_num == 7)
                                    current_image = rightlaser7;
                                if (laser_begin_sprite_num == 8)
                                    current_image = rightlaser8;
                            } else {
                                if (laser_begin_sprite_num == 9)
                                    current_image = rightlaser9;
                                if (laser_begin_sprite_num == 10)
                                    current_image = rightlaser10;
                                if (laser_begin_sprite_num == 11)
                                    current_image = rightlaser11;
                                if (laser_begin_sprite_num == 12)
                                    current_image = rightlaser12;
                                if (laser_begin_sprite_num == 13)
                                    current_image = rightlaser13;
                                if (laser_begin_sprite_num == 14)
                                    current_image = rightlaser14;
                            }
                        } else {
                            if (lazer_cycle_sprite_num == 1)
                                current_image = leftcycle1;
                            if (lazer_cycle_sprite_num == 2)
                                current_image = leftcycle2;
                            if (lazer_cycle_sprite_num == 3)
                                current_image = leftcycle3;
                            if (lazer_cycle_sprite_num == 4)
                                current_image = leftcycle4;
                            if (lazer_cycle_sprite_num == 5)
                                current_image = leftcycle5;
                            if (lazer_cycle_sprite_num == 6)
                                current_image = leftcycle6;

                        }

                        g2.drawImage(current_image, laser[i].x + k, laser[i].y, 300, 100, null);
                    }
                }

            }
        }
    }


    public void draw(Graphics2D g2) {


        //------------------------------------------------
        //DRAW PLAYER
        // --------------------------------------------------


        if (activity == "idle") {
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
        } else if (activity == "shoot") {
            if (direction == "left") {
                if (spriteNum == 0) {
                    current_image = leftshoot0;
                } else if (spriteNum == 1) {
                    current_image = leftshoot1;
                } else if (spriteNum == 2) {
                    current_image = leftshoot2;
                } else if (spriteNum == 3) {
                    current_image = leftshoot3;
                } else if (spriteNum == 4) {
                    current_image = leftshoot4;
                } else if (spriteNum == 5) {
                    current_image = leftshoot5;
                } else if (spriteNum == 6) {
                    current_image = leftshoot6;
                } else if (spriteNum == 7) {
                    current_image = leftshoot7;
                } else if (spriteNum == 8) {
                    current_image = leftshoot8;
                } else if (spriteNum == 9) {
                    current_image = leftshoot9;
                } else if (spriteNum == 10) {
                    current_image = leftshoot10;
                } else if (spriteNum == 11) {
                    current_image = leftshoot11;
                } else if (spriteNum == 12) {
                    current_image = leftshoot12;
                } else if (spriteNum == 13) {
                    current_image = leftshoot13;
                } else if (spriteNum == 14) {
                    current_image = leftshoot14;
                }

            } else if (direction == "right") {
                if (spriteNum == 0) {
                    current_image = rightshoot0;
                } else if (spriteNum == 1) {
                    current_image = rightshoot1;
                } else if (spriteNum == 2) {
                    current_image = rightshoot2;
                } else if (spriteNum == 3) {
                    current_image = rightshoot3;
                } else if (spriteNum == 4) {
                    current_image = rightshoot4;
                } else if (spriteNum == 5) {
                    current_image = rightshoot5;
                } else if (spriteNum == 6) {
                    current_image = rightshoot6;
                } else if (spriteNum == 7) {
                    current_image = rightshoot7;
                } else if (spriteNum == 8) {
                    current_image = rightshoot8;
                } else if (spriteNum == 9) {
                    current_image = rightshoot9;
                } else if (spriteNum == 10) {
                    current_image = rightshoot10;
                } else if (spriteNum == 11) {
                    current_image = rightshoot11;
                } else if (spriteNum == 12) {
                    current_image = rightshoot12;
                } else if (spriteNum == 13) {
                    current_image = rightshoot13;
                } else if (spriteNum == 14) {
                    current_image = rightshoot14;
                }

            }
        } else if (activity == "laser") {


            if (direction == "left") {


                if (spriteNum == 0) {
                    current_image = leftLasershoot0;
                } else if (spriteNum == 1) {
                    current_image = leftLasershoot1;
                } else if (spriteNum == 2) {
                    current_image = leftLasershoot2;
                } else if (spriteNum == 3) {
                    current_image = leftLasershoot3;
                } else if (spriteNum == 4) {
                    current_image = leftLasershoot4;
                } else if (spriteNum == 5) {
                    current_image = leftLasershoot5;
                } else if (spriteNum == 6) {
                    current_image = leftLasershoot6;
                } else if (spriteNum == 7) {
                    current_image = leftLasershoot7;
                }
            } else if (direction == "right") {
                System.out.println(spriteNum);

                if (spriteNum == 0) {
                    current_image = rightLasershoot0;
                } else if (spriteNum == 1) {
                    current_image = rightLasershoot1;
                } else if (spriteNum == 2) {
                    current_image = rightLasershoot2;
                } else if (spriteNum == 3) {
                    current_image = rightLasershoot3;
                } else if (spriteNum == 4) {
                    current_image = rightLasershoot4;
                } else if (spriteNum == 5) {
                    current_image = rightLasershoot5;
                } else if (spriteNum == 6) {
                    current_image = rightLasershoot6;
                } else if (spriteNum == 7) {
                    current_image = rightLasershoot7;
                }
            }

        }
        else if(activity=="ground")
        {
            if(direction=="left")
            {
                if (spriteNum == 0) {
                    current_image = leftGround0;
                } else if (spriteNum == 1) {
                    current_image = leftGround1;
                } else if (spriteNum == 2) {
                    current_image = leftGround2;
                } else if (spriteNum == 3) {
                    current_image = leftGround3;
                } else if (spriteNum == 4) {
                    current_image = leftGround4;
                } else if (spriteNum == 5) {
                    current_image = leftGround5;
                } else if (spriteNum == 6) {
                    current_image = leftGround6;
                } else if (spriteNum == 7) {
                    current_image = leftGround7;
                }
                else if (spriteNum == 8) {
                    current_image = leftGround8;
                }
                else if (spriteNum == 9) {
                    current_image = leftGround9;
                }

            }
            else if(direction=="right")
            {
                if (spriteNum == 0) {
                    current_image = rightGround0;
                } else if (spriteNum == 1) {
                    current_image = rightGround1;
                } else if (spriteNum == 2) {
                    current_image = rightGround2;
                } else if (spriteNum == 3) {
                    current_image = rightGround3;
                } else if (spriteNum == 4) {
                    current_image = rightGround4;
                } else if (spriteNum == 5) {
                    current_image = rightGround5;
                } else if (spriteNum == 6) {
                    current_image = rightGround6;
                } else if (spriteNum == 7) {
                    current_image = rightGround7;
                }
                else if (spriteNum == 8) {
                    current_image = rightGround8;
                }
                else if (spriteNum == 9) {
                    current_image = rightGround9;
                }


            }
        }


        if (can_get_hit % 2 == 0 && dying_animation < 2)
            g2.drawImage(current_image, x, y, width, height, null);


        ///DDRAW PLAYER BULLET


        for (int i = 0; i < current_visible_bullet; i++) {
            if (bullet[i].visibility == true) {
                if (bullet[i].direction == "left") {
                    if (bullet[i].spriteNum == 1)
                        current_image = bullet[i].left1;
                    else if (bullet[i].spriteNum == 2)
                        current_image = bullet[i].left2;
                    else if (bullet[i].spriteNum == 3)
                        current_image = bullet[i].left3;
                    else if (bullet[i].spriteNum == 4)
                        current_image = bullet[i].left4;


                } else if (bullet[i].direction == "right") {
                    if (bullet[i].spriteNum == 1)
                        current_image = bullet[i].right1;
                    else if (bullet[i].spriteNum == 2)
                        current_image = bullet[i].right2;
                    else if (bullet[i].spriteNum == 3)
                        current_image = bullet[i].right3;
                    else if (bullet[i].spriteNum == 4)
                        current_image = bullet[i].right4;


                }

                g2.drawImage(current_image, bullet[i].x, bullet[i].y, 280, 280, null);

                bullet[i].spriteCounter = (++bullet[i].spriteCounter) % 8;

                if (bullet[i].spriteCounter == 7) {
                    if (bullet[i].spriteNum == 1)
                        bullet[i].spriteNum = 2;
                    else if (bullet[i].spriteNum == 2)
                        bullet[i].spriteNum = 1;

                }
            }

        }

        if(ground.visibility==true)
        {
            if (ground.spriteNum == 0) {
                current_image = ground0;
            } else if (ground.spriteNum == 1) {
                current_image = ground1;
            } else if (ground.spriteNum == 2) {
                current_image = ground2;
            } else if (ground.spriteNum == 3) {
                current_image = ground3;
            } else if (ground.spriteNum == 4) {
                current_image = ground4;
            } else if (ground.spriteNum == 5) {
                current_image = ground5;
            } else if (ground.spriteNum == 6) {
                current_image = ground6;
            } else if (ground.spriteNum == 7) {
                current_image = ground7;
            }


            g2.drawImage(current_image, ground.x, ground.y, gp.tileSize*5, gp.tileSize*5, null);
        }

        //HEATH VAR

        double oneScale = (double) 615 / max_health;
        double hpBarValue = oneScale * current_health;

        if (current_health < 0)
            hpBarValue = 0;


        g2.setColor(new Color(110, 20, 35));
        g2.fillRect( 555, gp.tileSize*2+22, (int) hpBarValue, 8);

        g2.setColor(new Color(100, 7, 29));
        g2.fillRect( 555, gp.tileSize*2+22+8, (int) hpBarValue, 9);

        g2.setColor(new Color(138, 35, 42));
        g2.fillRect( 555, gp.tileSize*2+22+17, (int) hpBarValue, 8);

        g2.drawImage(title, 735, gp.tileSize*2-38, 239, 46, null);

        g2.drawImage(boss_health_bar, 510, gp.tileSize*2, 700, 69, null );

        if(last_hit_time>0) {
            //just change following values for adjustment

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


            //DRAW GROUND








