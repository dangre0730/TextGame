import java.util.Scanner;
public class Game {

    //Create objects that will be utilized in game. These objects can and will be accessed from other classes given their existence outside the main method and having 'public static'.
    public static Player player = new Player(); //Creates Player object that will store our PC's stats
    public static Enemy enemy = new Enemy(); //Create Enemy Object that has enemy names and stats.
    public static void main(String[] args) {

        //Global / Player Variable definitions

        //Integers
        int playerWeaponSelect;
        int playerClassSelection;
        int continueGameSelect;
        //Doubles
        //Booleans
        boolean hasLevelled = false;
        boolean continueGame = true;

        //Enemy Variable Definitions
        //Integers
        int enemyHealth;
        int enemyDamage;
        int enemyDamageMod;
        int enemyArmor;
        //Doubles
        double enemyCriticalChance;

        //Get player name
        Scanner getInput = new Scanner(System.in); //Create Scanner object which will be used to take input from the player.

        //Execute Dialog methods
        Dialog.greeting();
        player.name = getInput.nextLine(); //Take the string value, no matter the characters, and set the player objects 'playerName' string variable to the input value.
        Dialog.intro(player.name);    //Run the 'intro' method from the Dialog class and pass the player name that was just set.

        //Get PC Class choice
        Functions.slowPrint("Now, lets go ahead and have you pick a class.");
//        Classes determine the likelihood that certain stats will roll better than others,
//        Functions.slowPrint("    though that doesn't mean you can't roll poorly. Your stats are based on die rolls with different modifiers based on your");
//        Functions.slowPrint("    chosen class. Anyway, go ahead and pick a class so, we can proceed to the fun stuff.");
        Functions.slowPrint("1. Soldier");
        Functions.slowPrint("2. Ranger");
        Functions.slowPrint("3. Brawler");
        Functions.slowPrint("4. Mage");
        playerClassSelection = getInput.nextInt();

//        if((playerClassSelection != 1) && (playerClassSelection != 2) && (playerClassSelection != 3 ) && (playerClassSelection != 4)){
            while((playerClassSelection != 1) && (playerClassSelection != 2) && (playerClassSelection != 3 ) && (playerClassSelection != 4)){
                Functions.slowPrint("That's not one of the options. Try picking something that I have available.");
                Functions.slowPrint("1. Soldier");
                Functions.slowPrint("2. Ranger");
                Functions.slowPrint("3. Brawler");
                Functions.slowPrint("4. Mage");
                playerClassSelection = getInput.nextInt();
            }
//        }

        //Convert numeric class choice to ENUM class in Player class
        if(playerClassSelection == 1){
            player.pcClass = Player.classChoice.SOLDIER;
        }
        if(playerClassSelection == 2){
            player.pcClass = Player.classChoice.RANGER;
        }
        if(playerClassSelection == 3){
            player.pcClass = Player.classChoice.BRAWLER;
        }
        if(playerClassSelection == 4){
            player.pcClass = Player.classChoice.MAGE;
        }

        //Initialize PC stats and store them in the Player Class
        player.classBuilder();

        //List out player details
        player.statPrinter();

        //Get PC weapon selected
        Functions.slowPrint("Today is 'Character Building Day' and, great news, you are my Test Subject! So lets start with a simple questionnaire.");
        Functions.slowPrint("Which would you prefer in a fight? (Enter the corresponding number to the weapon of choice)");
        Functions.slowPrint("1. A Sword");
        Functions.slowPrint("2. A Wand");
        Functions.slowPrint("3. A Crossbow");
        Functions.slowPrint("4. Brass Knuckles");
        playerWeaponSelect = getInput.nextInt();

        while((playerWeaponSelect != 1) && (playerWeaponSelect != 2) && (playerWeaponSelect != 3 ) && (playerWeaponSelect != 4)){
            Functions.slowPrint("That's not one of the options. Try picking something that I have available.");
            Functions.slowPrint("1. A Sword");
            Functions.slowPrint("2. A Wand");
            Functions.slowPrint("3. A Crossbow");
            Functions.slowPrint("4. Baseball Bat");
            playerWeaponSelect = getInput.nextInt();
        }
//        System.out.println(I WANT TO RANDOMLY SELECT AN ENEMY FROM AN ENUM IN THE LEVELUP CLASS);
        //Convert numeric weapon choice to ENUM weapon in Player class
        if(playerWeaponSelect == 1){
            player.playerWeapon = Player.weaponChoice.SWORD;
        } else if(playerWeaponSelect == 2){
            player.playerWeapon = Player.weaponChoice.WAND;
        } else if(playerWeaponSelect == 3){
            player.playerWeapon = Player.weaponChoice.CROSSBOW;
        } else {
            player.playerWeapon = Player.weaponChoice.BAT;
        }
        //Initialize damage stats and store them in Player class
        player.setWeaponChoice();
        Functions.slowPrint("Ok, so you chose the " + player.playerWeapon + ". Which deals " + player.attackDamage + " base damage plus your damage modifier of " + player.damageMod + ", has an attack speed of " + player.attackSpeed + ", and " + player.criticalChance + "% critical chance.");

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
        enemyDamageMod = 0;
        enemyArmor = 0;
        enemyCriticalChance = 0.0;
        int earnedExp = 0;

        while(player.level != 4 && continueGame) {

            Functions.slowPrint("Your current level is: " + player.level + " and you need " + player.nextLevel + " experience to level up.");
            Functions.slowPrint("Your starting health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
            Functions.slowPrint("Enemy Health is " + enemyHealth);
            earnedExp = Functions.battleFrame(enemyHealth, player.attackDamage, player.damageMod, enemyDamage, enemyDamageMod, player.attackSpeed, player.classArmor, enemyArmor, player.criticalChance, enemyCriticalChance);
            player.experience += earnedExp;
            hasLevelled = Functions.hasLevelled(player.experience, player.nextLevel);
            if(hasLevelled){
                player.nextLevel = Functions.nextLevel(player.level, player.nextLevel);
                hasLevelled = false;
            }
            Functions.slowPrint("Your ending health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
//        player.playerHealth = player.maxPlayerHealth;
//        Functions.slowPrint("Health restored, current health is: " + player.playerHealth);
            Functions.slowPrint("You earned " + earnedExp + " experience for a grand total of " + player.experience);
            while (player.experience < 20) {
                Functions.slowPrint("Lets fight another enemy!");
                Functions.slowPrint("Your starting health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
                earnedExp = Functions.battleFrame(enemyHealth, player.attackDamage, player.damageMod, enemyDamage, enemyDamageMod, player.attackSpeed, player.classArmor, enemyArmor, player.criticalChance, enemyCriticalChance);
                player.experience += earnedExp;
                Functions.slowPrint("Your ending health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
                Functions.slowPrint("You earned " + earnedExp + " experience for a grand total of " + player.experience);
                hasLevelled = Functions.hasLevelled(player.experience, player.nextLevel);
                if(hasLevelled){
//                    player.playerLevel += 1;
                    player.nextLevel = Functions.nextLevel(player.level, player.nextLevel);
                    hasLevelled = false;
                }
            }
//            player.playerLevel += 1;


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
