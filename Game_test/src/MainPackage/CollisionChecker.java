package MainPackage;

import Entity.Entity;
import Entity.Weapon;

import java.awt.*;

public class CollisionChecker {

    GamePanel gp;
    public CollisionChecker(GamePanel gp)
    {
        this.gp=gp;
    }

    public void checkTile(Entity entity)
    {



        int entityLeftWorldX= entity.x+entity.solidArea.x;
        int entityRightWorldX= entity.x+entity.solidArea.x+entity.solidArea.width;
        int entityTopWorldY= entity.y+entity.solidArea.y;
        int entityBottomWorldY= entity.y+entity.solidArea.y+entity.solidArea.height;

        int entityLeftCol= entityLeftWorldX/gp.tileSize;
        int entityRightCol= entityRightWorldX/gp.tileSize;
        int entityTopRow= entityTopWorldY/gp.tileSize;
        int entityBottomRow= entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2, tileNum3;

        switch(entity.move_direction)
        {
            case "up":
                entityTopRow= (entityTopWorldY-entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum3=gp.tileM.mapTileNum[gp.currentMap][(entityRightCol+entityLeftCol)/2][entityTopRow];


                if(  gp.tileM.tile[tileNum1].collison=="solid"  || gp.tileM.tile[tileNum2].collison=="solid"  || gp.tileM.tile[tileNum3].collison=="solid" )
                    entity.collisonOn=true;
                else  if(gp.tileM.tile[tileNum1].collison=="death" || gp.tileM.tile[tileNum2].collison=="death"  || gp.tileM.tile[tileNum3].collison=="death" )
                {
                    if(gp.game_state== gp.playState)
                    entity.current_health =0;
                    else
                        entity.collisonOn=true;
                }
                break;
            case "down":

                entityBottomRow= (entityBottomWorldY+entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                tileNum3=gp.tileM.mapTileNum[gp.currentMap][(entityRightCol+entityLeftCol)/2][entityBottomRow];




                if(gp.tileM.tile[tileNum1].collison=="solid"  || gp.tileM.tile[tileNum2].collison=="solid"  || gp.tileM.tile[tileNum3].collison=="solid" )
                    entity.collisonOn=true;
                else  if(gp.tileM.tile[tileNum1].collison=="death" || gp.tileM.tile[tileNum2].collison=="death"  || gp.tileM.tile[tileNum3].collison=="death" )
                {
                    if(gp.game_state== gp.playState)
                        entity.current_health =0;
                    else
                        entity.collisonOn=true;
                }
                break;
            case "left":

                entityLeftCol= (entityLeftWorldX-entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum3=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][(entityTopRow+entityBottomRow)/2];

                //only for winter level



                if(gp.tileM.tile[tileNum1].collison=="solid"  || gp.tileM.tile[tileNum2].collison=="solid"  || gp.tileM.tile[tileNum3].collison=="solid" )
                    entity.collisonOn=true;
                else  if(gp.tileM.tile[tileNum1].collison=="death" || gp.tileM.tile[tileNum2].collison=="death"  || gp.tileM.tile[tileNum3].collison=="death" )
                {
                    if(gp.game_state== gp.playState)
                        entity.current_health =0;
                    else
                        entity.collisonOn=true;
                }

                break;
            case "right":

                entityRightCol= (entityRightWorldX+entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];

                tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                tileNum3=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][(entityTopRow+entityBottomRow)/2];




                if(gp.tileM.tile[tileNum1].collison=="solid" || gp.tileM.tile[tileNum2].collison=="solid"  || gp.tileM.tile[tileNum3].collison=="solid" )
                    entity.collisonOn=true;
                else  if(gp.tileM.tile[tileNum1].collison=="death" || gp.tileM.tile[tileNum2].collison=="death"  || gp.tileM.tile[tileNum3].collison=="death" )
                {
                    if(gp.game_state== gp.playState)
                        entity.current_health =0;
                    else
                        entity.collisonOn=true;
                }


                break;

        }

    }

    public boolean dash_state_collison(Entity e)
    {
        int col=0;
        int row=0;
        int x=0;
        int y=0;

        Rectangle player_solid_area = new Rectangle(e.solidArea);

        player_solid_area.x+=e.x;
        player_solid_area.y+=e.y;



        while(col<gp.MaxScreenCol && row< gp.MaxScreenRow)
        {
            int tileNum= gp.tileM.mapTileNum[gp.currentMap][col][row];


            Rectangle object= new Rectangle();




            if(gp.tileM.tile[tileNum].collison=="solid") {
                object.x = x;
                object.y = y;
                object.width = gp.tileSize;
                object.height = gp.tileSize;
            }
            if(player_solid_area.intersects(object)==true)
            {
                return true;
            }



            col++;
            x+=gp.tileSize;

            if(col==gp.MaxScreenCol)
            {
                col=0;
                x=0;
                row++;
                y+=gp.tileSize;
            }



        }

        return false;

    }

    //overlaading for player bullet
    public void checkTile(Entity entity, String type)
    {



        int entityLeftWorldX= entity.x+entity.solidArea.x;
        int entityRightWorldX= entity.x+entity.solidArea.x+entity.solidArea.width;
        int entityTopWorldY= entity.y+entity.solidArea.y;
        int entityBottomWorldY= entity.y+entity.solidArea.y+entity.solidArea.height;

        int entityLeftCol= entityLeftWorldX/gp.tileSize;
        int entityRightCol= entityRightWorldX/gp.tileSize;
        int entityTopRow= entityTopWorldY/gp.tileSize;
        int entityBottomRow= entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2, tileNum3;

        switch(entity.move_direction)
        {
            case "up":
                entityTopRow= (entityTopWorldY-entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum3=gp.tileM.mapTileNum[gp.currentMap][(entityRightCol+entityLeftCol)/2][entityTopRow];


                if(  gp.tileM.tile[tileNum1].collison=="solid"  || gp.tileM.tile[tileNum2].collison=="solid"  || gp.tileM.tile[tileNum3].collison=="solid" )
                    entity.collisonOn=true;
                else  if(gp.tileM.tile[tileNum1].collison=="death" || gp.tileM.tile[tileNum2].collison=="death"  || gp.tileM.tile[tileNum3].collison=="death" )
                {
                    if(gp.game_state== gp.playState)
                        entity.current_health =0;
                    else
                        entity.collisonOn=true;
                }
                break;
            case "down":

                entityBottomRow= (entityBottomWorldY+entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                tileNum3=gp.tileM.mapTileNum[gp.currentMap][(entityRightCol+entityLeftCol)/2][entityBottomRow];




                if(gp.tileM.tile[tileNum1].collison=="solid"  || gp.tileM.tile[tileNum2].collison=="solid"  || gp.tileM.tile[tileNum3].collison=="solid" )
                    entity.collisonOn=true;
                else  if(gp.tileM.tile[tileNum1].collison=="death" || gp.tileM.tile[tileNum2].collison=="death"  || gp.tileM.tile[tileNum3].collison=="death" )
                {
                    if(gp.game_state== gp.playState)
                        entity.current_health =0;
                    else
                        entity.collisonOn=true;
                }
                break;
            case "left":

                entityLeftCol= (entityLeftWorldX-entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum3=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][(entityTopRow+entityBottomRow)/2];

                //only for winter level

                if(tileNum1>=46 && tileNum1<=48)
                    (gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow])++;
                if(tileNum2>=46 && tileNum2<=48)
                    (gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow])++;

                if(tileNum1==49)
                    gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow]=0;
                if(tileNum2==49)
                    gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow]=0;



                if(gp.tileM.tile[tileNum1].collison=="solid"  || gp.tileM.tile[tileNum2].collison=="solid"  || gp.tileM.tile[tileNum3].collison=="solid" )
                    entity.collisonOn=true;
                else  if(gp.tileM.tile[tileNum1].collison=="death" || gp.tileM.tile[tileNum2].collison=="death"  || gp.tileM.tile[tileNum3].collison=="death" )
                {
                    if(gp.game_state== gp.playState)
                        entity.current_health =0;
                    else
                        entity.collisonOn=true;
                }

                break;
            case "right":

                entityRightCol= (entityRightWorldX+entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];

                tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                tileNum3=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][(entityTopRow+entityBottomRow)/2];

                //only for winter level

                if(tileNum1>=46 && tileNum1<=48)
                    (gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow])++;
                if(tileNum2>=46 && tileNum2<=48)
                    (gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow])++;

                if(tileNum1==49)
                    gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow]=0;
                if(tileNum2==49)
                    gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow]=0;



                if(gp.tileM.tile[tileNum1].collison=="solid" || gp.tileM.tile[tileNum2].collison=="solid"  || gp.tileM.tile[tileNum3].collison=="solid" )
                    entity.collisonOn=true;
                else  if(gp.tileM.tile[tileNum1].collison=="death" || gp.tileM.tile[tileNum2].collison=="death"  || gp.tileM.tile[tileNum3].collison=="death" )
                {
                    if(gp.game_state== gp.playState)
                        entity.current_health =0;
                    else
                        entity.collisonOn=true;
                }


                break;

        }

    }

    public int checkObject(Entity entity)
    {
        Rectangle player = new Rectangle(entity.solidArea);

        Rectangle object= new Rectangle(player.x+gp.tileSize, player.y+gp.tileSize, gp.tileSize, gp.tileSize );


        int tileNum= gp.tileM.mapTileNum[gp.currentMap][(entity.x+ gp.tileSize)/ gp.tileSize][(entity.y+gp.tileSize)/ gp.tileSize];

        if(player.intersects(object) && tileNum>=20 && tileNum<30)
        {
             gp.tileM.mapTileNum[gp.currentMap][(entity.x+ gp.tileSize)/ gp.tileSize][(entity.y+gp.tileSize)/ gp.tileSize]=0;

            //The code doesn't work-why??
            //The code works-why???

            return tileNum;

        }
        else
            return -1;




    }



    public boolean check_player_collison_with_lazer(Entity e)
    {
        int col=0;
        int row=0;
        int x=0;
        int y=0;

        Rectangle player_solid_area = new Rectangle(e.solidArea);

        player_solid_area.x+=e.x;
        player_solid_area.y+=e.y;



        while(col<gp.MaxScreenCol && row< gp.MaxScreenRow)
        {
            int tileNum= gp.tileM.mapTileNum[gp.currentMap][col][row];


            Rectangle lazer= new Rectangle();

            if(tileNum==16)
            {
                lazer.x=x+24;
                lazer.y=y;
                lazer.width= 33-24;
                lazer.height=gp.tileSize;
            }
            else if(tileNum==18)
            {
                lazer.x=x;
                lazer.y=y+14;
                lazer.width= gp.tileSize;
                lazer.height=23-14;
            }

            if(player_solid_area.intersects(lazer)==true && e.damagable==true && gp.tileM.lazer_animation_delay<60)
            {
                return true;
            }



            col++;
            x+=gp.tileSize;

            if(col==gp.MaxScreenCol)
            {
                col=0;
                x=0;
                row++;
                y+=gp.tileSize;
            }



        }

        return false;
    }

    public void bullet_bullet_collison_check(Weapon w1[], Weapon w2[], Entity e)
    {
        for(int i=0; i<gp.player.lifetime_max_charge; i++)
        {
            for(int j=0; j<e.lifetime_max_charge; j++)
            {
                if(w1[i].visibility==true && w2[j].visibility==true) {

                    Rectangle bullet1 = new Rectangle();

                    bullet1.x = w1[i].x + w1[i].solidArea.x;
                    bullet1.y = w1[i].y + w1[i].solidArea.y;
                    bullet1.height = w1[i].solidArea.height;
                    bullet1.width = w1[i].solidArea.width;


                    Rectangle bullet2 = new Rectangle();

                    bullet2.x = w2[j].x + w1[j].solidArea.x;
                    bullet2.y = w2[j].y + w2[j].solidArea.y;
                    bullet2.height = w2[j].solidArea.height;
                    bullet2.width = w2[j].solidArea.width;

                    if (bullet1.intersects(bullet2)) {

                        if(w1[j].destroyable==true)
                        w1[i].visibility=false;

                         if(w2[j].destroyable==true)
                        w2[j].visibility=false;

                    }
                }
            }
        }

        int false_count=0;


        //there is just one player, so we don't need to pass the player class


        for(int i = 0; i<gp.player.current_visible_bullet; i++)
        {
            if (w1[i].visibility==false ) { // found a false value
                false_count++; // increment the count of false values found
            } else if (false_count > 0) { // shift the array to the left
                w1[i - false_count].copy_weapon(w1[i]);
            }
        }

        gp.player.current_visible_bullet -=false_count;

        for(int i = gp.player.current_visible_bullet +1; i<gp.player.lifetime_max_charge; i++)
            w1[i].visibility=false;

        false_count=0;

        if(w2[0].destroyable==true) {

            for (int i = 0; i < e.current_visible_bullet; i++) {
                if (w2[i].visibility == false) { // found a false value
                    false_count++; // increment the count of false values found
                } else if (false_count > 0) { // shift the array to the left
                    w2[i - false_count].copy_weapon(w2[i]);
                }
            }

            e.current_visible_bullet -= false_count;

            for (int i = e.current_visible_bullet + 1; i < e.lifetime_max_charge; i++)
                w2[i].visibility = false;

        }


    }

    public boolean player_obstacle_collison_check()
    {
        Entity e1=gp.player;

        Rectangle entity1 = new Rectangle();

        entity1.x = e1.x + e1.solidArea.x;
        entity1.y = e1.y + e1.solidArea.y;
        entity1.height = e1.solidArea.height;
        entity1.width = e1.solidArea.width;

        for(int i=0; i<8; i++)
        {
            Entity e2=gp.obstacle[gp.currentMap][i];

            if(e2!=null)
            {
                Rectangle entity2 = new Rectangle();

                entity2.x = e2.x + e2.solidArea.x;
                entity2.y = e2.y + e2.solidArea.y;
                entity2.height = e2.solidArea.height;
                entity2.width = e2.solidArea.width;



                if(entity1.intersects(entity2))
                {

                    return true;


                }
                else return false;

            }

        }

        return false;
    }

    public void entity_entity_collison_check(Entity e1, Entity e2)
    {

        Rectangle entity1 = new Rectangle();

        entity1.x = e1.x + e1.solidArea.x;
        entity1.y = e1.y + e1.solidArea.y;
        entity1.height = e1.solidArea.height;
        entity1.width = e1.solidArea.width;


        Rectangle entity2 = new Rectangle();

        entity2.x = e2.x + e2.solidArea.x;
        entity2.y = e2.y + e2.solidArea.y;
        entity2.height = e2.solidArea.height;
        entity2.width = e2.solidArea.width;



        if(e1.can_get_hit<=0 &&  e2.can_get_hit<=0  &&  entity1.intersects(entity2))
        {
            if(e1.damagable==true)
            e1.current_health -=e2.hit_damage;

            if(e2.damagable==true)
            e2.current_health -=e1.hit_damage;

            if(e1.damagable==true)
            e1.can_get_hit= e1.max_hit_delay;

            e2.can_get_hit=e2.max_hit_delay;

            e2.last_hit_time=e2.hp_bar_display_time;




        }



    }



    public void entity_bullet_collison_check(Entity e, Entity shooter)
    {

        Rectangle entity = new Rectangle();

        entity.x = e.x + e.solidArea.x;
        entity.y = e.y + e.solidArea.y;
        entity.height = e.solidArea.height;
        entity.width = e.solidArea.width;

        for(int i=0; i<shooter.lifetime_max_charge; i++)
        {
            if(shooter.bullet[i].visibility==true)
            {
                Rectangle bullet= new Rectangle();

                bullet.x = shooter.bullet[i].x + shooter.bullet[i].solidArea.x;
                bullet.y = shooter.bullet[i].y + shooter.bullet[i].solidArea.y;
                bullet.height = shooter.bullet[i].solidArea.height;
                bullet.width = shooter.bullet[i].solidArea.width;


                if(e.can_get_hit<=0 && entity.intersects(bullet))
                {
                    if(e.damagable==true)
                    e.current_health -= shooter.bullet[i].power;

                    if(shooter.bullet[i].destroyable==true)
                    shooter.bullet[i].visibility=false;

                    if(e.damagable==true)
                    e.can_get_hit= e.max_hit_delay;


                    e.last_hit_time=e.hp_bar_display_time;
                }
            }
        }

        int false_count=0;


        //there is just one player, so we don't need to pass the player class


        for(int i = 0; i<shooter.current_visible_bullet; i++)
        {
            if (shooter.bullet[i].visibility==false ) { // found a false value
                false_count++; // increment the count of false values found
            } else if (false_count > 0) { // shift the array to the left
                shooter.bullet[i - false_count].copy_weapon(shooter.bullet[i]);
            }
        }

        shooter.current_visible_bullet -=false_count;

        for(int i = shooter.current_visible_bullet +1; i<shooter.lifetime_max_charge; i++)
            shooter.bullet[i].visibility=false;





    }


}
