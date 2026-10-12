public class Player {

//    public Player(String name, String className, String weapon) {
//        Game.setPlayerName(name);
//        Game.setPlayerClass(className);
//        Game.setPlayerWeapon(weapon);
//
//
//    }

    // ============================================
    //      Player Object & Variable Declarations
    // ============================================

    // region

    // Identification
    String name;

    //Level and XP
    int level = 1;
    int experienceTotal = 0;
    int nextLevel = 20;

    //Class details
    enum ClassType {
        SOLDIER,
        RANGER,
        BRAWLER,
        MAGE
    }
    enum AttackModifier {
        STRENGTH,
        DEXTERITY,
        WISDOM,
        INTELLIGENCE,
        CHARISMA
    }
    ClassType pcClass;
    AttackModifier atkMod;
    String className, weaponName, classMod;
    int classArmor;
    int hitDie;

    //Attack Stats
    enum WeaponType {
        SWORD,
        CROSSBOW,
        BAT,
        WAND
    }
    WeaponType weaponType;
    int attackDamage;
    double criticalChance;
    double attackSpeed;
    int damageMod;
    // Persists between combatLoop calls. Accumulates fractional attacks from attackSpeed; triggers a bonus attack when >= 1.
    double extraAttack;

    //Stats and Modifiers
    int maxHealth;
    int health;
    int strength;
    int strMod;
    int dexterity;
    int dexMod;
    int constitution;
    int conMod;
    int intelligence;
    int intMod;
    int wisdom;
    int wisMod;
    int charisma;
    int chaMod;

    // endregion


    // ============================================
    //      Player Builder Methods
    // ============================================

    // region Count: 2

    /**
     * Rolls character stats based on the selected player class
     */
    public void buildClass(){

        //Build Character Stats
        switch (pcClass) {
            case SOLDIER -> {
                className = "Soldier";
                classMod = "STRENGTH";
                atkMod = AttackModifier.STRENGTH;
                classArmor = 15;
                hitDie = 12;
                maxHealth = hitDie;
                strength = Functions.statDiceRoll(6, 4, 2);
                dexterity = Functions.statDiceRoll(6, 4, 0);
                constitution = Functions.statDiceRoll(6, 4, 2);
                intelligence = Functions.statDiceRoll(6, 4, 0);
                wisdom = Functions.statDiceRoll(6, 4, 1);
                charisma = Functions.statDiceRoll(6, 4, 0);
            }
            case RANGER -> {
                className = "Ranger";
                classMod = "DEXTERITY";
                atkMod = AttackModifier.DEXTERITY;
                classArmor = 12;
                hitDie = 8;
                maxHealth = hitDie;
                strength = Functions.statDiceRoll(6, 4, 0);
                dexterity = Functions.statDiceRoll(6, 4, 2);
                constitution = Functions.statDiceRoll(6, 4, 0);
                intelligence = Functions.statDiceRoll(6, 4, 2);
                wisdom = Functions.statDiceRoll(6, 4, 1);
                charisma = Functions.statDiceRoll(6, 4, 0);
            }
            case BRAWLER -> {
                className = "Brawler";
                classMod = "STRENGTH";
                atkMod = AttackModifier.STRENGTH;
                classArmor = 14;
                hitDie = 10;
                maxHealth = hitDie;
                strength = Functions.statDiceRoll(6, 4, 2);
                dexterity = Functions.statDiceRoll(6, 4, 0);
                constitution = Functions.statDiceRoll(6, 4, 1);
                intelligence = Functions.statDiceRoll(6, 4, 0);
                wisdom = Functions.statDiceRoll(6, 4, 1);
                charisma = Functions.statDiceRoll(6, 4, 1);
            }
            case MAGE -> {
                className = "Mage";
                classMod = "INTELLIGENCE";
                atkMod = AttackModifier.INTELLIGENCE;
                classArmor = 10;
                hitDie = 6;
                maxHealth = hitDie;
                strength = Functions.statDiceRoll(6, 4, 0);
                dexterity = Functions.statDiceRoll(6, 4, 1);
                constitution = Functions.statDiceRoll(6, 4, 0);
                intelligence = Functions.statDiceRoll(6, 4, 2);
                wisdom = Functions.statDiceRoll(6, 4, 2);
                charisma = Functions.statDiceRoll(6, 4, 0);
            }
        }

        System.out.println("Congratulations! You've selected the " + className + " class type!");
        System.out.println("Your attack modifier is " + classMod + "!");

        /*
            Minimum stat of 8 enforced to prevent extreme negative modifiers from breaking combat math.

                REVIEW ROUNDING BEHAVIOR HERE:
                    ANYTHING BELOW 10 FOR A STAT ROUNDS 'DOWN TOWARD NEGATIVE INFINITY' USING THE FLOOR METHOD.
                    playerStrength of 9 = -1 modifier
                    playerStrength of 8 = -1 modifier
                    playerStrength of 7 = -2 modifier
                    ect.
                    ect
         */
        if(strength < 8){
            strength = 8;
        }
        strMod = (int) (Math.floor((double) (strength - 10) / 2));
        if(dexterity < 8){
            dexterity = 8;
        }
        dexMod = (int) (Math.floor((double) (dexterity - 10) / 2));
        if(constitution < 8){
            constitution = 8;
        }
        conMod = (int) (Math.floor((double) (constitution - 10) / 2));
        if(intelligence < 8){
            intelligence = 8;
        }
        intMod = (int) (Math.floor((double) (intelligence - 10) / 2));
        if(wisdom < 8){
            wisdom = 8;
        }
        wisMod = (int) (Math.floor((double) (wisdom - 10) / 2));
        if(charisma < 8){
            charisma = 8;
        }
        chaMod = (int) (Math.floor((double) (charisma - 10) / 2));
        maxHealth = Functions.healthDiceRoll(maxHealth, hitDie, 3, conMod);
        System.out.println("Starting health is " + maxHealth);

        health = maxHealth;

        switch (pcClass){
            case SOLDIER, BRAWLER -> damageMod = strMod;
            case RANGER -> damageMod = dexMod;
            case MAGE -> damageMod = intMod;
        }

    }

    /**
     * Populates weapon stats based on the selected player weapon
     */
    public void loadWeaponStats() {
        switch(weaponType){
            case SWORD:
                weaponName = "SWORD";
                attackDamage = 3;
                attackSpeed = 1.6;
                criticalChance = 15.0;
                break;
            case CROSSBOW:
                weaponName = "CROSSBOW";
                attackDamage = 5;
                attackSpeed = .75;   // reduced 1 -> .75, item was too strong
                criticalChance = 19.5;
                break;
            case BAT:
                weaponName = "BAT";
                attackDamage = 3;
                attackSpeed = 1.8;
                criticalChance = 11.0;
                break;
            case WAND:
                weaponName = "WAND";
                attackDamage = 4;
                attackSpeed = 2.0;
                criticalChance = 0.0;
                break;
        }
    }

    // endregion


    // ============================================
    //      Player Updater Methods
    // ============================================

    // region Count: 0



    // endregion


    // ============================================
    //      Event Trigger & Take Action Methods
    // ============================================

    // region Count: 1

    /**
     * Prints player stats to the console for quick troubleshooting.
     *
     * Will deprecate in the future.
     */
    public void printStats(){
        Functions.slowPrint("Ok, you are a " + pcClass + " and as one, your stats have been randomly generated as: ");
        Functions.slowPrint("Health: " + health + " | Max Health: " + maxHealth + " | Hit Die: " + hitDie);
        Functions.slowPrint("Strength: " + strength + " |  Str Mod: " + strMod);
        Functions.slowPrint("Dexterity: " + dexterity + " | Dex Mod: " + dexMod);
        Functions.slowPrint("Constitution: " + constitution + " | Con Mod: " + conMod);
        Functions.slowPrint("Intelligence: " + intelligence + " | Int Mod: " + intMod);
        Functions.slowPrint("Wisdom: " + wisdom + " | Wis Mod: " + wisMod);
        Functions.slowPrint("Charisma: " + charisma + " | Cha Mod: " + chaMod);
        Functions.slowPrint("Player Level: " + level);
    }

    // endregion


    // ============================================
    //      Deprecated Methods
    // ============================================

    // region Count: 0



    // endregion


}
