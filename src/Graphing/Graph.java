// Adapted from https://www.geeksforgeeks.org/implementing-generic-graph-in-java/

package Graphing;

import java.util.*;

class Graph<T> {

    // We use Hashmap to store the edges in the graph
    private Map<T, List<T> > map = new HashMap<>();
    private Map<T, Vertex> vertexMap = new HashMap<>();

    // This function adds a new vertex to the graph
    public void addVertex(T s)
    {
        map.put(s, new LinkedList<T>());
        vertexMap.put(s, new Vertex((Integer) s));
    }

    public Boolean vertexVisited(T s) {
        return vertexMap.get(s).visited;
    }

    // This function adds the edge
    // between source to destination
    public void addEdge(T source,
                        T destination,
                        boolean bidirectional)
    {

        if (!map.containsKey(source))
            addVertex(source);

        if (!map.containsKey(destination))
            addVertex(destination);

        map.get(source).add(destination);
        if (bidirectional == true) {
            map.get(destination).add(source);
        }
    }

    // This function gives the count of vertices
    public void getVertexCount()
    {
        System.out.println("The graph has "
                + map.keySet().size()
                + " vertex");
    }

    // This function gives the count of edges
    public void getEdgesCount(boolean bidirection)
    {
        int count = 0;
        for (T v : map.keySet()) {
            count += map.get(v).size();
        }
        if (bidirection == true) {
            count = count / 2;
        }
        System.out.println("The graph has "
                + count
                + " edges.");
    }

    // This function gives whether a vertex is present or not.
    public Boolean hasVertex(T s)
    {
        if (map.containsKey(s)) {
            return true;
        }
        else {
            return false;
        }
    }

    // This function gives whether an edge is present or not.
    public Boolean hasEdge(T s, T d)
    {
        if (map.get(s).contains(d)) {
            return true;
        }
        else {
            return false;
        }
    }

    // Find the number of edges a vertex has
    public int numOfEdges(T s) {
        return(map.get(s).size());
    }

    public void depthFirstSearch(int node) {
        depthFirstSearchHelper(vertexMap.get(node));
    }

    public void depthFirstSearchHelper(Vertex node) {
        node.visit();
        System.out.print(node.n + " ");

        LinkedList<T> allNeighbors = (LinkedList<T>) map.get(node.n);
        if (allNeighbors == null)
            return;

        for (T neighbor : allNeighbors) {

            if (!vertexVisited(neighbor))
                depthFirstSearchHelper(vertexMap.get(neighbor));
        }
    }

    // Prints the adjacency list of each vertex.
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for (T v : map.keySet()) {
            builder.append(v.toString() + ": ");
            for (T w : map.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }

        return (builder.toString());
    }
}
