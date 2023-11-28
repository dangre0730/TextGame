public class Enemy {
//Using this class to teach myself how to create a large list of enemies with their stats.
//  Then call this class in the main method and randomly select an enemy to fight.

    //Declare Variables
    //Integers
    Integer enemySelection;
    Integer enemyHealth;
    Integer enemyArmor;
    Integer enemyDamage;
    Integer enemyDamageMod;
    Integer earnedExp;
    //Doubles
    Double enemyCriticalChance;
    //Strings
    String enemyName;
    String region;

//    String[] enemyList = {"Training Dummy", "Swarm of Rats"};
    public void enemyStats(int enemySelection) {
        if(enemySelection == 0) {
            enemyName = "Training Dummy";
            region = "Tutorial";
            enemyHealth = 15;
            enemyDamage = 2;
            enemyDamageMod = 0;
            enemyArmor = 1;
            enemyCriticalChance = 0.0;
            earnedExp = 9;
        } else if (enemySelection == 1) {
            enemyName = "Swarm of Rats";
            region = "Tutorial";
            enemyHealth = 20;
            enemyDamage = 2;
            enemyDamageMod = 1;
            enemyArmor = 0;
            enemyCriticalChance = 2.0;
            earnedExp = 12;
        }
        Functions.slowPrint("You are fighting a " + enemyName + ". Enemy Health is " + enemyHealth + ". Enemy Armor is " + enemyArmor + ". Enemy Damage is " + enemyDamage + ". Enemy Damage Modifier is " + enemyDamageMod + ". Enemy Critical Chance is " + enemyCriticalChance +
                ". Enemy spawn region is " + region + ". Enemy Experience Value is " + earnedExp + ".");
    }

}
