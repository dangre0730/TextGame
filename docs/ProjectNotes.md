# TextGame Project - Notes & References

### Project Intent
This project is intended as a learning experience as I refamiliarize myself with the myriad ways that Java can be 
leveraged for personal projects like games, apps, and storytelling.

### Project Goal
Build a text-based RPG game which bridges the classic ZORK text-based exploration & combat gameplay with classes, 
equipable weapons / armor, a stat sheet, leveling mechanics with stat allocation, skills & skill upgrades / unlocks, 
a persistent inventory / item tracking for the player & game-world, and the world is able to link different rooms 
procedurally within a specified scope to ensure some rooms always have valid connections and no generations occur 
where the game cannot be completed due to the order rooms get generated creating dead-ends or similar.
* Players choose their name, starting class, and a starting item.
* Classes determine whether the player can utilize certain types of items.
* Interactivity with the world is handled entirely through text prompting.
* Combat is fixed in certain situations, but it can occur randomly in most rooms.
* Persistent inventory means picking up a lantern in one area, moving to another zone, dropping the item, and 
  returning to it later in the game is possible.
* NPCs can move to other zones / rooms, offer quests, buy / sell items, make comments hinting at information that will 
  help with completing the game, etc.
* Gaining levels allows players to unlock special skills that make the classes a unique experience to the other options.
* Death is permanent, but saving where you are must be possible to prevent restarting each time the game launches.

### Architectural Decision Records & Code Patterns
It's very important to me that I learn how to write this game in a modular fashion. No "God class" that runs 
literally everything. Instead, classes should contain information directly related to them and the methods they 
contain should follow the best practice of doing a single thing so that troubleshooting and future enhancements are 
much easier to deal with. I need decisions I make for how the code is written to remain consistent across the entire 
project. Code which is self-documenting to reduce the need for in-line comments is something to strive for 
without making variable or method names overcomplicated. 
* Class naming: PascalCase (Upper Camel Case); typically a noun representing the object - Game, UserInterface, EnemyNPC
* Variable naming: camelCase (Lower Camel Case); typically nouns that explain the variables purpose - gameWidth, name, 
  mainTextArea
* Method naming: camelCase; typically verbs that describe the action to take - slowPrintText, handleInput
* Constants & ENUM naming: SCREAMING_SNAKE_CASE; static final fields that are immutable, fixed values - IN_COMBAT, WALL
* Other:
  * Documents relating to this project, and which are contained within this project, will be written as Markdown 
    files. This ensures consistency with presentation of information across all devices and removes the potential 
    for unsupported file types or other unforeseen issues that can be caused by relying on specialized software.
  * Boolean prefixes should have auxiliary verbs like - is, had, can, should, etc.
  * Don't start a variable with a prefix indicating the data type unless absolutely necessary.

-----------------------------------------------------------------------------------------------------------------

Current direction of work:
* Build out combat loop feature to present information clearly and consistently.
  * Known issues:
    * Text presented upon loop entry is missing a splash display stating combat is about to begin w/ continue.
    * Post combat text display doesn't present until player hits enter AND if further combat is selected it requires 
      player input before information on screen updates correctly.
    * When a crit triggers, damage is doubled but no explicit message notifies the player they crit.

Last things I did / pickup from here:
* Updated the window scale value to use a variable set at the start of the game execution. Currently 70% of screen size.
* Changed variables in Game class to use primitives for int, boolean, and double.

Long-running things to work on / smooth out:
* Learn how to integrate the UI into the Event Dispatch Thread (EDT) so that text can be "typed" out for the player 
  to read rather than occurring "invisibly" and then suddenly appearing onscreen (screen freezes for a bit while 
  logic processes actions). This is going to be important so 
  we can separate UI actions from core game actions appropriately.
  * WE MUST LEARN TO AVOID BLOCKING THE EDT WITH ANYTHING UNRELATED TO THE GUI SPECIFICALLY.
* Starting weapons and armor are eventually class specific, with the character builder process being- choose name 
  and class, then select 1 starting item for the character. 