//import com.fasterxml.jackson.databind //Trying to figure out how to download and learn to use the Jackson library for JSON to POJO

public class Dialog {

    static String[][] chatTracker = {
            {   // [0,N] greeting dialog
                String.valueOf(false),
                "Hello, Traveler! I'm Abernathy, what is your name? Enter your desired name and then hit enter!"
            },
            {   // [1, N] intro dialog
                String.valueOf(false),
                "Well then, it's nice to meet you, ${playerName}. This is my world, coded with these two hands, and I use it to test out cool new features for my game.",
                "",
                "All of the text you are seeing for now, has been written in the \"Dialog.java\" class using println statements.",
                "Later on, I want to learn how to implement JSON files to store and read dialog. I think that will be cleaner in the long run, but it's early days right now.",
                "",
                "I'll talk more about that later, but for now just enjoy this test experience and remember, THIS IS A TEST AND I'M STILL LEARNING! :)",
                "Type 'Continue' when you are ready..."
            },
            {   // [2, N] Class Choice
                String.valueOf(false),
                "If you were the hero of an adventure, what class would you want to play? Type the name of the class you want.",
                "[Note -- Class attack modifier is determined by selected class type]",
                "1. Soldier",
                "2. Ranger",
                "3. Brawler",
                "4. Mage"
            },
            {   // [3, N] Weapon Choice
                String.valueOf(false),
                "Which of these weapons would you prefer to have in a fight?",
                "[Note -- Different stat bonuses apply to different kinds of weapons]",
                "1. A Sword",
                "2. A Crossbow",
                "3. A Bat",
                "4. A Wand"
            },
            {   // [4, N] Player stat presentation
                String.valueOf(false),
                "Alright, now that you've chosen your class and weapon I'll generate your stats...",
                    "[PLAYER NAME: ${playerName} | PLAYER CLASS: ${playerClass} (${attackModifier}) | PLAYER WEAPON: ${playerWeapon}]",
                    "[PLAYER LEVEL: ${playerLevel} | HP: ${currentHP} / ${maxHP} | HIT DIE: d${hitDie}]",
                    "[STRENGTH: ${strength} (${strMod}) | DEXTERITY: ${dexterity} (${dexMod}) | CONSTITUTION: ${constitution} (${conMod})]",
                    "[INTELLIGENCE: ${intelligence} (${intMod}) | WISDOM: ${wisdom} (${wisMod}) | CHARISMA: ${charisma} (${chaMod})]"
            },
//            {   // [5, N]
//            String.valueOf(false),
//                "Ok, you're now a fully generated character!",
//                    "Do you think I could entice you with a battle tutorial?",
//                    "",
//                    "I've got some practice fights setup for you. They aren't deadly I promise.",
//                    "Well...usually...",
//                    "Anywho...what do you think?",
//                    "Yes / No"
//            },
            {
                String.valueOf(false),
                "---GRAND DINING HALL---",
                    "You enter a grand dining hall, where the floor, walls, and ceiling are decorated in gold leaf.",
                    "A massive dining table that could easily seat 30 people is laden with steaming foods beyond your imagination.",
                    "Three small children are seated at the table, devouring the food before them. You try to get their attention, but they pay you no mind.",
                    "\nAlong the East wall, a door sits ajar. Through the crack you can see a forest, though it doesn't seem like the door should be going outside...",
                    "To the South, is a Large Black Door.",
                    "To the West, is a small white interior door."
            }
    };

    Integer chatLevel = 0;

    public static void chatter(String[] chatTracker){
        for(int i = 0; i < chatTracker.length; i++){
            Functions.slowPrint(chatTracker[i]);

        }
    }

    // Constructor for all npcs, just abernathy for now, continue to build out variables and overloaded constructor methods.
    public class NPC {
        boolean alreadyMet = false;
        boolean isAlive = true;
        boolean isHostile = false;
        String name = "";
        String location = "";
        int encounterNumber = 0;
    }

//<editor-fold defaultstate="collapsed" desc="old methods">
//    public static void greeting(){
//        Functions.slowPrint("Hello, Traveler! I'm Abernathy, what is your name?");
//    }
//    public static void intro(String playerName){
//        Functions.slowPrint("Well then, it's nice to meet you, " + playerName + ". This is my world, coded with these two hands, and I use it to test out cool new features for my game.");
//        System.out.println();
//        Functions.slowPrint("All of the text you are seeing for now, has been written in the \"Dialog.java\" class using println statements.");
//        Functions.slowPrint("Later on, I want to learn how to implement JSON files to store and read dialog. I think that will be cleaner in the long run, but it's early days right now.");
//        System.out.println();
//        Functions.slowPrint("I'll talk more about that later, but for now just enjoy this test experience and remember, THIS IS A TEST AND I'M STILL LEARNING! :)");
//        System.out.println();
//    }
//    public static void classSelection(){
//        Functions.slowPrint("If you were the Hero of an Adventure, what class would you want to play?");
//        Functions.slowPrint("1. Soldier");
//        Functions.slowPrint("2. Ranger");
//        Functions.slowPrint("3. Brawler");
//        Functions.slowPrint("4. Mage");
//    }
//
//    public static void classSelectionLoop(){
//        Functions.slowPrint("That's not one of the options! Try picking something that I have given you.");
//        Functions.slowPrint("1. Soldier");
//        Functions.slowPrint("2. Ranger");
//        Functions.slowPrint("3. Brawler");
//        Functions.slowPrint("4. Mage");
//    }
//    public static void weaponSelection(){
//        Functions.slowPrint("Which would you prefer in a fight?");
//        Functions.slowPrint("1. A Sword");
//        Functions.slowPrint("2. A Wand");
//        Functions.slowPrint("3. A Crossbow");
//        Functions.slowPrint("4. Baseball Bat");
//    }
//
//    public static void weaponSelectionLoop(){
//        Functions.slowPrint("That's not one of the options. Try picking something that I have available.");
//        Functions.slowPrint("1. A Sword");
//        Functions.slowPrint("2. A Wand");
//        Functions.slowPrint("3. A Crossbow");
//        Functions.slowPrint("4. Baseball Bat");
//    }
//</editor-fold>

}
