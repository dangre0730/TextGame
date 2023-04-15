public class Game {

    public static void main(String[] args) {

        int health = diceRoll(10, 4, 0);
        int armor = 12;
        int attack = 5;
        double critChance = 12.5;

        int enemyHealth = diceRoll(6, 4, 0);
        int enemyArmor = 10;
        int enemyAttack = 7;
        double enemyCritChance = 15;

        System.out.println();

        System.out.println("Starting health " + health);
        System.out.println("Enemy Health " + enemyHealth);

        System.out.println("Let's fight an enemy.");
        while((health > 0) && (enemyHealth > 0)){
            enemyHealth = makeAnAttack(enemyHealth, attack, enemyArmor, critChance);
            System.out.println("Enemy health after attack " + enemyHealth);
            while((health > 0) && (enemyHealth > 0)) {
                health = makeAnAttack(health, enemyAttack, armor, enemyCritChance);
                System.out.println("Player health after attack " + health);
                break;
            }
        }


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
