CSCI 310 Programming Assignment
Chloe Stapleton
Due Thursday, Nov. 12, 2020

The Java classes provided must be compiled as part of the same Package ("Graphing"). All graphs were generated using the code written and partially commented-out in GraphDriver.java.

Included in this folder are the graphs that I have generated for Problem (1) along with the actions performed on them in Problems (2) and (3). 

output_upto_100.txt 
shows easily readable graphs generated from Problem (1):
Undirected - 10 nodes, 	edges = 80% of nC2,	proof each vertex has at least 1 edge,	proof whether graph is bipartite or not
Undirected - 100 nodes, edges = 80% of nC2, 	proof each vertex has at least 1 edge,	proof whether graph is bipartite or not
Directed   - 10 nodes, 	edges = 80% of nC2, 	proof each vertex has at least 1 edge,	list of strongly connected components and time taken to find
Directed   - 100 nodes,	edges = 80% of nC2, 	proof each vertex has at least 1 edge,	list of strongly connected components and time taken to find

undirected_upto_10000.txt
shows more undirected graphs generated from Problem (1), not including 100,000 as this is too large for machine to computer

directed_upto_10000.txt
shows more directed graphs generated from Problem (1), not including 100,000 as this is too large for machine to computer

(Also included in output_upto_100.txt is "extra" at the end that proves validity of the functions that find strongly connected components and bipartite property.)

The code in graph.java was written in part using https://www.geeksforgeeks.org/implementing-generic-graph-in-java/ for the Graph ADT implementation.
