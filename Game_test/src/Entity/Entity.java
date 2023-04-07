package Entity;

import MainPackage.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity
{

    public int x=0, y=0;

    public BufferedImage current_image;
    public int speed;
    public boolean can_shoot;
    public Weapon[] bullet = new Weapon[10];

    public BufferedImage left0, right0 ,left1, left2, left3, left4, right1, right2, right3, right4;
    public String direction=" ";
    public String move_direction= " ";
    public String state="alive";
    public int  max_hit_delay=50;

    public boolean damagable=true;




    public int can_get_hit=0;
    public int height, width, left_limit, right_limit, hit_damage;

    public int max_health, current_health;
    public int max_shoot_delay, current_shoot_delay, last_hit_time=0, hp_bar_display_time=400;

    public GamePanel gp;

    public int spriteCounter=0;

    public int lifetime_max_charge;
    public int current_visible_bullet =0;

    public int spriteNum=1;

    public Rectangle solidArea;
    public boolean collisonOn=false;

    public boolean display=true;

    String dialogues[]= new String[20];
    int dialogue_index=0;

    public Entity(GamePanel gp)
    {
        this.gp=gp;
    }

    public void do_action() {}

    public void speak(){}

    public void update()
    {
        do_action();
    }

    public void draw(Graphics2D g2){ }

    public void draw_common(Graphics2D g2) {

        draw(g2);


    }

    public void draw_stone_golem_laser(Graphics2D g2)
    {}

    public void draw_laser(Graphics2D g2)
    {
     draw_stone_golem_laser(g2);
    }

}
