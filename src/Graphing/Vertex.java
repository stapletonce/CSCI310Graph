package Graphing;

public class Vertex {
    public int n;
    public boolean visited;

    public Vertex(int n) {
        this.n = n;
        visited = false;
    }

    public Boolean isVisited() {
        return this.visited;
    }

    public void visit() {
        visited = true;
    }

    public void unvisit() {
        visited = false;
    }


}
