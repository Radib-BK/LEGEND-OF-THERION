package MainPackage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener
{

  public boolean upPressed, downPressed, leftPressed, rightPressed, onePressed, twoPressed, spacePressed, H_Pressed, T_Pressed;

  public boolean dialogue_enter_pressed;

  GamePanel gp;

  public KeyHandler(GamePanel gp)
  {
  this.gp=gp;
  }

  public void keyTyped(KeyEvent e)
  {
      //abstract method..so we must override
  }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(gp.game_state==gp.playState) {
            if (code == KeyEvent.VK_UP)
                upPressed = true;
            if (code == KeyEvent.VK_DOWN)
                downPressed = true;
            if (code == KeyEvent.VK_LEFT)
                leftPressed = true;
            if (code == KeyEvent.VK_RIGHT)
                rightPressed = true;
            if (code == KeyEvent.VK_1) // check for '1' key
                onePressed = true;
            if (code == KeyEvent.VK_2) // check for '2' key
                twoPressed = true;
            if (code == KeyEvent.VK_SPACE)
                spacePressed = true;
            if (code == KeyEvent.VK_H)
                H_Pressed = true;
            if (code == KeyEvent.VK_T)
                T_Pressed = true;
            if (code==KeyEvent.VK_Q)
                gp.game_state=gp.upgradeState;

        }

        else if(gp.game_state==gp.dialogueState)
        {
            if(code==KeyEvent.VK_ENTER)
            {
                dialogue_enter_pressed=true;
            }


        }
        else if(gp.game_state==gp.upgradeState)
        {
            if(code==KeyEvent.VK_ESCAPE)
                gp.game_state=gp.playState;
            if (code == KeyEvent.VK_UP)
                upPressed = true;
            if (code == KeyEvent.VK_DOWN)
                downPressed = true;
            if (code == KeyEvent.VK_LEFT)
                leftPressed = true;
            if (code == KeyEvent.VK_RIGHT)
                rightPressed = true;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

      ///MUST SET TO FALSE WHEN KEY IS RELEASED

        int code= e.getKeyCode();

        if(code==KeyEvent.VK_UP)
            upPressed=false;
        if(code== KeyEvent.VK_DOWN)
            downPressed=false;
        if(code== KeyEvent.VK_LEFT)
            leftPressed=false;
        if(code== KeyEvent.VK_RIGHT)
            rightPressed=false;
        if (code== KeyEvent.VK_1) // check for '1' key
            onePressed = false;
        if (code== KeyEvent.VK_2) // check for '2' key
            twoPressed = false;
        if (code == KeyEvent.VK_SPACE)
            spacePressed = false;
        if (code == KeyEvent.VK_H)
            H_Pressed = false;
        if (code == KeyEvent.VK_T)
            T_Pressed = false;
        if(code==KeyEvent.VK_ENTER)
            dialogue_enter_pressed=false;



    }
}
