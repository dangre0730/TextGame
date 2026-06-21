//import com.fasterxml.jackson.databind //Trying to figure out how to download and learn to use the Jackson library for JSON to POJO
//  On second thought, we probably won't ever use this, as I think I heard that POJOs are deprecated functionality.
//  NOTE NOTE NOTE!!!!::::: Need to confirm that doing a JSON to POJO change would be a waste of time at some point!!!

public class Dialog {

    // ============================================
    //      [Class Object &/OR Variable] Declarations
    // ============================================

    // region

    // 2D String array that stores the intro character building dialog
    static String[][] chatTracker = {
            {   // [0,N] greeting dialog
                    "Hello, Traveler! I'm Abernathy, what is your name? Enter your desired name and then hit enter!"
            },
            {   // [1, N] intro dialog
                    "Well then, it's nice to meet you, ${playerName}. This is my world, coded with these two hands, and I use it to test out cool new features for my game.",
                    "",
                    "All of the text you are seeing for now, has been written in the \"Dialog.java\" class using println statements.",
                    "Later on, I want to learn how to implement JSON files to store and read dialog. I think that will be cleaner in the long run, but it's early days right now.",
                    "",
                    "I'll talk more about that later, but for now just enjoy this test experience and remember, THIS IS A TEST AND I'M STILL LEARNING! :)",
                    "Type 'Continue' when you are ready..."
            },
            {   // [2, N] Class Choice
                    "If you were the hero of an adventure, what class would you want to play? Type the name of the class you want.",
                    "[Note -- Class attack modifier is determined by selected class type]",
                    "1. Soldier",
                    "2. Ranger",
                    "3. Brawler",
                    "4. Mage"
            },
            {   // [3, N] Weapon Choice
                    "Which of these weapons would you prefer to have in a fight?",
                    "[Note -- Different stat bonuses apply to different kinds of weapons]",
                    "1. A Sword",
                    "2. A Crossbow",
                    "3. A Bat",
                    "4. A Wand"
            },
            {   // [4, N] Player stat presentation
                    "Alright, now that you've chosen your class and weapon I'll generate your stats...",
                    "[PLAYER NAME: ${playerName} | PLAYER CLASS: ${playerClass} (${classMod}) | PLAYER WEAPON: ${playerWeapon}]",
                    "[PLAYER LEVEL: ${playerLevel} | HP: ${currentHP} / ${maxHP} | HIT DIE: d${hitDie}]",
                    "[STRENGTH: ${strength} (${strMod}) | DEXTERITY: ${dexterity} (${dexMod}) | CONSTITUTION: ${constitution} (${conMod})]",
                    "[INTELLIGENCE: ${intelligence} (${intMod}) | WISDOM: ${wisdom} (${wisMod}) | CHARISMA: ${charisma} (${chaMod})]",
                    "",
                    "Type 'Continue' when you are ready..."
            },
            {
                    "---BEGIN EXPLORATION?---",
                    "Now that we understand who you are, you are welcome to explore the manor's grounds.",
                    "What would you like to do?",
                    "",
                    "1. Explore",
                    "2. Quit"
            }
//            {   // [5, N]
//                "Ok, you're now a fully generated character!",
//                    "Do you think I could entice you with a battle tutorial?",
//                    "",
//                    "I've got some practice fights setup for you. They aren't deadly I promise.",
//                    "Well...usually...",
//                    "Anywho...what do you think?",
//                    "Yes / No"
//            }
    };

    Integer chatLevel = 0;

    // endregion


    // ============================================
    //      [Class Builder] Methods
    // ============================================

    // region

    // Constructor for all npcs, just abernathy for now, continue to build out variables and overloaded constructor methods.
    public class NPC {
        boolean alreadyMet = false;
        boolean isAlive = true;
        boolean isHostile = false;
        String name = "";
        String location = "";
        int encounterNumber = 0;
    }

    // endregion


    // ============================================
    //      [Class Updater] Methods
    // ============================================

    // region



    // endregion


    // ============================================
    //      [Event / Take Action] Methods
    // ============================================

    // region



    // endregion


    // ============================================
    //      Deprecated Methods
    // ============================================

    // region



    // endregion


    public static void chatter(String[] chatTracker){
        for(int i = 0; i < chatTracker.length; i++){
            Functions.slowPrint(chatTracker[i]);

        }
    }

}
