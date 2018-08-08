package others;

/*
Find same contacts in a list of contacts

Given a list of contacts containing username, email and phone number in any order. Identify the same contacts (i.e., same person having many different contacts) and
output the same contacts together.

Notes:
1) A contact can store its three fields in any order, i.e., phone number can appear before username or username can appear before phone number.

2) Two contacts are same if they have either same username or email or phone number.

Example:

Input: contact[] =
     { {"Gaurav", "gaurav@gmail.com", "gaurav@gfgQA.com"},
       { "Lucky", "lucky@gmail.com", "+1234567"},
       { "gaurav123", "+5412312", "gaurav123@skype.com"}.
       { "gaurav1993", "+5412312", "gaurav@gfgQA.com"}
     }
Output:
   0 2 3
   1
contact[2] is same as contact[3] because they both have same
contact number.
contact[0] is same as contact[3] because they both have same
e-mail address.
Therefore, contact[0] and contact[2] are also same.



The idea is to first create a graph of contacts using given array. In the graph, there is an edge between vertex i to vertex j if they both have either same username or same
email or same phone number. Once the graph is constructed, the task reduces to finding connected components in an undirected graph. We can find connected components either by doing
DFS or BFS starting from every unvisited vertex.

Time complexity: O(n2) where n is number of contacts.
Note that there are other efficient ways to solve this.Use of hashing etc.



 */

import java.util.LinkedList;
import java.util.Objects;

class Contacts {
    public Contacts(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    @Override
    public boolean equals(Object o) {
        //Two contacts are same if they have either same username or email or phone number.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacts contacts = (Contacts) o;
        return Objects.equals(field1, contacts.field1) ||
                Objects.equals(field1, contacts.field2) ||
                Objects.equals(field1, contacts.field3) ||
                Objects.equals(field2, contacts.field1) ||
                Objects.equals(field2, contacts.field2) ||
                Objects.equals(field2, contacts.field3) ||
                Objects.equals(field3, contacts.field1) ||
                Objects.equals(field3, contacts.field2) ||
                Objects.equals(field3, contacts.field3);
    }

    String field1;
    String field2;
    String field3;

    @Override
    public String toString() {
        return "Contacts{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                '}';
    }
}

public class FindSameContacts {

    private int V;

    private Contacts[] contacts;

    private LinkedList<Integer>[] adjList;

    public FindSameContacts(int v, Contacts[] contacts) {
        V = v;
        this.contacts = contacts;
        adjList = new LinkedList[v];
        for(int i =0; i<V; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    private void addEdge(int i, int j) {
        adjList[i].add(j);
        adjList[j].add(i);
    }

    private void dfs(int i, boolean[] visited){
        visited[i] = true;
        System.out.println(contacts[i]);
        LinkedList<Integer> adjNodes = adjList[i];
        for(Integer j : adjNodes){
            if(!visited[j]){
                dfs(j, visited);
            }
        }
    }

    public void dfs(){
        boolean[] visited = new boolean[V];
        for(int i =0; i<V; i++){
            if(!visited[i]){
                dfs(i,visited);
                System.out.println();
            }
        }
    }



    public static void main(String[] args) {
        Contacts arr[] = {new Contacts("Gaurav", "gaurav@gmail.com", "gaurav@gfgQA.com"),
                new Contacts("Lucky", "lucky@gmail.com", "+1234567"),
                new Contacts("gaurav123", "+5412312", "gaurav123@skype.com"),
                new Contacts("gaurav1993", "+5412312", "gaurav@gfgQA.com"),
                new Contacts("raja", "+2231210", "raja@gfg.com"),
                new Contacts("bahubali", "+878312", "raja")};
        FindSameContacts graph = new FindSameContacts(arr.length, arr);
        for(int i =0; i<arr.length; i++){
            for(int j = i; j < arr.length; j++){
                if(arr[i].equals(arr[j])){
                    graph.addEdge(i,j);
                }
            }
        }
        graph.dfs();
    }
}
