import javax.swing.*;
import java.util.Scanner;

public class Functions {

    // ============================================
    //      General Methods
    // ============================================

    // region Count: 3

    public static int diceRoll(int numberOfSides, int numberOfDice, int rollModifier){

        int rollValue;
        int diceTotal = 0;
        for(int i = 1; i <= numberOfDice; i++) {
            rollValue = (int) (Math.random() * numberOfSides + 1);
            diceTotal += rollValue;
        }
        diceTotal += rollModifier;
        return diceTotal;
    }

    public static void slowPrint(String output){
        char[] stringToChar = output.toCharArray();

        for(int i = 0; i < output.length(); i++){
            System.out.print(stringToChar[i]);
            System.out.flush();
            try {
                Thread.sleep(5);
            } catch(InterruptedException e) {

            }
        }
        System.out.println();
    }

    public static void slowPrintText(String[] text, JTextArea textArea) {

        for(int i = 0; i < text.length; i++){
            String string = text[i];

            if (string.contains("${playerName}")) {
                string = string.replace("${playerName}", Game.player.name);
            }
            if (string.contains("${playerClass}")) {
                string = string.replace("${playerClass}", Game.player.className);
            }
            if (string.contains("${playerWeapon}")) {
                string = string.replace("${playerWeapon}", Game.player.weaponName);
            }
            if (string.contains("${playerLevel}")) {
                string = string.replace("${playerLevel}", Integer.toString(Game.player.level));
            }
            if (string.contains("${classMod}")) {
                string = string.replace("${classMod}", Game.player.classMod);
            }
            if (string.contains("${strength}")) {
                string = string.replace("${strength}", Integer.toString(Game.player.playerStrength));
            }
            if (string.contains("${strMod}")) {
                string = string.replace("${strMod}", Integer.toString(Game.player.strMod));
            }
            if (string.contains("${dexterity}")) {
                string = string.replace("${dexterity}", Integer.toString(Game.player.playerDexterity));
            }
            if (string.contains("${dexMod}")) {
                string = string.replace("${dexMod}", Integer.toString(Game.player.dexMod));
            }
            if (string.contains("${constitution}")) {
                string = string.replace("${constitution}", Integer.toString(Game.player.playerConstitution));
            }
            if (string.contains("${conMod}")) {
                string = string.replace("${conMod}", Integer.toString(Game.player.conMod));
            }
            if (string.contains("${intelligence}")) {
                string = string.replace("${intelligence}", Integer.toString(Game.player.playerIntelligence));
            }
            if (string.contains("${intMod}")) {
                string = string.replace("${intMod}", Integer.toString(Game.player.intMod));
            }
            if (string.contains("${wisdom}")) {
                string = string.replace("${wisdom}",  Integer.toString(Game.player.playerWisdom));
            }
            if (string.contains("${wisMod}")) {
                string = string.replace("${wisMod}", Integer.toString(Game.player.wisMod));
            }
            if (string.contains("${charisma}")) {
                string = string.replace("${charisma}", Integer.toString(Game.player.playerCharisma));
            }
            if (string.contains("${chaMod}")) {
                string = string.replace("${chaMod}", Integer.toString(Game.player.chaMod));
            }
            if (string.contains("${currentHP}")) {
                string = string.replace("${currentHP}", Integer.toString(Game.player.playerHealth));
            }
            if (string.contains("${maxHP}")) {
                string = string.replace("${maxHP}", Integer.toString(Game.player.maxPlayerHealth));
            }
            if (string.contains("${hitDie}")) {
                string = string.replace("${hitDie}", Integer.toString(Game.player.hitDie));
            }
            char[] stringToChar = string.toCharArray();

            for(int j = 0; j < string.length(); j++){
                char currentChar = stringToChar[j];

                try {
                    Thread.sleep(1);
                    textArea.append(String.valueOf(currentChar));

                    // Trying to understand how javax.swing.Timer works and / or how SwingWorker works. Not sure where this even should go really though.
//                    ActionListener sleepPrint = new ActionListener() {
//                        public void actionPerformed(ActionEvent e) {
//                            textArea.append(String.valueOf(currentChar));
//                        }
//                    };
//                    Timer printTimer = new Timer(5, sleepPrint);
//                    printTimer.start();
//                    printTimer.stop();
//                    Game.ui.mainCon.revalidate();     // WHY ARE YOU NOT SLOW PRINTING? INSTEAD YOU JUST WAIT THE TIME IT WOULD HAVE TAKEN TO SLOWPRINT AND THEN IT ALL APPEARS AT ONCE.
//                    Game.ui.mainCon.repaint();
                } catch(InterruptedException e) {
                    textArea.append(String.valueOf(currentChar));
                    System.out.println("Error encountered! " + e.getMessage());
                }
            }

            textArea.append("\n");
        }

//        System.out.println();
    }

    // endregion


    // ============================================
    //      Player Methods
    // ============================================

    // region Count: 4

    public static boolean hasLevelled(int playerExp, int nextLevel){
        return playerExp >= nextLevel;
    }

    public static int nextLevel(int playerLevel, int nextLevel){
        nextLevel += (int) (nextLevel * 1.2);
        Game.player.level += 1;
        slowPrint("New Level: " + Game.player.level);
        slowPrint("Look at that, your Character Experience total went up enough to gain a new level! When you hit certain milestones of experience, you will level up and earn new class features.");
        return nextLevel;
    }

    public static int statDiceRoll(int numberOfSides, int numberOfDice, int rollModifier){

        int minRoll = 100;
        int rollValue;
        int diceTotal = 0;

        for(int i = 0; i < numberOfDice; i++) {
            rollValue = (int) (Math.random() * numberOfSides + 1);
            diceTotal += rollValue;
            if(minRoll > rollValue){
                minRoll = rollValue;
            }
        }
        diceTotal -= minRoll;
//        System.out.println("Dropped lowest value: " + minRoll);
        diceTotal += rollModifier;
        return diceTotal;
    }

    public static int healthDiceRoll(int startingHealth, int numberOfSides, int numberOfDice, int rollModifier){

        int rollValue;
        int diceTotal = startingHealth;

        for(int i = 0; i < numberOfDice; i++) {
            rollValue = ((int) (Math.random() * numberOfSides + 1)) + rollModifier;
//            System.out.print(diceTotal + " + roll " + rollValue);
            diceTotal += rollValue;
//            System.out.println(" = New health: " + diceTotal + ".");
        }
//        System.out.println();
        return diceTotal;
    }

    // endregion


    // ============================================
    //      UI Methods
    // ============================================

    // region Count: 0



    // endregion


    // ============================================
    //      Combat Methods
    // ============================================

    // region Count: 3

    public static int rollEnemy(){
        int rollValue = (int) (Math.random() * 2);
        return rollValue;
    }

    public static int battleFrame(int enemyHealth, int playerAttackDamage, int playerDamageMod, int enemyDamage, int enemyDamageMod, double attackSpeed, int playerArmor, int enemyArmor, double playerCritChance, double enemyCritChance){
        //Had idea about storing all player and enemy stats in arrays and trying to pass the entire array to this method for input.
        //That would make this much cleaner and easier to call in Main method.

        int playerHealth = Game.player.playerHealth;
        int earnedXP = 0;
        int damageDealt = 0;
        double extraAttack = 0.0;

        Scanner getInput = new Scanner(System.in);
        while((enemyHealth > 0) && (playerHealth > 0)){

            System.out.println("What would you like to do?");
            slowPrint("1. Attack  2. Move");
            slowPrint("3. Mock    4. Run");
            int frameSelection = getInput.nextInt();

            if((frameSelection == 1) || (frameSelection == 2) || (frameSelection == 3) || (frameSelection == 4)) {
                if (frameSelection == 1) {
                    damageDealt = makeAnAttack(playerAttackDamage, playerDamageMod, enemyArmor, playerCritChance);
                    extraAttack += attackSpeed - 1d;
                    if(damageDealt > 0) {
                        slowPrint("B A M !");
                        enemyHealth -= damageDealt;
//                        earnedXP += 3;
                        slowPrint("Wow! You dealt " + damageDealt + " damage to the target.");
                    } else {
                        slowPrint("Player failed to deal damage this round");
//                        earnedXP += 3;
                    }
                    if(enemyHealth > 0){
                        slowPrint("Enemy Health is " + enemyHealth);
                    } else {
                        slowPrint("Enemy slain! You survived the battle.");
                        break;
                    }
                    if(extraAttack >= 1){
                        slowPrint("---EXTRA ATTACK---");
                        damageDealt = makeAnAttack(playerAttackDamage, playerDamageMod, enemyArmor, playerCritChance);
                        if(damageDealt > 0) {
                            slowPrint("P O W !");
                            enemyHealth -= damageDealt;
//                            earnedXP += 3;
                            slowPrint("Wow! You dealt " + damageDealt + " damage to the target.");
                        } else {
                            slowPrint("Player failed to deal damage this round");
//                            earnedXP += 0;
                        }
                        if(enemyHealth > 0){
                            slowPrint("Enemy Health is " + enemyHealth);
                        } else {
                            slowPrint("Enemy slain! You survived the battle.");
                            break;
                        }
                        extraAttack = 0.0;
                    }
                } else if(frameSelection == 2) {
                    slowPrint("The Movement Module doesn't exist yet.");
                } else if(frameSelection == 3){
                    slowPrint("The Mock Module doesn't exist yet.");
                } else {
                    slowPrint("Adventurer abandoned their Quest...");
                    return 40;
                }

            } else {
                slowPrint("That's not an option.");
            }
            if(enemyHealth > 0) {
                slowPrint("Enemy Turn Begins.");
                damageDealt = makeAnAttack(enemyDamage, enemyDamageMod, playerArmor, enemyCritChance);
                if (damageDealt > 0) {
                    playerHealth -= damageDealt;
                } else {
                    slowPrint("Enemy failed to deal damage this round");
                }
                if(playerHealth > 0){
                    slowPrint("You took " + damageDealt + " damage.");
                    slowPrint("Player health is " + playerHealth);
                }
            } else {
                slowPrint("Enemy health has been reduced to 0.");
            }
        }
//        if(playerHealth <= 0){
//            System.out.println("You lost the fight");
//            Game.player.playerHealth = playerHealth;
//            return earnedXP;
//        } else
        if(enemyHealth <= 0){
            slowPrint("You won the fight! Your closing health is " + playerHealth);
            Game.player.playerHealth = playerHealth;
//            return earnedXP;
            return Game.enemyNPC.earnedExp;
        } else {
            return 0;
        }
        // while(())
//        if(earnedXP < 3){
//            return 3;
//        } else {
//            return earnedXP;
//        }
    }

    public static int CONSOLEbattleFrame(int enemyHealth, int playerAttackDamage, int playerDamageMod, int enemyDamage, int enemyDamageMod, double attackSpeed, int playerArmor, int enemyArmor, double playerCritChance, double enemyCritChance){
        //Had idea about storing all player and enemy stats in arrays and trying to pass the entire array to this method for input.
        //That would make this much cleaner and easier to call in Main method.

        int playerHealth = Game.player.playerHealth;
        int earnedXP = 0;
        int damageDealt = 0;
        double extraAttack = 0.0;

        Scanner getInput = new Scanner(System.in);
        while((enemyHealth > 0) && (playerHealth > 0)){

            System.out.println("What would you like to do?");
            slowPrint("1. Attack  2. Move");
            slowPrint("3. Mock    4. Run");
            int frameSelection = getInput.nextInt();

            if((frameSelection == 1) || (frameSelection == 2) || (frameSelection == 3) || (frameSelection == 4)) {
                if (frameSelection == 1) {
                    damageDealt = makeAnAttack(playerAttackDamage, playerDamageMod, enemyArmor, playerCritChance);
                    extraAttack += attackSpeed - 1d;
                    if(damageDealt > 0) {
                        slowPrint("B A M !");
                        enemyHealth -= damageDealt;
//                        earnedXP += 3;
                        slowPrint("Wow! You dealt " + damageDealt + " damage to the target.");
                    } else {
                        slowPrint("Player failed to deal damage this round");
//                        earnedXP += 3;
                    }
                    if(enemyHealth > 0){
                        slowPrint("Enemy Health is " + enemyHealth);
                    } else {
                        slowPrint("Enemy slain! You survived the battle.");
                        break;
                    }
                    if(extraAttack >= 1){
                        slowPrint("---EXTRA ATTACK---");
                        damageDealt = makeAnAttack(playerAttackDamage, playerDamageMod, enemyArmor, playerCritChance);
                        if(damageDealt > 0) {
                            slowPrint("P O W !");
                            enemyHealth -= damageDealt;
//                            earnedXP += 3;
                            slowPrint("Wow! You dealt " + damageDealt + " damage to the target.");
                        } else {
                            slowPrint("Player failed to deal damage this round");
//                            earnedXP += 0;
                        }
                        if(enemyHealth > 0){
                            slowPrint("Enemy Health is " + enemyHealth);
                        } else {
                            slowPrint("Enemy slain! You survived the battle.");
                            break;
                        }
                        extraAttack = 0.0;
                    }
                } else if(frameSelection == 2) {
                    slowPrint("The Movement Module doesn't exist yet.");
                } else if(frameSelection == 3){
                    slowPrint("The Mock Module doesn't exist yet.");
                } else {
                    slowPrint("Adventurer abandoned their Quest...");
                    return 40;
                }

            } else {
                slowPrint("That's not an option.");
            }
            if(enemyHealth > 0) {
                slowPrint("Enemy Turn Begins.");
                damageDealt = makeAnAttack(enemyDamage, enemyDamageMod, playerArmor, enemyCritChance);
                if (damageDealt > 0) {
                    playerHealth -= damageDealt;
                } else {
                    slowPrint("Enemy failed to deal damage this round");
                }
                if(playerHealth > 0){
                    slowPrint("You took " + damageDealt + " damage.");
                    slowPrint("Player health is " + playerHealth);
                }
            } else {
                slowPrint("Enemy health has been reduced to 0.");
            }
        }
//        if(playerHealth <= 0){
//            System.out.println("You lost the fight");
//            Game.player.playerHealth = playerHealth;
//            return earnedXP;
//        } else
        if(enemyHealth <= 0){
            slowPrint("You won the fight! Your closing health is " + playerHealth);
            Game.player.playerHealth = playerHealth;
//            return earnedXP;
            return Game.enemyNPC.earnedExp;
        } else {
            return 0;
        }
        // while(())
//        if(earnedXP < 3){
//            return 3;
//        } else {
//            return earnedXP;
//        }
    }

    public static int makeAnAttack(int attackerDamage, int attackerDamageMod, int targetArmor, double attackerCritChance) {
        int finalDamage = attackerDamage + attackerDamageMod;
        int reducedDamage = targetArmor / 10;
        boolean isCrit = false;
        double criticalStrike = attackerCritChance / 100;

        if((Math.random() * 100) >= targetArmor){
            if(Math.random() >= (1 - criticalStrike)){
                isCrit = true;
                System.out.println("It's a crit!");
            }

            if(isCrit){
                finalDamage = finalDamage * 2;
            }

            finalDamage = finalDamage - reducedDamage;
            return finalDamage;
        } else {
            System.out.println("Attacked missed!");
            return 0;
        }
    }

    // endregion


    // ============================================
    //      Deprecated Methods
    // ============================================

    // region Count: 0



    // endregion

}
