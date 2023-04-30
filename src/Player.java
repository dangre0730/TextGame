public class Player {


//Stat declarations and initializations.
    //Identification
    public String playerName;

    //Level and XP
    public int playerLevel = 1;
    public int playerExp = 0;

    //Class details
    enum classChoice{
        SOLDIER,
        RANGER,
        BRAWLER,
        MAGE
    }
    classChoice pcClass;

    int hitDie;

    //Attack Stats
    int weaponChoice;
    int attackDamage = 4;
    double critChance = 10.0;
    double attackSpeed = 1.6;

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
                hitDie = 12;
                playerStrength = Functions.statDiceRoll(6, 4, 2);
                playerDexterity = Functions.statDiceRoll(6, 4, 0);
                playerConstitution = Functions.statDiceRoll(6, 4, 2);
                playerIntelligence = Functions.statDiceRoll(6, 4, 0);
                playerWisdom = Functions.statDiceRoll(6, 4, 1);
                playerCharisma = Functions.statDiceRoll(6, 4, 0);
                break;
            case RANGER:
                hitDie = 8;
                playerStrength = Functions.statDiceRoll(6, 4, 0);
                playerDexterity = Functions.statDiceRoll(6, 4, 2);
                playerConstitution = Functions.statDiceRoll(6, 4, 0);
                playerIntelligence = Functions.statDiceRoll(6, 4, 2);
                playerWisdom = Functions.statDiceRoll(6, 4, 1);
                playerCharisma = Functions.statDiceRoll(6, 4, 0);
                break;
            case BRAWLER:
                hitDie = 10;
                playerStrength = Functions.statDiceRoll(6, 4, 2);
                playerDexterity = Functions.statDiceRoll(6, 4, 0);
                playerConstitution = Functions.statDiceRoll(6, 4, 1);
                playerIntelligence = Functions.statDiceRoll(6, 4, 0);
                playerWisdom = Functions.statDiceRoll(6, 4, 1);
                playerCharisma = Functions.statDiceRoll(6, 4, 1);
                break;
            case MAGE:
                hitDie = 6;
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
        maxPlayerHealth = Game.healthDiceRoll(hitDie, 4, conMod);
        playerHealth = maxPlayerHealth;


    }
}
