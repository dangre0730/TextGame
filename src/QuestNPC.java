import java.util.Arrays;

public class QuestNPC extends NPC{
    String[][] dialog;

    public QuestNPC(String name, Room.RoomId location, String[][] dialog) {
        super(name, location);
        this.dialog = dialog;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[" + name + "," + location + "," + Arrays.deepToString(dialog) + "]";
    }
}
