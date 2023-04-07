package Entity;

import MainPackage.GamePanel;
import MainPackage.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;


public class Player extends Entity {

    KeyHandler keyH;




    int shoot_delay=0;



    public int coin_count=0, health_potion_count=0, boost_potion_count=0;

    public int player_max_transformation, player_current_transformation;



    public String transformation_state;

    public int dash_count=0;

    public int transform_potion_buff, transform_potion_duration, max_transform_potion_duration;

    public int transformation_animation=0;

    public int fall_height=0, final_fall_height=0;

    public int health_potion_buff;

    public int key_press_delay=0;

    int upgrade_level[]= new int[15];

    boolean  fly_collected=false;
    boolean  block_collected=false;
    boolean dash_collected=false;





    public BufferedImage fly_left_1, fly_right_1, fly_left_2, fly_right_2, fly_left_3, fly_right_3, fly_left_4, fly_right_4 ;
    public BufferedImage left_shoot, right_shoot, left_death, right_death;

    public BufferedImage left_block, right_block;

    public BufferedImage left_dash, right_dash;

    public int bomb_radius=0, bomb_power=0;

    public int max_shoot_delay=50;

    int fall_factor=0;

    public BufferedImage ani0, ani1, ani2, ani3, ani4, ani5, ani6, ani7, ani8, ani9, ani10, ani11;

    public int player_default_start_x[];
    public int player_default_start_y[];
    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);


        this.keyH = keyH;
        solidArea = new Rectangle(22,15,gp.tileSize*2-44, gp.tileSize*2-28);

        player_default_start_x = new int[gp.maxMap];
        player_default_start_y = new int[gp.maxMap];


        //player width is 96 px which is equal to gp.tileSize(48) *2 =96

        set_start_x_y();
        setDefaultValues();
        getPlayerImage();
        getAnimationImage();
    }





    public void setDefaultValues() {

        //it is important to initialize current map in player class constructor rather than gamepanel constructor

//        try
//        {
//            gp.currentMap=gp.db.getSavedGame();
//        }






        set_default_x_y();

        spriteNum=0;

        speed = 4;
        direction = "right";
        move_direction="right";
        transformation_state="normal";
        max_hit_delay=50;

       coin_count=100;
        health_potion_count=0;
       boost_potion_count=0;

       set_upgrade_level();



        try
        {
            int arr[]=gp.db.getPlayer(gp);

            current_health=arr[0];
            coin_count=arr[1];
            health_potion_count=arr[2];
            boost_potion_count=arr[3];

            int arr2[]=gp.db.getUpgrade(gp);

            for(int i=0; i<15; i++)
                upgrade_level[i]=arr2[i];





        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }



        max_health =30;


        if(gp.currentMap<=3)
        player_max_transformation=0;
        else
            player_max_transformation=300;
        player_current_transformation=player_max_transformation;
        hit_damage=5;
        health_potion_buff=20;

        transform_potion_buff=100;
        transform_potion_duration=0;
        max_transform_potion_duration=500;

        lifetime_max_charge=10;

        setPlayerBulletValues();

        set_upgrade_level();
        change_upgrade_level();



        current_health=max_health;

    }

    public void set_upgrade_level()
    {
        for(int i=0; i<15; i++)
            gp.upgradeScreen.icon[i].current_level=upgrade_level[i];
    }

    public void change_upgrade_level()
    {
        for(int i=0; i<15; i++)
            upgrade_level[i]= gp.upgradeScreen.icon[i].current_level;

        max_health= upgrade_level[0]*10+30;

        if(current_health>=max_health)
        current_health=max_health;

        speed=upgrade_level[1]+2;

        max_shoot_delay=50-upgrade_level[2]*7;

        for(int i=0; i<lifetime_max_charge; i++)
            bullet[i].speed=4+upgrade_level[3];

        health_potion_buff=20+upgrade_level[4]*10;
        transform_potion_buff=100+upgrade_level[5]*100;

        if(gp.currentMap<=3)
            player_max_transformation=0;
        else
        player_max_transformation=300+upgrade_level[6]*100;
player_current_transformation=player_max_transformation;
        hit_damage= 5*upgrade_level[7]+5;


        for(int i=0; i<lifetime_max_charge; i++)
            bullet[i].power=5+upgrade_level[8]*2;

        bomb_power=0;
        bomb_radius=0;

        max_transform_potion_duration=upgrade_level[10]*200+500;

        fall_factor=upgrade_level[12]*10;


        max_hit_delay=50+upgrade_level[13]*10;






    }

    public void set_start_x_y()
    {
        player_default_start_x[0]=40;
        player_default_start_y[0]=630;

        player_default_start_x[1]=40;
        player_default_start_y[1]=630;
        player_default_start_x[2]=40;
        player_default_start_y[2]=630;
        player_default_start_x[3]=40;
        player_default_start_y[3]=630;

        player_default_start_x[4]=40;
        player_default_start_y[4]=630;

        player_default_start_x[5]=40;
        player_default_start_y[5]=490;

        player_default_start_x[6]=40;
        player_default_start_y[6]=730;

        player_default_start_x[7]=40;
        player_default_start_y[7]=630+48;

        player_default_start_x[9]=40;
        player_default_start_y[9]=630+48;

        player_default_start_x[10]=40;
        player_default_start_y[10]=630+48;

        player_default_start_x[8]=40;
        player_default_start_y[8]=290;
    }

    public void set_default_x_y()
    {
        x=player_default_start_x[gp.currentMap];
        y=player_default_start_y[gp.currentMap];

    }


    public void getPlayerImage() {
        try {
            left0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/idle/left.png"));
            right0 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/idle/right.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/run/left/1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/run/left/2.png"));
            left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/run/left/3.png"));
            left4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/run/left/4.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/run/right/1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/run/right/2.png"));
            right3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/run/right/3.png"));
            right4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/run/right/4.png"));

            left_shoot = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/attack/left.png"));
            right_shoot = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/attack/right.png"));


            fly_left_1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/transformation/fly/left/0.png"));
            fly_left_2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/transformation/fly/left/1.png"));
            fly_left_3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/transformation/fly/left/2.png"));
            fly_left_4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/transformation/fly/left/3.png"));
            fly_right_1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/transformation/fly/right/0.png"));
            fly_right_2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/transformation/fly/right/1.png"));
            fly_right_3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/transformation/fly/right/2.png"));
            fly_right_4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/transformation/fly/right/3.png"));

            left_death= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/die/left.png"));
            right_death =ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/die/right.png"));

            left_block= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/transformation/block/left.png"));
            right_block =ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/transformation/block/right.png"));

            left_dash= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/transformation/dash/left.png"));
            right_dash= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/transformation/dash/right.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAnimationImage()
    {
        try{
            ani0 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Transformation_animation/Smoke VFX B0.png"));
            ani1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Transformation_animation/Smoke VFX B1.png"));
            ani2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Transformation_animation/Smoke VFX B2.png"));
            ani3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Transformation_animation/Smoke VFX B3.png"));
            ani4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Transformation_animation/Smoke VFX B4.png"));
            ani5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Transformation_animation/Smoke VFX B5.png"));
            ani6 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Transformation_animation/Smoke VFX B6.png"));
            ani7 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Transformation_animation/Smoke VFX B7.png"));
            ani8 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Transformation_animation/Smoke VFX B8.png"));
            ani9 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Transformation_animation/Smoke VFX B9.png"));
            ani10 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Transformation_animation/Smoke VFX B10.png"));
            ani11 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Transformation_animation/Smoke VFX B11.png"));



        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setPlayerBulletdefaultValues(int lifetime_max_charge)
    {


       

        for(int i=0; i<lifetime_max_charge; i++)
            bullet[i].power=5;

        for(int i=0; i<lifetime_max_charge; i++)
            bullet[i].speed=4;


        for(int i=0; i<lifetime_max_charge; i++)
        {
            bullet[i].solidArea= new Rectangle(20,20,gp.tileSize*2-40, gp.tileSize*2-40);

        }



    }

    public void player_fall_below()
    {
        for(int i=0; i<2; i++)
        {
            move_direction="down";

            int fall_speed=4;

            y+=fall_speed;

            collisonOn=false;

            gp.cChecker.checkTile(this);

            if(collisonOn==true)
                y-=fall_speed;





            //it needs to be inside the for loop

        }

    }

    public void setPlayerBulletValues()
    {




        try{
            for(int i=0; i<lifetime_max_charge; i++)
                bullet[i]= new Weapon(gp);

            for(int i=0; i<lifetime_max_charge; i++)
                bullet[i].left1=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/weapon/shuriken/left/0.png"));

            for(int i=0; i<lifetime_max_charge; i++)
                bullet[i].left2=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/weapon/shuriken/left/1.png"));

            for(int i=0; i<lifetime_max_charge; i++)
                bullet[i].right1=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/weapon/shuriken/right/0.png"));

            for(int i=0; i<lifetime_max_charge; i++)
                bullet[i].right2=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/weapon/shuriken/right/1.png"));


        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        setPlayerBulletdefaultValues(lifetime_max_charge);

    }



    public void shoot_player_bullet()
    {
        if(current_visible_bullet <10)
        {
            if (direction == "right") {
                bullet[current_visible_bullet].direction = "right";
                bullet[current_visible_bullet].move_direction = "right";
                bullet[current_visible_bullet].visibility = true;
                bullet[current_visible_bullet].x = x + gp.tileSize;
                bullet[current_visible_bullet].y = y;
            } else if (direction == "left") {
                bullet[current_visible_bullet].direction = "left";
                bullet[current_visible_bullet].move_direction = "left";
                bullet[current_visible_bullet].visibility = true;
                bullet[current_visible_bullet].x = x - gp.tileSize;
                bullet[current_visible_bullet].y = y;
            }


            current_visible_bullet++;
        }
    }

    public void move_player_bullet()
    {

        for(int i = 0; i< current_visible_bullet; i++)
        {
            if(bullet[i].visibility==true)
            {
                if(bullet[i].direction=="left")
                    bullet[i].x-= bullet[i].speed;
                else if(bullet[i].direction=="right")
                    bullet[i].x+= bullet[i].speed;
            }

        }
    }

    public void check_player_bullet_collison_with_wall()
    {

        for(int i = 0; i< current_visible_bullet; i++ )
        {
            bullet[i].collisonOn=false;



            gp.cChecker.checkTile(bullet[i], " ");

            if(bullet[i].collisonOn==true)
                bullet[i].visibility=false;

            if(bullet[i].x<0 || bullet[i].y<0 || bullet[i].x>gp.screenWidth-gp.tileSize*2 || bullet[i].y>gp.screenHeight-gp.tileSize*2)
                bullet[i].visibility=false;

        }

        int false_count=0;





        for(int i = 0; i< current_visible_bullet; i++)
        {
            if (bullet[i].visibility==false ) { // found a false value
                false_count++; // increment the count of false values found
            } else if (false_count > 0) { // shift the array to the left
                bullet[i - false_count].copy_weapon(bullet[i]);
            }
        }

        current_visible_bullet -=false_count;


        for(int i = current_visible_bullet +1; i<10; i++)
            bullet[i].visibility=false;


    }



    public void update() {





        if (gp.game_state != gp.gameOverState) {
            check_player_bullet_collison_with_wall();

            move_player_bullet();


            if(transformation_state=="block" || transformation_state=="dash")
                damagable=false;
            else
                damagable=true;


            if (transformation_state == "normal")
                normal_state_logic();
            else if (transformation_state == "fly")
                fly_state_logic();
            else if(transformation_state=="block")
                block_state_logic();
            else if(transformation_state=="dash")
                dash_state_logic();

            if(key_press_delay>0)
                key_press_delay--;




            //------CHECK OBJECT COLLISON------//

            int tileNum = gp.cChecker.checkObject(this);

            if (tileNum >= 20)
                pickObject(tileNum);

            boolean is_lazer_hit = false;

            if (can_get_hit == 0)
                is_lazer_hit = gp.cChecker.check_player_collison_with_lazer(this);

            if (is_lazer_hit) {
                can_get_hit = max_hit_delay;
                current_health -= 10;
            }
            check_map_transition();

            if (can_get_hit > 0)
                can_get_hit--;

            if (current_health <= 0) {
                gp.game_state = gp.gameOverState;

                if (transformation_state != "normal") {
                    transformation_state = "normal";
                    transformation_animation = 2;
                    can_get_hit = 0;
                }

                spriteNum = 6;
            }
        }
        else
        {
            spriteNum=6;
            can_get_hit=0;
            transformation_state="normal";
            transformation_animation=0;

        }

    }

    public void dash_state_logic()
    {

        if(direction=="left")
        {
            for(int i=0; i<2; i++)
            {
                if(x > speed) {
                    x -= 7;

                    boolean is_obstacle = gp.cChecker.dash_state_collison(this);

                    if ( is_obstacle == true)
                        x += 7;

                }


            }
        }
        else if(direction=="right")
        {
            for(int i=0; i<2; i++)
            {
                if( x < gp.screenWidth - gp.tileSize * 2 - speed) {
                    x += 7;




                    boolean is_obstacle = gp.cChecker.dash_state_collison(this);

                    if (is_obstacle == true)
                        x -= 7;


                }

            }
        }

        if ((keyH.onePressed &&  transformation_animation == 0 && key_press_delay==0)) {
            transformation_state = "normal";
            transformation_animation = 1;
            spriteNum = 0;

            key_press_delay=15;


        }

        else if (keyH.twoPressed && gp.currentMap>=4 && transformation_animation == 0 && player_current_transformation > 80 && key_press_delay==0) {
            transformation_state = "fly";
            transformation_animation = 1;
            spriteNum = 1;
            final_fall_height = 0;
            fall_height = 0;
            key_press_delay=15;


        }
        else if (keyH.threePressed && gp.currentMap>=7 && transformation_animation == 0 && player_current_transformation > 80 && key_press_delay==0) {
            transformation_state = "block";
            transformation_animation = 1;
            damagable=false;
            spriteNum = 1;
            final_fall_height = 0;
            fall_height = 0;
            key_press_delay=15;


        }

        dash_count++;

        if ( dash_count>=20 || player_current_transformation <= 0 || current_health <= 0) {
            transformation_state = "normal";
            transformation_animation = 1;
            spriteNum = 0;
            dash_count=0;

        }

        player_current_transformation-=8;


    }

    public void block_state_logic()
    {


        if ((keyH.onePressed && transformation_animation == 0 && key_press_delay==0)) {
            transformation_state = "normal";
            transformation_animation = 1;
            spriteNum = 0;

            key_press_delay=15;
            damagable=true;


        }
        else if (keyH.twoPressed && (gp.currentMap>=4 || fly_collected==true) && transformation_animation == 0 && player_current_transformation > 80 && key_press_delay==0) {
            transformation_state = "fly";
            transformation_animation = 1;
            spriteNum = 1;
            final_fall_height = 0;
            fall_height = 0;
            key_press_delay=15;
            damagable=true;


        }
        else if (keyH.fourPressed && (gp.currentMap>=9 || dash_collected==true) && transformation_animation == 0 && player_current_transformation > 80 && key_press_delay==0) {
            transformation_state = "dash";
            transformation_animation = 1;
            spriteNum = 1;
            final_fall_height = 0;
            fall_height = 0;
            key_press_delay=15;
            damagable=false;


        }


        for (int i = 0; i < 2; i++) {
            move_direction = "down";

            int fall_speed = 4;

            y += fall_speed;

            collisonOn = false;

            gp.cChecker.checkTile(this);
            boolean is_obstacle= gp.cChecker.player_obstacle_collison_check();

            if (collisonOn == true || is_obstacle==true) {
                y -= fall_speed;






            }

            //it needs to be inside the for loop



            }


        if (player_current_transformation <= 0 || current_health <= 0) {
            transformation_state = "normal";
            transformation_animation = 1;
            spriteNum = 0;

        }

        //Do that will always be done


        //if not in other state, increase transformation duration
        player_current_transformation-=2;
    }

    public void normal_state_logic()
    {
        if (keyH.leftPressed == true || keyH.rightPressed == true) {

            if (keyH.leftPressed == true && x > speed) {
                direction = "left";
                move_direction = "left";

                if(spriteNum==5)
                    spriteNum=1;

                x -= speed;
            } else if (keyH.rightPressed == true && x < gp.screenWidth - gp.tileSize * 2 - speed) {
                direction = "right";
                move_direction = "right";

                x += speed;

                if(spriteNum==5)
                    spriteNum=1;
            }


            spriteCounter = (++spriteCounter) % 18;
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
                    spriteNum = 1;

                }


            }

            collisonOn = false;

            gp.cChecker.checkTile(this);
            boolean is_obstacle= gp.cChecker.player_obstacle_collison_check();

            if (collisonOn == true || is_obstacle==true) {
                switch (move_direction) {

                    case "left":
                        x += speed;
                        break;
                    case "right":
                        x -= speed;
                        break;
                }
            }


        } else if (keyH.twoPressed && (gp.currentMap>=4 || fly_collected==true) && transformation_animation == 0 && player_current_transformation > 80 && key_press_delay==0) {
            transformation_state = "fly";
            transformation_animation = 1;
            spriteNum = 1;
            final_fall_height = 0;
            fall_height = 0;
            key_press_delay=15;


        }
        else if (keyH.threePressed && (gp.currentMap>=7 || block_collected==true) &&   transformation_animation == 0 && player_current_transformation > 80 && key_press_delay==0) {
            transformation_state = "block";
            transformation_animation = 1;
            damagable=false;
            spriteNum = 1;
            final_fall_height = 0;
            fall_height = 0;
            key_press_delay=15;


        }
        else if (keyH.fourPressed && (gp.currentMap>=9 || dash_collected==true) && transformation_animation == 0 && player_current_transformation > 80 && key_press_delay==0) {
            transformation_state = "dash";
            transformation_animation = 1;
            spriteNum = 1;
            final_fall_height = 0;
            fall_height = 0;
            key_press_delay=15;
            damagable=false;


        }
        else if (keyH.spacePressed &&  shoot_delay == 0  && (gp.currentMap>=2 || gp.dialogue.level1_dialogue_state=="finished") ) {
            shoot_delay = max_shoot_delay;
            spriteNum = 5;
            shoot_player_bullet();
        } else if (keyH.H_Pressed && current_health != max_health && health_potion_count > 0) {
            current_health += health_potion_buff;
            if (current_health > max_health)
                current_health = max_health;


            if (health_potion_count > 0) ;
            health_potion_count--;
        } else if (keyH.T_Pressed && boost_potion_count > 0 && transform_potion_duration == 0) {
            player_max_transformation += transform_potion_buff;
            transform_potion_duration = max_transform_potion_duration;

            if(boost_potion_count>0)
                boost_potion_count--;
        } else {

            if (shoot_delay > 0)
                shoot_delay--;

            if(shoot_delay<=max_shoot_delay-15)
                spriteNum=0;
            //this condition is used to hold shoot animation for a while




        }


        //potion

        //Do steps that will always be done if even no key is pressed

        //if not in other state, increase transformation duration
        for (int i = 0; i < 2; i++)
            if (player_current_transformation < player_max_transformation)
                player_current_transformation++;

        if (transform_potion_duration > 0)
            transform_potion_duration--;

        //it is important to do this on 1, instead of 0

        if (transform_potion_duration == 1)
        {
            player_max_transformation-=transform_potion_buff;
            if(player_current_transformation >player_max_transformation)
                player_current_transformation=player_max_transformation;
        }

        //player always goes down

        for (int i = 0; i < 2; i++) {
            move_direction = "down";

            int fall_speed = 4;

            y += fall_speed;

            collisonOn = false;

            gp.cChecker.checkTile(this);
            boolean is_obstacle= gp.cChecker.player_obstacle_collison_check();

            if (collisonOn == true || is_obstacle==true) {
                y -= fall_speed;


                final_fall_height = fall_height;

                fall_height = 0;



            } else {
                fall_height++;
                //System.out.println(fall_height);

                if(fall_height>20)
                spriteNum = 0;

            }

            //it needs to be inside the for loop
            if (final_fall_height > 0) {


                if (final_fall_height < 65+fall_factor) {
                    //no damage
                } else {
                    can_get_hit = max_hit_delay;

                    if (final_fall_height < 90+fall_factor)
                        current_health -= 5;
                    else if (final_fall_height < 115+fall_factor)
                        current_health -= 20;
                    else
                        current_health = 0;
                }


                final_fall_height = 0;


            }
        }
    }

    public void fly_state_logic()
    {
        spriteCounter = (++spriteCounter) % 8;

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
                spriteNum = 1;
            }
        }

        if (keyH.leftPressed == true || keyH.rightPressed == true || keyH.upPressed || keyH.downPressed) {

            if (keyH.leftPressed == true && x > speed) {
                direction = "left";
                move_direction = "left";
                x -= speed;
            } else if (keyH.rightPressed == true && x < gp.screenWidth - gp.tileSize * 2 - speed) {
                direction = "right";
                move_direction = "right";
                x += speed;
            } else if (keyH.upPressed == true && y > speed) {
                move_direction = "up";
                y -= speed;
            } else if (keyH.downPressed == true && y < gp.screenHeight - gp.tileSize * 2 - speed) {
                move_direction = "down";
                y += speed;
            }


            collisonOn = false;

            gp.cChecker.checkTile(this);

            if (collisonOn == true) {
                switch (move_direction) {
                    case "up":
                        y += speed;
                        break;
                    case "down":
                        y -= speed;
                        break;
                    case "left":
                        x += speed;
                        break;
                    case "right":
                        x -= speed;
                        break;
                }
            }


        } else if ((keyH.onePressed &&  transformation_animation == 0 && key_press_delay==0)) {
            transformation_state = "normal";
            transformation_animation = 1;
            spriteNum = 0;

            key_press_delay=15;


        }

        else if (keyH.threePressed &&  (gp.currentMap>=7 || block_collected==true) && transformation_animation == 0 && player_current_transformation > 80 && key_press_delay==0) {
            transformation_state = "block";
            transformation_animation = 1;
            damagable=false;
            spriteNum = 1;
            final_fall_height = 0;
            fall_height = 0;
            key_press_delay=15;


        }
        else if (keyH.fourPressed && (gp.currentMap>=9 || dash_collected==true) && transformation_animation == 0 && player_current_transformation > 80 && key_press_delay==0) {
            transformation_state = "dash";
            transformation_animation = 1;
            spriteNum = 1;
            final_fall_height = 0;
            fall_height = 0;
            key_press_delay=15;
            damagable=false;


        }

        if (player_current_transformation <= 0 || current_health <= 0) {
            transformation_state = "normal";
            transformation_animation = 1;
            spriteNum = 0;

        }

        //Do that will always be done


        //if not in other state, increase transformation duration
        player_current_transformation--;

    }

    public void check_map_transition()
    {
        if(x >= gp.screenWidth - gp.tileSize * 3 - speed)
        {
            if(gp.currentMap<=2 || (gp.currentMap==3 && gp.enemy[3][0]==null) || gp.currentMap==4 || gp.currentMap==5 || (gp.currentMap==6 && gp.enemy[6][0]==null) || gp.currentMap==7 || (gp.currentMap==8 && gp.enemy[8][0]==null) || gp.currentMap==9)
            {


                gp.currentMap++;
                set_default_x_y();

                if(gp.game_state==gp.playState)
                {
                    gp.db.setSavedGame(gp);
                    gp.db.setPlayer(gp);
                    gp.db.setUpgrade(gp, upgrade_level);
                }
            }

            else if(gp.currentMap==10 && gp.enemy[10][0]==null)
            {

                gp.titleScreen.press_delay=60;
                gp.next_selected="title";

            }



//            gp.db.setPlayer(gp);
//            gp.db.setSavedGame(gp);
        }
    }
    public void pickObject(int i)
    {
        if(i==20)
            health_potion_count++;
        else if(i==21)
            boost_potion_count++;
        else if(i==24)
        {
            if(gp.currentMap==3)
            {
                player_max_transformation=300;
                fly_collected=true;
            }
            else if(gp.currentMap==6)
                block_collected=true;
            else if(gp.currentMap==8)
                dash_collected=true;
        }
        else if(i==25)
            coin_count++;
        else if(i==29)
            gp.tileM.open_door();



    }



    public void draw(Graphics2D g2) {




        BufferedImage image = null;

        //------------------------------------------------
        //DRAW PLAYER
        // --------------------------------------------------


         if(transformation_state=="normal")
        {
            if (direction == "left") {
                if(spriteNum==0)
                    image=left0;
                if (spriteNum == 1)
                    image = left1;
                if (spriteNum == 2)
                    image = left2;
                if (spriteNum == 3)
                    image = left3;
                if (spriteNum == 4)
                    image = left4;
                if(spriteNum==5)
                    image=left_shoot;
                if(spriteNum==6)
                    image=left_death;

            } else if (direction == "right") {
                if(spriteNum==0)
                    image=right0;
                if (spriteNum == 1)
                    image = right1;
                if (spriteNum == 2)
                    image = right2;
                if (spriteNum == 3)
                    image = right3;
                if (spriteNum == 4)
                    image = right4;
                if(spriteNum==5)
                    image=right_shoot;
                if(spriteNum==6)
                    image=right_death;
            }
        }
        else if(transformation_state=="fly")
        {
            if(direction=="left")
            {
                if(spriteNum==1)
                    image=fly_left_1;
                if(spriteNum==2)
                    image=fly_left_2;
                if(spriteNum==3)
                    image=fly_left_3;
                if(spriteNum==4)
                    image=fly_left_4;
                if(spriteNum==6)
                    image=left_death;

            }
            else if(direction=="right")
            {
                if(spriteNum==1)
                    image=fly_right_1;
                if(spriteNum==2)
                    image=fly_right_2;
                if(spriteNum==3)
                    image=fly_right_3;
                if(spriteNum==4)
                    image=fly_right_4;
                if(spriteNum==6)
                    image=right_death;
            }

        }
        else if(transformation_state=="block")
         {
             if(direction=="left")
                 image=left_block;
             else
                 image=right_block;
         }
         else if(transformation_state=="dash")
         {
             if(direction=="left")
                 image=left_dash;
             else
                 image=right_dash;
         }


    if(spriteNum==6)
    {
        if(direction=="left")
             {   g2.drawImage(left_death, x, y, gp.tileSize*2, gp.tileSize*2, null);


            }
        else
        {
            g2.drawImage(right_death, x, y, gp.tileSize*2, gp.tileSize*2, null);

        }
    }
    else

{
    if((transformation_animation==0 || transformation_animation>2) && can_get_hit%2==0 )
    {
        if(transformation_state!="dash")
        g2.drawImage(image, x, y, gp.tileSize*2, gp.tileSize*2, null); //which image, x, y, height, width, convention
    else
        {
            if(direction=="left")
            g2.drawImage(image, x, y-gp.tileSize, gp.tileSize*3, gp.tileSize*3, null); //which image, x, y, height, width, convention
        else  if(direction=="right")
                g2.drawImage(image, x-gp.tileSize, y-gp.tileSize, gp.tileSize*3, gp.tileSize*3, null);
        }
    }
}

        //------------------------------------------------
        //DRAW TRANSFORMATION ANIMATION
        //--------------------------------------------------


        if(transformation_animation>=1)
        {
            if(transformation_animation==1)
                image= ani1;
            else if(transformation_animation==2)
                image=ani2;
            else if(transformation_animation==3)
                image=ani3;
            else if(transformation_animation==4)
                image=ani4;
            else if(transformation_animation==5)
                image=ani5;
            else if(transformation_animation==6)
                image=ani6;
            else if(transformation_animation==7)
                image=ani7;
            else if(transformation_animation==8)
                image=ani8;
            else if(transformation_animation==9)
                image=ani9;
            else if(transformation_animation==10)
                image=ani10;
            else if(transformation_animation==11)
                image=ani11;

            g2.drawImage(image, x, y, gp.tileSize*2, gp.tileSize*2, null); //which image, x, y, height, width, convention

            transformation_animation++;

            transformation_animation%=12;


        }

        //------------------------------------------------
        //DRAW PLAYER BULLET
        //--------------------------------------------------

        for(int i = 0; i< current_visible_bullet; i++)
        {
            if(bullet[i].visibility==true)
            {
                if(bullet[i].direction=="left")
                {
                    if(bullet[i].spriteNum==1)
                    {
                        image = bullet[i].left1;


                    }
                    else if(bullet[i].spriteNum==2)
                    {
                        image = bullet[i].left2;


                    }
                }
                else  if(bullet[i].direction=="right")
                {
                    if(bullet[i].spriteNum==1)
                    {
                        image = bullet[i].right1;

                    }
                    else if(bullet[i].spriteNum==2)
                    {
                        image = bullet[i].right2;


                    }
                }
                g2.drawImage(image, bullet[i].x, bullet[i].y, gp.tileSize*2, gp.tileSize*2, null);

                bullet[i].spriteCounter= (++bullet[i].spriteCounter)%8;

                if(bullet[i].spriteCounter==7)
                {
                    if(bullet[i].spriteNum==1)
                        bullet[i].spriteNum=2;
                    else if(bullet[i].spriteNum==2)
                        bullet[i].spriteNum=1;

                }
            }
        }


    }



}



    



