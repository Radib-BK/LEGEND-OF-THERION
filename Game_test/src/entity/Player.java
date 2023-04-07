package entity;

import MainPackage.GamePanel;
import MainPackage.KeyHandler;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Player extends Entity {
    public GamePanel gp;
    KeyHandler keyH;

    Weapon [] player_bullet;

    int current_visible_player_bullet;
    int shoot_delay=0;

    public int player_max_health, player_current_health;

    int coin_count=0, health_potion_count=0, boost_potion_count=0;



    public String transformation_state;

    public int transformation_animation=0;


    public BufferedImage fly_left_1, fly_right_1, fly_left_2, fly_right_2, fly_left_3, fly_right_3, fly_left_4, fly_right_4 ;
    public BufferedImage left_shoot, right_shoot;

    public BufferedImage ani0, ani1, ani2, ani3, ani4, ani5, ani6, ani7, ani8, ani9, ani10, ani11;

    public int player_default_start_x[];
    public int player_default_start_y[];
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        solidArea = new Rectangle(15,15,gp.tileSize*2-30, gp.tileSize*2-30);
        player_bullet = new Weapon[10];
        player_default_start_x = new int[gp.maxMap];
        player_default_start_y = new int[gp.maxMap];


        //player width is 96 px which is equal to gp.tileSize(48) *2 =96

        set_start_x_y();
        setDefaultValues();
        getPlayerImage();
        getAnimationImage();
    }



    public void setDefaultValues() {
        x = player_default_start_x[gp.currentMap];
        y = player_default_start_y[gp.currentMap];
        speed = 3;
        direction = "right";
        move_direction="right";
        transformation_state="normal";

        coin_count=0;
        health_potion_count=0;
        boost_potion_count=0;
        player_max_health=40;
        player_current_health=0;

        setPlayerBulletValues();
    }

    public void set_start_x_y()
    {
        player_default_start_x[0]=40;
        player_default_start_y[0]=650;

        player_default_start_x[1]=40;
        player_default_start_y[1]=450;
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
            player_bullet[i].charge=6;

        for(int i=0; i<lifetime_max_charge; i++)
            player_bullet[i].power=5;

        for(int i=0; i<lifetime_max_charge; i++)
            player_bullet[i].speed=4;


        for(int i=0; i<lifetime_max_charge; i++)
        {
            player_bullet[i].solidArea= new Rectangle(20,20,gp.tileSize*2-40, gp.tileSize*2-40);

        }



    }

    public void setPlayerBulletValues()
    {
        int lifetime_max_charge=10;



        try{
            for(int i=0; i<lifetime_max_charge; i++)
                player_bullet[i]= new Weapon();

            for(int i=0; i<lifetime_max_charge; i++)
                player_bullet[i].left1=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/weapon/shuriken/left/0.png"));

            for(int i=0; i<lifetime_max_charge; i++)
                player_bullet[i].left2=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/weapon/shuriken/left/1.png"));

            for(int i=0; i<lifetime_max_charge; i++)
                player_bullet[i].right1=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/weapon/shuriken/right/0.png"));

            for(int i=0; i<lifetime_max_charge; i++)
                player_bullet[i].right2=  ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/weapon/shuriken/right/1.png"));


        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        setPlayerBulletdefaultValues(lifetime_max_charge);

    }



    public void shoot_player_bullet()
    {
        if(direction=="right")
        {
            player_bullet[current_visible_player_bullet].direction="right";
            player_bullet[current_visible_player_bullet].move_direction="right";
            player_bullet[current_visible_player_bullet].visibility=true;
            player_bullet[current_visible_player_bullet].x= x+ gp.tileSize;
            player_bullet[current_visible_player_bullet].y= y;
        }
        else if(direction=="left")
        {
            player_bullet[current_visible_player_bullet].direction="left";
            player_bullet[current_visible_player_bullet].move_direction="left";
            player_bullet[current_visible_player_bullet].visibility=true;
            player_bullet[current_visible_player_bullet].x= x- gp.tileSize;
            player_bullet[current_visible_player_bullet].y= y;
        }

        current_visible_player_bullet++;

    }

    public void move_player_bullet()
    {

        for(int i=0; i<current_visible_player_bullet; i++)
        {
            if(player_bullet[i].visibility==true)
            {
                if(player_bullet[i].direction=="left")
                    player_bullet[i].x-=player_bullet[i].speed;
                else if(player_bullet[i].direction=="right")
                    player_bullet[i].x+=player_bullet[i].speed;
            }

        }
    }

    public void check_player_bullet_collison_with_wall()
    {

        for(int i=0; i<current_visible_player_bullet; i++ )
        {
            player_bullet[i].collisonOn=false;



            gp.cChecker.checkTile(player_bullet[i]);

            if(player_bullet[i].collisonOn==true)
                player_bullet[i].visibility=false;

            if(player_bullet[i].x<0 || player_bullet[i].y<0 || player_bullet[i].x>gp.screenWidth-gp.tileSize*2 || player_bullet[i].y>gp.screenHeight-gp.tileSize*2)
                player_bullet[i].visibility=false;

        }

        int false_count=0;





        for(int i=0; i<current_visible_player_bullet; i++)
        {
            if (player_bullet[i].visibility==false ) { // found a false value
                false_count++; // increment the count of false values found
            } else if (false_count > 0) { // shift the array to the left
                player_bullet[i - false_count].copy_weapon(player_bullet[i]);
            }
        }

        current_visible_player_bullet-=false_count;


    }

    public void update() {

        check_player_bullet_collison_with_wall();
        move_player_bullet();


        if (transformation_state=="normal")
        {
            if (keyH.leftPressed == true || keyH.rightPressed == true) {

               if (keyH.leftPressed == true && x > speed) {
                    direction = "left";
                    move_direction="left";

                    x -= speed;
                } else if (keyH.rightPressed == true && x < gp.screenWidth - gp.tileSize * 2 - speed) {
                    direction = "right";
                    move_direction="right";

                    x += speed;
                }


                spriteCounter = (++spriteCounter) % 18;
                if (spriteCounter == 0) {
                    if(spriteNum==0)
                        spriteNum=1;
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

                collisonOn=false;

                gp.cChecker.checkTile(this);

                if(collisonOn==true)
                {
                    switch(move_direction)
                    {

                        case "left":
                            x+=speed;
                                break;
                        case "right":
                            x-=speed;
                                break;
                    }
                }


            }
            else if(keyH.twoPressed && transformation_animation==0)
            {
                transformation_state="fly";
                transformation_animation=1;
                spriteNum=1;
            }
            else if(keyH.spacePressed &&  shoot_delay==0)
            {
                shoot_delay=1;
                spriteNum=5;
                shoot_player_bullet();
            }
            else
            {
                if(shoot_delay>0)
                    shoot_delay =(++shoot_delay) % 30;

                //this condition is used to hold shoot animation for a while

                if(shoot_delay==0 || shoot_delay>20)
                spriteNum=0;
            }



            for(int i=0; i<2; i++)
            {
                move_direction="down";

                int fall_speed=4;

                y+=fall_speed;

                collisonOn=false;

                gp.cChecker.checkTile(this);

                if(collisonOn==true)
                {
                    switch(move_direction)
                    {
                        case "up":
                            y+=fall_speed;
                            break;
                        case "down":
                            y-=fall_speed;
                            break;
                        case "left":
                            x+=fall_speed;
                            break;
                        case "right":
                            x-=fall_speed;
                            break;
                    }
                }
            }

        }
        else if (transformation_state=="fly")
        {
            spriteCounter = (++spriteCounter) % 8;
            if (spriteCounter == 0) {
                if(spriteNum==0)
                    spriteNum=1;
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
                    move_direction="left";
                    x -= speed;
                } else if (keyH.rightPressed == true && x < gp.screenWidth - gp.tileSize * 2 - speed) {
                    direction = "right";
                    move_direction="right";
                    x += speed;
                }
                else if(keyH.upPressed==true && y > speed)
                {
                    move_direction="up";
                    y-=speed;
                }
                else if(keyH.downPressed==true && y < gp.screenHeight - gp.tileSize*2 - speed)
                {
                    move_direction="down";
                    y+=speed;
                }




                collisonOn=false;

                gp.cChecker.checkTile(this);

                if(collisonOn==true)
                {
                    switch(move_direction)
                    {
                        case "up":
                            y+=speed;
                            break;
                        case "down":
                            y-=speed;
                            break;
                        case "left":
                            x+=speed;
                            break;
                        case "right":
                            x-=speed;
                            break;
                    }
                }


            }
            else if(keyH.onePressed && transformation_animation==0)
            {
                transformation_state="normal";
                transformation_animation=1;
                spriteNum=0;
            }

        }

        //------CHECK OBJECT COLLISON------//

        int tileNum=gp.cChecker.checkObject(this);

        if(tileNum>=20)
            pickObject(tileNum);

        gp.cChecker.check_player_collison_with_lazer(this);

        check_map_transition();
    }

    public void check_map_transition()
    {
        if(x >= gp.screenWidth - gp.tileSize * 3 - speed)
        {
            if(gp.currentMap==0)
            {
                gp.currentMap=1;
                x=50;
            }
        }
    }
    public void pickObject(int i)
    {
        if(i==20)
            health_potion_count++;
        else if(i==21)
            boost_potion_count++;
        else if(i==25)
            coin_count++;
        else if(i==29)
        {
            System.out.println("key collected");
            gp.tileM.open_door();
        }


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
            }

        }




        if(transformation_animation==0 || transformation_animation>2)
        g2.drawImage(image, x, y, gp.tileSize*2, gp.tileSize*2, null); //which image, x, y, height, width, convention

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

        for(int i=0; i<current_visible_player_bullet; i++)
        {
            if(player_bullet[i].visibility==true)
            {
                if(player_bullet[i].direction=="left")
                {
                    if(player_bullet[i].spriteNum==1)
                    {
                        image = player_bullet[i].left1;


                    }
                    else if(player_bullet[i].spriteNum==2)
                    {
                        image = player_bullet[i].left2;


                    }
                }
                else  if(player_bullet[i].direction=="right")
                {
                    if(player_bullet[i].spriteNum==1)
                    {
                        image = player_bullet[i].right1;

                    }
                    else if(player_bullet[i].spriteNum==2)
                    {
                        image = player_bullet[i].right2;


                    }
                }
                g2.drawImage(image, player_bullet[i].x, player_bullet[i].y, gp.tileSize*2, gp.tileSize*2, null);

                player_bullet[i].spriteCounter= (++player_bullet[i].spriteCounter)%8;

                if(player_bullet[i].spriteCounter==7)
                {
                    if(player_bullet[i].spriteNum==1)
                        player_bullet[i].spriteNum=2;
                    else if(player_bullet[i].spriteNum==2)
                        player_bullet[i].spriteNum=1;

                }
            }
        }


    }



}



    



