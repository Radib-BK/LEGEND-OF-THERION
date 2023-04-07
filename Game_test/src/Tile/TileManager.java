package Tile;

import MainPackage.GamePanel;
import MainPackage.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager
{
    GamePanel gp;
    public Tile[] tile;
    public Tile[] background;
    public int mapTileNum[][][];

    BufferedImage oldManHouse, oldManHouseOpen;

    int coin_animation_delay=0;
    int coin_sprite_counter=0;

    public int lazer_animation_delay=0;



    public int max_tile_count=60;

    BufferedImage border;



    public TileManager(GamePanel gp)
    {
        this.gp=gp;
        tile= new Tile[max_tile_count];
        background= new Tile[gp.maxMap];
        mapTileNum=  new int[gp.maxMap][gp.MaxScreenCol][gp.MaxScreenRow];


        getTileImage();
        for(int i=0; i<=6; i++)
        loadMap(i);



    }

    public void getTileImage()
    {
        try
        {
            for(int i=0; i<max_tile_count; i++)
                tile[i]=new Tile();
            for(int i=0; i<gp.maxMap; i++)
                background[i]=new Tile();


            background[4].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/background.png"));
            background[0].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/winter_background.jpg"));
            background[1].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/winter_background.png"));
            background[2].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/mountain.jpg"));


            oldManHouse= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/house.png"));
            oldManHouseOpen= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/house_open.png"));



            setup(1,"tile", "solid");
            setup(2,"tile2", "solid");
            setup(3,"tile3", "solid");
            setup(4,"tile4", "solid");
            setup(5,"tile5", "solid");
            setup(6,"tile6", "solid");
            setup(7,"tile7", "solid");
            setup(8,"tile8", "solid");
            setup(9,"tile9", "death");
            setup(10,"tile10", "death");
            setup(11,"tile11", "solid");
            setup(12,"tile12", "solid");
            setup(13,"tile13", "solid");
            setup(14,"tile14", "solid");
            setup(15,"tile15", "solid");
            setup(16,"tile16", "damage");
            setup(17,"tile17", "solid");
            setup(18,"tile18", "damage");
            setup(20,"tile20", " ");
            setup(21,"tile21", " ");
            setup(25,"tile25 (1)", " ");
            setup(26,"tile25 (2)", " ");
            setup(27,"tile25 (3)", " ");
            setup(28,"tile25 (4)", " ");
            setup(29,"tile29", " ");
            setup(30,"tile30", "solid");
            setup(31,"tile31", "solid");
            setup(32,"tile32", "solid");
            setup(33,"tile33", "solid");

            setup(40,"tile40", "solid");
            setup(41,"tile41", "solid");
            setup(42,"tile42", "solid");
            setup(43,"tile43", "solid");
            setup(44,"tile44", "solid");
            setup(45,"tile45", "solid");
            setup(46,"tile46", "solid");
            setup(47,"tile47", "solid");
            setup(48,"tile48", "solid");
            setup(49,"tile49", "solid");
            setup(50,"tile50", "solid");
            setup(51,"tile51", "death");
            setup(52,"tile52", "solid");
            setup(53,"tile53", "solid");
            //(52,"tile52", "solid");




            //read machine shoot










            //read other objects like coin or potion





            border = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/border.png"));


        }
        catch (IOException e)
        {

        }


    }

    public void loadMap(int i)
    {
        String map_dest = new String();
        if(i==0)
            map_dest = "/maps/map0.txt";
        else if(i==1)
            map_dest= "/maps/map1.txt";
        else if(i==2)
            map_dest = "/maps/map2.txt";
        else if(i==3)
            map_dest= "/maps/map3.txt";
        else if(i==4)
            map_dest= "/maps/map4.txt";
        else if(i==5)
            map_dest= "/maps/map5.txt";
        else if(i==6)
            map_dest="/maps/map6.txt";
        try{

            InputStream is= getClass().getResourceAsStream(map_dest);

            BufferedReader br= new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row=0;

            while(col<=gp.MaxScreenCol && row<gp.MaxScreenRow)
            {
                String line = br.readLine();

                while(col<gp.MaxScreenCol)
                {
                    String numbers[]= line.split("\\s+");

                    //this will replace with one or more space, just using " " would split by one space only



                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[i][col][row]= num;
                    col++;
                }
                if(col==gp.MaxScreenCol)
                {
                    col=0;
                    row++;
                }

            }
            br.close();
        }
        catch (Exception e){

        }
    }

    public void open_door()
    {
        int col=0;
        int row=0;
        int x=0;
        int y=0;



        while(col<gp.MaxScreenCol && row< gp.MaxScreenRow)
        {
            int tileNum= mapTileNum[gp.currentMap][col][row];

            if(tileNum==32 || tileNum==33)
                mapTileNum[gp.currentMap][col][row]=0;

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

    public void draw_background(Graphics2D g2)
    {
        if(gp.currentMap==0) {


            g2.drawImage(background[0].image, 0 , 0, gp.screenWidth , gp.screenHeight, null);


        }
        else if(gp.currentMap==1)
        {
            g2.drawImage(background[1].image, 0 , 0, gp.screenWidth , gp.screenHeight, null);

            if(gp.player.x<1085)
                g2.drawImage(oldManHouse, 800 , 220, 600 , 500, null);
            else
                g2.drawImage(oldManHouseOpen, 800 , 220, 600 , 500, null);
        }
        else if(gp.currentMap==2 || gp.currentMap==3)
        {
            g2.drawImage(background[2].image, 0 , 0, gp.screenWidth , gp.screenHeight, null);
        }

        else if(gp.currentMap==4 || gp.currentMap==5 || gp.currentMap==6) {
            g2.drawImage(background[4].image, 0, 0, gp.screenWidth / 2, gp.screenHeight, null);
            g2.drawImage(background[4].image, gp.screenWidth / 2, 0, gp.screenWidth / 2, gp.screenHeight, null);

        }//        g2.drawImage(tile[1].image, 48, 0 , gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96, 0 , gp.tileSize, gp.tileSize, null);

    }


    public void draw(Graphics2D g2) {





        int col=0;
        int row=0;
        int x=0;
        int y=0;



        while(col<gp.MaxScreenCol && row< gp.MaxScreenRow)
        {
            int tileNum= mapTileNum[gp.currentMap][col][row];



            if(tileNum==25)
                g2.drawImage(tile[tileNum+coin_sprite_counter].image, x, y, gp.tileSize, gp.tileSize, null);
            else if((tileNum==16 || tileNum==18) && lazer_animation_delay>=60); //print nothing
            else if(tileNum!=0)
                g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);

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

        coin_animation_delay=(++coin_animation_delay)%10;

        if(coin_animation_delay==0)
            coin_sprite_counter=(++coin_sprite_counter)%4;

        lazer_animation_delay=(++lazer_animation_delay)%120;








    }

    public void setup(int index, String imagePath, String collison)
    {
        UtilityTool uTool= new UtilityTool();

        try{
            tile[index]= new Tile();
            tile[index].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/"+imagePath+".png"));
            tile[index].image=uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collison=collison;
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void draw_border(Graphics2D g2) {





        int col=0;
        int row=0;
        int x=0;
        int y=0;



        while(col<gp.MaxScreenCol && row< gp.MaxScreenRow)
        {




                g2.drawImage(border, x, y, gp.tileSize, gp.tileSize, null);

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
