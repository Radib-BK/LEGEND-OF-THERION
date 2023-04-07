package Tile;

import MainPackage.GamePanel;

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
    public int mapTileNum[][][];

    int coin_animation_delay=0;
    int coin_sprite_counter=0;

    public int lazer_animation_delay=0;



    public int max_tile_count=35;

    BufferedImage border;



    public TileManager(GamePanel gp)
    {
        this.gp=gp;
        tile= new Tile[max_tile_count];
        mapTileNum=  new int[gp.maxMap][gp.MaxScreenCol][gp.MaxScreenRow];

        getTileImage();
        loadMap(0);
        loadMap(1);


    }

    public void getTileImage()
    {
        try
        {
            for(int i=0; i<max_tile_count; i++)
                tile[i]=new Tile();


            tile[0].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/background.png"));


            tile[1].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile.png"));


            tile[2].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile2.png"));


            tile[3].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile3.png"));


            tile[4].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile4.png"));




            tile[5].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile5.png"));


            tile[6].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile6.png"));


            tile[7].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile7.png"));


            tile[8].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile8.png"));


            tile[9].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile9.png"));


            tile[10].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile10.png"));


            tile[11].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile11.png"));


            tile[12].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile12.png"));


            tile[13].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile13.png"));


            tile[14].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile14.png"));

            //read machine shoot

            tile[15].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile15.png"));
            tile[16].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile16.png"));
            tile[17].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile17.png"));
            tile[18].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile18.png"));

            int solid_object_count=14;

            for(int i=1; i<=solid_object_count; i++)
                tile[i].collison="solid";

            tile[15].collison="solid";
            tile[17].collison="solid";



            tile[16].collison="damage";
            tile[18].collison="damage";

            //read other objects like coin or potion

            tile[20].image=ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile20.png"));
            tile[21].image=ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile21.png"));

            tile[25].image=ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile25 (1).png"));
            tile[26].image=ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile25 (2).png"));
            tile[27].image=ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile25 (3).png"));
            tile[28].image=ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile25 (4).png"));

            tile[29].image=ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile29.png"));

            tile[30].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile30.png"));
            tile[31].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/tile31.png"));

            tile[30].collison="solid";
            tile[31].collison="solid";

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
            map_dest = "/maps/map1.txt";
        else if(i==1)
            map_dest= "/maps/map2.txt";
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

            if(tileNum==31)
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

    public void draw(Graphics2D g2) {

        g2.drawImage(tile[0].image, 0, 0, gp.screenWidth / 2, gp.screenHeight, null);
        g2.drawImage(tile[0].image, gp.screenWidth / 2, 0, gp.screenWidth / 2, gp.screenHeight, null);
//        g2.drawImage(tile[1].image, 48, 0 , gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96, 0 , gp.tileSize, gp.tileSize, null);


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
