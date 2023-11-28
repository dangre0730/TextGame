public class Room {
    //This class will store the values for each room that the player can progress through.
        //I need to track what rooms are accessible, so they can be listed for the player.
        //Don't forget a description. No items for now :)

    //Variables
    Integer currentRoom; //Integer identifier for each room
    Integer[] adjacentRooms; //Array that stores the integer value of up to 4 rooms that it has access to. Zeroes = no room that direction. [North, East, South, West]
    String description;

    public void getRoom(int currentRoom) {
        if (currentRoom == 1) {
            description = "This room has an old man standing in the corner. He smells of sour clothes and has crumbs in his beard...best not stay here too long." + "\n" +
                "To the North a large black door awaits. To the south a stairwell leads into the darkness.";
            adjacentRooms = new Integer[]{2, 0, 3, 0};
        } else if (currentRoom == 2) {
            description = "You enter a grand dining hall, where the floor, walls, and ceiling are decorated in gold leaf. \n" +
                    "A massive dining table that could easily seat 30 people is laden with steaming foods beyond your imagination.\n" +
                    "Three small children are seated at the table, devouring the food before them. You try to get their attention, but they pay you no mind.\n" +
                    "Along the East wall, a door sits ajar. Through the crack you can see a forest, though it doesn't seem like the door should be going outside...\n" +
                    "To the South, is a Large Black Door.\n" +
                    "To the West, is a small white interior door.";
            adjacentRooms = new Integer[]{0,4,1,5};
        } else if (currentRoom == 3) {
            description = "The stairway leads down, deep into the Earth. Following what feels like an hour in complete darkness, your feet finally find flat ground.\n" +
                    "You reach out finding no purchase for your hands. After searching for a long time, you hear a muffled cough nearby.\n" +
                    "Calling out, a voice responds telling you to walk backwards until you find the wall to the North behind you. From there you can find the stairway you came down.\n" +
                    "The voice sounds familiar...\n" +
                    "The only exit you can find to the room, is the stairway to your North.";
            adjacentRooms = new Integer[]{1,0,0,0};
        } else if (currentRoom == 4) {
            description = "You step into what you now realize is a massive bamboo forest. Incredibly, it exists within a giant room where the ceiling is almost unable to be seen.\n" +
                    "A breeze blows across your face, as the bamboo shoots lightly bounce against each other making a calming melody. A sense of peace and relaxation flows through you.\n" +
                    "You search the forest, eventually realizing that the only door in or out is to the West.";
            adjacentRooms = new Integer[]{0,0,0,2};
        } else if (currentRoom == 5) {
            description = "You find yourself in a exceptionally clean bathroom. It's so clean, you probably shouldn't use it.\n" +
                    "To your East, is the bathroom door you entered through.";
            adjacentRooms = new Integer[]{0,2,0,0};
        }
    }
}
