public class Dialog {

    public static void greeting(){
        Functions.slowPrint("Hello, Traveler! I'm Abernathy, what is your name?");
    }
    public static void intro(String playerName){
        Functions.slowPrint("Well then, it's nice to meet you, " + playerName + ". This is my world, coded with these two hands, and I use it to test out cool new features for my game.");
        System.out.println();
        Functions.slowPrint("All of the text you are seeing for now, has been written directly in the main class using standard println statements.");
        Functions.slowPrint("Later on, I want to learn how to implement JSON files to store and read dialog. I think that will be cleaner in the long run, but it's early days right now.");
        System.out.println();
        Functions.slowPrint("I'll talk more about that later, but for now just enjoy this test experience and remember, THIS IS A TEST AND I'M STILL LEARNING! :)");
        System.out.println();
    }
    public static void classSelection(){
        Functions.slowPrint("If you were the Hero of an Adventure, what class would you want to play?");
        Functions.slowPrint("1. Soldier");
        Functions.slowPrint("2. Ranger");
        Functions.slowPrint("3. Brawler");
        Functions.slowPrint("4. Mage");
    }

    public static void classSelectionLoop(){
        Functions.slowPrint("That's not one of the options! Try picking something that I have given you.");
        Functions.slowPrint("1. Soldier");
        Functions.slowPrint("2. Ranger");
        Functions.slowPrint("3. Brawler");
        Functions.slowPrint("4. Mage");
    }
    public static void weaponSelection(){
        Functions.slowPrint("Which would you prefer in a fight?");
        Functions.slowPrint("1. A Sword");
        Functions.slowPrint("2. A Wand");
        Functions.slowPrint("3. A Crossbow");
        Functions.slowPrint("4. Baseball Bat");
    }

    public static void weaponSelectionLoop(){
        Functions.slowPrint("That's not one of the options. Try picking something that I have available.");
        Functions.slowPrint("1. A Sword");
        Functions.slowPrint("2. A Wand");
        Functions.slowPrint("3. A Crossbow");
        Functions.slowPrint("4. Baseball Bat");
    }



}
