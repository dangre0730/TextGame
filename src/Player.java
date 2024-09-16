public class Player {


//Stat declarations and initializations.
    //Identification
    String name;

    //Level and XP
    int level = 1;
    int experience = 0;
    int nextLevel = 20;

    //Class details
    enum classChoice{
        SOLDIER,
        RANGER,
        BRAWLER,
        MAGE
    }
    classChoice pcClass;
    int classArmor;
    int hitDie;

    //Attack Stats
    enum weaponChoice{
        SWORD,
        CROSSBOW,
        BAT,
        WAND
    }
    weaponChoice playerWeapon;
    int attackDamage;
    double criticalChance;
    double attackSpeed;
    int damageMod;

    //Stats and Modifiers
    int maxPlayerHealth;
    int playerHealth;
    int playerStrength; // = 12;
    int strMod; // = 1;
    int playerDexterity;
    int dexMod;
    int playerConstitution;
    int conMod;
    int playerIntelligence;
    int intMod;
    int playerWisdom;
    int wisMod;
    int playerCharisma;
    int chaMod;

    public void classBuilder(){

        //Build Character Stats
        switch (pcClass){
            case SOLDIER:
                classArmor = 15;
                hitDie = 12;
                maxPlayerHealth = hitDie;
                System.out.println("Your attack modifier is Strength!");
                playerStrength = Functions.statDiceRoll(6, 4, 2);
                playerDexterity = Functions.statDiceRoll(6, 4, 0);
                playerConstitution = Functions.statDiceRoll(6, 4, 2);
                playerIntelligence = Functions.statDiceRoll(6, 4, 0);
                playerWisdom = Functions.statDiceRoll(6, 4, 1);
                playerCharisma = Functions.statDiceRoll(6, 4, 0);
                break;
            case RANGER:
                classArmor = 12;
                hitDie = 8;
                maxPlayerHealth = hitDie;
                System.out.println("Your attack modifier is Dexterity!");
                playerStrength = Functions.statDiceRoll(6, 4, 0);
                playerDexterity = Functions.statDiceRoll(6, 4, 2);
                playerConstitution = Functions.statDiceRoll(6, 4, 0);
                playerIntelligence = Functions.statDiceRoll(6, 4, 2);
                playerWisdom = Functions.statDiceRoll(6, 4, 1);
                playerCharisma = Functions.statDiceRoll(6, 4, 0);
                break;
            case BRAWLER:
                classArmor = 14;
                hitDie = 10;
                maxPlayerHealth = hitDie;
                System.out.println("Your attack modifier is Strength!");
                playerStrength = Functions.statDiceRoll(6, 4, 2);
                playerDexterity = Functions.statDiceRoll(6, 4, 0);
                playerConstitution = Functions.statDiceRoll(6, 4, 1);
                playerIntelligence = Functions.statDiceRoll(6, 4, 0);
                playerWisdom = Functions.statDiceRoll(6, 4, 1);
                playerCharisma = Functions.statDiceRoll(6, 4, 1);
                break;
            case MAGE:
                classArmor = 10;
                hitDie = 6;
                maxPlayerHealth = hitDie;
                System.out.println("Your attack modifier is Intelligence!");
                playerStrength = Functions.statDiceRoll(6, 4, 0);
                playerDexterity = Functions.statDiceRoll(6, 4, 1);
                playerConstitution = Functions.statDiceRoll(6, 4, 0);
                playerIntelligence = Functions.statDiceRoll(6, 4, 2);
                playerWisdom = Functions.statDiceRoll(6, 4, 2);
                playerCharisma = Functions.statDiceRoll(6, 4, 0);
                break;
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
        maxPlayerHealth = Functions.healthDiceRoll(maxPlayerHealth, hitDie, 3, conMod);
        System.out.println("Starting health is " + maxPlayerHealth);
//        System.out.println(maxPlayerHealth);
        playerHealth = maxPlayerHealth;
//        System.out.println(playerHealth);
        switch (pcClass){
            case SOLDIER, BRAWLER -> damageMod = strMod;
            case RANGER -> damageMod = dexMod;
            case MAGE -> damageMod = intMod;
        }

    }

    public void setWeaponChoice() {
        switch(playerWeapon){
            case SWORD:
                attackDamage = 3;
                attackSpeed = 1.6;
                criticalChance = 15.0;
                break;
            case CROSSBOW:
                attackDamage = 5;
                attackSpeed = 1.0;
                criticalChance = 19.5;
                break;
            case BAT:
                attackDamage = 3;
                attackSpeed = 1.8;
                criticalChance = 11.0;
                break;
            case WAND:
                attackDamage = 4;
                attackSpeed = 2.0;
                criticalChance = 0.0;
                break;
        }
    }

    public void statPrinter(){
        Functions.slowPrint("Ok, you are a " + pcClass + " and as one, your stats have been randomly generated as: ");
        Functions.slowPrint("Health: " + playerHealth + " | Max Health: " + maxPlayerHealth + " | Hit Die: " + hitDie);
        Functions.slowPrint("Strength: " + playerStrength + " |  Str Mod: " + strMod);
        Functions.slowPrint("Dexterity: " + playerDexterity + " | Dex Mod: " + dexMod);
        Functions.slowPrint("Constitution: " + playerConstitution + " | Con Mod: " + conMod);
        Functions.slowPrint("Intelligence: " + playerIntelligence + " | Int Mod: " + intMod);
        Functions.slowPrint("Wisdom: " + playerWisdom + " | Wis Mod: " + wisMod);
        Functions.slowPrint("Charisma: " + playerCharisma + " | Cha Mod: " + chaMod);
        Functions.slowPrint("Player Level: " + level);
    }
}
