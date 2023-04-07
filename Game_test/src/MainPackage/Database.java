package MainPackage;

import java.sql.*;

public class Database {
    private ResultSet getQueryResult (String query) {
        ResultSet rs = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "user1138", "1138");
            Statement stmt = con.createStatement();

            rs = stmt.executeQuery(query);
        } catch(Exception e)
        {
            System.out.println(e);
        }

        return rs;
    }

    public int[] getPlayer (GamePanel gp) throws SQLException {
        ResultSet rs = getQueryResult("select * from player where saved_game_id = 1 and map= "+gp.currentMap);

        int arr[]= new int[4];


        while(rs.next()){
            arr[0] = rs.getInt(3);
            arr[1] = rs.getInt(4);
            arr[2] = rs.getInt(5);
            arr[3] = rs.getInt(6);


        }

        return arr;
    }

    public int[] getUpgrade (GamePanel gp) throws SQLException {
        ResultSet rs = getQueryResult("select * from upgrade_level where saved_game_id= 1 and map= "+gp.currentMap);

        int arr[]= new int[15];


        while(rs.next()){
           for(int i=0; i<15; i++)
           {
               arr[i]=rs.getInt(i+3);
           }


        }

        return arr;
    }



    public int getSavedGame () throws SQLException {
        ResultSet rs = getQueryResult("select * from saved_game where id = 1");

        int map = 0;
        while(rs.next()){
            map = rs.getInt(3);
        }

        return map;
    }
    public void setPlayer (GamePanel gp) {

        getQueryResult("delete from player where saved_game_id = 1 and map= "+gp.currentMap);

        String upd = "insert into player values (1, " +gp.currentMap + "," + gp.player.current_health + "," + gp.player.coin_count + ","+ gp.player.health_potion_count+ ", "+ gp.player.boost_potion_count+ ")";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "user1138", "1138");

            Statement stmt = con.createStatement();

            stmt.executeUpdate(upd);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUpgrade (GamePanel gp, int upgrade_level[]) {

        getQueryResult("delete from upgrade_level where saved_game_id = 1 and map= "+gp.currentMap);

        String upd = "insert into upgrade_level values (1, " +gp.currentMap + "," + upgrade_level[0] + "," + upgrade_level[1] + ","+ upgrade_level[2]+ ", "+ upgrade_level[3]+","  + upgrade_level[4]+ ", "+ upgrade_level[5]+ ", "+ upgrade_level[6]+ ", "+upgrade_level[7]+ ", "+ upgrade_level[8]+ ", "+  upgrade_level[9]+ ", "+  upgrade_level[10]+ ", "+ upgrade_level[11]+ ", "+ upgrade_level[12]+ ", "+ upgrade_level[13]+ ", "+ upgrade_level[14]+ "  )";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "user1138", "1138");

            Statement stmt = con.createStatement();

            stmt.executeUpdate(upd);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void setSavedGame (GamePanel gp) {
        String upd = "update saved_game set current_map = " + gp.currentMap +" where id = 1";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "user1138", "1138");

            Statement stmt = con.createStatement();

            stmt.executeUpdate(upd);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}