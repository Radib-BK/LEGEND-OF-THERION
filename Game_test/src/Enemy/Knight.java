package Enemy;

import Entity.Entity;
import Entity.Weapon;
import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Knight extends Entity{

    BufferedImage title, boss_health_bar;

    public BufferedImage  ani1, ani2, ani3, ani4, ani5, ani6;

    public int dying_animation= 0, dying_animation_delay=3;

    public BufferedImage other0, other1, hyper0, hyper1;

    public BufferedImage spark0, spark1, spark2, spark3, spark4, spark5, spark6, spark7, spark8;

    public BufferedImage blast0, blast1, blast2, blast3, blast4, blast5, blast6, blast7, blast8, blast9;

    public BufferedImage [] left_ray= new BufferedImage[30];
    public BufferedImage [] right_ray= new BufferedImage[30];

    public Weapon [] spark = new Weapon[10];

    public Weapon [] ray= new Weapon[30];

    public BufferedImage [] teleport= new BufferedImage[8];

    public BufferedImage [] slash_left= new BufferedImage[5];
    public BufferedImage [] slash_right= new BufferedImage[5];

    public Weapon []blast= new Weapon[18];

    public Weapon beam= new Weapon(gp);

    public BufferedImage [] beam_sprite= new BufferedImage[8];

    int spark_count=0;

    int spark_at_once=0;
    int blast_at_once=0;

    String activity="idle";

    boolean ray_shooted=false;


    final int default_x=700, default_y=480;
    String action="idle";

    int activity_delay=120;
    public int hit_delay;

    public boolean slashed=false;


    public Knight(GamePanel gp) {
        super(gp);

        solidArea = new Rectangle(149, 71, 261-149, 343-71);
        //    player_bullet = new Weapon[10];


        //player width is 96 px which is equal to gp.tileSize(48) *2 =96


        setDefaultValues();
        getEnemyImage();
        getDeathAnimationImage();
        getWeaponImage();

        for(int i=0; i<10; i++)
        {
            spark[i]= new Weapon(gp);
            spark[i].solidArea=new Rectangle();
        }

        for(int i=0; i<18; i++)
        {
            blast[i]= new Weapon(gp);
            blast[i].solidArea=new Rectangle();
        }

        for(int i=0; i<30; i++)
        {
            ray[i]= new Weapon(gp);
            ray[i].solidArea=new Rectangle();
        }

    }

    public void getWeaponImage()
    {
        try {



            spark0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/spark/0.png"));
            spark1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/spark/1.png"));
            spark2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/spark/2.png"));
            spark3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/spark/3.png"));
            spark4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/spark/4.png"));
            spark5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/spark/5.png"));
            spark6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/spark/6.png"));
            spark7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/spark/7.png"));
            spark8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/spark/8.png"));

            blast0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/blast/0.png"));
            blast1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/blast/1.png"));
            blast2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/blast/2.png"));
            blast3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/blast/3.png"));
            blast4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/blast/4.png"));
            blast5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/blast/5.png"));
            blast6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/blast/6.png"));
            blast7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/blast/7.png"));
            blast8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/blast/8.png"));
            blast9 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/blast/9.png"));



            for (int i = 0; i < 30; i++) {
                left_ray[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/ray/" + i + ".png"));
            }

            for (int i = 0; i < 30; i++) {
                right_ray[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/ray/right/"+ i+" - Copy.png"));
            }

            for(int i=0; i<8; i++)
            {
                beam_sprite[i]=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/fx/beam/"+i+".png"));
            }







        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void getEnemyImage() {
        try {

            left0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/idle/0.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/idle/1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/idle/2.png"));
            left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/idle/3.png"));

            other0= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/attack/other/0.png"));
            other1= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/attack/other/1.png"));

            hyper0= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/attack/hyper/0.png"));
            hyper1= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/attack/hyper/1.png"));


            boss_health_bar= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/Boss Health Bar.png"));
            title= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/title.png"));

            for(int i=0; i<8; i++)
                teleport[i]= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/teleport/"+i+".png"));

            for(int i=0; i<5; i++)
                slash_left[i]= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/attack/slash/left-hand/"+i+".png"));
            for(int i=0; i<5; i++)
                slash_right[i]= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Enemy/knight/attack/slash/right-hand/"+i+".png"));

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

    public void setDefaultValues() {

        speed = 3;
        direction = "left";
        move_direction = "left";

        max_hit_delay = 30;
        height = 420;
        width = 420;
        can_shoot = false;


        max_health = 100;
        current_health = max_health;
        lifetime_max_charge = 10;
        hit_damage=30;


    }

    public void create_spark()
    {
        if(spark_count<9)
        {
            spark[spark_count].visibility=true;
            spark[spark_count].x=gp.player.x-48;
            spark[spark_count].y=gp.player.y-60;
            spark[spark_count].spriteNum=0;
            spark[spark_count].spriteCounter=0;

            spark_count++;
        }
    }



    public void change_sparks()
    {
        for(int i=0; i<spark_count; i++)
        {
            if(spark[i].spriteNum<2)
            spark[i].spriteCounter=(++spark[i].spriteCounter)%12;

                spark[i].spriteCounter=(++spark[i].spriteCounter)%5;

            if(spark[i].spriteCounter==0)
            {
                if(spark[i].spriteNum>=0 && spark[i].spriteNum<=7)
                    spark[i].spriteNum++;
                else
                {
                    spark[i].visibility=false;

                }
            }
        }


        int false_count = 0;


        for (int i = 0; i < spark_count; i++) {
            if (spark[i].visibility == false) { // found a false value
                false_count++; // increment the count of false values found
            } else if (false_count > 0) { // shift the array to the left
                spark[i - false_count].copy_weapon(spark[i]);
            }
        }

        spark_count -= false_count;

        for (int i = spark_count + 1; i < 10; i++)
            spark[i].visibility = false;
    }

    public void create_blast()
    {

        for(int i=0; i<3; i++)
        {
            for(int j=0; j<6; j++)
            {
                blast[i*6+j].visibility=true;
                blast[i*6+j].spriteNum=0;
                blast[i*6+j].spriteCounter=0;

                Random random = new Random();

                int random_x = random.nextInt(200);

                blast[i*6+j].x= -30+ j*250+random_x;


                int random_y = random.nextInt(100);

                blast[i*6+j].y= 80+i*210+random_y;


            }
        }
    }

    public void change_blast()
    {
        if(blast[0].visibility==true)
        {
            for(int i=0; i<18; i++)
            {
                if(blast[i].spriteNum<2)
                blast[i].spriteCounter=(++blast[i].spriteCounter)%6;
                else
                    blast[i].spriteCounter=(++blast[i].spriteCounter)%3;


                if(blast[i].spriteCounter==0)
                {
                    if(blast[i].spriteNum>=0 && blast[i].spriteNum<=8)
                        blast[i].spriteNum++;
                    else
                    {
                        blast[i].spriteNum=0;
                        blast[i].visibility=false;
                    }
                }
            }
        }
    }

    public void create_ray()
    {
        Random random = new Random();

        for(int i=0; i<30; i++)
        {

            int rand_speed= random.nextInt(2);

            ray[i].speed= rand_speed+10;

            ray[i].visibility=true;

            int direction = random.nextInt(2);

            if(direction==1)
            {
                ray[i].direction="right";
                ray[i].direction="right";
                ray[i].spriteNum=0;
                ray[i].spriteCounter=0;



                int y_val= random.nextInt(600);

                ray[i].y=150+y_val;

                int x_val= random.nextInt(500);

                ray[i].x=-500+x_val;

            }
            else
            {
                ray[i].direction="left";
                ray[i].direction="left";
                ray[i].spriteNum=0;
                ray[i].spriteCounter=0;

                int y_val= random.nextInt(600);

                ray[i].y=y_val;

                int x_val= random.nextInt(500);

                ray[i].x=gp.screenWidth+x_val;
            }
        }
    }

    public void move_ray()
    {

        for(int i=0; i<30; i++)
        {
            if(ray[i].visibility==true)
            {
                if(ray[i].direction=="left")
                    ray[i].x-=ray[i].speed;
                else
                    ray[i].x+=ray[i].speed;
            }
        }
    }

    public void change_ray()
    {
        for(int i=0; i<30; i++)
        {
            if(ray[i].visibility==true)
            {
                ray[i].spriteCounter=(++ray[i].spriteCounter)%2;

                if(ray[i].spriteCounter==0)
                {
                    if(ray[i].spriteNum>=0 && ray[i].spriteNum<=28)
                        ray[i].spriteNum++;
                    else
                        ray[i].spriteNum=0;
                }

                if(ray[i].direction=="left" && ray[i].x<0)
                    ray[i].visibility=false;
                else if(ray[i].direction=="true" && ray[i].x>gp.screenHeight)
                    ray[i].visibility=false;



            }
        }
    }

    public void create_beam()
    {
        if(beam.visibility==false)
        {
            beam.x=gp.player.x-120;
            beam.y=0;
            beam.height=824;
            beam.width=438;
            beam.spriteNum=0;
            beam.spriteCounter=0;
            beam.visibility=true;
            beam.lifetime=0;
        }

    }

    public void change_beam()
    {
        if(beam.visibility==true)
        {
            if(beam.spriteNum<=1)
            beam.spriteCounter=(++beam.spriteCounter)%12;
            else
                beam.spriteCounter=(++beam.spriteCounter)%8;
            beam.lifetime++;

            if(beam.spriteCounter==0)
            {
                if(beam.spriteNum>=0 && beam.spriteNum<=6)
                    beam.spriteNum++;
                else
                {
                    beam.visibility=false;
                    beam.spriteNum=0;
                }
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

        //it is opposite, left slash is actually right

        if(activity=="right_slash") {
            slash_area.x = x +75;
            slash_area.y = y + 166;
            slash_area.height =364-166 ;
            slash_area.width = 180-75;
        }
        else if(activity=="left_slash")
        {
            slash_area.x = x +202;
            slash_area.y = y + 166;
            slash_area.height = 364-166;
            slash_area.width = 330-202;
        }
        if(gp.player.can_get_hit<=0 && spriteNum==3 &&   player.intersects(slash_area))
        {
            if(gp.player.damagable==true)
                gp.player.current_health -=50;



            if(gp.player.damagable==true)
                gp.player.can_get_hit= gp.player.max_hit_delay;






        }
    }

    public void check_spark_damage()
    {
        for(int i=0; i<18; i++)
        {
            if(blast[i].visibility==true)
            {



                Rectangle player = new Rectangle();

                player.x = gp.player.x + gp.player.solidArea.x;
                player.y = gp.player.y + gp.player.solidArea.y;
                player.height = gp.player.solidArea.height;
                player.width = gp.player.solidArea.width;


                Rectangle spark_area = new Rectangle();




                spark_area.x = blast[i].x +105;
                spark_area.y = blast[i].y+ 107;
                spark_area.height =194-107;
                spark_area.width = 190-105;

                if(gp.player.can_get_hit<=0  && blast[i].spriteNum>=4 &&  player.intersects(spark_area))
                {
                    if(gp.player.damagable==true)
                        gp.player.current_health -=15;



                    if(gp.player.damagable==true)
                        gp.player.can_get_hit= gp.player.max_hit_delay;






                }

            }
        }
    }

    public void check_beam_damage()
    {
        Rectangle player = new Rectangle();

        player.x = gp.player.x + gp.player.solidArea.x;
        player.y = gp.player.y + gp.player.solidArea.y;
        player.height = gp.player.solidArea.height;
        player.width = gp.player.solidArea.width;


        Rectangle beam_area = new Rectangle();

        //it is opposite, left slash is actually right


            beam_area.x = beam.x +141;
            beam_area.y = 0;
            beam_area.height =beam.height;
            beam_area.width = 299-141;

        if(gp.player.can_get_hit<=0  &&   player.intersects(beam_area))
        {
            if(gp.player.damagable==true)
                gp.player.current_health -=30;



            if(gp.player.damagable==true)
                gp.player.can_get_hit= gp.player.max_hit_delay;






        }
    }

    public void check_ray_damage()
    {
        for(int i=0; i<30; i++)
        {
            if(ray[i].visibility==true)
            {



                Rectangle player = new Rectangle();

                player.x = gp.player.x + gp.player.solidArea.x;
                player.y = gp.player.y + gp.player.solidArea.y;
                player.height = gp.player.solidArea.height;
                player.width = gp.player.solidArea.width;


                Rectangle ray_area = new Rectangle();

                //it is opposite, left slash is actually right


                ray_area.x = ray[i].x +28;
                ray_area.y = ray[i].y+ 37;
                ray_area.height =59-37;
                ray_area.width = 56-28;

                if(gp.player.can_get_hit<=0  &&   player.intersects(ray_area))
                {
                    if(gp.player.damagable==true)
                        gp.player.current_health -=10;



                    if(gp.player.damagable==true)
                        gp.player.can_get_hit= gp.player.max_hit_delay;






                }

            }
        }
    }

    public void check_blast_damage()
    {
        for(int i=0; i<10; i++)
        {
            if(spark[i].visibility==true)
            {



                Rectangle player = new Rectangle();

                player.x = gp.player.x + gp.player.solidArea.x;
                player.y = gp.player.y + gp.player.solidArea.y;
                player.height = gp.player.solidArea.height;
                player.width = gp.player.solidArea.width;


                Rectangle blast_area = new Rectangle();




                blast_area.x = spark[i].x +47;
                blast_area.y = spark[i].y+ 47;
                blast_area.height =148-47;
                blast_area.width = 148-47;

                if(gp.player.can_get_hit<=0  && spark[i].spriteNum>=5 &&  player.intersects(blast_area))
                {
                    if(gp.player.damagable==true)
                        gp.player.current_health -=13;



                    if(gp.player.damagable==true)
                        gp.player.can_get_hit= gp.player.max_hit_delay;






                }

            }
        }
    }

    public void teleport()
    {
        if(gp.player.x<gp.screenWidth/2-100)
        {
            if(slashed==false) {
                x = gp.player.x -20;
                y = gp.player.y - 220;
                action="right_slash";
            }
            else
            {
                x=default_x;
                y=default_y;
                action="idle";
            }

        }
        else
        {
            if(slashed==false) {
                x = gp.player.x - 280;

                y = gp.player.y - 220;
                action="left_slash";
            }
            else
            {
                x=default_x;
                y=default_y;
                action="idle";
            }

        }
    }

    public void do_action() {

        change_sparks();
        change_blast();
        change_ray();
        move_ray();
        change_beam();

        if( (activity=="left_slash" || activity=="right_slash") && spriteNum==3 )
        check_slash_damage();

        if(beam.visibility==true && beam.spriteNum>=2 )
            check_beam_damage();

        check_ray_damage();
        check_blast_damage();
        check_spark_damage();



        if(activity_delay<=0 )
        {
            Random random = new Random();

            int randomNumber = random.nextInt(5);

            if(randomNumber==0)
            {
                activity="spark";
                action="spark";
                activity_delay=390;
                spriteNum=0;
            }
            else if(randomNumber==1)
            {
                activity="blast";
                activity_delay=480;
                spriteNum=0;
            }
            else if(randomNumber==2)
            {
                activity="ray";
                activity_delay=250;
                spriteNum=0;
            }
            else if(randomNumber==3)
            {
                activity="beam";
                activity_delay=350;
                spriteNum=0;
            }
            else if(randomNumber==4)
            {
                activity="teleport";
                activity_delay=150;
                spriteNum=0;
            }

        }
        else
            activity_delay--;



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
        else if(activity=="spark" )
        {
            spriteCounter = (++spriteCounter) % 18;

            if(spriteCounter==0)
            {
                if (spriteNum == 0)
                    spriteNum = 1;
                else if(spriteNum==1)
                {
                    if(spark_at_once<=5)
                    {
                        create_spark();
                        spark_at_once++;
                        spriteNum=2;
                    }
                    else
                    {
                        activity="idle";
                        spriteNum=0;
                        spark_at_once=0;
                    }
                }
                else if(spriteNum==2)
                    spriteNum=0;
            }



        }
        else if(activity=="blast")
        {
            if(spriteNum<2)
            spriteCounter = (++spriteCounter) % 16;
            else
                spriteCounter = (++spriteCounter) % 30;

            if(spriteCounter==0)
            {
                if(spriteNum==0)
                    spriteNum=1;
                else if(spriteNum==1)
                {
                    if(blast_at_once<=6) {
                        create_blast();
                        blast_at_once++;
                        spriteNum = 2;
                    }
                    else
                    {
                        activity="idle";
                        spriteNum=0;
                        blast_at_once=0;

                    }
                    }
                else if(spriteNum==2)
                {

                    spriteNum=0;
                }
            }

        }
        else if(activity=="ray")
        {
            if(spriteNum<1)
                spriteCounter = (++spriteCounter) % 10;
            else
                spriteCounter = (++spriteCounter) % 16;

            if(spriteCounter==0)
            {
                if(spriteNum==0)
                    spriteNum=1;
                else if(spriteNum==1)
                {

                        create_ray();



                        activity="idle";

                    spriteNum=0;

                }
            }
        }
        else if(activity=="beam")
        {

                spriteCounter = (++spriteCounter) % 12;

            if(spriteCounter==0)
            {
                if(spriteNum==0)
                    spriteNum=1;
                else if(spriteNum==1)
                {

                    if(activity_delay<200)
                    create_beam();



                    if(activity_delay<50)
                    activity="idle";

                    spriteNum=0;

                }
            }
        }
        else if(activity=="teleport")
        {
            if(slashed==false)
            spriteCounter = (++spriteCounter) % 3;
    else
        spriteCounter = (++spriteCounter) % 6;

            if(spriteCounter==0)
            {
                if(spriteNum<=2)
                    spriteNum++;
                else if(spriteNum==3)
                {
                    teleport();
                    spriteNum++;
                }
                else if(spriteNum>=4 && spriteNum<=6)
                    spriteNum++;
                else
                {
                    if(slashed==false) {
                        if(action=="left_slash")
                            activity="left_slash";
                        else
                            activity="right_slash";

                        slashed=true;

                        spriteNum = 0;

                    }
                    else
                    {
                        activity="idle";
                        slashed=false;
                    spriteNum=0;
                    }
                    }
            }
        }
        else if(activity=="left_slash" || activity=="right_slash")
        {

            spriteCounter = (++spriteCounter) % 8;

            if(spriteCounter==0)
            {
                if(spriteNum<=3)
                    spriteNum++;


                else
                {
                   activity="teleport";

                        spriteNum = 0;

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
            gp.tileM.mapTileNum[gp.currentMap][x/gp.tileSize][y/gp.tileSize+1]=25;
            gp.tileM.mapTileNum[gp.currentMap][x/gp.tileSize+1][y/gp.tileSize+1]=25;
            gp.tileM.mapTileNum[gp.currentMap][x/gp.tileSize+1][y/gp.tileSize+2]=25;
            gp.tileM.mapTileNum[gp.currentMap][x/gp.tileSize][y/gp.tileSize+2]=25;
        }

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

        }
        else if(activity=="spark" || activity=="blast" || activity=="ray")
        {
            if(spriteNum==0)
                current_image=other0;
            else if(spriteNum==1)
                current_image=other1;
            else if(spriteNum==2)
                current_image=left0;
        }
        else if(activity=="beam")
        {
            if(spriteNum==0)
                current_image=hyper0;
            else
                current_image=hyper1;
        }
        else if(activity=="teleport")
        {
            current_image=teleport[spriteNum];
        }
        else if(activity=="left_slash")
        {
            current_image=slash_left[spriteNum];
        }
        else if(activity=="right_slash")
        {
            current_image=slash_right[spriteNum];
        }







        if(dying_animation<2)
        {

            if ( can_get_hit % 2 == 0)
                g2.drawImage(current_image, x, y, width, height, null);




        }



//DRAW PLAYER BULLET


        //------------------------------------------------
        //DRAW LIGHTNING
        //--------------------------------------------------



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

        g2.drawImage(title, 770, gp.tileSize*2-38, 200, 46, null);

        g2.drawImage(boss_health_bar, 510, gp.tileSize*2, 700, 69, null );

        if(last_hit_time>0) {
            //just change following values for adjustment

        }


        //---------------------
        ///DRAW SPARK
        //---------------------





        for (int i = 0; i < spark_count; i++) {

            if (spark[i].visibility == true) {

                if (spark[i].spriteNum == 0)
                    image = spark0;
                else if (spark[i].spriteNum == 1)
                    image = spark1;
                else if (spark[i].spriteNum == 2)
                    image = spark2;
                else if (spark[i].spriteNum == 3)
                    image = spark3;
                else if (spark[i].spriteNum == 4)
                    image = spark4;
                else if (spark[i].spriteNum == 5)
                    image = spark5;
                else if (spark[i].spriteNum == 6)
                    image = spark6;
                else if (spark[i].spriteNum == 7)
                    image = spark7;
                else if (spark[i].spriteNum == 8)
                    image = spark8;
                else
                    image=null;







                g2.drawImage(image, spark[i].x, spark[i].y, gp.tileSize*4, gp.tileSize*4, null);


            }

        }



        //---------------------
        ///DRAW BLAST
        //---------------------

        if(blast[0].visibility==true)
        {
            for(int i=0; i<18; i++)
            {
                if (blast[i].spriteNum == 0)
                    image = blast0;
                else if (blast[i].spriteNum == 1)
                    image = blast1;
                else if (blast[i].spriteNum == 2)
                    image = blast2;
                else if (blast[i].spriteNum == 3)
                    image = blast3;
                else if (blast[i].spriteNum == 4)
                    image = blast4;
                else if (blast[i].spriteNum == 5)
                    image = blast5;
                else if (blast[i].spriteNum == 6)
                    image = blast6;
                else if (blast[i].spriteNum == 7)
                    image = blast7;
                else if (blast[i].spriteNum == 8)
                    image = blast8;
                else if (blast[i].spriteNum == 9)
                    image = blast9;

                g2.drawImage(image, blast[i].x, blast[i].y, 300, 300, null);
            }
        }

        //---------------------
        ///DRAW RAY
        //---------------------

        for(int i=0; i<30; i++)
        {
            if(ray[i].visibility==true)
            {
                if(ray[i].direction=="left")
                    image=left_ray[ray[i].spriteNum];
                else
                    image=right_ray[ray[i].spriteNum];

                g2.drawImage(image, ray[i].x, ray[i].y, 100, 100, null);

            }
        }

        //---------------------
        ///DRAW BEAM
        //---------------------

        if(beam.visibility==true)
        {
            image=beam_sprite[beam.spriteNum];

            g2.drawImage(image, beam.x, beam.y, beam.width, beam.height, null);
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
