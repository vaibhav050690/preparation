package creational.prototypepattern;

/**
 * Created by vaibhavmishra on 23/9/17.
 */
public class Client {

    public static void main(String[] args) {
        Maze maze = MazeMap.getMaze("door");
        Maze maze1 = MazeMap.getMaze("wall");
        System.out.println(maze);
        System.out.println(maze1);
    }
}
