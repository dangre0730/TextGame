public class Functions {

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

}
