import java.util.Scanner;

public class Game {

    public static void main(String[] args) {

//      Variable definitions
        String playerName;
        String playerWeapon;
        String playerClass;

        int weaponChoice;
        int classChoice;
        int attackDamage;
        int attackSpeed;
        int playerHealth;
        int playerStrength;
        int playerDexterity;
        int playerConstitution;
        int playerIntelligence;
        int playerWisdom;
        int playerCharisma;

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
            attackSpeed = 10;
            critChance = 10.0;
        } else if(weaponChoice == 2){
            playerWeapon = "Wand";
            attackDamage = 3;
            attackSpeed = 12;
            critChance = 9.0;
        } else if(weaponChoice == 3){
            playerWeapon = "Crossbow";
            attackDamage = 5;
            attackSpeed = 8;
            critChance = 15.0;
        } else {
            playerWeapon = "Brass Knuckles";
            attackDamage = 2;
            attackSpeed = 14;
            critChance = 12.5;
        }

        slowPrint("Ok, so you chose the " + playerWeapon + ". Which deals " + attackDamage + " damage, has an attack speed of " + ((double) (attackSpeed / 10)) + ", and " + critChance + "% critical chance.");
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
            playerHealth = diceRoll(10, 3, 3);
            playerStrength = statDiceRoll(6, 4, 2);
            playerDexterity = statDiceRoll(6, 4, 0);
            playerConstitution = statDiceRoll(6, 4, 2);
            playerIntelligence = statDiceRoll(6, 4, 0);
            playerWisdom = statDiceRoll(6, 4, 1);
            playerCharisma = statDiceRoll(6, 4, 0);
        }
        int health = diceRoll(10, 4, 0);
        int armor = 12;


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

    public static int makeAnAttack(int targetHealth, int attackerDamage, int targetArmor, double attackerCritChance) {
        int newHP = targetHealth;
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
            newHP = newHP - finalDamage;
            if(newHP < 0){
                newHP = 0;
                return newHP;
            }
            return newHP;
        } else {
            return newHP;
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
            System.out.println(rollValue);
            System.out.println(minRoll);
        }
        diceTotal -= minRoll;
        diceTotal += rollModifier;
        return diceTotal;
    }
    public static int diceRoll(int numberOfSides, int numberOfDice, int rollModifier){

        int rollValue;
        int diceTotal = 0;
        for(int i = 1; i <= numberOfDice; i++) {
            rollValue = (int) (Math.random() * numberOfSides + 1);
            diceTotal += rollValue;
            System.out.println(rollValue);
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
                Thread.sleep(100);
            } catch(InterruptedException e) {

            }
        }
        System.out.println();
    }

}
