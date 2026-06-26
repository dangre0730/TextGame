public class Room {
    // This class will store the values for each room that the player can progress through.
        // I need to track what rooms are accessible, so they can be listed for the player.
        // Don't forget a description. No items for now :)

    // Variables
//    Integer currentRoom; //Integer identifier for each room
//    Integer[] adjacentRooms; //Array that stores the integer value of up to 4 rooms that it has access to. Zeroes = no room that direction. [North, East, South, West]
    String[] description;

    // Create enum so that the dungeon room controller can be another state-machine
    public enum RoomObjects{
        TUTORIAL,
        ENTRY_ROOM_BASE,
        ENTRY_ROOM_REVISIT,
        GRAND_DINING_HALL_BASE,
        GRAND_DINING_HALL_REVISIT,
        DARK_BASEMENT_BASE,
        DARK_BASEMENT_REVISIT,
        BAMBOO_FOREST_START_BASE,
        BAMBOO_FOREST_START_REVISIT,
        BAMBOO_FOREST_CANYON_BRIDGE_BASE,
        BAMBOO_FOREST_CANYON_BRIDGE_REVISIT,
        BAMBOO_FOREST_TURNBACK_BASE,
        BAMBOO_FOREST_TURNBACK_REVISIT,
        BAMBOO_FOREST_CANYON_RIVER_BASE,
        BAMBOO_FOREST_CANYON_RIVER_REVISIT,
        SECRET_DWARVEN_HALLWAY_BASE,
        SECRET_DWARVEN_HALLWAY_REVISIT,
        SUSPICIOUSLY_CLEAN_BATHROOM_BASE,
        SUSPICIOUSLY_CLEAN_BATHROOM_REVISIT,
        WALL
    }

    // Create an object for current and previous rooms. This enables teleport back to previous room if necessary.
    RoomObjects currentRoomObject, lastRoomObject, tempRoomObject;

    // RoomObject Array for storing the current room's linked locations.
    RoomObjects[] nextRooms;

    // Revisit flags for displaying alternate room descriptions
    boolean revisitEntryRoomBase = false;
    boolean revisitGrandDiningHall = false;
    boolean revisitDarkBasement = false;
    boolean revisitBambooForestStart = false;
    boolean revisitBambooForestCanyonBridge = false;
    boolean revisitBambooForestTurnback = false;
    boolean revisitBambooForestCanyonRiver = false;
    boolean revisitSecretDwarvenHallway = false;
    boolean revisitSuspiciouslyCleanBathroom = false;

    // This method is simple, but handles all of our room indexing. Calling this method in the Main class fully initializes the variables declared above.
    // We store each adjacent room id in an integer array. I think this is probably a pretty primitive method of handling this, but it's what I was able to come up with for now.
        //Array index 0 = north
        //array index 1 = east
        //array index 2 = south
        //array index 3 = west
        //additional indexes added later could support alternative prompts like "follow path", "enter trap door", "climb ladder", etc.
    public void getRoom(RoomObjects roomObjects) {

        switch (roomObjects) {
            case RoomObjects.TUTORIAL -> System.out.println("Tutorial");
            // [N: GDH, E: Wall, S: DarkBasement, W: Wall]
            case RoomObjects.ENTRY_ROOM_BASE -> {
                if (revisitEntryRoomBase) {
                    getRoom(RoomObjects.ENTRY_ROOM_REVISIT);
                } else {
                    description = new String[]{
                            "---ENTRY ROOM---",
                            "This room has an old man standing in the corner. He smells of sour clothes and has crumbs in his beard...best not stay here too long.",
                            "To the North a large black door awaits. To the south a stairwell leads into the darkness."
                    };
                    nextRooms = new RoomObjects[]{RoomObjects.GRAND_DINING_HALL_BASE, RoomObjects.WALL, RoomObjects.DARK_BASEMENT_BASE, RoomObjects.WALL};
                    revisitEntryRoomBase = true;
                }
            }
            case RoomObjects.ENTRY_ROOM_REVISIT -> {
                description = new String[]{
                        "---ENTRY ROOM | Revisited---",
                        "You return to the room where you originally appeared.",
                        "The old man who had stood in the corner is missing, though his smell lingers...",
                        "",
                        "*muffled cough nearby*",
                        "",
                        "To the North a large black door awaits. To the south a stairwell leads into the darkness."
                };
                nextRooms = new RoomObjects[]{RoomObjects.GRAND_DINING_HALL_BASE, RoomObjects.WALL, RoomObjects.DARK_BASEMENT_BASE, RoomObjects.WALL};
            }
            // [N: Wall, E: BF_S, S: Entry, W: Bathroom]
            case RoomObjects.GRAND_DINING_HALL_BASE -> {
                if (revisitGrandDiningHall){
                    getRoom(RoomObjects.GRAND_DINING_HALL_REVISIT);
                } else {
                    description = new String[]{
                            "---GRAND DINING HALL---",
                            "You enter a grand dining hall, where the floor, walls, and ceiling are decorated in gold leaf.\s",
                            "A massive dining table that could easily seat 30 people is laden with steaming foods beyond your imagination.",
                            "Three small children are seated at the table, devouring the food before them. You try to get their attention, but they pay you no mind.",
                            "Along the East wall, a door sits ajar. Through the crack you can see a forest, though it doesn't seem like the door should be going outside...",
                            "To the South, is a Large Black Door.",
                            "To the West, is a small white interior door."
                    };
                    nextRooms = new RoomObjects[]{RoomObjects.WALL, RoomObjects.BAMBOO_FOREST_START_BASE, RoomObjects.ENTRY_ROOM_BASE, RoomObjects.SUSPICIOUSLY_CLEAN_BATHROOM_BASE};
                    revisitGrandDiningHall = true;
                }
            }
            case RoomObjects.GRAND_DINING_HALL_REVISIT -> {
                // REWRITE MEEEEEEEEE
                description = new String[]{
                        "---GRAND DINING HALL | Revisited---",
                        "You enter a grand dining hall, where the floor, walls, and ceiling are decorated in gold leaf.\s",
                        "A massive dining table that could easily seat 30 people is laden with steaming foods beyond your imagination.",
                        "Three small children are seated at the table, devouring the food before them. You try to get their attention, but they pay you no mind.",
                        "Along the East wall, a door sits ajar. Through the crack you can see a forest, though it doesn't seem like the door should be going outside...",
                        "To the South, is a Large Black Door.",
                        "To the West, is a small white interior door."
                };
                nextRooms = new RoomObjects[]{RoomObjects.WALL, RoomObjects.BAMBOO_FOREST_START_BASE, RoomObjects.ENTRY_ROOM_BASE, RoomObjects.SUSPICIOUSLY_CLEAN_BATHROOM_BASE};
            }
            // [N: Entry, E: Wall, S: Wall, W: Wall]
            case RoomObjects.DARK_BASEMENT_BASE -> {
                if (revisitDarkBasement){
                    getRoom(RoomObjects.DARK_BASEMENT_REVISIT);
                } else {
                    description = new String[]{
                            "---DARK BASEMENT---",
                            "The stairway leads down, deep into the Earth. Following what feels like an hour in complete darkness, your feet finally find flat ground.",
                            "You reach out finding no purchase for your hands. After searching for a long time, you hear a muffled cough nearby.",
                            "Calling out, a voice responds telling you to walk backwards until you find the wall to the North behind you. From there you can find the stairway you came down.",
                            "The voice sounds familiar...",
                            "The only exit you can find to the room, is the stairway to your North."
                    };
                    nextRooms = new RoomObjects[]{RoomObjects.ENTRY_ROOM_BASE, RoomObjects.WALL, RoomObjects.WALL, RoomObjects.WALL};
                    revisitDarkBasement = true;
                }
            }
            case RoomObjects.DARK_BASEMENT_REVISIT -> {
                // REWRITE MEEEEEEEEE
                description = new String[]{
                        "---DARK BASEMENT | Revisited---",
                        "The stairway leads down, deep into the Earth. Following what feels like an hour in complete darkness, your feet finally find flat ground.",
                        "You reach out finding no purchase for your hands. After searching for a long time, you hear a muffled cough nearby.",
                        "Calling out, a voice responds telling you to walk backwards until you find the wall to the North behind you. From there you can find the stairway you came down.",
                        "The voice sounds familiar...",
                        "The only exit you can find to the room, is the stairway to your North."
                };
                nextRooms = new RoomObjects[]{RoomObjects.ENTRY_ROOM_BASE, RoomObjects.WALL, RoomObjects.WALL, RoomObjects.WALL};
            }
            // [N: BF_CB, E: Wall, S: Wall, W: GDH]
            case RoomObjects.BAMBOO_FOREST_START_BASE -> {
                if (revisitBambooForestStart){
                    getRoom(RoomObjects.BAMBOO_FOREST_START_REVISIT);
                } else {
                    description = new String[]{
                            "---BAMBOO FOREST---",
                            "You step into what you now realize is a massive bamboo forest. Incredibly, it exists within a giant room where the ceiling is almost imperceptible.",
                            "A breeze blows across your face, as the bamboo shoots lightly bounce against each other making a calming melody. A sense of peace and relaxation flows through you.",
                            "You search the forest, finding a small hut in the deep recess of the 'room'. Smoke looks to be rising from the chimney. When you knock on the door, something stirs inside, but you get no answer.",
                            "Beyond the hut, is a small trail leading further North...",
                            "Behind you, you recall that the door back to the Dining Hall resides to the West."
                    };
                    nextRooms = new RoomObjects[]{RoomObjects.BAMBOO_FOREST_CANYON_BRIDGE_BASE, RoomObjects.WALL, RoomObjects.WALL, RoomObjects.GRAND_DINING_HALL_BASE};
                    revisitBambooForestStart = true;
                }
            }
            case RoomObjects.BAMBOO_FOREST_START_REVISIT -> {
                // REWRITE MEEEEEEEEE
                description = new String[]{
                        "---BAMBOO FOREST | Revisited---",
                        "You step into what you now realize is a massive bamboo forest. Incredibly, it exists within a giant room where the ceiling is almost imperceptible.",
                        "A breeze blows across your face, as the bamboo shoots lightly bounce against each other making a calming melody. A sense of peace and relaxation flows through you.",
                        "You search the forest, finding a small hut in the deep recess of the 'room'. Smoke looks to be rising from the chimney. When you knock on the door, something stirs inside, but you get no answer.",
                        "Beyond the hut, is a small trail leading further North...",
                        "Behind you, you recall that the door back to the Dining Hall resides to the West."
                };
                nextRooms = new RoomObjects[]{RoomObjects.BAMBOO_FOREST_CANYON_BRIDGE_BASE, RoomObjects.WALL, RoomObjects.WALL, RoomObjects.GRAND_DINING_HALL_BASE};
            }
            // [N: BF_T, E: BF_CR, S: BF_S, W: Wall]
            case RoomObjects.BAMBOO_FOREST_CANYON_BRIDGE_BASE -> {
                if (revisitBambooForestCanyonBridge){
                    getRoom(RoomObjects.BAMBOO_FOREST_CANYON_BRIDGE_REVISIT);
                } else {
                    description = new String[]{
                            "---BAMBOO FOREST - INTERIOR CANYON---",
                            "The trail winds through the bamboo as the ceiling far above your head grows dark, seeming to disappear and morph into a night sky.",
                            "Eventually, you find yourself on the precipice of a canyon. When did you leave the house?",
                            "Your foot brushes a small rock that topples over the edge and far down into a river below. It must be at least 200 feet to the surface of the water and it looks to be moving quickly.",
                            "A worn rope bridge stretches out, spanning the gap over the canyon. You touch the rope where it knots around wooden posts in the ground, finding it coarse and dirt stained. It's clearly quite old.",
                            "The trail splits here before going in different directions.",
                            "To the North, ahead of you, likes the rope bridge. Awaiting the next soul brave enough to traverse it.",
                            "To the East, a small trail follows the canyon edge. From what you can tell, the rivers source lies to the East as well, though it's too dark at this point to be sure.",
                            "To the South, the trail delves back into the Bamboo Forest."
                    };
                    nextRooms = new RoomObjects[]{RoomObjects.BAMBOO_FOREST_TURNBACK_BASE, RoomObjects.BAMBOO_FOREST_CANYON_RIVER_BASE, RoomObjects.BAMBOO_FOREST_START_BASE, RoomObjects.WALL};
                    revisitBambooForestCanyonBridge = true;
                }
            }
            case RoomObjects.BAMBOO_FOREST_CANYON_BRIDGE_REVISIT -> {
                // REWRITE MEEEEEEEEE
                description = new String[]{
                        "---BAMBOO FOREST - INTERIOR CANYON | Revisited---",
                        "The trail winds through the bamboo as the ceiling far above your head grows dark, seeming to disappear and morph into a night sky.",
                        "Eventually, you find yourself on the precipice of a canyon. When did you leave the house?",
                        "Your foot brushes a small rock that topples over the edge and far down into a river below. It must be at least 200 feet to the surface of the water and it looks to be moving quickly.",
                        "A worn rope bridge stretches out, spanning the gap over the canyon. You touch the rope where it knots around wooden posts in the ground, finding it coarse and dirt stained. It's clearly quite old.",
                        "The trail splits here before going in different directions.",
                        "To the North, ahead of you, likes the rope bridge. Awaiting the next soul brave enough to traverse it.",
                        "To the East, a small trail follows the canyon edge. From what you can tell, the rivers source lies to the East as well, though it's too dark at this point to be sure.",
                        "To the South, the trail delves back into the Bamboo Forest."
                };
                nextRooms = new RoomObjects[]{RoomObjects.BAMBOO_FOREST_TURNBACK_BASE, RoomObjects.BAMBOO_FOREST_CANYON_RIVER_BASE, RoomObjects.BAMBOO_FOREST_START_BASE, RoomObjects.WALL};
            }
            // [N: Wall, E: Wall, S: BF_CB, W: Wall]
            case RoomObjects.BAMBOO_FOREST_TURNBACK_BASE -> {
                if (revisitBambooForestTurnback){
                    getRoom(RoomObjects.BAMBOO_FOREST_TURNBACK_REVISIT);
                } else {
                    description = new String[]{
                            "---TURN BACK SIGN---",
                            "You step onto the bridge and it groans beneath you. Some of the planks are soft with rot, likely from existing over a river to many many years.",
                            "The further you progress, the more you realize how precarious the structure you are on is. Nearing the other side, one of the planks snaps under your weight.",
                            "Luckily, you were holding onto the rope and, while slightly stressed from the moment, you make it across without any serious issues.",
                            "Setting your feet back onto solid ground, you lookup and see a sign facing you. It states:",
                            "    'The area beyond this point has not been rendered, please turn back. Or Else!'",
                            "    '-Abernathy'",
                            "    'P.S. say 'exit' to leave this house and continue your testing!'",
                            "You probably shouldn't go any further.",
                            "To you South, a rope bridge spans the gap across the canyon."
                    };
                    nextRooms = new RoomObjects[]{RoomObjects.WALL, RoomObjects.WALL, RoomObjects.BAMBOO_FOREST_CANYON_BRIDGE_BASE, RoomObjects.WALL};
                    revisitBambooForestTurnback = true;
                }
            }
            case RoomObjects.BAMBOO_FOREST_TURNBACK_REVISIT -> {
                // REWRITE MEEEEEEEEE
                description = new String[]{
                        "---TURN BACK SIGN | Revisited---",
                        "You step onto the bridge and it groans beneath you. Some of the planks are soft with rot, likely from existing over a river to many many years.",
                        "The further you progress, the more you realize how precarious the structure you are on is. Nearing the other side, one of the planks snaps under your weight.",
                        "Luckily, you were holding onto the rope and, while slightly stressed from the moment, you make it across without any serious issues.",
                        "Setting your feet back onto solid ground, you lookup and see a sign facing you. It states:",
                        "    'The area beyond this point has not been rendered, please turn back. Or Else!'",
                        "    '-Abernathy'",
                        "    'P.S. say 'exit' to leave this house and continue your testing!'",
                        "You probably shouldn't go any further.",
                        "To you South, a rope bridge spans the gap across the canyon."
                };
                nextRooms = new RoomObjects[]{RoomObjects.WALL, RoomObjects.WALL, RoomObjects.BAMBOO_FOREST_CANYON_BRIDGE_BASE, RoomObjects.WALL};
            }
            // [N: Wall, E: Wall, S: SDH, W: BF_CB]
            case RoomObjects.BAMBOO_FOREST_CANYON_RIVER_BASE -> {
                if (revisitBambooForestCanyonRiver){
                    getRoom(RoomObjects.BAMBOO_FOREST_CANYON_RIVER_REVISIT);
                } else {
                    description = new String[]{
                            "---CANYON RIVER---",
                            "The trail leads east along the canyon edge, eventually beginning to slope down. It separates from the edge of the canyon as it dives down toward the floor below.",
                            "The ground is uneven and jagged, requiring you to watch each of your footsteps. This is considerably more difficult as the light wanes to near complete darkness the further down you descend.",
                            "Eventually, the trail levels out, the temperature drops significantly, and a chilling mist begins to wet your skin as the roar of a rapid river starts to drown out all other sound.",
                            "You can only see a handful of meters ahead of you, but you can see the waves crashing against the side of the canyon you stand upon and the puddles filling the low points of the trail you now traverse.",
                            "Following the river-level pathway, you come to a point where the path stops. It comes abruptly to a wall of stone reaching back up to the top of the canyon and appears to have no way to continue further.",
                            "You investigate the stone making up the cliff face, feeling as if you can't have come all this way for nothing.",
                            "You are rewarded, as your fingers find a purchase within a small cavity in the rock. Something moves as you pull on it...",
                            "A low scraping tone, from deep within the rock reverberates out as you feel it through your fingertips.",
                            "Suddenly, the face of the rock shudders and slides into itself, away from the river. A moment later, dull light floods out of a doorway that feels like something a dwarven city would have created.",
                            "To the south, a secret passageway awaits.",
                            "To the west, the trail climbs back up to the top of the canyon."
                    };
                    nextRooms = new RoomObjects[]{RoomObjects.WALL, RoomObjects.WALL, RoomObjects.SECRET_DWARVEN_HALLWAY_BASE, RoomObjects.BAMBOO_FOREST_CANYON_BRIDGE_BASE};
                    revisitBambooForestCanyonRiver = true;
                }
            }
            case RoomObjects.BAMBOO_FOREST_CANYON_RIVER_REVISIT -> {
                // REWRITE MEEEEEEEEE
                description = new String[]{
                        "---CANYON RIVER | Revisited---",
                        "The trail leads east along the canyon edge, eventually beginning to slope down. It separates from the edge of the canyon as it dives down toward the floor below.",
                        "The ground is uneven and jagged, requiring you to watch each of your footsteps. This is considerably more difficult as the light wanes to near complete darkness the further down you descend.",
                        "Eventually, the trail levels out, the temperature drops significantly, and a chilling mist begins to wet your skin as the roar of a rapid river starts to drown out all other sound.",
                        "You can only see a handful of meters ahead of you, but you can see the waves crashing against the side of the canyon you stand upon and the puddles filling the low points of the trail you now traverse.",
                        "Following the river-level pathway, you come to a point where the path stops. It comes abruptly to a wall of stone reaching back up to the top of the canyon and appears to have no way to continue further.",
                        "You investigate the stone making up the cliff face, feeling as if you can't have come all this way for nothing.",
                        "You are rewarded, as your fingers find a purchase within a small cavity in the rock. Something moves as you pull on it...",
                        "A low scraping tone, from deep within the rock reverberates out as you feel it through your fingertips.",
                        "Suddenly, the face of the rock shudders and slides into itself, away from the river. A moment later, dull light floods out of a doorway that feels like something a dwarven city would have created.",
                        "To the south, a secret passageway awaits.",
                        "To the west, the trail climbs back up to the top of the canyon."
                };
                nextRooms = new RoomObjects[]{RoomObjects.WALL, RoomObjects.WALL, RoomObjects.SECRET_DWARVEN_HALLWAY_BASE, RoomObjects.BAMBOO_FOREST_CANYON_BRIDGE_BASE};
            }
            // [N: BF_CR, E: Wall, S: Wall, W: DarkBasement]
            case RoomObjects.SECRET_DWARVEN_HALLWAY_BASE -> {
                if (revisitSecretDwarvenHallway){
                    getRoom(RoomObjects.SECRET_DWARVEN_HALLWAY_REVISIT);
                } else {
                    description = new String[]{
                            "---DWARVEN HALLWAY---",
                            "You follow the stone hallway, as it twists and turns. Small gemstones are crafted into the walls periodically that give off a warm glow, ensuring that the trip is well illuminated.",
                            "The craftsmanship of the hallway is exquisite, the floor is perfectly smooth, the walls have miniscule divets carved carefully that cause the light to bounce in a mesmirizing manner.",
                            "It's clear that whoever made this secret passage was a true master, though they have likely been dead a millenia with the lack of dwarves in the area...",
                            "Eventually, you find yourself at another dead-end. Though this time, a stone button awaits you on the wall.",
                            "You press it cautiously and, again, hear the same low scraping tone from within the earth. The wall ahead of you slides into the wall and reveals a pitch black room ahead of you.",
                            "The light spills out a short distance, revealing another stone floor. You see nothing else in the room, clearly it's a large space.",
                            "To your east, the hallway awaits from the direction you came.",
                            "To your west, the dark room sits, expectantly."
                    };
                    nextRooms = new RoomObjects[]{RoomObjects.BAMBOO_FOREST_CANYON_RIVER_BASE, RoomObjects.WALL, RoomObjects.WALL, RoomObjects.DARK_BASEMENT_BASE};
                    revisitSecretDwarvenHallway = true;
                }
            }
            case RoomObjects.SECRET_DWARVEN_HALLWAY_REVISIT -> {
                // REWRITE MEEEEEEEEE
                description = new String[]{
                        "---DWARVEN HALLWAY | Revisited---",
                        "You follow the stone hallway, as it twists and turns. Small gemstones are crafted into the walls periodically that give off a warm glow, ensuring that the trip is well illuminated.",
                        "The craftsmanship of the hallway is exquisite, the floor is perfectly smooth, the walls have miniscule divets carved carefully that cause the light to bounce in a mesmirizing manner.",
                        "It's clear that whoever made this secret passage was a true master, though they have likely been dead a millenia with the lack of dwarves in the area...",
                        "Eventually, you find yourself at another dead-end. Though this time, a stone button awaits you on the wall.",
                        "You press it cautiously and, again, hear the same low scraping tone from within the earth. The wall ahead of you slides into the wall and reveals a pitch black room ahead of you.",
                        "The light spills out a short distance, revealing another stone floor. You see nothing else in the room, clearly it's a large space.",
                        "To your east, the hallway awaits from the direction you came.",
                        "To your west, the dark room sits, expectantly."
                };
                nextRooms = new RoomObjects[]{RoomObjects.BAMBOO_FOREST_CANYON_RIVER_BASE, RoomObjects.WALL, RoomObjects.WALL, RoomObjects.DARK_BASEMENT_BASE};
            }
            // [N: Wall, E: GDH, S: Wall, W: Wall]
            case RoomObjects.SUSPICIOUSLY_CLEAN_BATHROOM_BASE -> {
                if (revisitSuspiciouslyCleanBathroom){
                    getRoom(RoomObjects.SUSPICIOUSLY_CLEAN_BATHROOM_REVISIT);
                } else {
                    description = new String[]{
                            "---SUSPICIOUSLY CLEAN BATHROOM---",
                            "You find yourself in a exceptionally clean bathroom. It's so clean, you probably shouldn't use it.",
                            "To your East, is the bathroom door you entered through."
                    };
                    nextRooms = new RoomObjects[]{RoomObjects.WALL, RoomObjects.GRAND_DINING_HALL_BASE, RoomObjects.WALL, RoomObjects.WALL};
                    revisitSuspiciouslyCleanBathroom = true;
                }
            }
            case RoomObjects.SUSPICIOUSLY_CLEAN_BATHROOM_REVISIT -> {
                // REWRITE MEEEEEEEEE
                description = new String[]{
                        "---SUSPICIOUSLY CLEAN BATHROOM | Revisited---",
                        "You find yourself in a exceptionally clean bathroom. It's so clean, you probably shouldn't use it.",
                        "To your East, is the bathroom door you entered through."
                };
                nextRooms = new RoomObjects[]{RoomObjects.WALL, RoomObjects.GRAND_DINING_HALL_BASE, RoomObjects.WALL, RoomObjects.WALL};
            }
            case RoomObjects.WALL -> {

            }

        }


    }

    /*
    2d array to test out replacing dialog with.
     */

    //        System.out.println(roomObjects);
//        if (currentRoom == 1) { //Entry room
//            description = "---ENTRY ROOM---\n" +
//                "This room has an old man standing in the corner. He smells of sour clothes and has crumbs in his beard...best not stay here too long." + "\n" +
//                "To the North a large black door awaits. To the south a stairwell leads into the darkness.";
//            adjacentRooms = new Integer[]{2, 0, 3, 0};
//        } else if (currentRoom == 2) {  //Grand Dining Hall
//            description = "---GRAND DINING HALL---\n" +
//                    "You enter a grand dining hall, where the floor, walls, and ceiling are decorated in gold leaf. \n" +
//                    "A massive dining table that could easily seat 30 people is laden with steaming foods beyond your imagination.\n" +
//                    "Three small children are seated at the table, devouring the food before them. You try to get their attention, but they pay you no mind.\n" +
//                    "Along the East wall, a door sits ajar. Through the crack you can see a forest, though it doesn't seem like the door should be going outside...\n" +
//                    "To the South, is a Large Black Door.\n" +
//                    "To the West, is a small white interior door.";
//            adjacentRooms = new Integer[]{0,4,1,5};
//        } else if (currentRoom == 3) {  //Dark Basement
//            description = "---DARK BASEMENT---\n" +
//                    "The stairway leads down, deep into the Earth. Following what feels like an hour in complete darkness, your feet finally find flat ground.\n" +
//                    "You reach out finding no purchase for your hands. After searching for a long time, you hear a muffled cough nearby.\n" +
//                    "Calling out, a voice responds telling you to walk backwards until you find the wall to the North behind you. From there you can find the stairway you came down.\n" +
//                    "The voice sounds familiar...\n" +
//                    "The only exit you can find to the room, is the stairway to your North.";
//            adjacentRooms = new Integer[]{1,0,0,0};
//        } else if (currentRoom == 4) {  //Bamboo Forest
//            description = "---BAMBOO FOREST---\n" +
//                    "You step into what you now realize is a massive bamboo forest. Incredibly, it exists within a giant room where the ceiling is almost imperceptible.\n" +
//                    "A breeze blows across your face, as the bamboo shoots lightly bounce against each other making a calming melody. A sense of peace and relaxation flows through you.\n" +
//                    "You search the forest, finding a small hut in the deep recess of the 'room'. Smoke looks to be rising from the chimney. When you knock on the door, something stirs inside, but you get no answer.\n" +
//                    "Beyond the hut, is a small trail leading further North...\n" +
//                    "Behind you, you recall that the door back to the Dining Hall resides to the West.";
//            adjacentRooms = new Integer[]{6,0,0,2};
//        } else if (currentRoom == 5) {  //Suspiciously clean bathroom
//            description = "---SUSPICIOUSLY CLEAN BATHROOM---\n" +
//                    "You find yourself in a exceptionally clean bathroom. It's so clean, you probably shouldn't use it.\n" +
//                    "To your East, is the bathroom door you entered through.";
//            adjacentRooms = new Integer[]{0,2,0,0};
//        } else if (currentRoom == 6) { //Bamboo Forest - Interior Canyon
//            description = "---BAMBOO FOREST - INTERIOR CANYON---\n" +
//                    "The trail winds through the bamboo as the ceiling far above your head grows dark, seeming to disappear and morph into a night sky.\n" +
//                    "Eventually, you find yourself on the precipice of a canyon. When did you leave the house?\n" +
//                    "Your foot brushes a small rock that topples over the edge and far down into a river below. It must be at least 200 feet to the surface of the water and it looks to be moving quickly.\n" +
//                    "A worn rope bridge stretches out, spanning the gap over the canyon. You touch the rope where it knots around wooden posts in the ground, finding it coarse and dirt stained. It's clearly quite old.\n" +
//                    "The trail splits here before going in different directions.\n" +
//                    "To the North, ahead of you, likes the rope bridge. Awaiting the next soul brave enough to traverse it.\n" +
//                    "To the East, a small trail follows the canyon edge. From what you can tell, the rivers source lies to the East as well, though it's too dark at this point to be sure.\n" +
//                    "To the South, the trail delves back into the Bamboo Forest.";
//            adjacentRooms = new Integer[]{7,8,4,0};
//        } else if (currentRoom == 7) { //Not Rendered Sign - Turn Back
//            description = "---TURN BACK SIGN---\n" +
//                    "You step onto the bridge and it groans beneath you. Some of the planks are soft with rot, likely from existing over a river to many many years.\n" +
//                    "The further you progress, the more you realize how precarious the structure you are on is. Nearing the other side, one of the planks snaps under your weight.\n" +
//                    "Luckily, you were holding onto the rope and, while slightly stressed from the moment, you make it across without any serious issues.\n" +
//                    "Setting your feet back onto solid ground, you lookup and see a sign facing you. It states:\n" +
//                    "    'The area beyond this point has not been rendered, please turn back. Or Else!'\n" +
//                    "    '-Abernathy'\n" +
//                    "    'P.S. say 'exit' to leave this house and continue your testing!'" +
//                    "You probably shouldn't go any further.\n" +
//                    "To you South, a rope bridge spans the gap across the canyon.";
//            adjacentRooms = new Integer[]{0,0,6,0};
//        } else if (currentRoom == 8) { //Canyon River
//            description = "---CANYON RIVER---\n" +
//                    "The trail leads east along the canyon edge, eventually beginning to slope down. It separates from the edge of the canyon as it dives down toward the floor below.\n" +
//                    "The ground is uneven and jagged, requiring you to watch each of your footsteps. This is considerably more difficult as the light wanes to near complete darkness the further down you descend.\n" +
//                    "Eventually, the trail levels out, the temperature drops significantly, and a chilling mist begins to wet your skin as the roar of a rapid river starts to drown out all other sound.\n" +
//                    "You can only see a handful of meters ahead of you, but you can see the waves crashing against the side of the canyon you stand upon and the puddles filling the low points of the trail you now traverse.\n" +
//                    "Following the river-level pathway, you come to a point where the path stops. It comes abruptly to a wall of stone reaching back up to the top of the canyon and appears to have no way to continue further.\n" +
//                    "You investigate the stone making up the cliff face, feeling as if you can't have come all this way for nothing.\n" +
//                    "You are rewarded, as your fingers find a purchase within a small cavity in the rock. Something moves as you pull on it...\n" +
//                    "A low scraping tone, from deep within the rock reverberates out as you feel it through your fingertips.\n" +
//                    "Suddenly, the face of the rock shudders and slides into itself, away from the river. A moment later, dull light floods out of a doorway that feels like something a dwarven city would have created.\n" +
//                    "To the south, a secret passageway awaits.\n" +
//                    "To the west, the trail climbs back up to the top of the canyon.";
//            adjacentRooms = new Integer[]{0,0,9,6};
//        } else if (currentRoom == 9) { //Dwarven Hallway
//            description = "---DWARVEN HALLWAY---\n" +
//                    "You follow the stone hallway, as it twists and turns. Small gemstones are crafted into the walls periodically that give off a warm glow, ensuring that the trip is well illuminated.\n" +
//                    "The craftsmanship of the hallway is exquisite, the floor is perfectly smooth, the walls have miniscule divets carved carefully that cause the light to bounce in a mesmirizing manner.\n" +
//                    "It's clear that whoever made this secret passage was a true master, though they have likely been dead a millenia with the lack of dwarves in the area...\n" +
//                    "Eventually, you find yourself at another dead-end. Though this time, a stone button awaits you on the wall.\n" +
//                    "You press it cautiously and, again, hear the same low scraping tone from within the earth. The wall ahead of you slides into the wall and reveals a pitch black room ahead of you.\n" +
//                    "The light spills out a short distance, revealing another stone floor. You see nothing else in the room, clearly it's a large space.\n" +
//                    "To your east, the hallway awaits from the direction you came.\n" +
//                    "To your west, the dark room sits, expectantly.";
//            adjacentRooms = new Integer[]{0,8,0,3};
//        }

}
