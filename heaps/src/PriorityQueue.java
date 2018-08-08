import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class QueryNode {
    String query;

    public QueryNode(String query, int count) {
        this.query = query;
        this.count = count;
    }

    @Override
    public String toString() {
        return "QueryNode{" +
                "query='" + query + '\'' +
                ", count=" + count +
                '}';
    }
    int count;
}



public class PriorityQueue {

    java.util.PriorityQueue<Node> maxHeap = new java.util.PriorityQueue<>(10, new Comparator<Node>() {
        @Override
        public int compare(Node node, Node t1) {
            return t1.count - node.count;
        }
    });

    Map<String,Node> map = new HashMap<>();

    public void addQuery(String query){
        if(map.containsKey(query)){
            Node node = map.get(query);
            int count = node.count + 1;
            maxHeap.remove(node);
            node = new Node(query,count);
            maxHeap.offer(node);
            map.put(query,node);
        }
        else {
            Node node = new Node(query,1);
            map.put(query,node);
            maxHeap.offer(node);

        }

    }

    public void print(){
        System.out.println(maxHeap);
    }

    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.addQuery("kohli");
        priorityQueue.addQuery("kohli");
        priorityQueue.addQuery("kohli");
        priorityQueue.addQuery("dhoni");
        priorityQueue.addQuery("dhoni");
        priorityQueue.addQuery("modi");
        priorityQueue.addQuery("pandaya");
        priorityQueue.addQuery("kohli");
        priorityQueue.addQuery("dhoni");
        priorityQueue.addQuery("dhoni");
        priorityQueue.addQuery("dhoni");
        priorityQueue.addQuery("dhoni");

        priorityQueue.print();
    }
}