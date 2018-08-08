package shortestpath;

/**
 * Created by vaibhavmishra on 28/12/17.
 */
public class Edge implements Comparable<Edge> {

    private int source;
    private int destination;
    private int weight;

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source +
                ", destination=" + destination +
                ", weight=" + weight +
                '}';
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public Edge() {
        this.source = -1;
        this.destination = -1;
        this.weight = 0;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge compareEdge) {
        return this.weight-compareEdge.weight;
    }
}
