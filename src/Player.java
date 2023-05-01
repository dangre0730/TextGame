public class Player {


//Stat declarations and initializations.
    //Identification
    String playerName;

    //Level and XP
    int playerLevel = 1;
    int playerExp = 0;

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
    double critChance;
    double attackSpeed;
    int damageMod;

    //Stats and Modifiers
    int maxPlayerHealth;
    int playerHealth;
    int playerStrength; // = 12;
    int strMod; // = 1;
    int playerDexterity = 14;
    int dexMod = 2;
    int playerConstitution = 12;
    int conMod = 1;
    int playerIntelligence = 8;
    int intMod = -1;
    int playerWisdom = 10;
    int wisMod = 0;
    int playerCharisma = 10;
    int chaMod = 0;

    public void classBuilder(){
        //Build Character Stats
        switch (pcClass){
            case SOLDIER:
                classArmor = 15;
                hitDie = 12;
                maxPlayerHealth = hitDie;
//                System.out.println(maxPlayerHealth);
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
//                System.out.println(maxPlayerHealth);
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
//                System.out.println(maxPlayerHealth);
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
        System.out.println("Starting health is " + maxPlayerHealth);
        maxPlayerHealth = Functions.healthDiceRoll(maxPlayerHealth, hitDie, 3, conMod);
//        System.out.println(maxPlayerHealth);
        playerHealth = maxPlayerHealth;
//        System.out.println(playerHealth);

    }

    public void setWeaponChoice() {
        switch(playerWeapon){
            case SWORD:
                attackDamage = 3;
                attackSpeed = 1.6;
                critChance = 15.0;
                break;
            case CROSSBOW:
                attackDamage = 5;
                attackSpeed = 1.0;
                critChance = 19.5;
                break;
            case BAT:
                attackDamage = 3;
                attackSpeed = 1.8;
                critChance = 11.0;
                break;
            case WAND:
                attackDamage = 4;
                attackSpeed = 2.0;
                critChance = 0.0;
                break;
        }
    }

    public void statPrinter(){
        System.out.println(playerHealth + " playerHealth.");
        System.out.println(maxPlayerHealth + " maxPlayerHealth.");
        System.out.println(pcClass + " pcClass.");
        System.out.println(playerLevel + " playerLevel.");
        System.out.println(playerExp + " playerExp.");
        System.out.println(playerStrength + " playerStrength.");
        System.out.println(playerDexterity + " playerDexterity.");
        System.out.println(playerConstitution + " playerConstitution.");
        System.out.println(playerWisdom + " playerWisdom.");
        System.out.println(playerCharisma + " playerCharisma.");
        System.out.println(playerName + " playerName.");
    }
}
