public class Game {

/* #########################################
        GLOBAL VARIABLES / OBJECTS
 #########################################*/

    // Game flow is dependent on the current game state. The UI object will keep the game running and actions taken
    //  will trigger code to run dependent on the game state.
    public enum GameState {
        WAITING_ON_GAME_START,
        WAITING_ON_PLAYER_NAME,
        WAITING_ON_CONTINUE,
        WAITING_ON_PLAYER_CLASS,
        WAITING_ON_PLAYER_WEAPON,
        STAT_ACKNOWLEDGEMENT,
        EXPLORING_PROMPT,
        EXPLORING,
        IN_COMBAT,
        IN_INVENTORY
    }

    // Object that controls the current state of the game
    static GameState gameState; // = GameState.WAITING_ON_GAME_START;
    static GameState previousGameState;

    //Create objects that will be utilized in game.
    // These objects can and will be accessed from other classes given their existence outside the main method and having 'public static'.
    public static Player player = new Player(); //Creates Player object that will store our PC's stats
    public static Enemy enemy = new Enemy(); //Create Enemy Object that has enemy names and stats.
    public static Room room = new Room(); //Create Room Object that will track where the player is, room description, and adjacent rooms.
    public static UserInterface ui = new UserInterface(); //Create UserInterface object that handles rendering the UI and triggers.
    public static Dialog dialog = new Dialog();
    public static String playerInput;

    public static void main(String[] args) {

        // Set the default game state so the correct UI is displayed when the game launches.
        gameState = GameState.WAITING_ON_GAME_START;
        previousGameState = GameState.WAITING_ON_GAME_START;
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

        // Load UI
        ui.createUI();

        // CURRENT NEXT STEP: Add weapon stats to textArea information then update the playersheet to match the information that is relevant to us.
        //                      Add option to type 'QUIT GAME' at any time to close the game immediately.
        //                      Figure out how we integrate the battleframe functionality.
        //
        //      ANOTHER UNRELATED IDEA: Use a linked list to track what rooms have been previously entered. Same thing for NPCs
        //                                  so that you can create a new object for each as they are encountered, to prevent creating objects
        //                                  for each NPC unnecessarily. Logically: if ROOM or NPC id doesn't exist in the linked lists create a
        //                                  new object and insert their id to the linked list.

//        Functions.slowPrint("Now that we've covered stats, lets test out your combat skills! Don't worry, your enemies won't hit back very hard :)");
//        int earnedExp;
//
//        while(player.level != 4 && continueGame) {
//            Functions.slowPrint("Your current level is: " + player.level + " and you need " + player.nextLevel + " experience to level up.");
//            Functions.slowPrint("Your starting health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
//
//            enemy.enemySelection = Functions.rollEnemy();
////            enemy.getEnemy(enemy.enemySelection);
//            enemy.enemyStats(enemy.enemySelection);
//
//            earnedExp = Functions.battleFrame(enemy.enemyHealth, player.attackDamage, player.damageMod, enemy.enemyDamage, enemy.enemyDamageMod, player.attackSpeed, player.classArmor, enemy.enemyArmor, player.criticalChance, enemy.enemyCriticalChance);
//            player.experience += earnedExp;
//            Functions.slowPrint("You currently have " + player.experience + " experience points. In order to reach the next level, you need " + player.nextLevel + " total experience points to level up.");
//            hasLevelled = Functions.hasLevelled(player.experience, player.nextLevel);
//            if(hasLevelled){
//                player.nextLevel = Functions.nextLevel(player.level, player.nextLevel);
//            }
//            Functions.slowPrint("Your ending health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
////            Functions.slowPrint("You earned " + earnedExp + " experience for a grand total of " + player.experience);
//            while (player.experience < 20) {
//                enemy.enemySelection = Functions.rollEnemy();
////                enemy.getEnemy(enemy.enemySelection);
//                enemy.enemyStats(enemy.enemySelection);
//                Functions.slowPrint("Lets fight another enemy!");
//                Functions.slowPrint("Your starting health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
//                earnedExp = Functions.battleFrame(enemy.enemyHealth, player.attackDamage, player.damageMod, enemy.enemyDamage, enemy.enemyDamageMod, player.attackSpeed, player.classArmor, enemy.enemyArmor, player.criticalChance, enemy.enemyCriticalChance);
//                player.experience += earnedExp;
//                Functions.slowPrint("Your ending health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
////                Functions.slowPrint("You earned " + earnedExp + " experience for a grand total of " + player.experience);
//                hasLevelled = Functions.hasLevelled(player.experience, player.nextLevel);
//                if(hasLevelled){
//                    player.nextLevel = Functions.nextLevel(player.level, player.nextLevel);
//                }
//            }
//
//            Functions.slowPrint("You currently have " + player.experience + " experience points. In order to reach the next level, you need " + player.nextLevel + " total experience points to level up.");
////            player.statPrinter();
//
//            if (player.playerHealth > 2) {
//                Functions.slowPrint("Since you are still standing, would you like to practice some more?");
//                Functions.slowPrint("1. Keep Playing!");
//                Functions.slowPrint("2. Exit Simulation!");
//                continueGameSelect = getInput.nextInt();
//
//                if (continueGameSelect != 1) {
//                    continueGame = false;
//                }
//            }
//        }
//
//        Functions.slowPrint("Hey buddy, it's me, Abernathy, again. Would you mind terribly if I placed you in my new house?");
//        Functions.slowPrint("It's still under construction, but check out the couple of rooms that have been built and let me know if you get lost!");
//        Functions.slowPrint("Although, I suppose it will be hard for you to let me know if you are lost...ehh, lets not worry about that right now.");
//        Functions.slowPrint("Ah and one last thing. If you wish to leave the house, at any point, simply say 'Exit' or 'exit' and you will be allowed to leave.");
//        System.out.println();
//
//        //Initiate room traversal section
//        room.currentRoom = 1; //Set player into the Entry room of the Mansion.
//        room.getRoom(room.currentRoom); //Initialize the variables in the room object, so that they can be printed for player.
//        Functions.slowPrint(room.description); //Print currentRoom Description.
//        //Super important to remember!! USE '.next', NOT '.nextLine'!! It will enter a carriage return instead of prompting for input.
//        roomSelect = getInput.next();
//        while(!roomSelect.equals("Exit") || !roomSelect.equals("exit")) {
//            switch (roomSelect) {
//                case "North", "north" -> room.currentRoom = room.adjacentRooms[0];
//                case "East", "east" -> room.currentRoom = room.adjacentRooms[1];
//                case "South", "south" -> room.currentRoom = room.adjacentRooms[2];
//                case "West", "west" -> room.currentRoom = room.adjacentRooms[3];
//            }
//            room.getRoom(room.currentRoom);
//            Functions.slowPrint(room.description);
//            roomSelect = getInput.next();
//            if(roomSelect.equals("exit") || roomSelect.equals("Exit")){
//                break;
//            }
//        }
//
//        System.out.println("Next up? How about we learn how to have some rooms initiate a fight if the player enters them.");
//        System.out.println("Good luck! You got this and remember, this is literally just for fun. You've always wanted to tell a story through a game and this is our pre-rough draft.");
//        System.out.println("I believe in you");
    }

    public static void handleInput (String playerInput) {
        boolean validInput = true;

        switch (gameState) {
            case WAITING_ON_GAME_START -> {
            }
            case WAITING_ON_PLAYER_NAME -> {
                setPlayerName(playerInput);

                setGameState(GameState.WAITING_ON_CONTINUE);
                System.out.println("Game.player.name: " + player.name);
                ui.clearText();
                dialog.chatLevel = 1;
                ui.displayText();
            }
            case WAITING_ON_CONTINUE -> {
                if (playerInput.equals("CONTINUE")) {
                    setGameState(GameState.WAITING_ON_PLAYER_CLASS);
                    ui.clearText();
                    dialog.chatLevel++;
                    ui.displayText();
                } else {
                    ui.playerInputField.setText("");
                }
            }
            case WAITING_ON_PLAYER_CLASS -> {
                setPlayerClass(playerInput);
            }
            case WAITING_ON_PLAYER_WEAPON -> {
                setPlayerWeapon(playerInput);
            }
            case STAT_ACKNOWLEDGEMENT -> {
                statAcknowledge(playerInput);
            }
            case EXPLORING_PROMPT -> {
                startExploration(playerInput);
            }
            case EXPLORING -> {
            }
            case IN_COMBAT -> {
            }
            case IN_INVENTORY -> {
            }
            default -> System.out.println("gameState default switch");
        }
    }

    public static void setPlayerName(String playerInput) {
        player.name = playerInput;
    }

    public static void setPlayerClass(String playerInput) {
        boolean validInput = true;

        switch (playerInput) {
            case "SOLDIER", "1" -> player.pcClass = Player.classChoice.SOLDIER;
            case "RANGER", "2" -> player.pcClass = Player.classChoice.RANGER;
            case "BRAWLER", "3" -> player.pcClass = Player.classChoice.BRAWLER;
            case "MAGE", "4" -> player.pcClass = Player.classChoice.MAGE;
            default -> validInput = false;
        }

        if (validInput) {
            player.classBuilder();
            player.statPrinter();
            ui.updatePlayerPanelLabels(player);
            dialog.chatLevel++;
            ui.clearText();
            setGameState(GameState.WAITING_ON_PLAYER_WEAPON);
            ui.displayText();
        } else {
            ui.playerInputField.setText("");
        }
    }

    public static void setPlayerWeapon(String playerInput) {
        boolean validInput = true;

        System.out.println(gameState);

        switch (playerInput) {
            case "SWORD", "1" -> player.playerWeapon = Player.weaponChoice.SWORD;
            case "CROSSBOW", "2" -> player.playerWeapon = Player.weaponChoice.CROSSBOW;
            case "BAT", "3" -> player.playerWeapon = Player.weaponChoice.BAT;
            case "WAND", "4" -> player.playerWeapon = Player.weaponChoice.WAND;
            default -> validInput = false;
        }

        if (validInput) {
            player.setWeaponChoice();
            ui.updatePlayerPanelLabels(player);
            if(Dialog.chatTracker.length <= dialog.chatLevel + 1) {
                System.out.println("cannot iterate chatLevel further | " + dialog.chatLevel + " | " + Dialog.chatTracker.length);
            } else {
                dialog.chatLevel++;
                setGameState(GameState.STAT_ACKNOWLEDGEMENT);
                ui.clearText();
                ui.displayText();
            }
        } else {
            ui.playerInputField.setText("");
        }
    }

    public static void statAcknowledge(String playerInput) {
        boolean validInput = false;

        System.out.println(gameState);

        switch (playerInput) {
            case "CONTINUE" -> validInput = true;
        }

        if (validInput) {
            dialog.chatLevel++;
            ui.clearText();
            setGameState(GameState.EXPLORING_PROMPT);
            ui.displayText();
        }  else {
            ui.playerInputField.setText("");
        }
    }

    public static void startExploration(String playerInput) {
        boolean validInput = false;

        System.out.println(gameState);

        switch (playerInput) {
            case "YES", "1" -> validInput = true;
            case "QUIT", "2" -> System.exit(0);
        }

        if (validInput) {
            dialog.chatLevel++;
            ui.clearText();
            setGameState(GameState.EXPLORING);
            ui.displayText();
        } else {
            ui.playerInputField.setText("");
        }
    }

    public static void exploringLoop(String playerInput) {
        boolean validInput = false;
        System.out.println(gameState);
        /*
            We need to transition over to the Room class' state tracking at this point of the game.
            * Set default room state
            * Create new UI methods for room-based movement that clear the existing UI and enable writing from new arrays
            * iterate through new game loop
         */
    }

    public static void setGameState(GameState gameState) {
        if (Game.gameState != gameState) {
            if (gameState != GameState.WAITING_ON_CONTINUE) {
                Game.previousGameState = Game.gameState;
            }
            Game.gameState = gameState;
        }
    }

}
