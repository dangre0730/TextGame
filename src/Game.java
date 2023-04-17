import java.util.Scanner;

public class Game {

    public static void main(String[] args) {

//      Variable definitions
        String playerName;
        String playerWeapon;
        String playerClass;

        int playerExp = 0;
        int weaponChoice;
        int classChoice;
        int attackDamage;
        int attackSpeed;
        int hitDie = 0;
        int playerHealth = 0;
        int playerStrength = 0;
        int strMod = 0;
        int playerDexterity = 0;
        int dexMod = 0;
        int playerConstitution = 0;
        int conMod = 0;
        int playerIntelligence = 0;
        int intMod = 0;
        int playerWisdom = 0;
        int wisMod = 0;
        int playerCharisma = 0;
        int chaMod = 0;
        int enemyHealth = 0;
        int enemyDamage = 0;
        int enemyArmor = 0;
        double enemyCritChance = 0.0;

        double critChance;

        Scanner getInput = new Scanner(System.in);
        System.out.println("Hello, Traveler! I'm Abernathy, what is your name?");
        playerName = getInput.nextLine();

        slowPrint("Well then, it's nice to meet you, " + playerName + ". This is my world, made with these two hands, and I use it to test out cool new features for my game.");
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
            attackSpeed = 12;
            critChance = 10.0;
        } else if(weaponChoice == 2){
            playerWeapon = "Wand";
            attackDamage = 3;
            attackSpeed = 14;
            critChance = 9.0;
        } else if(weaponChoice == 3){
            playerWeapon = "Crossbow";
            attackDamage = 5;
            attackSpeed = 10;
            critChance = 15.0;
        } else {
            playerWeapon = "Brass Knuckles";
            attackDamage = 2;
            attackSpeed = 16;
            critChance = 12.5;
        }

        slowPrint("Ok, so you chose the " + playerWeapon + ". Which deals " + attackDamage + " damage, has an attack speed of " + ((double) (attackSpeed / 10d)) + ", and " + critChance + "% critical chance.");
        slowPrint("Now, lets go ahead and have you pick a class. Classes determine the likelihood that certain stats will roll better than others, ");
        slowPrint("    though that doesn't mean you can't roll poorly. Your stats are based on die rolls with different modifiers based on your");
        slowPrint("    chosen class. Anyways, go ahead and pick a class so we can proceed to the fun stuff.");
        slowPrint("1. Soldier");
        slowPrint("2. Ranger");
        slowPrint("3. Brawler");
        slowPrint("4. Mage");

        classChoice = getInput.nextInt();
        if((classChoice != 1) && (classChoice != 2) && (classChoice != 3 ) && (classChoice != 4)){
            while((classChoice != 1) && (classChoice != 2) && (classChoice != 3 ) && (classChoice != 4)){
                slowPrint("That's not one of the options. Try picking something that I have available.");
                slowPrint("1. Soldier");
                slowPrint("2. Ranger");
                slowPrint("3. Brawler");
                slowPrint("4. Mage");
                classChoice = getInput.nextInt();
            }
        }
        if(classChoice == 1){
            playerClass = "Veteran Soldier";
            hitDie = 12;
            playerStrength = statDiceRoll(6, 4, 2);
            playerDexterity = statDiceRoll(6, 4, 0);
            playerConstitution = statDiceRoll(6, 4, 2);
            playerIntelligence = statDiceRoll(6, 4, 0);
            playerWisdom = statDiceRoll(6, 4, 1);
            playerCharisma = statDiceRoll(6, 4, 0);
        }
        if(classChoice == 2){
            playerClass = "Forest Ranger";
            hitDie = 8;
            playerStrength = statDiceRoll(6, 4, 0);
            playerDexterity = statDiceRoll(6, 4, 2);
            playerConstitution = statDiceRoll(6, 4, 0);
            playerIntelligence = statDiceRoll(6, 4, 2);
            playerWisdom = statDiceRoll(6, 4, 1);
            playerCharisma = statDiceRoll(6, 4, 0);
        }
        if(classChoice == 3){
            playerClass = "Tavern Brawler";
            hitDie = 10;
            playerStrength = statDiceRoll(6, 4, 2);
            playerDexterity = statDiceRoll(6, 4, 0);
            playerConstitution = statDiceRoll(6, 4, 1);
            playerIntelligence = statDiceRoll(6, 4, 0);
            playerWisdom = statDiceRoll(6, 4, 1);
            playerCharisma = statDiceRoll(6, 4, 1);
        }
        if(classChoice == 4){
            playerClass = "Old Mage";
            hitDie = 6;
            playerStrength = statDiceRoll(6, 4, 0);
            playerDexterity = statDiceRoll(6, 4, 1);
            playerConstitution = statDiceRoll(6, 4, 0);
            playerIntelligence = statDiceRoll(6, 4, 2);
            playerWisdom = statDiceRoll(6, 4, 2);
            playerCharisma = statDiceRoll(6, 4, 0);
        }
        if(playerStrength < 8){
            playerStrength = 8;
        }
        strMod = (playerStrength - 10) / 2;
        if(playerDexterity < 8){
            playerDexterity = 8;
        }
        dexMod = (playerDexterity - 10) / 2;
        if(playerConstitution < 8){
            playerConstitution = 8;
        }
        conMod = (playerConstitution - 10) / 2;
        if(playerIntelligence < 8){
            playerIntelligence = 8;
        }
        intMod = (playerIntelligence - 10) / 2;
        if(playerWisdom < 8){
            playerWisdom = 8;
        }
        wisMod = (playerWisdom - 10) / 2;
        if(playerCharisma < 8){
            playerCharisma = 8;
        }
        chaMod = (playerCharisma - 10) / 2;
        playerHealth = healthDiceRoll(hitDie, 4, conMod);


        System.out.println("Health: " + playerHealth + "| Hit Die: " + hitDie);
        System.out.println("Strength: " + playerStrength + "| Str Mod: " + strMod);
        System.out.println("Dexterity: " + playerDexterity + "| Dex Mod: " + dexMod);
        System.out.println("Constitution: " + playerConstitution + "| Con Mod: " + conMod);
        System.out.println("Intelligence: " + playerIntelligence + "| Int Mod: " + intMod);
        System.out.println("Wisdom: " + playerWisdom + "| Wis Mod: " + wisMod);
        System.out.println("Charisma: " + playerCharisma + "| Cha Mod: " + chaMod);
        int armor = 12;

        slowPrint("Ok, great! All of those values above are your Character Stats. Those play super important roles in my world and are vital for interacting with my world in a significant way.");
        slowPrint("Here are some examples to help things make sense:");
        slowPrint("    Let's say you are trying to climb up a wall using a rope. I will make you roll a strength check, which means a d20 plus your Strength Modifier.");
        slowPrint("        For you, that would be a modifier of " + strMod + ". And if you were grappled by an enemy you would have to make a strength save, using the same roll method.");
        slowPrint("    The same goes for Dexterity: Stealing an item - Dexterity Check | Dodging a Fireball - Dexterity Save");
        slowPrint("    Constitution: Constitution Modifier is added to hit die when getting extra health after a level up");
        slowPrint("    Intelligence: You are trying to solve a riddle - Intelligence Check | Seeing through an enemy charming you");
        slowPrint("    Wisdom: ");
        slowPrint("    Charisma: ");

        slowPrint("Now that we've covered stats, lets test out your weapon on a training dummy. Don't worry, it usually won't hit back.");
//        slowPrint("What would you like to do?");
//        slowPrint("1. Attack  2. Move");
//        slowPrint("3. Mock    4. Run");
        enemyHealth = 25;
        enemyDamage = 0;
        enemyArmor = 0;
        enemyCritChance = 0.0;
        int earnedExp = 0;

        earnedExp = battleFrame(playerHealth, enemyHealth, attackDamage, enemyDamage, armor, enemyArmor, critChance, enemyCritChance);
        playerExp += earnedExp;
        slowPrint("You earned " + earnedExp + " experience for a grand total of " + playerExp);
        slowPrint("Look at that, your Character Experience total went up! When you hit certain milestones of experience, you will level up and earn new class features.");
//        int enemyHealth = diceRoll(6, 4, 0);
//        int enemyArmor = 10;
//        int enemyAttack = 7;
//        double enemyCritChance = 15;
//
//        System.out.println("Starting health " + health);
//        System.out.println("Enemy Health " + enemyHealth);
//
//        System.out.println("Let's fight an enemy.");
//        while((health > 0) && (enemyHealth > 0)){
//            enemyHealth = makeAnAttack(enemyHealth, attackDamage, enemyArmor, critChance);
//            System.out.println("Enemy health after attack " + enemyHealth);
//            while((health > 0) && (enemyHealth > 0)) {
//                health = makeAnAttack(health, enemyAttack, armor, enemyCritChance);
//                System.out.println("Player health after attack " + health);
//                break;
//            }
//        }


    }

    public static int battleFrame(int playerHealth, int enemyHealth, int attackDamage, int enemyDamage, int playerArmor, int enemyArmor, double playerCritChance, double enemyCritChance){

        int earnedXP = 0;
        int damageDealt = 0;
        Scanner getInput = new Scanner(System.in);
        while((enemyHealth > 0) && (playerHealth > 0)){

            System.out.println("What would you like to do?");
            slowPrint("1. Attack  2. Move");
            slowPrint("3. Mock    4. Run");
            int frameSelection = getInput.nextInt();

            if((frameSelection == 1) || (frameSelection == 2) || (frameSelection == 3) || (frameSelection == 4)) {
                if (frameSelection == 1) {
                    damageDealt = makeAnAttack(attackDamage, enemyArmor, playerCritChance);
                    if(damageDealt >= 0) {
                        enemyHealth -= damageDealt;
                        earnedXP += 3;
                    } else {
                        earnedXP += 3;
                    }
                    slowPrint("Enemy Health is " + enemyHealth);
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
            slowPrint("Enemy Turn Begins.");
            playerHealth -= makeAnAttack(enemyDamage, playerArmor, enemyCritChance);
            slowPrint("Player health is " + playerHealth);
        }
        if(playerHealth == 0){
            System.out.println("You lost the fight");
            return earnedXP;
        } else if(enemyHealth == 0){
            slowPrint("You won the fight!");
            return earnedXP;
        }
       // while(())
        return 3;
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

    public static int healthDiceRoll(int numberOfSides, int numberOfDice, int rollModifier){

        int rollValue;
        int diceTotal = 0;

        for(int i = 0; i < numberOfDice; i++) {
            rollValue = (int) (Math.random() * numberOfSides + 1);
            diceTotal += rollValue + rollModifier;
            System.out.print(rollValue);
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
                Thread.sleep(70);
            } catch(InterruptedException e) {

            }
        }
        System.out.println();
    }

}
