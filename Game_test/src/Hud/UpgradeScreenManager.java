package Hud;

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UpgradeScreenManager {

    public Icon[] icon= new Icon[18];
    BufferedImage question;
    BufferedImage background;

    BufferedImage physical, weapon, potion, coin, header, underline;

    int current_slot=0;
    int press_delay=0;

    GamePanel gp;

    public UpgradeScreenManager(GamePanel gp)
    {
        this.gp=gp;

        for(int i=0; i<18; i++)
            icon[i]= new Icon();

        load_icons();
        set_icon_text();


        //use database value here

    }

    public void load_icons()
    {

        set_icons(0, "Upgrade/available1.png", "Upgrade/not_available1.png",  true, 130, 550);
        set_icons(1, "Upgrade/available2.png", "Upgrade/not_available2.png",  true, 330, 550);
        set_icons(2, "Upgrade/available7.png", "Upgrade/not_available7.png", true, 730, 550);
        set_icons(3, "Upgrade/available8.png", "Upgrade/not_available8.png", true, 930, 550);
        set_icons(4, "Upgrade/available10.png",  "Upgrade/not_available10.png",true, 1330, 550);
        set_icons(5, "Upgrade/available11.png",  "Upgrade/not_available11.png",true, 1530, 550);
        set_icons(6, "Upgrade/available3.png", "Upgrade/not_available3.png", true, 130, 400);
        set_icons(7, "Upgrade/available4.png", "Upgrade/not_available4.png", true, 330, 400);
        set_icons(8, "Upgrade/available9.png",  "Upgrade/not_available9.png",true, 730, 400);
        set_icons(9, "Upgrade/available9.png",  "Upgrade/not_available9.png",false, 930, 400);
        set_icons(10, "Upgrade/available12.png", "Upgrade/not_available12.png", true, 1330, 400);
        set_icons(11, "Upgrade/available13.png", "Upgrade/not_available13.png", true, 1530, 400);
        set_icons(12, "Upgrade/available5.png", "Upgrade/not_available5.png", true, 130, 250);
        set_icons(13, "Upgrade/available6.png", "Upgrade/not_available6.png", true, 330, 250);
        set_icons(14, "Upgrade/available6.png",  "Upgrade/not_available6.png",false, 830, 250);

        try{
            question= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Upgrade/Question.png"));
            background= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Upgrade/Background.jpg"));
            physical= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Upgrade/Physical.png"));
            weapon= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Upgrade/Weapon.png"));
            potion= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Upgrade/Potion.png"));
            coin= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile25 (1).png"));
            header= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Upgrade/header.png"));
            underline = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Upgrade/underline.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }




    }


    public void set_icons(int index, String filePath, String filePath2,  boolean is_unlocked, int x, int y)
    {
        try {
            icon[index].available = ImageIO.read(getClass().getClassLoader().getResourceAsStream(filePath));
            icon[index].not_available = ImageIO.read(getClass().getClassLoader().getResourceAsStream(filePath2));
            icon[index].is_unlocked=is_unlocked;
            icon[index].x=x;
            icon[index].y=y;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {

        if(press_delay>0)
            press_delay--;
        if(press_delay==0)
        {
            if(gp.keyH.rightPressed==true)
            {
                press_delay=20;

                right_logic();


            }
            else if(gp.keyH.leftPressed==true)
            {
                press_delay=20;

                left_logic();
            }
            else if(gp.keyH.upPressed==true)
            {
                press_delay=20;

                up_logic();
            }
            else if(gp.keyH.downPressed==true)
            {
                press_delay=20;
                down_logic();
            }
            else if(gp.keyH.enterPressed==true)
            {
                press_delay=30;
                upgrade_logic();
            }
        }
    }

    public void upgrade_logic()
    {
        if(gp.player.coin_count>=icon[current_slot].level_cost[icon[current_slot].current_level] && icon[current_slot].current_level<3 && icon[current_slot].is_unlocked==true)
        {
            gp.player.coin_count-=icon[current_slot].level_cost[icon[current_slot].current_level];
            icon[current_slot].current_level++;
            gp.player.change_upgrade_level();

            if(current_slot==0)
                gp.player.current_health=gp.player.max_health;
        }
    }


    public void draw(Graphics2D g2)
    {
        g2.drawImage(background, 0, 0, gp.screenWidth, gp.screenHeight, null);

        g2.drawImage(header, 580, 30, 600, 150, null);
        g2.drawImage(underline, 560, 160, 630, 34, null);


        int x=90, y=120, width=80, height=69;

      gp.hud.frame(g2, 90, 127, 95, 62, new Color(255,255 ,255 , 200), Color.black, 5);

        int factor=0;
        if(gp.player.coin_count<100)
            factor=0;
        else if(gp.player.coin_count<1000)
            factor=15;
        else
            factor=25;


        gp.hud.frame(g2, 90, 127, 100+factor, 62, new Color(255,255 ,255 , 200), Color.black, 5);
       // gp.hud.frame(g2, 90, 127, 95+factor, 62, new Color(255,255 ,255 , 200), new Color(92, 64, 51), 5);


        g2.drawImage(coin,  100, 130, 48, 48, null);
        String str="x"+gp.player.coin_count+" ";
        g2.setColor(new Color(92, 64, 51));
       // g2.setFont(gp.hud.purisaB);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        g2.drawString(str, 143, 169);

        for(int i=0; i<=14; i++)
        {
            if(icon[i].is_unlocked==true)
            {
                if(icon[i].level_cost[icon[i].current_level]<=gp.player.coin_count )
                g2.drawImage(icon[i].available, icon[i].x, icon[i].y, gp.tileSize*2, gp.tileSize*2, null);
                else
                    g2.drawImage(icon[i].not_available, icon[i].x, icon[i].y, gp.tileSize*2, gp.tileSize*2, null);
            }
            else
            {
                g2.drawImage(question, icon[i].x, icon[i].y, gp.tileSize*2, gp.tileSize*2, null);
            }

            if(i==current_slot)
            {
                g2.setColor(Color.white);
                g2.setStroke(new BasicStroke(4));
                g2.drawRoundRect(icon[i].x-5, icon[i].y-5, gp.tileSize*2+10, gp.tileSize*2+10, 10, 10);
            }

        }


        g2.drawImage(physical, 130, 690, 298,50, null);
        g2.drawImage(weapon, 730, 690, 298,50, null);
        g2.drawImage(potion, 1330, 690, 298,50, null);

        gp.hud.frame(g2, 400, 770, 928, 135,  new Color(255,255 ,255 , 200), Color.black);

        g2.setColor(Color.black);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));

        str="Name   :";
        g2.setFont(gp.hud.arnesonBold);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22F));
        g2.drawString(str, 450, 810);
        str= icon[current_slot].name+" ";


        g2.setFont(gp.hud.arneson);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 18F));

        g2.drawString(str, 537, 810);

        str="Level   : ";
        g2.setFont(gp.hud.arnesonBold);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22F));
        g2.drawString(str, 450, 835);
        g2.setFont(gp.hud.arneson);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 18F));
        str= icon[current_slot].current_level+"/3";


        g2.drawString(str, 537, 835);

        str="Cost   :";
        g2.setFont(gp.hud.arnesonBold);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22F));
        g2.drawString(str, 450, 860);

        g2.setFont(gp.hud.arneson);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 18F));
        str= icon[current_slot].level_cost[icon[current_slot].current_level]+ " ";
        g2.drawString(str, 537, 860);

        str="Details :";
        g2.setFont(gp.hud.arnesonBold);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22F));
        g2.drawString(str, 450, 885);
        str= icon[current_slot].level_text[icon[current_slot].current_level];

        g2.setFont(gp.hud.arneson);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 18F));
        g2.drawString(str, 537, 885);

    }


    public void right_logic()
    {
        if(current_slot>=0 && current_slot<=5)
        {
            current_slot= (++current_slot)%6;
        }
        else if(current_slot>=6 && current_slot<=10)
        {
            current_slot++;
        }
        else if(current_slot==11)
        {
            current_slot=6;
        }
        else if(current_slot==12 || current_slot==13)
        {
            current_slot++;
        }
        else if(current_slot==14)
        {
            current_slot=12;
        }

    }

    public  void left_logic()
    {
        if(current_slot>=1 && current_slot<=5)
        {
            current_slot--;
        }
        else if(current_slot==0)
        {
            current_slot=5;
        }
        else if(current_slot>=7 && current_slot<=11)
        {
            current_slot--;
        }
        else if(current_slot==6)
        {
            current_slot=6;
        }
        else if(current_slot==13 || current_slot==14)
        {
            current_slot--;
        }
        else if(current_slot==12)
        {
            current_slot=14;
        }
    }

    public void up_logic()
    {
        if(current_slot>=0 && current_slot<=8)
            current_slot+=6;
        else if(current_slot==9)
            current_slot+=5;
        else if(current_slot==10 || current_slot==11)
            current_slot-=6;
        else if(current_slot>=12 && current_slot<=14)
            current_slot-=12;

    }

    public void down_logic()
    {
        if(current_slot>=6 && current_slot<=14)
            current_slot-=6;
        else if(current_slot==3)
            current_slot+=11;
        else if(current_slot==4 || current_slot==5)
            current_slot+=6;
        else if(current_slot>=0 && current_slot<=2)
            current_slot+=12;

    }

    public void set_icon_text()
    {
        icon[0].name="MAX HEALTH";
        icon[0].level_cost[0]=5;
        icon[0].level_cost[1]=8;
        icon[0].level_cost[2]=12;
        icon[0].level_text[0]="Upgrade the maximum health.";
        icon[0].level_text[1]="Upgrade the maximum health.";
        icon[0].level_text[2]="Upgrade the maximum health.";

        icon[1].name="MAX SPEED";
        icon[1].level_cost[0]=1;
        icon[1].level_cost[1]=4;
        icon[1].level_cost[2]=7;
        icon[1].level_text[0]="Upgrade the movement speed.";
        icon[1].level_text[1]="Upgrade the movement speed.";
        icon[1].level_text[2]="Upgrade the movement speed.";

        icon[2].name="FASTER SHURIKENS THROW";
        icon[2].level_cost[0]=5;
        icon[2].level_cost[1]=8;
        icon[2].level_cost[2]=11;
        icon[2].level_text[0]="Decrease the delay between throwing 2 shurikens.";
        icon[2].level_text[1]="Decrease the delay between throwing 2 shurikens.";
        icon[2].level_text[2]="Decrease the delay between throwing 2 shurikens.";

        icon[3].name="RAPID SHURIKENS";
        icon[3].level_cost[0]=5;
        icon[3].level_cost[1]=7;
        icon[3].level_cost[2]=10;
        icon[3].level_text[0]="Increase the speed of Shurikens.";
        icon[3].level_text[1]="Increase the speed of Shurikens.";
        icon[3].level_text[2]="Increase the speed of Shurikens.";

        icon[4].name="RED POTION BUFF";
        icon[4].level_cost[0]=3;
        icon[4].level_cost[1]=5;
        icon[4].level_cost[2]=7;
        icon[4].level_text[0]="Increase the amount of health restored by using red potion.";
        icon[4].level_text[1]="Increase the amount of health restored by using red potion.";
        icon[4].level_text[2]="Increase the amount of health restored by using red potion.";

        icon[5].name="BLUE POTION BUFF";
        icon[5].level_cost[0]=3;
        icon[5].level_cost[1]=5;
        icon[5].level_cost[2]=7;
        icon[5].level_text[0]="Increase the effect for using blue potion.";
        icon[5].level_text[1]="Increase the effect for using blue potion.";
        icon[5].level_text[2]="Increase the effect for using blue potion.";

        icon[6].name="TRANSFOMRATION DURATION";
        icon[6].level_cost[0]=5;
        icon[6].level_cost[1]=8;
        icon[6].level_cost[2]=12;
        icon[6].level_text[0]="Increase the maximum amount of time you can stay in other transformation state.";
        icon[6].level_text[1]="Increase the maximum amount of time you can stay in other transformation state.";
        icon[6].level_text[2]="Increase the maximum amount of time you can stay in other transformation state.";

        icon[7].name="HIT DAMAGE";
        icon[7].level_cost[0]=5;
        icon[7].level_cost[1]=7;
        icon[7].level_cost[2]=10;
        icon[7].level_text[0]="Increase the damage dealt to enemies for hitting them physically.";
        icon[7].level_text[1]="Increase the damage dealt to enemies for hitting them physically.";
        icon[7].level_text[2]="Increase the damage dealt to enemies for hitting them physically.";

        icon[8].name="LETHAL SHURIKENS";
        icon[8].level_cost[0]=6;
        icon[8].level_cost[1]=8;
        icon[8].level_cost[2]=12;
        icon[8].level_text[0]="Increase the damage dealt to enemies with Shurikens.";
        icon[8].level_text[1]="Increase the damage dealt to enemies with Shurikens.";
        icon[8].level_text[2]="Increase the damage dealt to enemies with Shurikens.";

        icon[9].name="BOMB RADIUS";
        icon[9].level_cost[0]=3;
        icon[9].level_cost[1]=6;
        icon[9].level_cost[2]=10;
        icon[9].level_text[0]="Increase the radius of area for damage of bomb.";
        icon[9].level_text[1]="Increase the radius of area for damage of bomb.";
        icon[9].level_text[2]="Increase the radius of area for damage of bomb.";


        icon[10].name="BLUE POTION DURATION";
        icon[10].level_cost[0]=5;
        icon[10].level_cost[1]=8;
        icon[10].level_cost[2]=12;
        icon[10].level_text[0]="Increase the duration of the effect of blue potion.";
        icon[10].level_text[1]="Increase the duration of the effect of blue potion.";
        icon[10].level_text[2]="Increase the duration of the effect of blue potion.";

        icon[11].name="REGENERATING POTION";
        icon[11].level_cost[0]=15;
        icon[11].level_cost[1]=20;
        icon[11].level_cost[2]=25;
        icon[11].level_text[0]="Red Potion also regenerates healths for a while.";
        icon[11].level_text[1]="Red Potion regenerates health for increase amount of time.";
        icon[11].level_text[2]="Red Potion regenerates health for increase amount of time.";

        icon[12].name="SAFE FALL";
        icon[12].level_cost[0]=3;
        icon[12].level_cost[1]=5;
        icon[12].level_cost[2]=7;
        icon[12].level_text[0]="Reduce the fall damage.";
        icon[12].level_text[1]="Reduce the fall damage.";
        icon[12].level_text[2]="Reduce the fall damage.";

        icon[13].name="PROTECTIVE SHIELD";
        icon[13].level_cost[0]=6;
        icon[13].level_cost[1]=10;
        icon[13].level_cost[2]=15;
        icon[13].level_text[0]="Increase the time of not getting any damage after one hit";
        icon[13].level_text[1]="Increase the time of not getting any damage after one hit";
        icon[13].level_text[2]="Increase the time of not getting any damage after one hit";

        icon[14].name="LETHAL BOMB";
        icon[14].level_cost[0]=6;
        icon[14].level_cost[1]=8;
        icon[14].level_cost[2]=10;
        icon[14].level_text[0]="Increase the damage dealt to enemies with Bomb.";
        icon[14].level_text[1]="Increase the damage dealt to enemies with Bomb.";
        icon[14].level_text[2]="Increase the damage dealt to enemies with Bomb.";

        for(int i=0; i<15; i++)
        {
            icon[i].level_cost[3]=0;
            icon[i].level_text[3]="!!COMPLETED!!";
        }


    }

}
