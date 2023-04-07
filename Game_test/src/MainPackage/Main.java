package MainPackage;

import javax.swing.JFrame;
import MainPackage.GamePanel;
public class Main {
    public static void main(String[] args)

    {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        window.setResizable((false));
        window.setTitle("LEGEND OF THERIAN");  //our window name


        GamePanel gamePanel= new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null); //sets screen to middle of our laptop screen
        window.setVisible((true));

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}