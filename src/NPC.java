public class NPC {

    // ============================================
    //      [Class Object &/OR Variable] Declarations
    // ============================================

    // region

    String name = "";
    Room.RoomObjects location;
    boolean isAlive = true;
    boolean alreadyMet = false;
//    boolean isHostile = false;
    int encounterNumber = 0;

    enum type {
        QUEST_GIVER,
        MERCHANT,
        TRAINER,
        ENEMY
    }

    // endregion


    // ============================================
    //      [Class Builder] Methods
    // ============================================

    // region

    public NPC (String name, Room.RoomObjects location) {
        this.name = name;
        this.location = location;
    }

    // endregion


    // ============================================
    //      [Class Updater] Methods
    // ============================================

    // region



    // endregion


    // ============================================
    //      [Event / Take Action] Methods
    // ============================================

    // region

    @Override
    public String toString() {
        return getClass().getName() + "[" + name + "," + location.toString() + "," + isAlive + "," + alreadyMet + "]";
    }


    // endregion


    // ============================================
    //      Deprecated Methods
    // ============================================

    // region



    // endregion

}
