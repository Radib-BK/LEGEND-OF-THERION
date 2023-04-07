package entity;

import java.awt.image.BufferedImage;

public class Weapon extends Entity {


    boolean visibility=false;
    int power;

    int charge;

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
        this.charge=w.power;





    }




}
