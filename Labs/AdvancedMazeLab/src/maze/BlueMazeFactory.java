package maze;

/**
 * Created by Binod Bhandari on 10/24/17.
 */
public class BlueMazeFactory extends MazeFactory{

    public Wall MakeWall(){
        return new BlueWall();

    }
    public Maze MakeMaze()
    {
        return new Maze();
    }

    public Door MakeDoor(Room room1, Room room2){
        return new BrownDoor(room1,room2);
    }

    public Room MakeRoom(int id){

        return new GreenRoom(id);
    }

}
