package Hud;

import MainPackage.GamePanel;

public class Dialogue {

    GamePanel gp;

    public int dialogue_index=0;

    public String level0_dialogue_state="not_started";

    public String level1_dialogue_state="not_started";

    String [] level0_dialogue= new String[6];
    String [] level1_dialogue= new String[20];
    String [] level1_speaker= new String[20];

    public int dialogue_delay=0;
    public Dialogue(GamePanel gp)
    {
        this.gp=gp;
        set_Dialogue();
    }



    public void change_level0_dialogue()
    {
        if(dialogue_index>=5)
        {
            gp.game_state= gp.playState;
            level0_dialogue_state="finished";
            gp.hud.nextDialogue=level1_dialogue[0];
            gp.hud.currentDialogue = "M";
            dialogue_index=0;
        }
        else {

            gp.hud.speaker="YOUNG BOY";

            gp.hud.currentDialogue = level0_dialogue[dialogue_index];


            gp.hud.nextDialogue = level0_dialogue[dialogue_index+1];

            if (gp.keyH.dialogue_enter_pressed == true && dialogue_delay == 0) {
                dialogue_index++;
                gp.hud.charIndex=0;
                gp.hud.combined_text="";
                dialogue_delay = 50;
            }

            if (dialogue_delay > 0)
                dialogue_delay--;
        }
        }


    public void change_level1_dialogue()
    {
        if(dialogue_index>17)
        {
            gp.game_state= gp.playState;
            level1_dialogue_state="finished";
            dialogue_index=0;
        }
        else {
            gp.hud.speaker=level1_speaker[dialogue_index];

            gp.hud.currentDialogue = level1_dialogue[dialogue_index];
            gp.hud.nextDialogue = level1_dialogue[dialogue_index+1];

            if (gp.keyH.dialogue_enter_pressed == true && dialogue_delay == 0) {

                gp.hud.charIndex=0;
                dialogue_delay = 50;
                gp.hud.combined_text="";
                dialogue_index++;
            }

            if (dialogue_delay > 0)
                dialogue_delay--;

        }




    }

    void set_Dialogue()
    {
      level0_dialogue[0]="I never knew my father, the king of Therian. He was murdered \nwhen I was just a young boy and my family was banished from \nthe kingdom.";
        level0_dialogue[1]="My mother and I lived in hiding for many years until one day she \nrevealed the truth about our family's past on her deathbed.";
        level0_dialogue[2]="On her last breath, she urged me to avenge my father's murder \nand gave me a special scarf that held some mysterious power. \nBut her final words left me with more questions than answers.";
        level0_dialogue[3]="Determined to reclaim my birthright, I made my way to Therian \nwith the note from my mother containing the name of a man \nwho could help me.";
        level0_dialogue[4]="My journey was long and treacherous, but I pressed on. Finally, \nI reached the outskirts of Therian. ";
        level0_dialogue[5]="My journey was long and treacherous, but I pressed on. Finally, \nI reached the outskirts of Therian. ";

        level1_speaker[0]="YOUNG BOY";
        level1_dialogue[0]="My heart raced as I knocked on the old man's door. This was it.\nThe moment I had been waiting for.";
        level1_speaker[1]="OLD MAN";
        level1_dialogue[1]="Come in, young one, I have been waiting for you so long.";
        level1_speaker[2]="YOUNG BOY";
        level1_dialogue[2]="He welcomed me with his warm voice and started to tell me \nabout my family's history and the power we possessed. ";
        level1_speaker[3]="YOUNG BOY";
        level1_dialogue[3]="As he spoke, my mind was filled with the images of legendary \ncreatures and the betrayal that had led to my family's downfall.";
        level1_speaker[4]="OLD MAN";
        level1_dialogue[4]="My son,you are the last of your family's bloodline. You possess \nthe power of the therian, the power to transform into different \ncreatures. But this power comes with great responsibility.";
        level1_speaker[5]="YOUNG BOY";
        level1_dialogue[5]="What do you mean?";
        level1_speaker[6]="OLD MAN";
        level1_dialogue[6]="You have been chosen to bring justice to your family and claim \nback what is rightfully yours. But revenge is a dangerous path. \nIt will consume you if you let it.";
        level1_speaker[7]="YOUNG BOY";
        level1_dialogue[7]="I understand but how do I awaken my powers?";
        level1_speaker[8]="OLD MAN";
        level1_dialogue[8]="Through intense training and meditation, I will teach you.";
        level1_speaker[9]="OLD MAN";
        level1_dialogue[9]="But at first, take rest for today. You have walked a long path \nalone. We will start from tomorrow.";
        level1_speaker[10]="YOUNG BOY";
        level1_dialogue[10]="As he welcomed me, I went inside of his house. First time in a \nwhile, I will have roof over my head";
        level1_speaker[11]=" ";
        level1_dialogue[11]="FEW DAYS LATER.....";
        level1_speaker[12]="YOUNG BOY";
        level1_dialogue[12]="For weeks, I trained with the old man, pushing myself to the \nlimit. I struggled at first but I refused to give up. I knew I had to \nbe strong to take back what was rightfully mine.";
        level1_speaker[13]="YOUNG BOY";
        level1_dialogue[13]="Finally, after what felt like an eternity, I awakened my powers. \nThe old man handed me a special shuriken that passed down \nthrough generations of my family.";

        level1_speaker[14]="YOUNG BOY";
        level1_dialogue[14]="I took the shuriken, feeling its weight in my hand. This was it. \nThe moment I had been waiting for.";
        level1_speaker[15]="OLD MAN";
        level1_dialogue[15]="Use this wisely. But remember, revenge is a double-edged \nsword.";
        level1_speaker[16]="YOUNG BOY";
        level1_dialogue[16]="Thank you, sir. I won't let you down";
        level1_speaker[17]="YOUNG BOY";
        level1_dialogue[17]="With newfound strength and determination, I set out to reclaim \nwhat was rightfully mine.";
        level1_speaker[18]="YOUNG BOY";
        level1_dialogue[18]="With newfound strength and determination, I set out to reclaim \nwhat was rightfully mine.";
    }

}
