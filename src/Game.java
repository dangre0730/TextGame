import javax.swing.*;

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
    public static EnemyNPC enemyNPC = new EnemyNPC(); //Create Enemy Object that has enemy names and stats.
    public static Room room = new Room(); //Create Room Object that will track where the player is, room description, and adjacent rooms.
    public static UserInterface ui = new UserInterface(); //Create UserInterface object that handles rendering the UI and triggers.
    public static Dialog dialog = new Dialog();
    public static String playerInput;

    public static void main(String[] args) {

        // Set the default game state so the correct UI is displayed when the game launches.
        gameState = GameState.WAITING_ON_GAME_START;
        previousGameState = GameState.WAITING_ON_GAME_START;
        //Global / Player Variable definitions
        QuestNPC abernathy = new QuestNPC("Abernathy", Room.RoomObjects.ENTRY_ROOM_BASE, Dialog.chatTracker);
        System.out.println(abernathy.toString());
        System.out.println(enemyNPC.toString());
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

        // CURRENT NEXT STEP: Figure out how we integrate the battleframe functionality.
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
//            enemyNPC.enemySelection = Functions.rollEnemy();
////            enemyNPC.getEnemy(enemyNPC.enemySelection);
//            enemyNPC.enemyStats(enemyNPC.enemySelection);
//
//            earnedExp = Functions.battleFrame(enemyNPC.enemyHealth, player.attackDamage, player.damageMod, enemyNPC.enemyDamage, enemyNPC.enemyDamageMod, player.attackSpeed, player.classArmor, enemyNPC.enemyArmor, player.criticalChance, enemyNPC.enemyCriticalChance);
//            player.experience += earnedExp;
//            Functions.slowPrint("You currently have " + player.experience + " experience points. In order to reach the next level, you need " + player.nextLevel + " total experience points to level up.");
//            hasLevelled = Functions.hasLevelled(player.experience, player.nextLevel);
//            if(hasLevelled){
//                player.nextLevel = Functions.nextLevel(player.level, player.nextLevel);
//            }
//            Functions.slowPrint("Your ending health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
////            Functions.slowPrint("You earned " + earnedExp + " experience for a grand total of " + player.experience);
//            while (player.experience < 20) {
//                enemyNPC.enemySelection = Functions.rollEnemy();
////                enemyNPC.getEnemy(enemyNPC.enemySelection);
//                enemyNPC.enemyStats(enemyNPC.enemySelection);
//                Functions.slowPrint("Lets fight another enemy!");
//                Functions.slowPrint("Your starting health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
//                earnedExp = Functions.battleFrame(enemyNPC.enemyHealth, player.attackDamage, player.damageMod, enemyNPC.enemyDamage, enemyNPC.enemyDamageMod, player.attackSpeed, player.classArmor, enemyNPC.enemyArmor, player.criticalChance, enemyNPC.enemyCriticalChance);
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
//        System.out.println("Next up? How about we learn how to have some rooms initiate a fight if the player enters them.");
//        System.out.println("Good luck! You got this and remember, this is literally just for fun. You've always wanted to tell a story through a game and this is our pre-rough draft.");
//        System.out.println("I believe in you");
    }

    // Called by PlayerInputHandler on every Enter press. One call = one game action.
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
                if (playerInput.equals("CONTINUE") || playerInput.isEmpty()) {
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
                exploringLoop(playerInput);
            }
            case IN_COMBAT -> {
                combatLoop(player, enemyNPC, playerInput);
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

        if (playerInput.equals("CONTINUE") || playerInput.isEmpty()) {
            validInput = true;
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
            room.currentRoomObject = Room.RoomObjects.ENTRY_ROOM_BASE;
            room.lastRoomObject = Room.RoomObjects.ENTRY_ROOM_BASE;
            room.getRoom(room.currentRoomObject);
            ui.clearText();
            setGameState(GameState.EXPLORING);
            ui.displayText();
        } else {
            ui.playerInputField.setText("");
        }
    }

    public static void exploringLoop(String playerInput) {
        boolean validInput = true;
        boolean goingBack = false;
        System.out.println(gameState);

        System.out.println("B4 input - Current Room: " + room.currentRoomObject);
        System.out.println("B4 input - Last Room: " + room.lastRoomObject);

        System.out.println("Room is wall? " + room.currentRoomObject.equals(Room.RoomObjects.WALL));
        System.out.println("Last room = current room? " + room.lastRoomObject.equals(room.currentRoomObject));

        room.tempRoomObject = room.lastRoomObject;

        if (!room.currentRoomObject.equals(Room.RoomObjects.WALL) && !room.lastRoomObject.equals(room.currentRoomObject)) {
            room.lastRoomObject = room.currentRoomObject;
        }

        // Special input: FLUSH triggers combat from the bathroom as an easter-egg
        if (((room.currentRoomObject.equals(Room.RoomObjects.SUSPICIOUSLY_CLEAN_BATHROOM_BASE) || room.currentRoomObject.equals(Room.RoomObjects.SUSPICIOUSLY_CLEAN_BATHROOM_REVISIT))
                || (room.currentRoomObject.equals(Room.RoomObjects.WALL)
                    && (room.lastRoomObject.equals(Room.RoomObjects.SUSPICIOUSLY_CLEAN_BATHROOM_BASE) || room.lastRoomObject.equals(Room.RoomObjects.SUSPICIOUSLY_CLEAN_BATHROOM_REVISIT))))
        && playerInput.equals("FLUSH")){
            room.currentRoomObject = Room.RoomObjects.TRAINING_ROOM;
            setGameState(GameState.IN_COMBAT);
            enemyNPC = new EnemyNPC(Room.RoomObjects.DARK_BASEMENT_BASE, 1);
            validInput = false;
            ui.clearText();
            combatLoop(player, enemyNPC, playerInput);
            ui.displayText();
        } else {
            switch (playerInput) {
                case "N", "NORTH", "1" -> room.currentRoomObject = room.nextRooms[0];
                case "E", "EAST", "2" -> room.currentRoomObject = room.nextRooms[1];
                case "S", "SOUTH", "3" -> room.currentRoomObject = room.nextRooms[2];
                case "W", "WEST", "4" -> room.currentRoomObject = room.nextRooms[3];
                case "B", "BACK" -> goingBack = true;
                case "Q", "QUIT" -> System.exit(0);
                default -> validInput = false;
            }
        }

        if (goingBack) {
            System.out.println(room.tempRoomObject);
            room.lastRoomObject = room.currentRoomObject;
            room.currentRoomObject = room.tempRoomObject;
        }

        if (validInput) {
            room.getRoom(room.currentRoomObject);

            System.out.println("After input - Current Room: " + room.currentRoomObject);
            System.out.println("After input - Last Room: " + room.lastRoomObject);

            ui.clearText();
            ui.displayText();
        } else {
            ui.playerInputField.setText("");
        }

    }

    /** Handles game changes and loop for combat.
     *
     * Description:
     *  Runs while gameState is IN_COMBAT.
     *  Manages player stats, combat step & turn order, and enemy generation
     *  Can call appropriate classes for inventory, skill upgrades and unlocks, etc.
     *  Returns game to EXPLORING state when acceptable
     *
     * @param playerInput - Text value that player entered into game console
     */
    public static void combatLoop(Player player, EnemyNPC enemyNPC, String playerInput) {
        boolean validInput = true;
//        boolean combatOver = false;
        System.out.println(gameState);
        String[] text;

        // turnsTaken == 0: show intro. turnsTaken >= 1: resolve round. combatOver: show post-fight options.
        if (enemyNPC.combatOver) {
            switch (playerInput) {
                case "1", "RETURN" -> {
                    setGameState(GameState.EXPLORING);
                    room.currentRoomObject = Room.RoomObjects.SUSPICIOUSLY_CLEAN_BATHROOM_BASE;
                    room.getRoom(room.currentRoomObject);
                    ui.clearText();
                    ui.displayText();
                }
                case "2", "FIGHT" -> Game.enemyNPC = new EnemyNPC(Room.RoomObjects.TRAINING_ROOM, 0);
            }
        } else {

            if (enemyNPC.turnsTaken == 0) {
                player.extraAttack = 0D;
                System.out.println(enemyNPC.name);
                text = new String[]{
                        "A " + enemyNPC.name + " stands before you. They clearly want a fight...",
                        "",
                        "What would you like to do?",
                        "",
                        "1. Attack  2. Move",
                        "3. Mock    4. Run"
                };

                Functions.combatPrintText(text, ui.mainTextArea);
                ++enemyNPC.turnsTaken;
            } else {
                ++enemyNPC.turnsTaken;

                int earnedXP = 0;
                int damageDealt = 0;
//            double extraAttack = 0.0;

//        while((enemyNPC.enemyHealth > 0) && (player.playerHealth > 0)){


//            Game.ui.getPlayerInput();

                switch (playerInput) {
                    case "1", "ATTACK" -> {
                        damageDealt = Functions.makeAnAttack(player.attackDamage, player.damageMod, enemyNPC.enemyArmor, player.criticalChance);
                        player.extraAttack += player.attackSpeed - 1d;
                        if (damageDealt > 0) {
                            Functions.slowPrint("B A M !");
                            enemyNPC.enemyHealth -= damageDealt;
//                        earnedXP += 3;
                            Functions.slowPrint("Wow! You dealt " + damageDealt + " damage to the target.");
                        } else {
                            Functions.slowPrint("Player failed to deal damage this round");
//                        earnedXP += 3;
                        }
                        if (enemyNPC.enemyHealth > 0) {
                            Functions.slowPrint("Enemy Health is " + enemyNPC.enemyHealth);
                        } else {
                            Functions.slowPrint("Enemy slain! You survived the battle.");
                            break;
                        }
                        if (player.extraAttack >= 1) {
                            Functions.slowPrint("---EXTRA ATTACK---");
                            damageDealt = Functions.makeAnAttack(player.attackDamage, player.damageMod, enemyNPC.enemyArmor, player.criticalChance);
                            if (damageDealt > 0) {
                                Functions.slowPrint("P O W !");
                                enemyNPC.enemyHealth -= damageDealt;
//                            earnedXP += 3;
                                Functions.slowPrint("Wow! You dealt " + damageDealt + " damage to the target.");
                            } else {
                                Functions.slowPrint("Player failed to deal damage this round");
//                            earnedXP += 0;
                            }
                            if (enemyNPC.enemyHealth > 0) {
                                Functions.slowPrint("Enemy Health is " + enemyNPC.enemyHealth);
                            } else {
                                Functions.slowPrint("Enemy slain! You survived the battle.");
                                break;
                            }
                            player.extraAttack = 0.0;
                        }
                    }
                    case "2", "MOVE" -> {
                        Functions.slowPrint("The Movement Module doesn't exist yet.");
                    }
                    case "3", "MOCK" -> {
                        Functions.slowPrint("The Mock Module doesn't exist yet.");
                    }
                    case "4", "RUN" -> {
                        Functions.slowPrint("Adventurer abandoned their Quest...");
                    }
                    default -> {
                        Functions.slowPrint("That's not an option.");
                        validInput = false;
                    }
                }

                if (enemyNPC.enemyHealth > 0) {
                    Functions.slowPrint("Enemy Turn Begins.");
                    damageDealt = Functions.makeAnAttack(enemyNPC.enemyDamage, enemyNPC.enemyDamageMod, player.classArmor, enemyNPC.enemyCriticalChance);
                    if (damageDealt > 0) {
                        player.playerHealth -= damageDealt;
                    } else {
                        Functions.slowPrint("Enemy failed to deal damage this round");
                    }
                    if (player.playerHealth > 0) {
                        Functions.slowPrint("You took " + damageDealt + " damage.");
                        Functions.slowPrint("Player health is " + player.playerHealth);
                    }
                } else {
                    Functions.slowPrint("Enemy health has been reduced to 0.");
                }
//        }
//        if(playerHealth <= 0){
//            System.out.println("You lost the fight");
//            Game.player.playerHealth = playerHealth;
//            return earnedXP;
//        } else

                // Build the round summary here. All combat narrative should land in text[] rather than printing mid-round.
                if (enemyNPC.enemyHealth <= 0) {
                    Functions.slowPrint("You won the fight! Your closing health is " + player.playerHealth);
                    player.experience += enemyNPC.earnedExp;
                    enemyNPC.combatOver = true;
                    text = new String[]{
                            "The " + enemyNPC.name + " has been slain.",
                            "",
                            "What would you like to do?",
                            "",
                            "1. Return to the Mansion  2. Fight another creature"
                    };
                } else {
                    text = new String[]{
                            "The " + enemyNPC.name + " yet stands, in spite of your attempt to end its life...",
                            "Enemy Stats: HP-" + enemyNPC.enemyHealth + "; ATK-" + (enemyNPC.enemyDamage + enemyNPC.enemyDamageMod) + "; CRIT-" + enemyNPC.enemyCriticalChance + ";",
                            "",
                            "What would you like to do?",
                            "",
                            "1. Attack  2. Move",
                            "3. Mock    4. Run"
                    };
                }
                // while(())
//        if(earnedXP < 3){
//            return 3;
//        } else {
//            return earnedXP;
//        }

                Game.player = player;
                Game.enemyNPC = enemyNPC;

                if (validInput) {
                    System.out.println("validInput");

                    ui.clearText();
                    Functions.combatPrintText(text, ui.mainTextArea);
                    ui.displayText();
                    ui.updatePlayerPanelLabels(player);
                } else {
                    ui.playerInputField.setText("");
                }

            }
        }

    }

    // Updates all player object values
    public static void updatePlayer(Player player) {

    }

    public static void setGameState(GameState gameState) {
        if (Game.gameState != gameState) {
            // Don't overwrite previousGameState for WAITING_ON_CONTINUE — it's a temporary pause, not a real transition
            if (gameState != GameState.WAITING_ON_CONTINUE) {
                Game.previousGameState = Game.gameState;
            }
            Game.gameState = gameState;
        }
    }

}
