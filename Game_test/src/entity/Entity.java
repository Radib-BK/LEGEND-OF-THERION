package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.image.BufferedImage;

public class Entity
{

    public int x, y;
    public int speed;

    public BufferedImage left0, right0 ,left1, left2, left3, left4, right1, right2, right3, right4;
    public String direction=" ";
    public String move_direction= " ";



    public int spriteCounter=0;

    public int spriteNum=1;

    public Rectangle solidArea;
    public boolean collisonOn=false;

}
