package Enemy;

import Entity.Entity;
import Entity.Weapon;
import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


public class FireDemon extends Entity {

    public int dying_animation= 0, dying_animation_delay=3;

    public Weapon[] burst= new Weapon[5];

    public BufferedImage burst0, burst1, burst2, burst3, burst4, burst5;

    String activity="idle";

public BufferedImage left5, right5;

    public BufferedImage boss_health_bar;

    public BufferedImage title;

    public BufferedImage ani0, ani1, ani2, ani3, ani4, ani5, ani6;

    public BufferedImage leftslash0, leftslash1, leftslash2, leftslash3, leftslash4, leftslash5, leftslash6, leftslash7, leftslash8, leftslash9, leftslash10, leftslash11, leftslash12, leftslash13, leftslash14;
    public BufferedImage rightslash0, rightslash1, rightslash2, rightslash3, rightslash4, rightslash5, rightslash6, rightslash7, rightslash8, rightslash9, rightslash10, rightslash11, rightslash12, rightslash13, rightslash14;

    public BufferedImage moveleft0, moveleft1, moveleft2, moveleft3, moveleft4, moveleft5, moveleft6, moveleft7, moveleft8, moveleft9, moveleft10, moveleft11;
    public BufferedImage moveright0, moveright1, moveright2, moveright3, moveright4, moveright5, moveright6, moveright7, moveright8, moveright9, moveright10, moveright11;

    public BufferedImage leftattack0, leftattack1, leftattack2, leftattack3, leftattack4, leftattack5, leftattack6, leftattack7, leftattack8, leftattack9, leftattack10, leftattack11;
    public BufferedImage rightattack0, rightattack1, rightattack2, rightattack3, rightattack4, rightattack5, rightattack6, rightattack7, rightattack8, rightattack9, rightattack10, rightattack11;

    int attack_sprite_pause=0;
    public int hit_delay = 0, shoot_animation_pause=0;


    int activity_delay=120;


    public FireDemon(GamePanel gp) {
        super(gp);

        solidArea = new Rectangle(216, 124, 361-216, 319-124);

        setDefaultValues();
        getEnemyImage();
        getDeathAnimationImage();
    }

    public void setDefaultValues() {

        speed = 6;
        direction = "left";
        move_direction = "left";

        max_hit_delay = 30;
        height = 320;
        width = 576;
        can_shoot = true;


        max_health = 100;
        current_health = max_health;
        lifetime_max_charge = 10;
        hit_damage=40;
        damagable=true;






        setPlayerBulletValues();

    }

    public void getEnemyImage() {

        for(int i=0; i<5; i++)
            burst[i]= new Weapon(gp);

        for(int i=0; i<5; i++)
            burst[i].solidArea= new Rectangle(55, 142, 183-55, 239-142);

        try {

            left0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/idle/left/0.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/idle/left/1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/idle/left/2.png"));
            left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/idle/left/3.png"));
            left4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/idle/left/4.png"));
            left5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/idle/left/5.png"));

            right0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/idle/right/0.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/idle/right/1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/idle/right/2.png"));
            right3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/idle/right/3.png"));
            right4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/idle/right/4.png"));
            right5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/idle/right/5.png"));

            leftslash0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/left/0.png"));
            leftslash1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/left/1.png"));
            leftslash2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/left/2.png"));
            leftslash3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/left/3.png"));
            leftslash4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/left/4.png"));
            leftslash5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/left/5.png"));
            leftslash6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/left/6.png"));
            leftslash7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/left/7.png"));
            leftslash8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/left/8.png"));
            leftslash9 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/left/9.png"));
            leftslash10 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/left/10.png"));
            leftslash11 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/left/11.png"));
            leftslash12 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/left/12.png"));
            leftslash13 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/left/13.png"));
            leftslash14 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/left/14.png"));

            rightslash0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/right/0.png"));
            rightslash1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/right/1.png"));
            rightslash2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/right/2.png"));
            rightslash3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/right/3.png"));
            rightslash4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/right/4.png"));
            rightslash5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/right/5.png"));
            rightslash6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/right/6.png"));
            rightslash7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/right/7.png"));
            rightslash8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/right/8.png"));
            rightslash9 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/right/9.png"));
            rightslash10 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/right/10.png"));
            rightslash11 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/right/11.png"));
            rightslash12 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/right/12.png"));
            rightslash13 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/right/13.png"));
            rightslash14 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/slash/right/14.png"));

            moveleft0= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/left/0.png"));
            moveleft1= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/left/1.png"));
            moveleft2= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/left/2.png"));
            moveleft3= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/left/3.png"));
            moveleft4= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/left/4.png"));
            moveleft5= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/left/5.png"));
            moveleft6= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/left/6.png"));
            moveleft7= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/left/7.png"));
            moveleft8= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/left/8.png"));
            moveleft9= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/left/9.png"));
            moveleft10= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/left/10.png"));
            moveleft11= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/left/11.png"));

            moveright0= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/right/0.png"));
            moveright1= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/right/1.png"));
            moveright2= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/right/2.png"));
            moveright3= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/right/3.png"));
            moveright4= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/right/4.png"));
            moveright5= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/right/5.png"));
            moveright6= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/right/6.png"));
            moveright7= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/right/7.png"));
            moveright8= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/right/8.png"));
            moveright9= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/right/9.png"));
            moveright10= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/right/10.png"));
            moveright11= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/move/right/11.png"));

            leftattack0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/left/0.png"));
            leftattack1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/left/1.png"));
            leftattack2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/left/2.png"));
            leftattack3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/left/3.png"));
            leftattack4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/left/4.png"));
            leftattack5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/left/5.png"));
            leftattack6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/left/6.png"));
            leftattack7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/left/7.png"));
            leftattack8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/left/8.png"));
            leftattack9 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/left/9.png"));
            leftattack10 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/left/10.png"));
            leftattack11 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/left/11.png"));

            rightattack0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/right/0.png"));
            rightattack1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/right/1.png"));
            rightattack2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/right/2.png"));
            rightattack3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/right/3.png"));
            rightattack4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/right/4.png"));
            rightattack5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/right/5.png"));
            rightattack6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/right/6.png"));
            rightattack7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/right/7.png"));
            rightattack8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/right/8.png"));
            rightattack9 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/right/9.png"));
            rightattack10 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/right/10.png"));
            rightattack11 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/attack/fireball/right/11.png"));

            burst0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/fx/burst/0.png"));
            burst1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/fx/burst/1.png"));
            burst2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/fx/burst/2.png"));
            burst3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/fx/burst/3.png"));
            burst4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/fx/burst/4.png"));
            burst5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/fx/burst/5.png"));



            boss_health_bar= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Boss Health Bar.png"));
            title= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/title.png"));




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
            bullet[i].power = 15;

        for (int i = 0; i < lifetime_max_charge; i++)
            bullet[i].speed = 15;



        for (int i = 0; i < lifetime_max_charge; i++)
            bullet[i].destroyable = false;


        for (int i = 0; i < lifetime_max_charge; i++) {
            bullet[i].solidArea = new Rectangle(2, 1, 64-2, 44-1);




        }
    }

    public void setPlayerBulletValues() {


        try {
            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i] = new Weapon(gp);

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].left0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/fx/fireball/left/0.png"));

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/fx/fireball/left/1.png"));
            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/fx/fireball/left/2.png"));

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].right0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/fx/fireball/right/0.png"));

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/fx/fireball/right/1.png"));
            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/fire demon/fx/fireball/right/2.png"));





        } catch (IOException e) {
            e.printStackTrace();
        }

        setPlayerBulletdefaultValues(lifetime_max_charge);


    }

    public void hit_burst()
    {
            int arr_x[]={ 100, 400, 740, 1060, 1350 };

            //294


        for(int i=0; i<5; i++)
        {

            Random random = new Random();

            int randomNumber = random.nextInt(60);

            burst[i].visibility=true;
            burst[i].spriteCounter=0;
            burst[i].spriteNum=0;
            burst[i].x=arr_x[i]-randomNumber;
            burst[i].y=150;
            burst[i].lifetime=0;
        }
    }

    public void check_burst()
    {
        for(int i=0; i<5; i++)
        {
            burst[i].lifetime++;

            if(burst[i].lifetime%4==0)
            {
                if(burst[i].spriteNum>=0 && burst[i].spriteNum<=4)
                    burst[i].spriteNum++;
                else
                    burst[i].spriteNum=0;
            }

            if(burst[i].lifetime>90)
                burst[i].visibility=false;
        }
    }

    public void check_collison_with_burst()
    {

        if(burst[0].visibility==true)
        for(int i=0; i<5; i++)
        {
            Rectangle player = new Rectangle();

            player.x = gp.player.x + gp.player.solidArea.x;
            player.y = gp.player.y + gp.player.solidArea.y;
            player.height = gp.player.solidArea.height;
            player.width = gp.player.solidArea.width;


            Rectangle burst_area = new Rectangle();

            burst_area.x = burst[i].x + burst[i].solidArea.x;
            burst_area.y = burst[i].y + burst[i].solidArea.y;
            burst_area.height = burst[i].solidArea.height;
            burst_area.width = burst[i].solidArea.width;

            if(gp.player.can_get_hit<=0 &&    player.intersects(burst_area))
            {
                if(gp.player.damagable==true)
                    gp.player.current_health -=30;



                if(gp.player.damagable==true)
                    gp.player.can_get_hit= gp.player.max_hit_delay;






            }
        }


    }

    public void check_slash_damage()
    {
        Rectangle player = new Rectangle();

        player.x = gp.player.x + gp.player.solidArea.x;
        player.y = gp.player.y + gp.player.solidArea.y;
        player.height = gp.player.solidArea.height;
        player.width = gp.player.solidArea.width;


        Rectangle slash_area = new Rectangle();

        if(direction=="left") {
            slash_area.x = x +52;
            slash_area.y = y + 182;
            slash_area.height = 319-182;
            slash_area.width = 159-52;
        }
        else if(direction=="right")
        {
            slash_area.x = x +415;
            slash_area.y = y + 182;
            slash_area.height = 525-415;
            slash_area.width = 159-52;
        }
        if(gp.player.can_get_hit<=0 &&    player.intersects(slash_area))
        {
            if(gp.player.damagable==true)
                gp.player.current_health -=50;



            if(gp.player.damagable==true)
                gp.player.can_get_hit= gp.player.max_hit_delay;






        }
    }


    public void shoot_player_bullet() {

        if(current_visible_bullet<10)
        for(int i=0; i<5; i++)
        {
            Random random = new Random();

            int randomNumber = random.nextInt(2);

            if(randomNumber==0)
            {
                randomNumber = random.nextInt(50);

                bullet[current_visible_bullet].direction="left";
                bullet[current_visible_bullet].move_direction="left";
                bullet[current_visible_bullet].visibility = true;
                bullet[current_visible_bullet].x =1550;
                bullet[current_visible_bullet].y = 60+50*i+randomNumber;
                bullet[current_visible_bullet].spriteNum=0;
                bullet[current_visible_bullet].spriteCounter=0;
            }
            else
            {
                randomNumber = random.nextInt(50);

                bullet[current_visible_bullet].direction="right";
                bullet[current_visible_bullet].move_direction="right";
                bullet[current_visible_bullet].visibility = true;
                bullet[current_visible_bullet].x =100;
                bullet[current_visible_bullet].y = 60+50*i+randomNumber;
                bullet[current_visible_bullet].spriteNum=0;
                bullet[current_visible_bullet].spriteCounter=0;
            }

            current_visible_bullet++;
        }

if(current_visible_bullet<10)
        for(int i=0; i<2; i++)
        {
            Random random = new Random();

            int randomNumber = random.nextInt(2);

            if(randomNumber==0)
            {
                randomNumber = random.nextInt(50);

                bullet[current_visible_bullet].direction="left";
                bullet[current_visible_bullet].move_direction="left";
                bullet[current_visible_bullet].visibility = true;
                bullet[current_visible_bullet].x =1260;
                bullet[current_visible_bullet].y = 410+55*i+randomNumber;
                bullet[current_visible_bullet].spriteNum=0;
                bullet[current_visible_bullet].spriteCounter=0;
            }
            else
            {
                randomNumber = random.nextInt(50);

                bullet[current_visible_bullet].direction="right";
                bullet[current_visible_bullet].move_direction="right";
                bullet[current_visible_bullet].visibility = true;
                bullet[current_visible_bullet].x =400;
                bullet[current_visible_bullet].y = 410+55*i+randomNumber;
                bullet[current_visible_bullet].spriteNum=0;
                bullet[current_visible_bullet].spriteCounter=0;
            }

            current_visible_bullet++;
        }


    }

    public void move_player_bullet() {

        for (int i = 0; i < current_visible_bullet; i++) {
            if (bullet[i].visibility == true) {

                if(bullet[i].direction=="left")
                    bullet[i].x-=bullet[i].speed;
                else if(bullet[i].direction=="right")
                    bullet[i].x+=bullet[i].speed;
            }

        }
    }

    public void check_player_bullet_collison_with_wall() {

        for (int i = 0; i < current_visible_bullet; i++) {
            bullet[i].collisonOn = false;




            if (bullet[i].x < 0 || bullet[i].y < 0 || bullet[i].x > gp.screenWidth - gp.tileSize * 3 || bullet[i].y > gp.screenHeight - gp.tileSize * 2)
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

    public void do_action() {

        check_player_bullet_collison_with_wall();
        move_player_bullet();
        check_burst();
        check_collison_with_burst();

        if(activity=="slash" && spriteNum>=9 && spriteNum<=11)
            check_slash_damage();




        if(y-gp.player.y<-110)
        {
            if(x-gp.player.x>-15 && direction=="left")
            {

                if(activity=="idle")
                activity="walk";
            }
            else if(x-gp.player.x<-475 && direction=="right")
            {
                if(activity=="idle")
                activity="walk";
            }
            else
            {
                if(activity!="slash")
                    spriteNum=0;

                activity="slash";
            }

        }
        else {
            if(activity_delay==0 && activity=="idle") {

                Random random = new Random();

                int randomNumber = random.nextInt(2);

                if(randomNumber==0)
                {
                    activity_delay=140;
                    activity="fireball";
                }
                else if(randomNumber==1)
                {
                    activity_delay=100;
                    activity="burst";
                }



            }

        }



        if(activity_delay>0)
            activity_delay--;

        if(x-gp.player.x<-290)
            direction="right";
        else if(x-gp.player.x>-250)
            direction="left";

        if(activity=="walk")
        {
            if(direction=="left")
                x-=speed;
            else
                x+=speed;
        }
        else if(activity=="fireball")
        {
            if(spriteNum==6 && spriteCounter==0)
            {

                shoot_player_bullet();

            }
        }
        else if(activity=="burst")
        {
            if(spriteNum==6 && spriteCounter==0)
                hit_burst();
        }


            if (activity == "idle") {
                spriteCounter = (++spriteCounter) % 10;
                if (spriteCounter == 0) {
                    if (spriteNum == 0)
                        spriteNum = 1;
                    else if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 3;
                    } else if (spriteNum == 3) {
                        spriteNum = 4;
                    } else if (spriteNum == 4) {
                        spriteNum = 5;
                    } else if (spriteNum == 5)
                        spriteNum = 0;


                }
            }
            else if(activity=="slash")
            {
                spriteCounter=(++spriteCounter) % 5;

                if(spriteCounter==0)
                {
                    if(spriteNum>=0 && spriteNum<=13)
                        spriteNum++;
                    else if(spriteNum==14)
                    {
                        activity="idle";
                        spriteNum=0;
                    }
                }
            }
            else if(activity=="walk")
            {
                spriteCounter=(++spriteCounter) % 5;

                if(spriteCounter==0)
                {
                    if(spriteNum>=0 && spriteNum<=10)
                        spriteNum++;
                    else if(spriteNum==11)
                    {

                        spriteNum=0;
                    }
                }
            }
            else if(activity=="burst" || activity=="fireball")
            {
                spriteCounter=(++spriteCounter) % 8;

                if(spriteCounter==0)
                {
                    if(spriteNum>=0 && spriteNum<=10)
                        spriteNum++;

                    else if(spriteNum==11)
                    {
                        activity="idle";
                        spriteNum=0;
                    }
                }
            }

            if(can_get_hit>0)
                can_get_hit--;


        if(current_health <=0)
            state="dying";

        if(state=="dying")
            dying_animation++;

        if(dying_animation>6*dying_animation_delay)
            state="dead";

        if(dying_animation==5*dying_animation_delay)
        {


            for(int i=x/gp.tileSize; i< x/gp.tileSize+width/gp.tileSize; i++ )
            {
                for(int j=y/gp.tileSize; j<y/gp.tileSize+height/gp.tileSize; j++)
                {
                    if(gp.tileM.mapTileNum[gp.currentMap][i][j]==0)
                    gp.tileM.mapTileNum[gp.currentMap][i][j]=25;
                }
            }

            gp.tileM.mapTileNum[gp.currentMap][x/gp.tileSize+width/gp.tileSize/2][y/gp.tileSize+height/gp.tileSize-1]=24;
        }

    }


    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        // System.out.println(current_visible_lightning);



        if(activity=="idle") {

            if (direction == "left") {
                if (spriteNum == 0)
                    current_image = left0;
                if (spriteNum == 1)
                    current_image = left1;
                if (spriteNum == 2)
                    current_image = left2;
                if (spriteNum == 3)
                    current_image = left3;
                if (spriteNum == 4)
                    current_image = left4;
                if (spriteNum == 5)
                    current_image = left5;
            }
            else if (direction == "right") {

                if (spriteNum == 1)
                    current_image = right1;
                if (spriteNum == 2)
                    current_image = right2;
                if (spriteNum == 3)
                    current_image = right3;
                if (spriteNum == 4)
                    current_image = right4;
                if (spriteNum == 5)
                    current_image = right5;

            }
        }
        else if(activity=="slash")
        {
            if(direction=="left")
            {
                if(spriteNum==0)
                    current_image=leftslash0;
                else if(spriteNum==1)
                    current_image=leftslash1;
                else if(spriteNum==2)
                    current_image=leftslash2;
                else if(spriteNum==3)
                    current_image=leftslash3;
                else if(spriteNum==4)
                    current_image=leftslash4;
                else if(spriteNum==5)
                    current_image=leftslash5;
                else if(spriteNum==6)
                    current_image=leftslash6;
                else if(spriteNum==7)
                    current_image=leftslash7;
                else if(spriteNum==8)
                    current_image=leftslash8;
                else if(spriteNum==9)
                    current_image=leftslash9;
                else if(spriteNum==10)
                    current_image=leftslash10;
                else if(spriteNum==11)
                    current_image=leftslash11;
                else if(spriteNum==12)
                    current_image=leftslash12;
                else if(spriteNum==13)
                    current_image=leftslash13;
                else if(spriteNum==14)
                    current_image=leftslash14;
            }
            else if(direction=="right")
            {
                if(spriteNum==0)
                    current_image=rightslash0;
                else if(spriteNum==1)
                    current_image=rightslash1;
                else if(spriteNum==2)
                    current_image=rightslash2;
                else if(spriteNum==3)
                    current_image=rightslash3;
                else if(spriteNum==4)
                    current_image=rightslash4;
                else if(spriteNum==5)
                    current_image=rightslash5;
                else if(spriteNum==6)
                    current_image=rightslash6;
                else if(spriteNum==7)
                    current_image=rightslash7;
                else if(spriteNum==8)
                    current_image=rightslash8;
                else if(spriteNum==9)
                    current_image=rightslash9;
                else if(spriteNum==10)
                    current_image=rightslash10;
                else if(spriteNum==11)
                    current_image=rightslash11;
                else if(spriteNum==12)
                    current_image=rightslash12;
                else if(spriteNum==13)
                    current_image=rightslash13;
                else if(spriteNum==14)
                    current_image=rightslash14;
            }

        }

        else if(activity=="walk")
        {
            if(direction=="left")
            {
                if(spriteNum==0)
                    current_image=moveleft0;
                else if(spriteNum==1)
                    current_image=moveleft1;
                else if(spriteNum==2)
                    current_image=moveleft2;
                else if(spriteNum==3)
                    current_image=moveleft3;
                else if(spriteNum==4)
                    current_image=moveleft4;
                else if(spriteNum==5)
                    current_image=moveleft5;
                else if(spriteNum==6)
                    current_image=moveleft6;
                else if(spriteNum==7)
                    current_image=moveleft7;
                else if(spriteNum==8)
                    current_image=moveleft8;
                else if(spriteNum==9)
                    current_image=moveleft9;
                else if(spriteNum==10)
                    current_image=moveleft10;
                else if(spriteNum==11)
                    current_image=moveleft11;

            }
            else if(direction=="right")
            {
                if(spriteNum==0)
                    current_image=moveright0;
                else if(spriteNum==1)
                    current_image=moveright1;
                else if(spriteNum==2)
                    current_image=moveright2;
                else if(spriteNum==3)
                    current_image=moveright3;
                else if(spriteNum==4)
                    current_image=moveright4;
                else if(spriteNum==5)
                    current_image=moveright5;
                else if(spriteNum==6)
                    current_image=moveright6;
                else if(spriteNum==7)
                    current_image=moveright7;
                else if(spriteNum==8)
                    current_image=moveright8;
                else if(spriteNum==9)
                    current_image=moveright9;
                else if(spriteNum==10)
                    current_image=moveright10;
                else if(spriteNum==11)
                    current_image=moveright11;
            }

        }
        else if(activity=="burst" || activity=="fireball")
        {
            if(direction=="left")
            {
                if(spriteNum==0)
                    current_image=leftattack0;
                else if(spriteNum==1)
                    current_image=leftattack1;
                else if(spriteNum==2)
                    current_image=leftattack2;
                else if(spriteNum==3)
                    current_image=leftattack3;
                else if(spriteNum==4)
                    current_image=leftattack4;
                else if(spriteNum==5)
                    current_image=leftattack5;
                else if(spriteNum==6)
                    current_image=leftattack6;
                else if(spriteNum==7)
                    current_image=leftattack7;
                else if(spriteNum==8)
                    current_image=leftattack8;
                else if(spriteNum==9)
                    current_image=leftattack9;
                else if(spriteNum==10)
                    current_image=leftattack10;
                else if(spriteNum==11)
                    current_image=leftattack11;
            }
            else if(direction=="right")
            {
                if(spriteNum==0)
                    current_image=rightattack0;
                else if(spriteNum==1)
                    current_image=rightattack1;
                else if(spriteNum==2)
                    current_image=rightattack2;
                else if(spriteNum==3)
                    current_image=rightattack3;
                else if(spriteNum==4)
                    current_image=rightattack4;
                else if(spriteNum==5)
                    current_image=rightattack5;
                else if(spriteNum==6)
                    current_image=rightattack6;
                else if(spriteNum==7)
                    current_image=rightattack7;
                else if(spriteNum==8)
                    current_image=rightattack8;
                else if(spriteNum==9)
                    current_image=rightattack9;
                else if(spriteNum==10)
                    current_image=rightattack10;
                else if(spriteNum==11)
                    current_image=rightattack11;
            }

        }





        if(dying_animation<2)
        {

            if ( can_get_hit % 2 == 0)
                g2.drawImage(current_image, x, y, width, height, null);




        }


        for (int i = 0; i < current_visible_bullet; i++) {
            if (bullet[i].visibility == true) {
                if (bullet[i].direction == "left") {
                    if (bullet[i].spriteNum == 0)
                        image = bullet[i].left0;
                    else if (bullet[i].spriteNum == 1)
                        image = bullet[i].left1;
                    else if (bullet[i].spriteNum == 2)
                        image = bullet[i].left2;


                } else if (bullet[i].direction == "right") {
                    if (bullet[i].spriteNum == 0)
                        image = bullet[i].right0;
                    else if (bullet[i].spriteNum == 1)
                        image = bullet[i].right1;
                    else if (bullet[i].spriteNum == 2)
                        image = bullet[i].right2;


                }


                g2.drawImage(image, bullet[i].x, bullet[i].y, 79, 46, null);

                bullet[i].spriteCounter = (++bullet[i].spriteCounter) % 8;

                if (bullet[i].spriteCounter == 0) {
                    if (bullet[i].spriteNum == 0)
                        bullet[i].spriteNum = 1;
                    else if (bullet[i].spriteNum == 1)
                        bullet[i].spriteNum = 2;
                    else if (bullet[i].spriteNum == 2)
                        bullet[i].spriteNum = 0;

                }


            }
        }

        for(int i=0; i<5; i++)
        {
            if(burst[i].visibility==true)
            {
                if(burst[i].spriteNum==0)
                    image=burst0;
                else if(burst[i].spriteNum==1)
                    image=burst1;
                else if(burst[i].spriteNum==2)
                    image=burst2;
                else if(burst[i].spriteNum==3)
                    image=burst3;
                else if(burst[i].spriteNum==4)
                    image=burst4;
                else if(burst[i].spriteNum==5)
                    image=burst5;


                g2.drawImage(image, burst[i].x, burst[i].y, 240, 240, null);
            }


        }

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
