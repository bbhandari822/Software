package maze;

import java.awt.*;

/**
 * Created by Binod Bhandari on 10/25/17.
 */
public class RedRoom extends Room {
    public RedRoom(int num) {
        super(num);
    }

    public Color getColor() {
        return Color.pink;
    }
}
