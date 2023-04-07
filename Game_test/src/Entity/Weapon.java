package Entity;

import MainPackage.GamePanel;

public class Weapon extends Entity {


    public boolean visibility=false;
    public int power;

    public int amplitude=0;

    public int lifetime=0;

    public int shoot_delay=0;

    public int bullet_theta=90;

    public boolean destroyable=true;





    public Weapon(GamePanel gp)
    {
        super(gp);
    }
    public void copy_weapon(Weapon w)
    {


        this.x=w.x;
        this.y=w.y;
        this.visibility=w.visibility;
        this.direction=w.direction;
          this.move_direction= w.move_direction;
        this.spriteCounter=w.spriteCounter;
        this.spriteNum=w.spriteNum;
        this.power=w.power;
        this.amplitude=w.amplitude;
        this.bullet_theta=w.bullet_theta;
        this.shoot_delay=w.shoot_delay;
        this.lifetime=w.lifetime;
        this.height=w.height;
        this.width=w.width;
        this.destroyable=w.destroyable;
        this.solidArea.x=w.solidArea.x;
        this.solidArea.y=w.solidArea.y;
        this.solidArea.width=w.solidArea.width;
        this.solidArea.height=w.solidArea.height;




    }




}
