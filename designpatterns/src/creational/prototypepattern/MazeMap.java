package creational.prototypepattern;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vaibhavmishra on 23/9/17.
 */
// prototype manager
public class MazeMap {

  private static Map<String, Maze> mazeMap = new HashMap<>();

  static {
    mazeMap.put("door", new Door());
    mazeMap.put("wall", new Wall());
  }

  public static Maze getMaze(String mazeName) {
    return (Maze) mazeMap.get(mazeName.toLowerCase()).clone();
  }
}
