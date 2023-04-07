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
    public int getSavedGame () throws SQLException {
        ResultSet rs = getQueryResult("select * from saved_game where id = 1");

        int map = 0;
        while(rs.next()){
            map = rs.getInt(3);
        }

        return map;
    }
    public void setPlayer (GamePanel gp) {
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