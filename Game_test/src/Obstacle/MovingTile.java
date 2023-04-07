package Obstacle;

import Entity.Entity;
import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class MovingTile extends Entity{

    public MovingTile(GamePanel gp) {
        super(gp);

        //    player_bullet = new Weapon[10];



        //player width is 96 px which is equal to gp.tileSize(48) *2 =96


        setDefaultValues();
        getEnemyImage();

        solidArea = new Rectangle(0,0,width, height);

    }



    public void setDefaultValues() {

        speed = 1;
        direction = "up";
        move_direction="up";


        height=gp.tileSize/2;
        width=gp.tileSize;
        can_shoot=false;






    }




    public void getEnemyImage() {
        try {

            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Obstacle/Moving Tile.png"));





        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void do_action() {





        //left limit is used as up limit here and right limit as down limit
        if ( y < left_limit) {
            direction = "down";
            move_direction="down";

        } else if (y > right_limit){
            direction = "up";
            move_direction="up";
        }


        if(direction=="up")
            y-=speed;
        else if(direction=="down")
            y+=speed;














        //Do steps that will always be done if even no key is pressed

        //if not in other state, increase transformation duration


        //player always goes down



        //Do that will always be done



        checkPlayerPositionChange();



        //------CHECK OBJECT COLLISON------//


    }

    public void checkPlayerPositionChange()
    {
        if(direction=="down")
        {
            Entity e1 = gp.player;

            Rectangle player = new Rectangle();

            player.x = e1.x + e1.solidArea.x;
            player.y = e1.y ;
            player.height = gp.tileSize*2;
            player.width = e1.solidArea.width;


            Rectangle entity2 = new Rectangle();

            Entity e2 = this;

            entity2.x = e2.x;
            entity2.y = e2.y;
            entity2.height = height;
            entity2.width = e2.width;

            if (player.intersects(entity2)) {
//                if (direction == "up")
//                    gp.player.y -= (speed + 4 * 2); //compensate for fall speed
//                else
                if(gp.player.y+50<y)
                    gp.player.y =y-gp.tileSize*2;
                else
                    gp.player.x-=20;

                    gp.player.fall_height=0;




            }
        }
        else  if(direction=="up")
        {
            Entity e1 = gp.player;

            Rectangle player = new Rectangle();

            player.x = e1.x + e1.solidArea.x;
            player.y = e1.y ;
            player.height = gp.tileSize*2;
            player.width = e1.solidArea.width;


            Rectangle entity2 = new Rectangle();

            Entity e2 = this;

            entity2.x = e2.x;
            entity2.y = e2.y;
            entity2.height = height;
            entity2.width = e2.width;

            if (player.intersects(entity2)) {

                if(gp.player.y+50<y)
                    gp.player.y= y-gp.tileSize*2; //compensate for fall speed

                gp.player.fall_height=0;


            }
        }
    }

    public void draw(Graphics2D g2) {





        //------------------------------------------------
        //DRAW PLAYER
        // --------------------------------------------------


            g2.drawImage(left1, x, y, width, height, null);















        //------------------------------------------------
        //DRAW HP BAR
        //--------------------------------------------------


        //------------------------------------------------
        //DRAW Dying ANIMATION
        //--------------------------------------------------








    }


}
