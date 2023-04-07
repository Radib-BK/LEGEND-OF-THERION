package MainPackage;

import entity.Entity;

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
                break;
            case "down":

                entityBottomRow= (entityBottomWorldY+entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                tileNum3=gp.tileM.mapTileNum[gp.currentMap][(entityRightCol+entityLeftCol)/2][entityBottomRow];


                if(gp.tileM.tile[tileNum1].collison=="solid"  || gp.tileM.tile[tileNum2].collison=="solid"  || gp.tileM.tile[tileNum3].collison=="solid" )
                    entity.collisonOn=true;
                break;
            case "left":

                entityLeftCol= (entityLeftWorldX-entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum3=gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][(entityTopRow+entityBottomRow)/2];

                if(gp.tileM.tile[tileNum1].collison=="solid"  || gp.tileM.tile[tileNum2].collison=="solid"  || gp.tileM.tile[tileNum3].collison=="solid" )
                    entity.collisonOn=true;

                break;
            case "right":

                entityRightCol= (entityRightWorldX+entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];

                tileNum2=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                tileNum3=gp.tileM.mapTileNum[gp.currentMap][entityRightCol][(entityTopRow+entityBottomRow)/2];


                if(gp.tileM.tile[tileNum1].collison=="solid" || gp.tileM.tile[tileNum2].collison=="solid"  || gp.tileM.tile[tileNum3].collison=="solid" )
                    entity.collisonOn=true;


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

    public void check_player_collison_with_lazer(Entity e)
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

            if(player_solid_area.intersects(lazer)==true && gp.tileM.lazer_animation_delay<60);
                //System.out.println("LAzer collided");


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
    }
}
