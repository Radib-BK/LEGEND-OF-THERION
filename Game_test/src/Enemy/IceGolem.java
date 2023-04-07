package Enemy;

import Entity.Entity;
import Entity.Weapon;
import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class IceGolem extends Entity {
    public IceGolem(GamePanel gp) {
        super(gp);

        solidArea = new Rectangle(48, 122, 297-48, 349);
        //    player_bullet = new Weapon[10];


        //player width is 96 px which is equal to gp.tileSize(48) *2 =96


        setDefaultValues();
        getEnemyImage();
        getDeathAnimationImage();
    }

    BufferedImage single0, single1, single2, single3, single4, single5, single6 ;
    BufferedImage burst0, burst1, burst2, burst3, burst4, burst5, burst6, burst7, burst8, burst9;
    public BufferedImage begin0, begin1, begin2, begin3, begin4, begin5, cycle1, cycle2, cycle3, cycle4, cycle5, cycle6;


    public int dying_animation= 0, dying_animation_delay=3;

    public Weapon[] lightning= new Weapon[10];

    String activity="idle";

    int burst_count=0;

    public BufferedImage boss_health_bar;

    public BufferedImage title;



    public int hit_delay = 0, shoot_animation_pause=0;

    int activity_delay=120;

    int bullet_original_y;

    int current_visible_lightning=0;




    public BufferedImage ani0, ani1, ani2, ani3, ani4, ani5, ani6, ani7, ani8, ani9, ani10, ani11;





    public void setDefaultValues() {

        speed = 3;
        direction = "left";
        move_direction = "left";

        max_hit_delay = 80;
        height = 350;
        width = 350;
        can_shoot = true;


        max_health = 60;
        current_health = max_health;
        lifetime_max_charge = 10;
        hit_damage=30;

        bullet_original_y=392+160;


        setPlayerBulletValues();
        setLigtningDefaultValues();
    }

    public void setLigtningDefaultValues() {

        for (int i = 0; i < lifetime_max_charge; i++)
            lightning[i] = new Weapon(gp);

        try {

            begin0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/fx/lightning/Lightning_beginning1.png"));
            begin1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/fx/lightning/Lightning_beginning2.png"));
            begin2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/fx/lightning/Lightning_beginning3.png"));
            begin3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/fx/lightning/Lightning_beginning4.png"));
            begin4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/fx/lightning/Lightning_beginning5.png"));
            begin5= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/fx/lightning/Lightning_beginning6.png"));

            cycle1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/fx/lightning/Lightning_cycle1.png"));
            cycle2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/fx/lightning/Lightning_cycle2.png"));
            cycle3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/fx/lightning/Lightning_cycle3.png"));
            cycle4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/fx/lightning/Lightning_cycle4.png"));
            cycle5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/fx/lightning/Lightning_cycle5.png"));
            cycle6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/fx/lightning/Lightning_cycle6.png"));




        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < lifetime_max_charge; i++)
            lightning[i].power = 5;

        for (int i = 0; i < lifetime_max_charge; i++)
            lightning[i].shoot_delay = 0;

        for (int i = 0; i < lifetime_max_charge; i++)
            lightning[i].destroyable = false;


        for (int i = 0; i < lifetime_max_charge; i++) {
            lightning[i].solidArea = new Rectangle(24, 0, 18, gp.tileSize);

        }
    }


    public void getEnemyImage() {
        try {

            left0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/idle/0.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/idle/1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/idle/2.png"));
            left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/idle/3.png"));
            left4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/idle/4.png"));

            single0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/attack/single/0.png"));
            single1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/attack/single/1.png"));
            single2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/attack/single/2.png"));
            single3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/attack/single/3.png"));
            single4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/attack/single/4.png"));
            single5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/attack/single/5.png"));
            single6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/attack/single/6.png"));

            burst0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/attack/burst/0.png"));
            burst1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/attack/burst/1.png"));
            burst2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/attack/burst/2.png"));
            burst3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/attack/burst/3.png"));
            burst4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/attack/burst/4.png"));
            burst5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/attack/burst/5.png"));
            burst6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/attack/burst/6.png"));
            burst7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/attack/burst/7.png"));
            burst8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/attack/burst/8.png"));
            burst9 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/attack/burst/9.png"));

            boss_health_bar= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Boss Health Bar.png"));
            title= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/title.png"));




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
            bullet[i].speed = 6;

        for (int i = 0; i < lifetime_max_charge; i++)
            bullet[i].amplitude = 40;

        for (int i = 0; i < lifetime_max_charge; i++)
            bullet[i].destroyable = false;


        for (int i = 0; i < lifetime_max_charge; i++) {
            bullet[i].solidArea = new Rectangle(20, 20, 80-20, 74-20);

            max_shoot_delay = 50;
            current_shoot_delay = 0;


        }
    }

    public void setPlayerBulletValues() {


        try {
            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i] = new Weapon(gp);

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/fx/ice/0.png"));

            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/fx/ice/1.png"));
            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/fx/ice/2.png"));
            for (int i = 0; i < lifetime_max_charge; i++)
                bullet[i].left4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/ice golem/fx/ice/3.png"));





        } catch (IOException e) {
            e.printStackTrace();
        }

        setPlayerBulletdefaultValues(lifetime_max_charge);


    }


    public void shoot_player_bullet() {


        if (current_visible_bullet < 10) {

                bullet[current_visible_bullet].direction = "left";
                bullet[current_visible_bullet].move_direction = "left";
                bullet[current_visible_bullet].visibility = true;
                bullet[current_visible_bullet].x = x - gp.tileSize;
                bullet[current_visible_bullet].y = y+130;
            bullet[current_visible_bullet].amplitude=70;
            bullet[current_visible_bullet].bullet_theta=0;

            Random random = new Random();

            int randomNumber = random.nextInt(30);

            bullet[current_visible_bullet].amplitude+=randomNumber;

            randomNumber=random.nextInt(360);

            bullet[current_visible_bullet].bullet_theta+=randomNumber;



            current_visible_bullet++;
        }
    }

    public void move_player_bullet() {

        for (int i = 0; i < current_visible_bullet; i++) {
            if (bullet[i].visibility == true) {

                    bullet[i].x -= bullet[i].speed;
                    bullet[i].y = bullet_original_y+  (int) ( (double) bullet[i].amplitude *  (Math.sin(Math.toRadians(bullet[i].bullet_theta))));

                bullet[i].bullet_theta+=4;
                    bullet[i].bullet_theta=bullet[i].bullet_theta%360;
            }

        }
    }

    public void check_player_bullet_collison_with_wall() {

        for (int i = 0; i < current_visible_bullet; i++) {
            bullet[i].collisonOn = false;




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

    public void shoot_player_ligtning() {


        if (current_visible_bullet < 10) {

            lightning[current_visible_lightning].direction = "down";
            lightning[current_visible_lightning].move_direction = "down";
            lightning[current_visible_lightning].visibility = true;
            lightning[current_visible_lightning].x = gp.player.x-10;
            lightning[current_visible_lightning].y = 0;
            lightning[current_visible_lightning].height=0;
            lightning[current_visible_lightning].lifetime=0;
            lightning[current_visible_lightning].solidArea.height=gp.tileSize;

            Random random = new Random();

            int randomNumber = random.nextInt(50);

            lightning[current_visible_lightning].x+=randomNumber;


            current_visible_lightning++;
        }
    }

    public void move_player_lightning()
    {
        for (int i = 0; i < current_visible_lightning; i++) {
            if (lightning[i].visibility == true) {



                if(lightning[i].shoot_delay==2)
                {
                    lightning[i].shoot_delay=0;

                    if(lightning[i].height<14)
                    {

                        lightning[i].height++;
                        lightning[i].solidArea.height=gp.tileSize*(lightning[i].height+1);
                    }
                    else
                        lightning[i].lifetime++;

                }
                else
                    lightning[i].shoot_delay++;
            }

        }

        for(int i=0; i<current_visible_lightning; i++)
        {
            lightning[i].spriteCounter = (++lightning[i].spriteCounter) % 5;
            if (lightning[i].spriteCounter == 0) {
                if (lightning[i].spriteNum == 0)
                    lightning[i].spriteNum = 1;
                else if (lightning[i].spriteNum == 1) {
                    lightning[i].spriteNum = 2;
                } else if (lightning[i].spriteNum == 2) {
                    lightning[i].spriteNum = 3;
                } else if (lightning[i].spriteNum == 3) {
                    lightning[i].spriteNum = 4;
                } else if (lightning[i].spriteNum == 4) {
                    lightning[i].spriteNum = 5;
                }

                else if (lightning[i].spriteNum ==5) {
                    lightning[i].spriteNum = 0;
                }
            }





        }
    }

    public void destroy_lightning()
    {
        for (int i = 0; i < current_visible_lightning; i++) {

            if(lightning[i].height==14 && lightning[i].lifetime==7)
                lightning[i].visibility=false;

        }

        int false_count = 0;


        for (int i = 0; i < current_visible_lightning; i++) {
            if (lightning[i].visibility == false) { // found a false value
                false_count++; // increment the count of false values found
            } else if (false_count > 0) { // shift the array to the left
                lightning[i - false_count].copy_weapon(lightning[i]);
            }
        }

        current_visible_lightning -= false_count;

        for (int i = current_visible_lightning + 1; i < lifetime_max_charge; i++)
            lightning[i].visibility = false;
    }

    public void player_lightning_collison_check()
    {

        Rectangle entity = new Rectangle();

        entity.x = gp.player.x + gp.player.solidArea.x;
        entity.y = gp.player.y + gp.player.solidArea.y;
        entity.height = gp.player.solidArea.height;
        entity.width = gp.player.solidArea.width;

        for(int i=0; i<this.lifetime_max_charge; i++)
        {
            if(lightning[i].visibility==true)
            {
                Rectangle bullet= new Rectangle();

                bullet.x = lightning[i].x + lightning[i].solidArea.x;
                bullet.y = lightning[i].y + lightning[i].solidArea.y;
                bullet.height = lightning[i].solidArea.height;
                bullet.width = lightning[i].solidArea.width;


                if(gp.player.can_get_hit<=0 && entity.intersects(bullet))
                {
                    gp.player.current_health -= lightning[i].power;



                    gp.player.can_get_hit= gp.player.max_hit_delay;


                    gp.player.last_hit_time=gp.player.hp_bar_display_time;
                }
            }
        }

    }


    public void do_action() {

        check_player_bullet_collison_with_wall();
        move_player_bullet();
        move_player_lightning();
        destroy_lightning();
        player_lightning_collison_check();



        if(can_get_hit>0)
            can_get_hit--;

        can_get_hit=can_get_hit%1000;


        if(activity_delay==0) {

            Random random = new Random();

            int randomNumber = random.nextInt(15);


            if(randomNumber<9)
            {
                activity="single";
            activity_delay=80;
            spriteNum=0;
            }
            else
            {
                activity="burst";
                activity_delay=230;
                spriteNum=0;
            }
        }
        else
            activity_delay--;




        if(dying_animation==0)
        {

        }





        if(activity=="idle") {
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
                    spriteNum = 0;
                }


            }
        }
        else if(activity=="single")
        {  spriteCounter = (++spriteCounter) % 10;
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
                }
                else if (spriteNum == 5) {
                    spriteNum = 6;
                }else if (spriteNum == 6) {
                    activity = "idle";
                    spriteNum=0;
                }

            }

        }

        else if(activity=="burst")
        {  spriteCounter = (++spriteCounter) % 6;
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
                    {

                        spriteNum = 5;
                    }
                }
                else if (spriteNum == 5) {


                    spriteNum = 6;
                }else if (spriteNum == 6) {



                        burst_count++;
                        spriteNum=7;

                }
                else if (spriteNum == 7) {


                    spriteNum = 8;
                }
                else if (spriteNum == 8) {


                    spriteNum = 9;
                }
                else if (spriteNum == 9) {


                    if(burst_count>=5)
                    {
                        spriteNum=0;
                        activity="idle";
                        burst_count=0;
                    }
                    else
                    {
                        spriteNum=1;
                        burst_count++;
                    }
                }


            }

        }


        if(activity=="single" && spriteNum==3 && spriteCounter==0)
            shoot_player_bullet();

        if(activity=="burst" && (spriteNum==3||spriteNum==7) && spriteCounter==0 )
        {

            shoot_player_ligtning();

        }


        //Do that will always be done





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


            for(int i=x/gp.tileSize; i< x/gp.tileSize+width/gp.tileSize; i++ )
            {
                for(int j=y/gp.tileSize; j<y/gp.tileSize+height/gp.tileSize; j++)
                    gp.tileM.mapTileNum[gp.currentMap][i][j]=25;
            }

            gp.tileM.mapTileNum[gp.currentMap][x/gp.tileSize+width/gp.tileSize/2][y/gp.tileSize+height/gp.tileSize-1]=24;
        }



        //------CHECK OBJECT COLLISON------//

    }


    public void draw(Graphics2D g2) {


        //------------------------------------------------
        //DRAW PLAYER
        // --------------------------------------------------
        BufferedImage image = null;

       // System.out.println(current_visible_lightning);



        if(activity=="idle") {
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
        }
        else if(activity=="single")
        {
            if (spriteNum == 0)
                current_image = single0;
            if (spriteNum == 1)
                current_image = single1;
            if (spriteNum == 2)
                current_image = single2;
            if (spriteNum == 3)
                current_image = single3;
            if (spriteNum == 4)
                current_image = single4;
            if (spriteNum == 5)
                current_image = single3;
            if (spriteNum == 6)
                current_image = single6;
        }

        else if(activity=="burst")
        {
            if (spriteNum == 0)
                current_image = burst0;
            if (spriteNum == 1)
                current_image = burst1;
            if (spriteNum == 2)
                current_image = burst2;
            if (spriteNum == 3)
                current_image = burst3;
            if (spriteNum == 4)
                current_image = burst4;
            if (spriteNum == 5)
                current_image = burst3;
            if (spriteNum == 6)
                current_image = burst6;
            if (spriteNum == 7)
                current_image = burst7;
            if (spriteNum == 8)
                current_image = burst8;
            if (spriteNum == 9)
                current_image = burst9;
        }





        if(dying_animation<2)
        {

                if ( can_get_hit % 2 == 0)
                    g2.drawImage(current_image, x, y, width, height, null);




        }



//DRAW PLAYER BULLET

        for (int i = 0; i < current_visible_bullet; i++) {

            if (bullet[i].visibility == true) {

                    if (bullet[i].spriteNum == 1)
                        image = bullet[i].left1;
                    else if (bullet[i].spriteNum == 2)
                        image = bullet[i].left2;
                    else if (bullet[i].spriteNum == 3)
                        image = bullet[i].left3;
                    else if (bullet[i].spriteNum == 4)
                        image = bullet[i].left4;




                g2.drawImage(image, bullet[i].x, bullet[i].y, 120, 90, null);

                bullet[i].spriteCounter = (++bullet[i].spriteCounter) % 8;

                if (bullet[i].spriteCounter == 0) {

                    if (bullet[i].spriteNum == 1)
                        bullet[i].spriteNum = 2;
                    else if (bullet[i].spriteNum == 2)
                        bullet[i].spriteNum = 3;
                    else if (bullet[i].spriteNum == 3)
                        bullet[i].spriteNum = 4;
                    else if (bullet[i].spriteNum == 4)
                        bullet[i].spriteNum = 1;

                }
            }

        }


        //------------------------------------------------
        //DRAW LIGHTNING
        //--------------------------------------------------

        for (int i = 0; i < current_visible_lightning; i++) {

            if(lightning[i].visibility==true)
            {
                int j=0;



                for( ; (double)j<=( (double)lightning[i].height-3)/3; j++)
                {

                    if(lightning[i].spriteNum==0)
                        image=cycle1;
                    if(lightning[i].spriteNum==1)
                        image=cycle2;
                    if(lightning[i].spriteNum==2)
                        image=cycle3;
                    if(lightning[i].spriteNum==3)
                        image=cycle4;
                    if(lightning[i].spriteNum==4)
                        image=cycle5;
                    if(lightning[i].spriteNum==5)
                        image=cycle6;

                    g2.drawImage(image, lightning[i].x, lightning[i].y + gp.tileSize*3*j, 64, gp.tileSize*3, null);
                }

                if(lightning[i].height%3==0)
                    g2.drawImage(begin0, lightning[i].x, lightning[i].y + gp.tileSize*3*j, 64, gp.tileSize*3, null);
                else     if(lightning[i].height%3==1)
                    g2.drawImage(begin1, lightning[i].x, lightning[i].y + gp.tileSize*3*j, 64, gp.tileSize*3, null);
                else     if(lightning[i].height%3==2)
                {
                    if(lightning[i].lifetime==0)
                    g2.drawImage(begin4, lightning[i].x, lightning[i].y + gp.tileSize*3*j, 64, gp.tileSize*3, null);
                    else
                    {
                        if(lightning[i].spriteNum %3==0)
                            image=begin2;
                        if(lightning[i].spriteNum%3==1)
                            image=begin3;
                        if(lightning[i].spriteNum%3==2)
                            image=begin5;

                        g2.drawImage(image, lightning[i].x, lightning[i].y + gp.tileSize*3*j, 64, gp.tileSize*3, null);
                    }
                }


            }
        }


        //------------------------------------------------
        //DRAW HP BAR
        //--------------------------------------------------



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

        g2.drawImage(title, 740, gp.tileSize*2-38, 229, 46, null);

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
