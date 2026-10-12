public class NPC {

    // ============================================
    //      [Class Object &/OR Variable] Declarations
    // ============================================

    // region

    String name = "";
    Room.RoomId location;
    boolean isAlive = true;
    boolean hasMet = false;
//    boolean isHostile = false;
    int encounterNumber = 0;

    public NPC() {

    }

    // Not yet used in logic. Placeholder for future NPC behavior branching (e.g. merchant vs. enemy).
    enum Type {
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

    public NPC (String name, Room.RoomId location) {
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
        return getClass().getName() + "[" + name + "," + location.toString() + "," + isAlive + "," + hasMet + "]";
    }


    // endregion


    // ============================================
    //      Deprecated Methods
    // ============================================

    // region



    // endregion

}
