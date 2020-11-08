package Graphing;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import static Graphing.Combination.nCr;

// Driver Code
public class GraphDriver {

    public static Graph<Integer> createUndirectedGraph(int nodes) {

        Graph<Integer> graph = new Graph<Integer>();

        BigDecimal eightypercent = BigDecimal.valueOf(0.8);

        BigDecimal numEdgesDec = nCr(nodes, 2).multiply(eightypercent);
        System.out.println(numEdgesDec);

        BigInteger numEdges = numEdgesDec.toBigInteger();

        BigInteger i = BigInteger.ZERO;

        while (i.compareTo(numEdges) != 0) {
            int source = new Random().nextInt(nodes);
            int dest = new Random().nextInt(nodes);
            if (!(graph.hasVertex(source))) {
                graph.addEdge(source, dest, false);
                i = i.add(BigInteger.ONE);
            }
            else if (!(graph.hasEdge(source, dest))) {
                graph.addEdge(source, dest, false);
                i = i.add(BigInteger.ONE);
            }
        }

        return graph;

    }

    public static void main(String args[]) {

        Graph<Integer> undirected10 = createUndirectedGraph(10);

        System.out.println(undirected10.toString());

        Graph<Integer> undirected100 = createUndirectedGraph(100);

        System.out.println(undirected100.toString());

    }
}
