public class Room {
    //This class will store the values for each room that the player can progress through.
        //I need to track what rooms are accessible, so they can be listed for the player.
        //Don't forget a description. No items for now :)

    //Variables
    Integer currentRoom; //Integer identifier for each room
    Integer[] adjacentRooms; //Array that stores the integer value of up to 4 rooms that it has access to. Zeroes = no room that direction. [North, East, South, West]
    String description;

    //This method is simple, but handles all of our room indexing. We store each adjacent room id in an integer array. I think this is probably a pretty primitive method of handling this, but its what I was able to come up with for now.
        //Array index 0 = north
        //array index 1 = east
        //array index 2 = south
        //array index 3 = west
        //additional indexes added later could support alternative prompts like "follow path", "enter trap door", "climb ladder", etc.
    public void getRoom(int currentRoom) {
        if (currentRoom == 1) { //Entry room
            description = "This room has an old man standing in the corner. He smells of sour clothes and has crumbs in his beard...best not stay here too long." + "\n" +
                "To the North a large black door awaits. To the south a stairwell leads into the darkness.";
            adjacentRooms = new Integer[]{2, 0, 3, 0};
        } else if (currentRoom == 2) {  //Grand Dining Hall
            description = "You enter a grand dining hall, where the floor, walls, and ceiling are decorated in gold leaf. \n" +
                    "A massive dining table that could easily seat 30 people is laden with steaming foods beyond your imagination.\n" +
                    "Three small children are seated at the table, devouring the food before them. You try to get their attention, but they pay you no mind.\n" +
                    "Along the East wall, a door sits ajar. Through the crack you can see a forest, though it doesn't seem like the door should be going outside...\n" +
                    "To the South, is a Large Black Door.\n" +
                    "To the West, is a small white interior door.";
            adjacentRooms = new Integer[]{0,4,1,5};
        } else if (currentRoom == 3) {  //Dark Basement
            description = "The stairway leads down, deep into the Earth. Following what feels like an hour in complete darkness, your feet finally find flat ground.\n" +
                    "You reach out finding no purchase for your hands. After searching for a long time, you hear a muffled cough nearby.\n" +
                    "Calling out, a voice responds telling you to walk backwards until you find the wall to the North behind you. From there you can find the stairway you came down.\n" +
                    "The voice sounds familiar...\n" +
                    "The only exit you can find to the room, is the stairway to your North.";
            adjacentRooms = new Integer[]{1,0,0,0};
        } else if (currentRoom == 4) {  //Bamboo Forest
            description = "You step into what you now realize is a massive bamboo forest. Incredibly, it exists within a giant room where the ceiling is almost unable to be seen.\n" +
                    "A breeze blows across your face, as the bamboo shoots lightly bounce against each other making a calming melody. A sense of peace and relaxation flows through you.\n" +
                    "You search the forest, finding a small hut in the deep recess of the 'room'. Smoke looks to be rising from the chimney. When you knock on the door, something stirs inside, but you get no answer.\n" +
                    "Beyond the hut, is a small trail leading further North...\n" +
                    "Behind you, you recall that the door back to the Dining Hall resides to the West.";
            adjacentRooms = new Integer[]{6,0,0,2};
        } else if (currentRoom == 5) {  //Suspiciously clean bathroom
            description = "You find yourself in a exceptionally clean bathroom. It's so clean, you probably shouldn't use it.\n" +
                    "To your East, is the bathroom door you entered through.";
            adjacentRooms = new Integer[]{0,2,0,0};
        } else if (currentRoom == 6) { //Bamboo Forest - Interior Canyon
            description = "The trail winds through the bamboo as the ceiling far above your head grows dark, seeming to disappear and morph into a night sky.\n" +
                    "Eventually, you find yourself on the precipice of a canyon. When did you leave the house?\n" +
                    "Your foot brushes a small rock that topples over the edge and far down into a river below. It must be at least 200 feet to the surface of the water and it looks to be moving quickly.\n" +
                    "A worn rope bridge stretches out, spanning the gap over the canyon. You touch the rope where it knots around wooden posts in the ground, finding it coarse and dirt stained. It's clearly quite old.\n" +
                    "The trail splits here before going in different directions.\n" +
                    "To the North, ahead of you, likes the rope bridge. Awaiting the next soul brave enough to traverse it.\n" +
                    "To the East, a small trail follows the canyon edge. From what you can tell, the rivers source lies to the East as well, though it's too dark at this point to be sure.\n" +
                    "To the South, the trail delves back into the Bamboo Forest.";
            adjacentRooms = new Integer[]{7,8,4,0};
        } else if (currentRoom == 7) { //Not Rendered Sign - Turn Back
            description = "You step onto the bridge and it groans beneath you. Some of the planks are soft with rot, likely from existing over a river to many many years.\n" +
                    "The further you progress, the more you realize how precarious the structure you are on is. Nearing the other side, one of the planks snaps under your weight.\n" +
                    "Luckily, you were holding onto the rope and, while slightly stressed from the moment, you make it across without any serious issues.\n" +
                    "Setting your feet back onto solid ground, you lookup and see a sign facing you. It states:\n" +
                    "    'The area beyond this point has not been rendered, please turn back. Or Else!'\n" +
                    "    '-Abernathy'\n" +
                    "    'P.S. say 'exit' to leave this house and continue your testing!'" +
                    "You probably shouldn't go any further.\n" +
                    "To you South, a rope bridge spans the gap across the canyon.";
            adjacentRooms = new Integer[]{0,0,6,0};
        } else if (currentRoom == 8) { //Canyon River
            description = "The trail leads east along the canyon edge, eventually beginning to slope down. It separates from the edge of the canyon as it dives down toward the floor below.\n" +
                    "The ground is uneven and jagged, requiring you to watch each of your footsteps. This is considerably more difficult as the light wanes to near complete darkness the further down you descend.\n" +
                    "Eventually, the trail levels out, the temperature drops significantly, and a chilling mist begins to wet your skin as the roar of a rapid river starts to drown out all other sound.\n" +
                    "You can only see a handful of meters ahead of you, but you can see the waves crashing against the side of the canyon you stand upon and the puddles filling the low points of the trail you now traverse.\n" +
                    "Following the river-level pathway, you come to a point where the path stops. It comes abruptly to a wall of stone reaching back up to the top of the canyon and appears to have no way to continue further.\n" +
                    "You investigate the stone making up the cliff face, feeling as if you can't have come all this way for nothing.\n" +
                    "You are rewarded, as your fingers find a purchase within a small cavity in the rock. Something moves as you pull on it...\n" +
                    "A low scraping tone, from deep within the rock reverberates out as you feel it through your fingertips.\n" +
                    "Suddenly, the face of the rock shudders and slides into itself, away from the river. A moment later, dull light floods out of a doorway that feels like something a dwarven city would have created.\n" +
                    "To the south, a secret passageway awaits.\n" +
                    "To the west, the trail climbs back up to the top of the canyon.";
            adjacentRooms = new Integer[]{0,0,9,6};
        } else if (currentRoom == 9) { //Dwarven Hallway
            description = "You follow the stone hallway, as it twists and turns. Small gemstones are crafted into the walls periodically that give off a warm glow, ensuring that the trip is well illuminated.\n" +
                    "The craftsmanship of the hallway is exquisite, the floor is perfectly smooth, the walls have miniscule divets carved carefully that cause the light to bounce in a mesmirizing manner.\n" +
                    "It's clear that whoever made this secret passage was a true master, though they have likely been dead a millenia with the lack of dwarves in the area...\n" +
                    "Eventually, you find yourself at another dead-end. Though this time, a stone button awaits you on the wall.\n" +
                    "You press it cautiously and, again, hear the same low scraping tone from within the earth. The wall ahead of you slides into the wall and reveals a pitch black room ahead of you.\n" +
                    "The light spills out a short distance, revealing another stone floor. You see nothing else in the room, clearly it's a large space.\n" +
                    "To your east, the hallway awaits from the direction you came.\n" +
                    "To your west, the dark room sits, expectantly.";
            adjacentRooms = new Integer[]{0,8,0,3};
        }
    }
}
