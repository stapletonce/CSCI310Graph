// Adapted from https://www.geeksforgeeks.org/implementing-generic-graph-in-java/
// and https://sites.google.com/site/indy256/algo/scc_tarjan
// and https://algorithms.tutorialhorizon.com/check-if-graph-is-bipartite-adjacency-list-using-breadth-first-searchbfs/

package Graphing;

import java.util.*;

class Graph<T> {

    // We use a Hashmap to store the edges in the graph
    private Map<T, List<T> > map = new HashMap<>();
    // We use a Hashmap to connected the vertexes in the edge list to understanding their vertex properties
    private Map<T, Vertex> vertexMap = new HashMap<>();

    // for Tarjan Algorithm
    List<Integer>[] graph;
    boolean[] visited;
    Stack<Integer> stack;
    int time;
    int[] lowlink;
    List<List<Integer>> components;

    // for bipartite finding
    enum Color{
        WHITE, RED, GREEN
    }

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

    ////////////////////////////////////////////////////////////////////////////
    // Tarjan's algorithm

    public List<List<Integer>> findSCC() {
        List<Integer>[] graph = this.turnMapToIntegerList(map);
        return scc(graph);
    }

    public List<Integer>[] turnMapToIntegerList(Map map) {
        List<Integer>[] g = new List[map.size()];
        for (int i = 0; i < g.length; i++) {
            Integer j = i;
            g[i] = (List<Integer>) map.get(j);
            // System.out.println("--" + g[i]);
        }


        return g;
    }

    public List<List<Integer>> scc(List<Integer>[] graph) {
        int n = graph.length;
        this.graph = graph;
        visited = new boolean[n];
        stack = new Stack<>();
        time = 0;
        lowlink = new int[n];
        components = new ArrayList<>();

        for (int u = 0; u < n; u++)
            if (!visited[u])
                dfs(u);

        return components;
    }

    void dfs(int u) {
        lowlink[u] = time++;
        visited[u] = true;
        stack.add(u);
        boolean isComponentRoot = true;

        for (int v : graph[u]) {
            if (!visited[v])
                dfs(v);
            if (lowlink[u] > lowlink[v]) {
                lowlink[u] = lowlink[v];
                isComponentRoot = false;
            }
        }

        if (isComponentRoot) {
            List<Integer> component = new ArrayList<>();
            while (true) {
                int x = stack.pop();
                component.add(x);
                lowlink[x] = Integer.MAX_VALUE;
                if (x == u)
                    break;
            }
            components.add(component);
        }
    }

    ////////////////////////////////////////////////////////////////////////////


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

    public boolean isBipartite() {

        int vertices = map.size();

        graph = turnMapToIntegerList(map);

        //check if graph is empty
        if (vertices == 0)
            return true;

        //initialize colors for all vertices
        Color colors[] = new Color[vertices];
        //color all the vertices with white color
        for (int i = 0; i < colors.length; i++) {
            colors[i] = Color.WHITE;
        }

        Queue<Integer> queue = new LinkedList<>();
        //start coloring vertices , this code will handle the disconnected graph as well
        //color the first vertex with RED
        for (int source = 0; source < vertices; source++) {

            if (colors[source] == Color.WHITE) {
                colors[source] = Color.RED;

                //add it to queue for BFS
                queue.add(source);

                while (!queue.isEmpty()) {
                    int v = queue.remove();
                    for (int i = 0; i < graph[v].size(); i++) {
                        int dest = graph[v].get(i);

                        if (colors[dest] == Color.WHITE) {
                            //color is with the alternate color of vertex v

                            if (colors[v] == Color.RED) {
                                //if source is in red, make vertex dest green
                                colors[dest] = Color.GREEN;
                            } else if (colors[v] == Color.GREEN) {
                                //if source is in red, make vertex dest green
                                colors[dest] = Color.RED;
                            }
                            //add vertex dest to queue
                            queue.add(dest);
                        } else if (colors[v] == colors[dest]) {
                            //means vertex v and dest are in same color, so graph is not bipartite
                            return false;
                        }
                    }
                }
            }
        }
        // if here means graph is successfully colored in 2 color, red and green
        // graph is bipartite
        return true;
    }

    // Some verifying that everything works properly
    public static void main(String[] args) {
        List<Integer>[] g = new List[3];
        for (int i = 0; i < g.length; i++)
            g[i] = new ArrayList<>();

        g[2].add(0);
        g[2].add(1);
        g[0].add(1);
        g[1].add(0);

        for (int i = 0; i < g.length; i++)
            System.out.println(g[i]);

        Graph<Integer> h = new Graph<>();
        h.addEdge(1, 2, true);
        h.addEdge(2, 3, true);
        h.addEdge(3, 0, true);
        h.addEdge(1, 0, true);

        h.toString();

        System.out.println(h.findSCC());

        System.out.println(h.isBipartite());
    }
}
