import java.util.Scanner;
public class Game {

    public static Player player = new Player(); //Creates Player object that will store our PC's stats

    public static void main(String[] args) {


//      Variable definitions
        String playerWeapon;
        player.playerExp = 0;
        int weaponSelect;
        int classSelection;
        int enemyHealth;
        int enemyDamage;
        int continueGameSelect;
        int enemyArmor;
        double enemyCritChance;
        boolean continueGame = true;

        //Get player name
        Scanner getInput = new Scanner(System.in);
        System.out.println("Hello, Traveler! I'm Abernathy, what is your name?");
        player.playerName = getInput.nextLine();

        //Get PC weapon selected
        Functions.slowPrint("Well then, it's nice to meet you, " + player.playerName + ". This is my world, made with these two hands figuratively speaking, and I use it to test out cool new features for my game.");
        System.out.println();
        Functions.slowPrint("All of the text you are seeing for now, has been written directly in the main class using standard println statements.");
        Functions.slowPrint("Later on, I will be learning how to implement JSON files to store and read dialog. I think that will be cleaner in the long run, but it's early days right now.");
        System.out.println();
        Functions.slowPrint("I'll talk more about that later, but for now just enjoy this test experience and remember, THIS IS A TEST AND I'M STILL LEARNING! :)");
        System.out.println();
        Functions.slowPrint("Today is 'Character Building Day' and, great news, you are my Test Subject! So lets start with a simple questionnaire.");
        Functions.slowPrint("Which would you prefer in a fight? (Enter the corresponding number to the weapon of choice)");
        Functions.slowPrint("1. A Sword");
        Functions.slowPrint("2. A Wand");
        Functions.slowPrint("3. A Crossbow");
        Functions.slowPrint("4. Brass Knuckles");
        weaponSelect = getInput.nextInt();

        while((weaponSelect != 1) && (weaponSelect != 2) && (weaponSelect != 3 ) && (weaponSelect != 4)){
            Functions.slowPrint("That's not one of the options. Try picking something that I have available.");
            Functions.slowPrint("1. A Sword");
            Functions.slowPrint("2. A Wand");
            Functions.slowPrint("3. A Crossbow");
            Functions.slowPrint("4. Baseball Bat");
            weaponSelect = getInput.nextInt();
        }
//        System.out.println(I WANT TO RANDOMLY SELECT AN ENEMY FROM AN ENUM IN THE LEVELUP CLASS);
        //Convert numeric weapon choice to ENUM weapon in Player class
        if(weaponSelect == 1){
            player.playerWeapon = Player.weaponChoice.SWORD;
        } else if(weaponSelect == 2){
            player.playerWeapon = Player.weaponChoice.WAND;
        } else if(weaponSelect == 3){
            player.playerWeapon = Player.weaponChoice.CROSSBOW;
        } else {
            player.playerWeapon = Player.weaponChoice.BAT;
        }
        //Initialize damage stats and store them in Player class
        player.setWeaponChoice();
        Functions.slowPrint("Ok, so you chose the " + player.playerWeapon + ". Which deals " + player.attackDamage + " damage, has an attack speed of " + player.attackSpeed + ", and " + player.critChance + "% critical chance.");

        //Get PC Class choice
        Functions.slowPrint("Now, lets go ahead and have you pick a class.");
//        Classes determine the likelihood that certain stats will roll better than others, ");
//        Functions.slowPrint("    though that doesn't mean you can't roll poorly. Your stats are based on die rolls with different modifiers based on your");
//        Functions.slowPrint("    chosen class. Anyway, go ahead and pick a class so, we can proceed to the fun stuff.");
        Functions.slowPrint("1. Soldier");
        Functions.slowPrint("2. Ranger");
        Functions.slowPrint("3. Brawler");
        Functions.slowPrint("4. Mage");
        classSelection = getInput.nextInt();

//        if((classSelection != 1) && (classSelection != 2) && (classSelection != 3 ) && (classSelection != 4)){
            while((classSelection != 1) && (classSelection != 2) && (classSelection != 3 ) && (classSelection != 4)){
                Functions.slowPrint("That's not one of the options. Try picking something that I have available.");
                Functions.slowPrint("1. Soldier");
                Functions.slowPrint("2. Ranger");
                Functions.slowPrint("3. Brawler");
                Functions.slowPrint("4. Mage");
                classSelection = getInput.nextInt();
            }
//        }

        //Convert numeric class choice to ENUM class in Player class
        if(classSelection == 1){
            player.pcClass = Player.classChoice.SOLDIER;
        }
        if(classSelection == 2){
            player.pcClass = Player.classChoice.RANGER;
        }
        if(classSelection == 3){
            player.pcClass = Player.classChoice.BRAWLER;
        }
        if(classSelection == 4){
            player.pcClass = Player.classChoice.MAGE;
        }

        //Initialize PC stats and store them in the Player Class
        player.classBuilder();

        //List out player details
        System.out.println("Ok, you are a " + player.pcClass + " and as one, your stats have been randomly generated as: ");
        System.out.println("Health: " + player.playerHealth + " | Max Health: " + player.maxPlayerHealth + " | Hit Die: " + player.hitDie);
        System.out.println("Strength: " + player.playerStrength + " |  Str Mod: " + player.strMod);
        System.out.println("Dexterity: " + player.playerDexterity + " | Dex Mod: " + player.dexMod);
        System.out.println("Constitution: " + player.playerConstitution + " | Con Mod: " + player.conMod);
        System.out.println("Intelligence: " + player.playerIntelligence + " | Int Mod: " + player.intMod);
        System.out.println("Wisdom: " + player.playerWisdom + " | Wis Mod: " + player.wisMod);
        System.out.println("Charisma: " + player.playerCharisma + " | Cha Mod: " + player.chaMod);

        //Describe what each stat governs in the game.
//        Functions.slowPrint();("Ok, great! All of those values above are your Character Stats. Those play super important roles in my world and are vital for interacting with my world in a significant way.");
//        Functions.slowPrint();("Here are some examples to help things make sense:");
//        Functions.slowPrint();("    Let's say you are trying to climb up a wall using a rope. I will make you roll a strength check, which means a d20 plus your Strength Modifier.");
//        Functions.slowPrint();("        For you, that would be a modifier of " + player.strMod + ". And if you were grappled by an enemy you would have to make a strength save, using the same roll method.");
//        Functions.slowPrint();("    The same goes for Dexterity: Stealing an item - Dexterity Check | Dodging a Fireball - Dexterity Save");
//        Functions.slowPrint();("    Constitution: Constitution Modifier is added to hit die when getting extra health after a level up");
//        Functions.slowPrint();("    Intelligence: You are trying to solve a riddle - Intelligence Check | Seeing through an enemy charming you");
//        Functions.slowPrint();("    Wisdom: ");
//        Functions.slowPrint();("    Charisma: ");

        Functions.slowPrint("Now that we've covered stats, lets test out your weapon on a training dummy. Don't worry, it usually won't hit back.");
        enemyHealth = 15;
        enemyDamage = 2;
        enemyArmor = 0;
        enemyCritChance = 0.0;
        int earnedExp = 0;

        while(player.playerLevel != 4 && continueGame) {

            Functions.slowPrint("Your current level is: " + player.playerLevel);
            Functions.slowPrint("Your starting health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
            earnedExp = Functions.battleFrame(enemyHealth, player.attackDamage, enemyDamage, player.attackSpeed, player.classArmor, enemyArmor, player.critChance, enemyCritChance);
            player.playerExp += earnedExp;
            Functions.slowPrint("Your ending health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
//        player.playerHealth = player.maxPlayerHealth;
//        Functions.slowPrint("Health restored, current health is: " + player.playerHealth);
            Functions.slowPrint("You earned " + earnedExp + " experience for a grand total of " + player.playerExp);
            while (player.playerExp < 20) {
                Functions.slowPrint("Lets fight another enemy!");
                Functions.slowPrint("Your starting health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
                earnedExp = Functions.battleFrame(enemyHealth, player.attackDamage, enemyDamage, player.attackSpeed, player.classArmor, enemyArmor, player.critChance, enemyCritChance);
                player.playerExp += earnedExp;
                Functions.slowPrint("Your ending health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
                Functions.slowPrint("You earned " + earnedExp + " experience for a grand total of " + player.playerExp);
            }
            player.playerLevel += 1;
            Functions.slowPrint("New Level: " + player.playerLevel);
            Functions.slowPrint("Look at that, your Character Experience total went up enough to gain a new level! When you hit certain milestones of experience, you will level up and earn new class features.");

            player.statPrinter();

            if (player.playerHealth > 2) {
                Functions.slowPrint("Since you are still standing, would you like to practice some more?");
                Functions.slowPrint("1. Keep Playing!");
                Functions.slowPrint("2. Exit Simulation!");
                continueGameSelect = getInput.nextInt();

                if (continueGameSelect == 1) {
                    continue;
                } else if (continueGameSelect == 2) {
                    continueGame = false;
                } else {
                    continueGame = false;
                }
            }
        }

        System.out.println("Next up, I need to get the battleframe function + playerExp going into the fight to check if a level up occurred after the fight concluded.");
        System.out.println("We are going to need to write a levelUp method that checks if the player has levelled up using playerExp. Then notify them and roll a dice for hp gained and improve a stat at level 4.");
        System.out.println("Good luck with that homie! You got this and remember, this is literally just for fun. You've always wanted to tell a story through a game and this is our pre-rough draft.");
        System.out.println("I believe in you");
    }

}
