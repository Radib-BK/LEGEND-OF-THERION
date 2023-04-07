package Hud;

import MainPackage.GamePanel;
import Tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class HudManager  {

    GamePanel gp;

    Hud []health_mid;
    Hud health_start;
    Hud health_end[];

    public HudManager(GamePanel gp)
    {
        this.gp=gp;

        health_mid= new Hud[11];
        health_end = new Hud[2];
        health_start= new Hud();

        load_hud_image();

    }

    public void load_hud_image()
    {
        for(int i=0; i<11; i++)
            health_mid[i]=new Hud();

        health_end[0]=new Hud();
        health_end[1]=new Hud();

        try{
            health_mid[0].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/mid0.png"));
            health_mid[1].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/mid1.png"));
            health_mid[2].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/mid2.png"));
            health_mid[3].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/mid3.png"));
            health_mid[4].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/mid4.png"));
            health_mid[5].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/mid5.png"));
            health_mid[6].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/mid6.png"));
            health_mid[7].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/mid7.png"));
            health_mid[8].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/mid8.png"));
            health_mid[9].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/mid9.png"));
            health_mid[10].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/mid10.png"));

            health_end[0].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/end0.png"));
            health_end[1].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/end1.png"));

            health_start.image =   ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/start.png"));





        }
        catch (IOException e)
        {

        }

    }



    public void draw_hud(Graphics2D g2) {

        int x= gp.tileSize;
        int y=gp.tileSize;
        g2.drawImage(health_start.image, x, y, gp.tileSize, gp.tileSize, null);

        int i=1, mid;



        while(true)
        {




            if(gp.player.player_current_health<i*10 && gp.player.player_current_health>=(i-1)*10)
            {
                mid=gp.player.player_current_health%10;

            }
            else if(gp.player.player_current_health>=i*10)
                mid=9;
            else
                mid=10;







            g2.drawImage(health_mid[mid].image, x+gp.tileSize*i*2, y, gp.tileSize, gp.tileSize, null);

            i++;

            if(i*10>gp.player.player_max_health)
                break;
        }


        if(gp.player.player_current_health==gp.player.player_max_health)
            g2.drawImage(health_end[1].image, x+gp.tileSize*i*2, y, gp.tileSize, gp.tileSize, null);
        else
            g2.drawImage(health_end[0].image, x+gp.tileSize*i*2, y, gp.tileSize, gp.tileSize, null);






    }

}
