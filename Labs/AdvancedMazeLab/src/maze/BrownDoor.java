package maze;

import java.awt.*;

/**
 * Created by Binod Bhandari on 10/25/17.
 */
public class BrownDoor extends Door{

    public BrownDoor(Room room1, Room room2) {
        super(room1, room2);
    }

    public Color getColor()
    {
        return new Color(111, 44, 4);
    }
}
