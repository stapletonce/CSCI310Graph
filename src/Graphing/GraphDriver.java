package Graphing;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import static Graphing.Combination.nCr;

// Driver Code
public class GraphDriver {

    public static Graph<Integer> createGraph(int nodes, Boolean bidirectional) {

        Graph<Integer> graph = new Graph<Integer>();

        BigDecimal eightypercent = BigDecimal.valueOf(0.8);
        BigDecimal numEdgesDec = nCr(nodes, 2).multiply(eightypercent);

        BigInteger numEdges = numEdgesDec.toBigInteger();


        BigInteger i = BigInteger.ZERO;

        while (i.compareTo(numEdges) != 0) {
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

    public static Boolean checkEachVertexHasAnEdge(Graph<Integer> graph, int nodes) {
        for (int q = 0; q < nodes; q++) {
            if (graph.numOfEdges(q) <= 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {

        Graph<Integer> undirected10 = createGraph(10, true);
        System.out.println("Undirected with 10 nodes");
        undirected10.getEdgesCount(true);
        System.out.print(undirected10.toString());
        System.out.println("Each vertex has at least one edge: " + checkEachVertexHasAnEdge(undirected10, 10));
        System.out.println("--------------------------------------------------");


        Graph<Integer> undirected100 = createGraph(100, true);
        System.out.println("Undirected with 100 nodes");
        undirected100.getEdgesCount(true);
        System.out.print(undirected100.toString());
        System.out.println("Each vertex has at least one edge: " + checkEachVertexHasAnEdge(undirected100, 100));
        System.out.println("--------------------------------------------------");

        //Graph<Integer> undirected10000 = createGraph(10000, true);
        //System.out.println("Undirected with 10000 nodes");
        //undirected1000.getEdgesCount(true);
        //System.out.print(undirected10000.toString());
        //System.out.println("Each vertex has at least one edge: " + checkEachVertexHasAnEdge(undirected10000, 10000));
        //System.out.println("--------------------------------------------------");

        //Graph<Integer> undirected100000 = createGraph(100000, true);
        //System.out.println("Undirected with 100000 nodes");
        //undirected100000.getEdgesCount(true);
        //System.out.print(undirected100000.toString());
        //System.out.println("Each vertex has at least one edge: " + checkEachVertexHasAnEdge(undirected100000, 100000));
        //System.out.println("--------------------------------------------------");

        Graph<Integer> directed10 = createGraph(10, false);
        System.out.println("Directed with 10 nodes");
        directed10.getEdgesCount(false);
        System.out.print(directed10.toString());
        System.out.println("Each vertex has at least one edge: " + checkEachVertexHasAnEdge(directed10, 10));
        System.out.println("--------------------------------------------------");

        Graph<Integer> directed100 = createGraph(100, false);
        System.out.println("Directed with 100 nodes");
        directed100.getEdgesCount(false);
        System.out.print(directed100.toString());
        System.out.println("Each vertex has at least one edge: " + checkEachVertexHasAnEdge(directed100, 100));
        System.out.println("--------------------------------------------------");

        //Graph<Integer> directed10000 = createGraph(10000, false);
        //System.out.println("Directed with 10000 nodes");
        //directed10000.getEdgesCount(false);
        //System.out.print(directed10000.toString());
        //System.out.println("Each vertex has at least one edge: " + checkEachVertexHasAnEdge(directed10000, 10000));
        //System.out.println("--------------------------------------------------");

        //Graph<Integer> directed100000 = createGraph(100000, false);
        //System.out.println("Directed with 100000 nodes");
        //directed100000.getEdgesCount(false);
        //System.out.print(directed100000.toString());
        //System.out.println("Each vertex has at least one edge: " + checkEachVertexHasAnEdge(directed100000, 100000));
        //System.out.println("--------------------------------------------------");

    }
}
