package MainPackage;

import Enemy.*;
import Entity.Entity;
import Obstacle.MovingTile;

public class AssetSetter {
    GamePanel gp;

    AssetSetter(GamePanel gp)
    {
        this.gp=gp;
    }

    public void set_enemy()
    {
        gp.enemy[2][0]=new BlueSlime(gp);
        set_enemy_parameter(gp.enemy[2][0], 1600, 672, 1400, gp.screenWidth-gp.tileSize*2-1);

        gp.enemy[2][1]=new BlueSlime(gp);
        set_enemy_parameter(gp.enemy[2][1], 780, 148, 760, 960);

        gp.enemy[2][2]=new BlueSlime(gp);
        set_enemy_parameter(gp.enemy[2][2], 1100, 292, 1056, 1202);

        gp.enemy[3][2]=new IceGolem(gp);
        set_enemy_parameter(gp.enemy[3][2], 1000, 392, 1255, 1435);

        gp.enemy[4][0]=new RedSlime(gp);
        set_enemy_parameter(gp.enemy[4][0], 1500, 540, 1350, gp.screenWidth-gp.tileSize*2-1);



        gp.enemy[4][1]=new RedSlime(gp);
        set_enemy_parameter(gp.enemy[4][1], 720, 105, 710, 820);

        gp.enemy[5][1]=new BlackMetal(gp);
        set_enemy_parameter(gp.enemy[5][1], 1600, 697, 900, gp.screenWidth-gp.tileSize*4);

        gp.enemy[5][0]=new RedSlime(gp);
        set_enemy_parameter(gp.enemy[5][0], 500, 346, 390, 800);

        gp.enemy[5][2]=new RedSlime(gp);
        set_enemy_parameter(gp.enemy[5][2], 1300, 344+48, 1255, 1435);

        gp.enemy[6][0]=new StoneGolem(gp);
        set_enemy_parameter(gp.enemy[6][0], 1000, 640, 100, 1500);





    }

    public void set_obstacle()
    {
        gp.obstacle[0][0]=new MovingTile(gp);
        set_enemy_parameter(gp.obstacle[0][0], 528, 500, 350, 800);

        gp.obstacle[0][1]=new MovingTile(gp);
        set_enemy_parameter(gp.obstacle[0][1], 790, 500, 350, 700);
        gp.obstacle[0][1].speed=2;
        gp.obstacle[0][2]=new MovingTile(gp);
        set_enemy_parameter(gp.obstacle[0][2], 900, 400, 100, 550);
        gp.obstacle[0][2].speed=3;

        gp.obstacle[0][3]=new MovingTile(gp);
        set_enemy_parameter(gp.obstacle[0][3], 1020, 400, 120, 800);
        gp.obstacle[0][3].speed=2;

        gp.obstacle[2][0]=new MovingTile(gp);
        set_enemy_parameter(gp.obstacle[2][0], 330, 400, 100, 800);
        gp.obstacle[2][0].speed=2;

        gp.obstacle[2][1]=new MovingTile(gp);
        set_enemy_parameter(gp.obstacle[2][1], 680, 300, 120, 450);
        gp.obstacle[2][1].speed=2;

        gp.obstacle[2][2]=new MovingTile(gp);
        set_enemy_parameter(gp.obstacle[2][2], 780, 300, 320, 800);
        gp.obstacle[2][2].speed=3;

        gp.obstacle[2][3]=new MovingTile(gp);
        set_enemy_parameter(gp.obstacle[2][3], 1310, 300, 250, 800);
        gp.obstacle[2][3].speed=4;
    }

    public void set_enemy_parameter(Entity e, int x, int y, int left_limit, int right_limit)
    {

        e.x= x;
        e.y= y;
        e.left_limit=left_limit;
        e.right_limit=right_limit;
    }
}
