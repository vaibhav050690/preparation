package shortestpath;

/**
 * Created by vaibhavmishra on 27/12/17.
 */
public class Node {
  private int vertex;

  @Override
  public String toString() {
    return "Node{" +
        "vertex=" + vertex +
        ", weight=" + weight +
        '}';
  }

  public int getVertex() {
    return vertex;
  }

  public void setVertex(int vertex) {
    this.vertex = vertex;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public Node(int vertex, int weight) {
    this.vertex = vertex;
    this.weight = weight;
  }

  private int weight;

}
