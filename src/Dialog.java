public class Dialog {

    public static void greeting(){
        Functions.slowPrint("Hello, Traveler! I'm Abernathy, what is your name?");
    }
    public static void intro(String playerName){
        Functions.slowPrint("Well then, it's nice to meet you, " + playerName + ". This is my world, coded with these two hands, and I use it to test out cool new features for my game.");
        System.out.println();
        Functions.slowPrint("All of the text you are seeing for now, has been written directly in the main class using standard println statements.");
        Functions.slowPrint("Later on, I want to learn how to implement JSON files to store and read dialog. I think that will be cleaner in the long run, but it's early days right now.");
        System.out.println();
        Functions.slowPrint("I'll talk more about that later, but for now just enjoy this test experience and remember, THIS IS A TEST AND I'M STILL LEARNING! :)");
        System.out.println();
    }
}
