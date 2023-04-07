package Hud;

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class HudManager  {

    GamePanel gp;
    Graphics2D g2;

    Hud []health_mid;
    Hud health_start;
    Hud health_end[];

    Hud []transform_mid;
    Hud transform_start;
    Hud transform_end[];

    BufferedImage coin, health_potion, transform_potion, game_over;

    public String currentDialogue="";

    public Font purisaB;




    public HudManager(GamePanel gp)
    {
        this.gp=gp;

        health_mid= new Hud[12];
        health_end = new Hud[2];
        health_start= new Hud();
        transform_mid= new Hud[12];
        transform_end = new Hud[2];
        transform_start= new Hud();

        load_hud_image();
        load_font();



    }

    public void load_font()
    {
        try{
            File is = new File("res/Font/Purisa Bold.ttf");
            purisaB= Font.createFont(Font.TRUETYPE_FONT, is);




        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public void load_hud_image()
    {
        for(int i=0; i<12; i++)
            health_mid[i]=new Hud();

        health_end[0]=new Hud();
        health_end[1]=new Hud();

        for(int i=0; i<12; i++)
            transform_mid[i]=new Hud();

        transform_end[0]=new Hud();
        transform_end[1]=new Hud();

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
            health_mid[11].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/mid11.png"));

            health_end[0].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/end0.png"));
            health_end[1].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/end1.png"));

            health_start.image =   ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/start.png"));

            transform_mid[0].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/Blue/mid0.png"));
            transform_mid[1].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/Blue/mid1.png"));
            transform_mid[2].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/Blue/mid2.png"));
            transform_mid[3].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/Blue/mid3.png"));
            transform_mid[4].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/Blue/mid4.png"));
            transform_mid[5].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/Blue/mid5.png"));
            transform_mid[6].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/Blue/mid6.png"));
            transform_mid[7].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/Blue/mid7.png"));
            transform_mid[8].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/Blue/mid8.png"));
            transform_mid[9].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/Blue/mid9.png"));
            transform_mid[10].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/Blue/mid10.png"));
            transform_mid[11].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/Blue/mid11.png"));

            transform_end[0].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/Blue/end0.png"));
            transform_end[1].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/Blue/end1.png"));

            transform_start.image =   ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/Blue/start.png"));

            coin = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile25 (1).png"));
            health_potion= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile20.png"));
            transform_potion= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile21.png"));

            game_over= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hud/Game Over.png"));





        }
        catch (IOException e)
        {

        }

    }



    public void draw_hud(Graphics2D g2) {


        int x, y, mid, i;

        //Draw health hud
         x= 10;
         y=gp.tileSize;
        g2.drawImage(health_start.image, x, y, gp.tileSize, gp.tileSize, null);

         i=1;



        while(true)
        {




            if(gp.player.current_health <i*10 && gp.player.current_health >=(i-1)*10)
            {
                mid=gp.player.current_health %10;

                if(gp.player.current_health <=0)
                    mid=11;
            }
            else if(gp.player.current_health >=i*10)
                mid=10;
            else
                mid=11;







            g2.drawImage(health_mid[mid].image, x+gp.tileSize*i, y, gp.tileSize, gp.tileSize, null);

            i++;

            if(i*10>gp.player.max_health)
                break;
        }


        if(gp.player.current_health ==gp.player.max_health)
            g2.drawImage(health_end[1].image, x+gp.tileSize*i, y, gp.tileSize, gp.tileSize, null);
        else
            g2.drawImage(health_end[0].image, x+gp.tileSize*i, y, gp.tileSize, gp.tileSize, null);

        //Draw transformation hud

         x= 10;
         y=gp.tileSize*2+2;
        g2.drawImage(transform_start.image, x, y, gp.tileSize, gp.tileSize, null);

         i=1;

         int scaled_transformation = gp.player.player_current_transformation/10 ;



         if(gp.player.player_max_transformation>0)
        while(true)
        {




            if(scaled_transformation<i*10 && scaled_transformation>=(i-1)*10)
            {
                mid=scaled_transformation%10;

                if(scaled_transformation==0)
                    mid=11;
            }
            else if(scaled_transformation>=i*10)
                mid=10;
            else
                mid=11;







            g2.drawImage(transform_mid[mid].image, x+gp.tileSize*i, y, gp.tileSize, gp.tileSize, null);

            i++;

            if(i*10>gp.player.player_max_transformation/10)
                break;
        }


        if(gp.player.player_current_transformation==gp.player.player_max_transformation && gp.player.player_max_transformation!=0)
            g2.drawImage(transform_end[1].image, x+gp.tileSize*i, y, gp.tileSize, gp.tileSize, null);
        else
            g2.drawImage(transform_end[0].image, x+gp.tileSize*i, y, gp.tileSize, gp.tileSize, null);



        draw_stackable_screen(g2);






    }


    public void draw_stackable_screen(Graphics2D g2)
    {
        int x= 20;
        int y= gp.tileSize*3+10;

        int width= gp.tileSize*4;
        int height= gp.tileSize;

        frame(g2, x, y, width, height);




        //draw items...the values are hardcoded for better adjustment

        g2.drawImage(coin, x+5, y-10, gp.tileSize, gp.tileSize, null);

        g2.drawImage(health_potion, x+15+gp.tileSize, y+2+2, gp.tileSize-10, gp.tileSize-10, null);

        g2.drawImage(transform_potion, x+25+gp.tileSize*2, y+2+2, gp.tileSize-10, gp.tileSize-10, null);


        //display amount...again the position is extremely hardcoded




            String s= "x"+ gp.player.coin_count+" ";


            g2.setColor(new Color(60, 60, 60));
            g2.drawString(s, x+5+gp.tileSize-11,  y -10+44);

            g2.setColor(Color.white);
            g2.drawString(s, x+5+gp.tileSize-3-11, y-10+44-3);

            s= "x"+gp.player.health_potion_count+" ";

            g2.setColor(new Color(60, 60, 60));
            g2.drawString(s, x+15+gp.tileSize+gp.tileSize-13,  y-10 +44);

            g2.setColor(Color.white);
            g2.drawString(s, x+15+gp.tileSize+gp.tileSize-13-3, y-10+44-3);

             s= "x"+gp.player.boost_potion_count+" ";

            g2.setColor(new Color(60, 60, 60));
            g2.drawString(s, x+25+gp.tileSize*2+gp.tileSize-13,  y-10 +44);

            g2.setColor(Color.white);
            g2.drawString(s, x+25+gp.tileSize*2+gp.tileSize-3-13, y-10+44-3);










    }

    public void frame(Graphics2D g2, int x, int y, int width, int height)
    {
        Color c= new Color(0, 0, 0, 150);
        g2.setColor(c);
        g2.fillRoundRect(x,y, width, height, 35, 35);

        int border_width=2;
        c= new Color (255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(border_width));
        g2.drawRoundRect(x+border_width, y+border_width, width-border_width*2, height-border_width*2, 25, 25);

    }

    public void frame(Graphics2D g2, int x, int y, int width, int height, Color c1, Color c2)
    {

        g2.setColor(c1);
        g2.fillRoundRect(x,y, width, height, 35, 35);

        int border_width=5;

        g2.setColor(c2);
        g2.setStroke(new BasicStroke(border_width));
        g2.drawRoundRect(x+border_width, y+border_width, width-border_width*2, height-border_width*2, 25, 25);

    }

    public void frame(Graphics2D g2, int x, int y, int width, int height, Color c1, Color c2, int border)
    {

        g2.setColor(c1);
        g2.fillRoundRect(x,y, width, height, 35, 35);

        int border_width=border;

        g2.setColor(c2);
        g2.setStroke(new BasicStroke(border_width));
        g2.drawRoundRect(x+border_width, y+border_width, width-border_width*2, height-border_width*2, 25, 25);

    }

    public void frame2(Graphics2D g2, int x, int y, int width, int height, Color c1, Color c2, int border)
    {
        GradientPaint gradient = new GradientPaint(x, y, new Color(255, 0, 102,   150), x + width, y + height, new Color(51, 0, 204, 150));
        g2.setPaint(gradient);
        g2.fillRect(x, y, width, height);

        // Set border color and width
        int border_width = border;
        g2.setColor(new Color(204, 204, 204));
        g2.setStroke(new BasicStroke(border_width));
        g2.drawRect(x + border_width, y + border_width, width - border_width * 2, height - border_width * 2);


    }





    public void drawGameOverScreen(Graphics g2)
    {
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
//
//        int x=480;
//        int y;
//        String text;
//        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 120f));
//
//        text= "GAME OVER";
//        g2.setColor(Color.black);
//        y=gp.tileSize*10+20;
//        g2.drawString(text, x, y);
//        g2.setColor(Color.white);
//        g2.drawString(text, x-10, y-10);

        g2.drawImage(game_over, 540, 400, 603, 107, null);

    }

    public void drawDialogueScreen(Graphics2D g2)
    {
        this.g2=g2;

        int x=gp.tileSize*6;
        int y=gp.tileSize*2;
        int width= gp.screenWidth-(gp.tileSize*12);
        int height= gp.tileSize*4;

        Color c1= new Color(0, 0, 0, 150);
        Color c2= new Color(255, 255, 255);

        frame2(g2, x,y, width, height, c1, c2, 5);

        x+= gp.tileSize;
        y+= gp.tileSize;
        g2.setFont(purisaB);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        g2.setColor(Color.white);

        for(String line: currentDialogue.split("\n"))
        {
            g2.drawString(line, x, y);
            y+=40;
        }
    }


}


