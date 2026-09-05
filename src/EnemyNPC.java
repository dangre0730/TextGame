public class EnemyNPC extends NPC {
//Using this class to teach myself how to create a large list of enemies with their stats.
//  Then call this class in the main method and randomly select an enemy to fight.

    //Declare Variables
    //Integers
    int enemySelection;
    int enemyHealth;
    int enemyArmor;
    int enemyDamage;
    int enemyDamageMod;
    int earnedExp;
    // Tracks how many times combatLoop has been called for this enemy. 0 = first call (show intro), 1+ = resolve round.
    int turnsTaken = 0;
    //Doubles
    double enemyCriticalChance;
    // Booleans
    // Set to true when enemy HP reaches 0. Switches combatLoop to the post-fight options branch.
    boolean combatOver = false;
    //Strings
    String name;
    String region;

    public EnemyNPC(Room.RoomObjects location, int enemySelection){
        super("", location);

        enemyStats(enemySelection);
    }

    public EnemyNPC() {
        super();
    }

    public String toString(){
        return getClass().getName() + "[" + name + "," + location + "," + enemyHealth + "," + enemyArmor + "," + enemyDamage + "]";
    }
//    String[] enemyList = {"Training Dummy", "Swarm of Rats"};

    // Populates all combat stats for this enemy. Called automatically by the constructor via enemySelection param.
    public void enemyStats(int enemySelection) {
        if(enemySelection == 0) {
            name = "Training Dummy";
            region = "Tutorial";
            enemyHealth = 50;
            enemyDamage = 2;
            enemyDamageMod = 0;
            enemyArmor = 1;
            enemyCriticalChance = 0.0;
            earnedExp = 9;
        } else if (enemySelection == 1) {
            name = "Swarm of Rats";
            region = "Tutorial";
            enemyHealth = 50;
            enemyDamage = 2;
            enemyDamageMod = 1;
            enemyArmor = 0;
            enemyCriticalChance = 2.0;
            earnedExp = 12;
        }
        Functions.slowPrint("You are fighting a " + name + ". Enemy Health is " + enemyHealth + ". Enemy Armor is " + enemyArmor + ". Enemy Damage is " + enemyDamage + ". Enemy Damage Modifier is " + enemyDamageMod + ". Enemy Critical Chance is " + enemyCriticalChance +
                ". Enemy spawn region is " + region + ". Enemy Experience Value is " + earnedExp + ".");
    }

}
