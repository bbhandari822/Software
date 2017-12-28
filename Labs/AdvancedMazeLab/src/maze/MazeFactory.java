package maze;

/**
 * Created by Binod Bhandari on 10/25/17.
 */
public class MazeFactory {

    public Wall MakeWall()
    {
        return new Wall();
    }

    public Maze MakeMaze()
    {
        return new Maze();
    }

    public Door MakeDoor(Room r1, Room r2)
    {
        return new Door(r1, r2);
    }

    public Room MakeRoom(int id)
    {
        return new Room(id);
    }
}
