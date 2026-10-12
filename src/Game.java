import java.util.ArrayList;

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
        QuestNPC abernathy = new QuestNPC("Abernathy", Room.RoomId.ENTRY_ROOM_BASE, Dialog.chatTracker);
        System.out.println(abernathy.toString());
        System.out.println(enemyNPC.toString());

        // Load UI
        ui.createUI();

        // CURRENT NEXT STEP: Figure out how we integrate the battleframe functionality.
        //
        //      ANOTHER UNRELATED IDEA: Use a linked list to track what rooms have been previously entered. Same thing for NPCs
        //                                  so that you can create a new object for each as they are encountered, to prevent creating objects
        //                                  for each NPC unnecessarily. Logically: if ROOM or NPC id doesn't exist in the linked lists create a
        //                                  new object and insert their id to the linked list.

    }

    // Called by PlayerInputHandler on every Enter press. One call = one game action.
    public static void handleInput (String playerInput) {

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
                acknowledgeStats(playerInput);
            }
            case EXPLORING_PROMPT -> {
                startExploration(playerInput);
            }
            case EXPLORING -> {
                handleExplorationInput(playerInput);
            }
            case IN_COMBAT -> {
                handleCombatInput(player, enemyNPC, playerInput);
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
        boolean isValidInput = true;

        switch (playerInput) {
            case "SOLDIER", "1" -> player.pcClass = Player.ClassType.SOLDIER;
            case "RANGER", "2" -> player.pcClass = Player.ClassType.RANGER;
            case "BRAWLER", "3" -> player.pcClass = Player.ClassType.BRAWLER;
            case "MAGE", "4" -> player.pcClass = Player.ClassType.MAGE;
            default -> isValidInput = false;
        }

        if (isValidInput) {
            player.buildClass();
            player.printStats();
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
        boolean isValidInput = true;

        System.out.println(gameState);

        switch (playerInput) {
            case "SWORD", "1" -> player.weaponType = Player.WeaponType.SWORD;
            case "CROSSBOW", "2" -> player.weaponType = Player.WeaponType.CROSSBOW;
            case "BAT", "3" -> player.weaponType = Player.WeaponType.BAT;
            case "WAND", "4" -> player.weaponType = Player.WeaponType.WAND;
            default -> isValidInput = false;
        }

        if (isValidInput) {
            player.loadWeaponStats();
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

    public static void acknowledgeStats(String playerInput) {
        boolean isValidInput = false;

        System.out.println(gameState);

        if (playerInput.equals("CONTINUE") || playerInput.isEmpty()) {
            isValidInput = true;
        }

        if (isValidInput) {
            dialog.chatLevel++;
            ui.clearText();
            setGameState(GameState.EXPLORING_PROMPT);
            ui.displayText();
        }  else {
            ui.playerInputField.setText("");
        }
    }

    public static void startExploration(String playerInput) {
        boolean isValidInput = false;

        System.out.println(gameState);

        switch (playerInput) {
            case "YES", "1" -> isValidInput = true;
            case "QUIT", "2" -> System.exit(0);
        }

        if (isValidInput) {
            dialog.chatLevel++;
            room.currentRoomId = Room.RoomId.ENTRY_ROOM_BASE;
            room.lastRoomId = Room.RoomId.ENTRY_ROOM_BASE;
            room.enterRoom(room.currentRoomId);
            ui.clearText();
            setGameState(GameState.EXPLORING);
            ui.displayText();
        } else {
            ui.playerInputField.setText("");
        }
    }

    public static void handleExplorationInput(String playerInput) {
        boolean isValidInput = true;
        boolean isGoingBack = false;
        System.out.println(gameState);

        System.out.println("B4 input - Current Room: " + room.currentRoomId);
        System.out.println("B4 input - Last Room: " + room.lastRoomId);

        System.out.println("Room is wall? " + room.currentRoomId.equals(Room.RoomId.WALL));
        System.out.println("Last room = current room? " + room.lastRoomId.equals(room.currentRoomId));

        room.tempRoomId = room.lastRoomId;

        if (!room.currentRoomId.equals(Room.RoomId.WALL) && !room.lastRoomId.equals(room.currentRoomId)) {
            room.lastRoomId = room.currentRoomId;
        }

        // Special input: FLUSH triggers combat from the bathroom as an easter-egg
        if (((room.currentRoomId.equals(Room.RoomId.SUSPICIOUSLY_CLEAN_BATHROOM_BASE) || room.currentRoomId.equals(Room.RoomId.SUSPICIOUSLY_CLEAN_BATHROOM_REVISIT))
                || (room.currentRoomId.equals(Room.RoomId.WALL)
                    && (room.lastRoomId.equals(Room.RoomId.SUSPICIOUSLY_CLEAN_BATHROOM_BASE) || room.lastRoomId.equals(Room.RoomId.SUSPICIOUSLY_CLEAN_BATHROOM_REVISIT))))
        && playerInput.equals("FLUSH")){
            room.currentRoomId = Room.RoomId.TRAINING_ROOM;
            setGameState(GameState.IN_COMBAT);
            enemyNPC = new EnemyNPC(Room.RoomId.DARK_BASEMENT_BASE, 1);
            isValidInput = false;
            ui.clearText();
            handleCombatInput(player, enemyNPC, playerInput);
            ui.displayText();
        } else {
            switch (playerInput) {
                case "N", "NORTH", "1" -> room.currentRoomId = room.nextRooms[0];
                case "E", "EAST", "2" -> room.currentRoomId = room.nextRooms[1];
                case "S", "SOUTH", "3" -> room.currentRoomId = room.nextRooms[2];
                case "W", "WEST", "4" -> room.currentRoomId = room.nextRooms[3];
                case "B", "BACK" -> isGoingBack = true;
                case "Q", "QUIT" -> System.exit(0);
                default -> isValidInput = false;
            }
        }

        if (isGoingBack) {
            System.out.println(room.tempRoomId);
            room.lastRoomId = room.currentRoomId;
            room.currentRoomId = room.tempRoomId;
        }

        if (isValidInput) {
            room.enterRoom(room.currentRoomId);

            System.out.println("After input - Current Room: " + room.currentRoomId);
            System.out.println("After input - Last Room: " + room.lastRoomId);

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
    public static void handleCombatInput(Player player, EnemyNPC enemyNPC, String playerInput) {
        boolean isValidInput = true;
//        boolean combatOver = false;
        System.out.println(gameState);
        String[] text;

        // turnsTaken == 0: show intro. turnsTaken >= 1: resolve round. combatOver: show post-fight options.
        if (enemyNPC.isCombatOver) {
            text = new String[]{
                    "The " + enemyNPC.name + " has been slain.",
                    "",
                    "What would you like to do?",
                    "",
                    "1. Return to the Mansion  2. Fight another creature"
            };
            ui.clearText();
            Functions.combatPrintText(text, ui.mainTextArea);
            ui.displayText();
            switch (playerInput) {
                case "1", "RETURN" -> {
                    setGameState(GameState.EXPLORING);
                    room.currentRoomId = Room.RoomId.SUSPICIOUSLY_CLEAN_BATHROOM_BASE;
                    room.enterRoom(room.currentRoomId);
                    ui.clearText();
                    ui.displayText();
                }
                case "2", "FIGHT" -> {
                    Game.enemyNPC = new EnemyNPC(Room.RoomId.TRAINING_ROOM, 0);
                }
            }
//            continue;     // I'm trying to make it so that post combat message is displayed so the above prompts are clear and the user input synchronizes with what they think they are responding to. Basically need to have a loop to display the above info and then take input...
        } else {

            if (enemyNPC.turnsTaken == 0) {
                player.extraAttack = 0D;
                System.out.println(enemyNPC.name);
//                player.extraAttack = player.attackSpeed;
                text = new String[]{
                        enemyNPC.name + "|  HP-" + enemyNPC.health + "; ATK-" + (enemyNPC.damage + enemyNPC.damageMod) + "; CRIT-" + enemyNPC.criticalChance + "%",
                        "",
                        "Your opponent stands before you. They clearly want a fight...",
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
                ArrayList<String> tempList = new ArrayList<>();
                tempList.add("");

                int earnedXP = 0;
                int damageDealt = 0;
//            double extraAttack = 0.0;

//        while((enemyNPC.enemyHealth > 0) && (player.playerHealth > 0)){

//            Game.ui.getPlayerInput();

                switch (playerInput) {
                    case "1", "ATTACK" -> {
                        damageDealt = Functions.makeAnAttack(player.attackDamage, player.damageMod, enemyNPC.armor, player.criticalChance);
//                        if (player.attackSpeed > 1) {
//                            player.extraAttack += player.attackSpeed - 1d;
//                        } else {
                            player.extraAttack += player.attackSpeed;
//                        }
                        if (player.extraAttack >= 1) {
                            if (damageDealt > 0) {
                                enemyNPC.health -= damageDealt;
//                        earnedXP += 3;
                                tempList.add("B A M ! You dealt " + damageDealt + " damage to the target!");
//                            tempList.add("");
                            } else {
                                tempList.add("Player attack missed!!");
                                tempList.add("");
//                        earnedXP += 3;
                            }

                            if (player.extraAttack >= 2) {
                                tempList.add("---EXTRA ATTACK---");
                                tempList.add("");
                                damageDealt = Functions.makeAnAttack(player.attackDamage, player.damageMod, enemyNPC.armor, player.criticalChance);
                                if (damageDealt > 0) {
                                    tempList.add("P O W ! You dealt " + damageDealt + " damage to the target.");
//                                tempList.add("");
                                    enemyNPC.health -= damageDealt;
//                            earnedXP += 3;
//                                tempList.add("");
                                } else {
                                    tempList.add("Player failed to deal damage this round");
//                                tempList.add("");
//                            earnedXP += 0;
                                }
//                                if (enemyNPC.enemyHealth > 0) {
//                                    tempList.add("Enemy Health is " + enemyNPC.enemyHealth);
////                                tempList.add("");
//                                } else {
//                                    tempList.add("Enemy slain! You survived the battle.");
////                                tempList.add("");
//                                    break;
//                                }
//                            player.extraAttack = 0.0;
                                player.extraAttack -= 2;
                            }
                        }
                        tempList.add("Enemy Health is " + enemyNPC.health);
                        tempList.add("");
                        if (enemyNPC.health > 0) {
                            tempList.add("Enemy Turn Begins.");
                            tempList.add("");
                            damageDealt = Functions.makeAnAttack(enemyNPC.damage, enemyNPC.damageMod, player.classArmor, enemyNPC.criticalChance);
                            if (damageDealt > 0) {
                                player.health -= damageDealt;
                            } else {
                                tempList.add("Enemy failed to deal damage this round");
//                        tempList.add("");
                            }
                            if (player.health > 0) {
                                tempList.add("You took " + damageDealt + " damage.");
                                tempList.add("Player health is " + player.health);
//                        tempList.add("");
                                tempList.add("");
                                tempList.add("Your opponent stands before you. They clearly want a fight...");
                                tempList.add("");
                                tempList.add("What would you like to do?");
                                tempList.add("");
                                tempList.add("1. Attack  2. Move");
                                tempList.add("3. Mock    4. Run");
                            }
                        } else {
                            tempList.add("Enemy slain! You survived the battle.");
//                            tempList.add("");
                            break;
                        }

                    }
                    case "2", "MOVE" -> {
                        tempList.add("The Movement Module doesn't exist yet.");
                        tempList.add("");
                    }
                    case "3", "MOCK" -> {
                        tempList.add("The Mock Module doesn't exist yet.");
                        tempList.add("");
                    }
                    case "4", "RUN" -> {
                        tempList.add("Adventurer abandoned their Quest...");
                        tempList.add("");
                    }
                    default -> {
                        tempList.add("That's not an option.");
                        tempList.add("");
                        isValidInput = false;
                    }
                }

                // Add to the start of the array list so that enemy stats are current onscreen.
                tempList.addFirst(enemyNPC.name + "|  HP-" + enemyNPC.health + "; ATK-" + (enemyNPC.damage + enemyNPC.damageMod) + "; CRIT-" + enemyNPC.criticalChance + "%");
//        }
//        if(playerHealth <= 0){
//            System.out.println("You lost the fight");
//            Game.player.playerHealth = playerHealth;
//            return earnedXP;
//        } else
                if (player.health <= 0) {
                    text = new String[]{
                            "Uh-oh...bad news buddy. You actually died just then.",
                            "",
                            "This is super awkward, no one was supposed to die in here again. I gotta call Terry...",
                            "*Muffled* Yeah, Terry? It's Abernathy, that tester I mentioned who potential - " + player.name + " - they had an...accident here at the mansion.",
                            "   They found the training room...that damn " + enemyNPC.name + " is STILL FUCKING HERE, TERRY!    FUCK!!!",
                            "   You were supposed to take care of that shit after the last mishap! Just get down here and deal with the mess.",
                            "",
                            "Well, sorry about that friend. I really hadn't planned for this to happen to ya. I actually thought you would be the first to get down there.",
                            "Back to the drawing board now, for old Abe here. Terry will be here soon to scrape you off the walls. Again, so so sorry about this."
                    };
                    ui.clearText();
                    Functions.combatPrintText(text, ui.mainTextArea);
                    ui.displayText();
                    ui.updatePlayerPanelLabels(player);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception ignored) {

                    }
                    System.exit(40);
                }

                // Build the round summary here. All combat narrative should land in text[] rather than printing mid-round.
                if (enemyNPC.health <= 0) {
                    tempList.add("You won the fight! Your closing health is " + player.health);
                    tempList.add("");
                    text = tempList.toArray(new String[0]);

                    player.experienceTotal += enemyNPC.experienceValue;
                    enemyNPC.isCombatOver = true;
//                    text = new String[]{
//                            "The " + enemyNPC.name + " has been slain.",
//                            "",
//                            "What would you like to do?",
//                            "",
//                            "1. Return to the Mansion  2. Fight another creature"
//                    };
                } else {

                    text = tempList.toArray(new String[0]);
//                    text = new String[]{
//                            "The " + enemyNPC.name + " yet stands, in spite of your attempt to end its life...",
//                            "Enemy Stats: HP-" + enemyNPC.enemyHealth + "; ATK-" + (enemyNPC.enemyDamage + enemyNPC.enemyDamageMod) + "; CRIT-" + enemyNPC.enemyCriticalChance + "%;",
//                            "",
//                            "What would you like to do?",
//                            "",
//                            "1. Attack  2. Move",
//                            "3. Mock    4. Run"
//                    };
                }
                // while(())
//        if(earnedXP < 3){
//            return 3;
//        } else {
//            return earnedXP;
//        }

                Game.player = player;
                Game.enemyNPC = enemyNPC;

                if (isValidInput) {
                    System.out.println("isValidInput");

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
