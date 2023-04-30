import java.util.Scanner;
public class Game {

    public static void main(String[] args) {
        Player player = new Player();

//      Variable definitions
//        String playerName;
        String playerWeapon;
//        String playerClass;

        player.playerExp = 0;
        int weaponChoice;
        int classSelection;
        int attackDamage;
//        int hitDie = 0;
//        int playerHealth;
//        int playerStrength = 0;
//        int strMod;
//        int playerDexterity = 0;
//        int dexMod;
//        int playerConstitution = 0;
//        int conMod;
//        int playerIntelligence = 0;
//        int intMod;
//        int playerWisdom = 0;
//        int wisMod;
//        int playerCharisma = 0;
//        int chaMod;
        int enemyHealth;
        int enemyDamage;
        int enemyArmor;
        double enemyCritChance;

        double critChance;
        double attackSpeed;

        Scanner getInput = new Scanner(System.in);
        System.out.println("Hello, Traveler! I'm Abernathy, what is your name?");
        player.playerName = getInput.nextLine();

        slowPrint("Well then, it's nice to meet you, " + player.playerName + ". This is my world, made with these two hands, and I use it to test out cool new features for my game.");
        slowPrint("Today is 'Character Building Day' and, great news, you are my Test Subject! So lets start with a simple questionnaire.");
        slowPrint("Which would you prefer in a fight? (Enter the corresponding number to the weapon of choice)");
        slowPrint("1. A Sword");
        slowPrint("2. A Wand");
        slowPrint("3. A Crossbow");
        slowPrint("4. Brass Knuckles");
        weaponChoice = getInput.nextInt();

        if((weaponChoice != 1) && (weaponChoice != 2) && (weaponChoice != 3 ) && (weaponChoice != 4)){
            while((weaponChoice != 1) && (weaponChoice != 2) && (weaponChoice != 3 ) && (weaponChoice != 4)){
                slowPrint("That's not one of the options. Try picking something that I have available.");
                slowPrint("1. A Sword");
                slowPrint("2. A Wand");
                slowPrint("3. A Crossbow");
                slowPrint("4. Brass Knuckles");
                weaponChoice = getInput.nextInt();
                System.out.println(weaponChoice);
            }
            System.out.println(weaponChoice);
        }

        if(weaponChoice == 1){
            playerWeapon = "Long Sword";
            attackDamage = 4;
            attackSpeed = 1.2;
            critChance = 10.0;
        } else if(weaponChoice == 2){
            playerWeapon = "Wand";
            attackDamage = 3;
            attackSpeed = 1.4;
            critChance = 9.0;
        } else if(weaponChoice == 3){
            playerWeapon = "Crossbow";
            attackDamage = 5;
            attackSpeed = 1.0;
            critChance = 15.0;
        } else {
            playerWeapon = "Brass Knuckles";
            attackDamage = 2;
            attackSpeed = 1.6;
            critChance = 12.5;
        }

        slowPrint("Ok, so you chose the " + playerWeapon + ". Which deals " + attackDamage + " damage, has an attack speed of " + attackSpeed + ", and " + critChance + "% critical chance.");
        slowPrint("Now, lets go ahead and have you pick a class. Classes determine the likelihood that certain stats will roll better than others, ");
        slowPrint("    though that doesn't mean you can't roll poorly. Your stats are based on die rolls with different modifiers based on your");
        slowPrint("    chosen class. Anyways, go ahead and pick a class so we can proceed to the fun stuff.");
        slowPrint("1. Soldier");
        slowPrint("2. Ranger");
        slowPrint("3. Brawler");
        slowPrint("4. Mage");

        classSelection = getInput.nextInt();
        if((classSelection != 1) && (classSelection != 2) && (classSelection != 3 ) && (classSelection != 4)){
            while((classSelection != 1) && (classSelection != 2) && (classSelection != 3 ) && (classSelection != 4)){
                slowPrint("That's not one of the options. Try picking something that I have available.");
                slowPrint("1. Soldier");
                slowPrint("2. Ranger");
                slowPrint("3. Brawler");
                slowPrint("4. Mage");
                classSelection = getInput.nextInt();
            }
        }
        if(classSelection == 1){
            player.pcClass = Player.classChoice.SOLDIER;
//            hitDie = 12;
//            playerStrength = statDiceRoll(6, 4, 2);
//            playerDexterity = statDiceRoll(6, 4, 0);
//            playerConstitution = statDiceRoll(6, 4, 2);
//            playerIntelligence = statDiceRoll(6, 4, 0);
//            playerWisdom = statDiceRoll(6, 4, 1);
//            playerCharisma = statDiceRoll(6, 4, 0);
        }
        if(classSelection == 2){
            player.pcClass = Player.classChoice.RANGER;
//            hitDie = 8;
//            playerStrength = statDiceRoll(6, 4, 0);
//            playerDexterity = statDiceRoll(6, 4, 2);
//            playerConstitution = statDiceRoll(6, 4, 0);
//            playerIntelligence = statDiceRoll(6, 4, 2);
//            playerWisdom = statDiceRoll(6, 4, 1);
//            playerCharisma = statDiceRoll(6, 4, 0);
        }
        if(classSelection == 3){
            player.pcClass = Player.classChoice.BRAWLER;
//            hitDie = 10;
//            playerStrength = statDiceRoll(6, 4, 2);
//            playerDexterity = statDiceRoll(6, 4, 0);
//            playerConstitution = statDiceRoll(6, 4, 1);
//            playerIntelligence = statDiceRoll(6, 4, 0);
//            playerWisdom = statDiceRoll(6, 4, 1);
//            playerCharisma = statDiceRoll(6, 4, 1);
        }
        if(classSelection == 4){
            player.pcClass = Player.classChoice.MAGE;
//            hitDie = 6;
//            playerStrength = statDiceRoll(6, 4, 0);
//            playerDexterity = statDiceRoll(6, 4, 1);
//            playerConstitution = statDiceRoll(6, 4, 0);
//            playerIntelligence = statDiceRoll(6, 4, 2);
//            playerWisdom = statDiceRoll(6, 4, 2);
//            playerCharisma = statDiceRoll(6, 4, 0);
        }
        System.out.println(player.pcClass);
        player.classBuilder();
//        if(playerStrength < 8){
//            playerStrength = 8;
//        }
//        strMod = (playerStrength - 10) / 2;
//        if(playerDexterity < 8){
//            playerDexterity = 8;
//        }
//        dexMod = (playerDexterity - 10) / 2;
//        if(playerConstitution < 8){
//            playerConstitution = 8;
//        }
//        conMod = (playerConstitution - 10) / 2;
//        if(playerIntelligence < 8){
//            playerIntelligence = 8;
//        }
//        intMod = (playerIntelligence - 10) / 2;
//        if(playerWisdom < 8){
//            playerWisdom = 8;
//        }
//        wisMod = (playerWisdom - 10) / 2;
//        if(playerCharisma < 8){
//            playerCharisma = 8;
//        }
//        chaMod = (playerCharisma - 10) / 2;
//        playerHealth = healthDiceRoll(hitDie, 4, conMod);

        System.out.println("Health: " + player.playerHealth + "| Hit Die: " + player.hitDie);
        System.out.println("Strength: " + player.playerStrength + "| Str Mod: " + player.strMod);
        System.out.println("Dexterity: " + player.playerDexterity + "| Dex Mod: " + player.dexMod);
        System.out.println("Constitution: " + player.playerConstitution + "| Con Mod: " + player.conMod);
        System.out.println("Intelligence: " + player.playerIntelligence + "| Int Mod: " + player.intMod);
        System.out.println("Wisdom: " + player.playerWisdom + "| Wis Mod: " + player.wisMod);
        System.out.println("Charisma: " + player.playerCharisma + "| Cha Mod: " + player.chaMod);
        int armor = 12;

//        slowPrint("Ok, great! All of those values above are your Character Stats. Those play super important roles in my world and are vital for interacting with my world in a significant way.");
//        slowPrint("Here are some examples to help things make sense:");
//        slowPrint("    Let's say you are trying to climb up a wall using a rope. I will make you roll a strength check, which means a d20 plus your Strength Modifier.");
//        slowPrint("        For you, that would be a modifier of " + player.strMod + ". And if you were grappled by an enemy you would have to make a strength save, using the same roll method.");
//        slowPrint("    The same goes for Dexterity: Stealing an item - Dexterity Check | Dodging a Fireball - Dexterity Save");
//        slowPrint("    Constitution: Constitution Modifier is added to hit die when getting extra health after a level up");
//        slowPrint("    Intelligence: You are trying to solve a riddle - Intelligence Check | Seeing through an enemy charming you");
//        slowPrint("    Wisdom: ");
//        slowPrint("    Charisma: ");

        slowPrint("Now that we've covered stats, lets test out your weapon on a training dummy. Don't worry, it usually won't hit back.");
//        slowPrint("What would you like to do?");
//        slowPrint("1. Attack  2. Move");
//        slowPrint("3. Mock    4. Run");
        enemyHealth = 25;
        enemyDamage = 2;
        enemyArmor = 0;
        enemyCritChance = 0.0;
        int earnedExp = 0;

        slowPrint("Your current level is: " + player.playerLevel);
        slowPrint("Your starting health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
        earnedExp = battleFrame(player.playerHealth, enemyHealth, attackDamage, enemyDamage, attackSpeed, armor, enemyArmor, critChance, enemyCritChance);
        player.playerExp += earnedExp;
        slowPrint("Your ending health is: " + player.playerHealth + ". Out of a total health of: " + player.maxPlayerHealth);
        player.playerHealth = player.maxPlayerHealth;
        slowPrint("Health restored, current health is: " + player.playerHealth);
        slowPrint("You earned " + earnedExp + " experience for a grand total of " + player.playerExp);
        if(player.playerExp >= 3){
            player.playerLevel += 1;
            slowPrint("New Level: " + player.playerLevel);
        }
        slowPrint("Look at that, your Character Experience total went up enough to gain a new level! When you hit certain milestones of experience, you will level up and earn new class features.");

    }

    public static int battleFrame(int playerHealth, int enemyHealth, int attackDamage, int enemyDamage, double attackSpeed, int playerArmor, int enemyArmor, double playerCritChance, double enemyCritChance){
    //Had idea about storing all player and enemy stats in arrays and trying to pass the entire array to this method for input.
    //That would make this much cleaner and easier to call in Main method.

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
                    damageDealt = makeAnAttack(attackDamage, enemyArmor, playerCritChance);
                    extraAttack += attackSpeed - 1d;
                    if(damageDealt > 0) {
                        enemyHealth -= damageDealt;
                        earnedXP += 3;
                    } else {
                        slowPrint("Player failed to deal damage this round");
                        earnedXP += 3;
                    }
                    if(enemyHealth > 0){
                        slowPrint("Enemy Health is " + enemyHealth);
                    } else {
                        slowPrint("Enemy slain! You survived the battle.");
                        break;
                    }
                    if(extraAttack > 1){
                        slowPrint("---EXTRA ATTACK---");
                        damageDealt = makeAnAttack(attackDamage, enemyArmor, playerCritChance);
                        if(damageDealt > 0) {
                            enemyHealth -= damageDealt;
                            earnedXP += 3;
                        } else {
                            slowPrint("Player failed to deal damage this round");
                            earnedXP += 3;
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
                    slowPrint("Adventurers don't run from fights.");
                }

            } else {
                slowPrint("That's not an option.");
            }
            if(enemyHealth > 0) {
                slowPrint("Enemy Turn Begins.");
                damageDealt = makeAnAttack(enemyDamage, playerArmor, enemyCritChance);
                if (damageDealt > 0) {
                    playerHealth -= damageDealt;
                } else {
                    slowPrint("Enemy failed to deal damage this round");
                }
                if(playerHealth > 0){
                    slowPrint("Player health is " + playerHealth);
                }
            } else {
                slowPrint("Enemy health has been reduced to 0.");
            }
        }
        if(playerHealth == 0){
            System.out.println("You lost the fight");
            return earnedXP;
        } else if(enemyHealth == 0){
            slowPrint("You won the fight!");
            return earnedXP;
        }
       // while(())
        if(earnedXP < 3){
            return 3;
        } else {
            return earnedXP;
        }
    }
    public static int makeAnAttack(int attackerDamage, int targetArmor, double attackerCritChance) {
        int finalDamage = attackerDamage;
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

//    public static int statDiceRoll(int numberOfSides, int numberOfDice, int rollModifier){
//
//        int minRoll = 100;
//        int rollValue;
//        int diceTotal = 0;
//
//        for(int i = 0; i < numberOfDice; i++) {
//            rollValue = (int) (Math.random() * numberOfSides + 1);
//            diceTotal += rollValue;
//            if(minRoll > rollValue){
//                minRoll = rollValue;
//            }
//        }
//        diceTotal -= minRoll;
////        System.out.println("Dropped lowest value: " + minRoll);
//        diceTotal += rollModifier;
//        return diceTotal;
//    }

    public static int healthDiceRoll(int numberOfSides, int numberOfDice, int rollModifier){

        int rollValue;
        int diceTotal = 0;

        for(int i = 0; i < numberOfDice; i++) {
            rollValue = (int) (Math.random() * numberOfSides + 1);
            diceTotal += rollValue + rollModifier;
            System.out.print(rollValue + " ");
         }
        System.out.println();
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
