import java.util.Scanner;
public class Game {

    //Create objects that will be utilized in game. These objects can and will be accessed from other classes given their existence outside the main method and having 'public static'.
    public static Player player = new Player(); //Creates Player object that will store our PC's stats
    public static Enemy enemy = new Enemy(); //Create Enemy Object that has enemy names and stats.
    public static Room room = new Room(); //Create Room Object that will track where the player is, room description, and adjacent rooms.
    public static void main(String[] args) {

        //Global / Player Variable definitions

        //Integers
        int playerWeaponSelect;
        int playerClassSelection;
        int continueGameSelect;
        //Doubles
        //Booleans
        boolean hasLevelled;
        boolean continueGame = true;
        //Strings
        String roomSelect;

        //Get player name
        Scanner getInput = new Scanner(System.in); //Create Scanner object which will be used to take input from the player.

        //Execute Dialog methods
        Dialog.greeting();
        player.name = getInput.nextLine(); //Take the string value, no matter the characters, and set the player objects 'playerName' string variable to the input value.
        Dialog.intro(player.name);    //Run the 'intro' method from the Dialog class and pass the player name that was just set.

        Functions.slowPrint("Today is \"Character Building Day\" and, great news, you are my Test Subject! So lets start with a simple questionnaire.");

        //Get PC Class choice
        Dialog.classSelection();
        playerClassSelection = getInput.nextInt();

        while((playerClassSelection != 1) && (playerClassSelection != 2) && (playerClassSelection != 3 ) && (playerClassSelection != 4)){
            Dialog.classSelectionLoop();
            playerClassSelection = getInput.nextInt();
        }

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
        Dialog.weaponSelection();
        playerWeaponSelect = getInput.nextInt();

        while((playerWeaponSelect != 1) && (playerWeaponSelect != 2) && (playerWeaponSelect != 3 ) && (playerWeaponSelect != 4)){
            Dialog.weaponSelectionLoop();
            playerWeaponSelect = getInput.nextInt();
        }

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

        Functions.slowPrint("Now that we've covered stats, lets test out your combat skills! Don't worry, your enemies won't hit back very hard :)");
        int earnedExp;

        while(player.level != 4 && continueGame) {
            Functions.slowPrint("Your current level is: " + player.level + " and you need " + player.nextLevel + " experience to level up.");
            Functions.slowPrint("Your starting health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);

            enemy.enemySelection = Functions.rollEnemy();
//            enemy.getEnemy(enemy.enemySelection);
            enemy.enemyStats(enemy.enemySelection);

            earnedExp = Functions.battleFrame(enemy.enemyHealth, player.attackDamage, player.damageMod, enemy.enemyDamage, enemy.enemyDamageMod, player.attackSpeed, player.classArmor, enemy.enemyArmor, player.criticalChance, enemy.enemyCriticalChance);
            player.experience += earnedExp;
            Functions.slowPrint("You currently have " + player.experience + " experience points. In order to reach the next level, you need " + player.nextLevel + " total experience points to level up.");
            hasLevelled = Functions.hasLevelled(player.experience, player.nextLevel);
            if(hasLevelled){
                player.nextLevel = Functions.nextLevel(player.level, player.nextLevel);
            }
            Functions.slowPrint("Your ending health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
//            Functions.slowPrint("You earned " + earnedExp + " experience for a grand total of " + player.experience);
            while (player.experience < 20) {
                enemy.enemySelection = Functions.rollEnemy();
//                enemy.getEnemy(enemy.enemySelection);
                enemy.enemyStats(enemy.enemySelection);
                Functions.slowPrint("Lets fight another enemy!");
                Functions.slowPrint("Your starting health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
                earnedExp = Functions.battleFrame(enemy.enemyHealth, player.attackDamage, player.damageMod, enemy.enemyDamage, enemy.enemyDamageMod, player.attackSpeed, player.classArmor, enemy.enemyArmor, player.criticalChance, enemy.enemyCriticalChance);
                player.experience += earnedExp;
                Functions.slowPrint("Your ending health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
//                Functions.slowPrint("You earned " + earnedExp + " experience for a grand total of " + player.experience);
                hasLevelled = Functions.hasLevelled(player.experience, player.nextLevel);
                if(hasLevelled){
                    player.nextLevel = Functions.nextLevel(player.level, player.nextLevel);
                }
            }

            Functions.slowPrint("You currently have " + player.experience + " experience points. In order to reach the next level, you need " + player.nextLevel + " total experience points to level up.");
//            player.statPrinter();

            if (player.playerHealth > 2) {
                Functions.slowPrint("Since you are still standing, would you like to practice some more?");
                Functions.slowPrint("1. Keep Playing!");
                Functions.slowPrint("2. Exit Simulation!");
                continueGameSelect = getInput.nextInt();

                if (continueGameSelect != 1) {
                    continueGame = false;
                }
            }
        }

        Functions.slowPrint("Hey buddy, it's me, Abernathy, again. Would you mind terribly if I placed you in my new house?");
        Functions.slowPrint("It's still under construction, but check out the couple of rooms that have been built and let me know if you get lost!");
        Functions.slowPrint("Although, I suppose it will be hard for you to let me know if you are lost...ehh, lets not worry about that right now.");
        Functions.slowPrint("Ah and one last thing. If you wish to leave the house, at any point, simply say 'Exit' or 'exit' and you will be allowed to leave.");
        System.out.println();

        //Initiate room traversal section
        room.currentRoom = 1; //Set player into the Entry room of the Mansion.
        room.getRoom(room.currentRoom); //Initialize the variables in the room object, so that they can be printed for player.
        Functions.slowPrint(room.description); //Print currentRoom Description.
        //Super important to remember!! USE '.next', NOT '.nextLine'!! It will enter a carriage return instead of prompting for input.
        roomSelect = getInput.next();
        while(!roomSelect.equals("Exit") || !roomSelect.equals("exit")) {
            switch (roomSelect) {
                case "North", "north" -> room.currentRoom = room.adjacentRooms[0];
                case "East", "east" -> room.currentRoom = room.adjacentRooms[1];
                case "South", "south" -> room.currentRoom = room.adjacentRooms[2];
                case "West", "west" -> room.currentRoom = room.adjacentRooms[3];
            }
            room.getRoom(room.currentRoom);
            Functions.slowPrint(room.description);
            roomSelect = getInput.next();
            if(roomSelect.equals("exit") || roomSelect.equals("Exit")){
                break;
            }
        }

        System.out.println("Next up? How about we learn how to have some rooms initiate a fight if the player enters them.");
        System.out.println("Good luck! You got this and remember, this is literally just for fun. You've always wanted to tell a story through a game and this is our pre-rough draft.");
        System.out.println("I believe in you");
    }

}
