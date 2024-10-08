import java.util.Scanner;

public class Functions {

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
            return Game.enemy.earnedExp;
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

}
