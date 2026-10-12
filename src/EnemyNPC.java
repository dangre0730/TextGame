public class EnemyNPC extends NPC {
//Using this class to teach myself how to create a large list of enemies with their stats.
//  Then call this class in the main method and randomly select an enemy to fight.

    //Declare Variables
    //Integers
    int enemySelection;
    int health;
    int armor;
    int damage;
    int damageMod;
    int experienceValue;
    // Tracks how many times combatLoop has been called for this enemy. 0 = first call (show intro), 1+ = resolve round.
    int turnsTaken = 0;
    //Doubles
    double criticalChance;
    // Booleans
    // Set to true when enemy HP reaches 0. Switches combatLoop to the post-fight options branch.
    boolean isCombatOver = false;
    //Strings
    String name;
    String region;

    public EnemyNPC(Room.RoomId location, int enemySelection){
        super("", location);

        loadStats(enemySelection);
    }

    public EnemyNPC() {
        super();
    }

    public String toString(){
        return getClass().getName() + "[" + name + "," + location + "," + health + "," + armor + "," + damage + "]";
    }
//    String[] enemyList = {"Training Dummy", "Swarm of Rats"};

    // Populates all combat stats for this enemy. Called automatically by the constructor via enemySelection param.
    public void loadStats(int enemySelection) {
        if(enemySelection == 0) {
            name = "Training Dummy";
            region = "Tutorial";
            health = 50;
            damage = 2;
            damageMod = 0;
            armor = 1;
            criticalChance = 0.0;
            experienceValue = 9;
        } else if (enemySelection == 1) {
            name = "Swarm of Rats";
            region = "Tutorial";
            health = 50;
            damage = 2;
            damageMod = 1;
            armor = 0;
            criticalChance = 2.0;
            experienceValue = 12;
        }
        Functions.slowPrint("You are fighting a " + name + ". Enemy Health is " + health + ". Enemy Armor is " + armor + ". Enemy Damage is " + damage + ". Enemy Damage Modifier is " + damageMod + ". Enemy Critical Chance is " + criticalChance +
                ". Enemy spawn region is " + region + ". Enemy Experience Value is " + experienceValue + ".");
    }

}
