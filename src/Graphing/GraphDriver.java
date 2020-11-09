package Graphing;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import static Graphing.Combination.nCr;

// Driver Code
public class GraphDriver {

    // create a graph with number of edges = 80% of nC2 where n is the number of vertices
    public static Graph<Integer> createGraph(int nodes, Boolean bidirectional) {

        Graph<Integer> graph = new Graph<Integer>();

        BigDecimal eightypercent = BigDecimal.valueOf(0.8);
        BigDecimal numEdgesDec = nCr(nodes, 2).multiply(eightypercent);

        BigInteger numEdges = numEdgesDec.toBigInteger();

        BigInteger i = BigInteger.ZERO;

        while (i.compareTo(numEdges) != 0) {
//            //printing to make sure it's still running
//            if ((i.mod(BigInteger.valueOf(5000000)).compareTo(BigInteger.ZERO)) == 0) {
//                System.out.println(i);
//            }

            int source = new Random().nextInt(nodes);
            int dest = new Random().nextInt(nodes);

            if (source == dest) {
                // skip adding an edge where it would be a self loop
                continue;
            }
            if (!(graph.hasVertex(source))) {
                graph.addEdge(source, dest, bidirectional);
                i = i.add(BigInteger.ONE);
            }
            else if (!(graph.hasEdge(source, dest))) {
                graph.addEdge(source, dest, bidirectional);
                i = i.add(BigInteger.ONE);
            }
        }

        return graph;

    }

    // create a graph with a specified number of vertices and edges
    public static Graph<Integer> createGraph(int nodes, int numEdges, Boolean bidirectional) {

        Graph<Integer> graph = new Graph<Integer>();

        int i = 0;

        while (i <= numEdges) {
            int source = new Random().nextInt(nodes);
            int dest = new Random().nextInt(nodes);

            if (source == dest) {
                // skip adding an edge where it would be a self loop
                continue;
            }
            if (!(graph.hasVertex(source))) {
                graph.addEdge(source, dest, bidirectional);
                i++;
            }
            else if (!(graph.hasEdge(source, dest))) {
                graph.addEdge(source, dest, bidirectional);
                i++;
            }
        }

        return graph;

    }

    // verify that each vertex has at least one edge
    public static Boolean checkEachVertexHasAnEdge(Graph<Integer> graph, int nodes) {
        for (int q = 0; q < nodes; q++) {
            if (graph.numOfEdges(q) <= 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {

        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");
        System.out.println("                 Undirected Graphs                ");
        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");
        System.out.println();

        Graph<Integer> undirected10 = createGraph(10, true);
        System.out.println("Undirected with 10 nodes");
        undirected10.getEdgesCount(true);
        System.out.print(undirected10.toString());
        System.out.println("Each vertex has at least one edge: " + checkEachVertexHasAnEdge(undirected10, 10));
        System.out.print("DFS: ");
        undirected10.depthFirstSearch(0);
        System.out.println();
        System.out.println("The graph is bipartite: " + undirected10.isBipartite());
        System.out.println("--------------------------------------------------");


        Graph<Integer> undirected100 = createGraph(100, true);
        System.out.println("Undirected with 100 nodes");
        undirected100.getEdgesCount(true);
        System.out.print(undirected100.toString());
        System.out.println("Each vertex has at least one edge: " + checkEachVertexHasAnEdge(undirected100, 100));
        System.out.print("DFS: ");
        undirected100.depthFirstSearch(0);
        System.out.println();
        System.out.println("The graph is bipartite: " + undirected100.isBipartite());
        System.out.println("--------------------------------------------------");

//        Graph<Integer> undirected10000 = createGraph(10000, true);
//        System.out.println("Undirected with 10000 nodes");
//        undirected10000.getEdgesCount(true);
//        System.out.print(undirected10000.toString());
//        System.out.println("Each vertex has at least one edge: " + checkEachVertexHasAnEdge(undirected10000, 10000));
//        //System.out.print("DFS: ");
//        //undirected10000.depthFirstSearch(0);
//        //System.out.println();
//        //System.out.println("The graph is bipartite: " + undirected10000.isBipartite());
//        System.out.println("--------------------------------------------------");
//
//        //Graph<Integer> undirected100000 = createGraph(100000, true);
//        //System.out.println("Undirected with 100000 nodes");
//        //undirected100000.getEdgesCount(true);
//        //System.out.print(undirected100000.toString());
//        //System.out.println("Each vertex has at least one edge: " + checkEachVertexHasAnEdge(undirected100000, 100000));
//        //System.out.print("DFS: ");
//        //undirected100000.depthFirstSearch(0);
//        //System.out.println();
//        //System.out.println("The graph is bipartite: " + undirected100000.isBipartite());
//        //System.out.println("--------------------------------------------------");

        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");
        System.out.println("                   Directed Graphs                ");
        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");
        System.out.println();

        Graph<Integer> directed10 = createGraph(10, false);
        System.out.println("Directed with 10 nodes");
        directed10.getEdgesCount(false);
        System.out.print(directed10.toString());
        System.out.println("Each vertex has at least one edge: " + checkEachVertexHasAnEdge(directed10, 10));
        System.out.print("DFS: ");
        directed10.depthFirstSearch(0);
        System.out.println();
        long startTime1 = System.nanoTime();
        System.out.println(directed10.findSCC());
        long endTime1 = System.nanoTime();
        System.out.println("Time to find SCC: " + (endTime1 - startTime1) + " ns");

        System.out.println("--------------------------------------------------");

        Graph<Integer> directed100 = createGraph(100, false);
        System.out.println("Directed with 100 nodes");
        directed100.getEdgesCount(false);
        System.out.print(directed100.toString());
        System.out.println("Each vertex has at least one edge: " + checkEachVertexHasAnEdge(directed100, 100));
        System.out.print("DFS: ");
        directed100.depthFirstSearch(0);
        System.out.println();
        long startTime = System.nanoTime();
        System.out.println(directed100.findSCC());
        long endTime = System.nanoTime();
        System.out.println("Time to find SCC: " + (endTime - startTime) + " ns");
        System.out.println("--------------------------------------------------");
//
//        Graph<Integer> directed10000 = createGraph(10000, false);
//        System.out.println("Directed with 10000 nodes");
//        directed10000.getEdgesCount(false);
//        System.out.print(directed10000.toString());
//        System.out.println("Each vertex has at least one edge: " + checkEachVertexHasAnEdge(directed10000, 10000));
//        //System.out.print("DFS: ");
//        //directed10000.depthFirstSearch(0);
//        //System.out.println();
//        //long startTime2 = System.nanoTime();
//        //System.out.println(directed10000.findSCC());
//        //long endTime2 = System.nanoTime();
//        //System.out.println("Time to find SCC: " + (endTime2 - startTime2) / 1000000000 + " seconds");
//        System.out.println("--------------------------------------------------");
//
//        //Graph<Integer> directed100000 = createGraph(100000, false);
//        //System.out.println("Directed with 100000 nodes");
//        //directed100000.getEdgesCount(false);
//        //System.out.print(directed100000.toString());
//        //System.out.println("Each vertex has at least one edge: " + checkEachVertexHasAnEdge(directed100000, 100000));
//        //System.out.print("DFS: ");
//        //directed100000.depthFirstSearch(0);
//        //System.out.println();
//        //long startTime3 = System.nanoTime();
//        //System.out.println(directed100000.findSCC());
//        //long endTime3 = System.nanoTime();
//        //System.out.println("Time to find SCC: " + (endTime3 - startTime3) / 1000000000 + " seconds");
//        //System.out.println("--------------------------------------------------");
//
        System.out.println("Here's some extra.");
        System.out.println("A graph where there are multiple strongly connected components");

        Graph<Integer> proveSCC = createGraph(9, 15, false);
        System.out.print(proveSCC);
        System.out.println(proveSCC.findSCC()+"\n");

        System.out.println("A bipartite graph");
        Graph<Integer> proveBipart = new Graph<>();
        proveBipart.addEdge(1, 2, true);
        proveBipart.addEdge(2, 3, true);
        proveBipart.addEdge(3, 0, true);
        proveBipart.addEdge(1, 0, true);
        System.out.print(proveBipart);
        System.out.println(proveBipart.isBipartite());
    }
}
