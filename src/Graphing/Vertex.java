package Graphing;

// Vertex class so that we can mark the number of the vertex and if it was visited or not (to be used for search and traversals)

public class Vertex {
    public int n;
    public boolean visited;

    public Vertex(int n) {
        this.n = n;
        visited = false;
    }

    public void visit() {
        visited = true;
    }

    public void unvisit() {
        visited = false;
    }


}
