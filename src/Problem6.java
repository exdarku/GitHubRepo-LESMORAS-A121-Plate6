/* Laurence Lesmoras - A121
 * 
 * Problem 6 - Discrete Structure 2
 * Write a java program that receives the vertex pairs associated to the edges of a graph, the program should construct an 
 * adjacency matrix for the graph. (Produce a version that works when loops, multiple edges, or directed edges are present.
 */

import java.util.*;

public class Problem6 {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("Adjency Matrix Constructor\nLaurence Lesmoras");
        System.out.println("==================================================");

        System.out.print("Is the graph Directed? (yes/no): ");
        boolean isDirected = sc.nextLine().trim().equalsIgnoreCase("yes");

        HashMap<String, Integer> indexOfVertex = new HashMap<>();
        HashSet<String> vertexSet = new HashSet<String>();
        ArrayList<String[]> edges = new ArrayList<>();
       

        System.out.println("Enter the edges (e.g. \"ab\") (type 'end' to finish):");

        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("end"))
                break;

            if (input.length() != 2){
                System.out.println("Invalid edge format. Please use \"ab\" format.");
                continue;
            }

            String u = input.substring(0,1);
            String v = input.substring(1);
            
            edges.add(new String[]{u, v});

            if (!indexOfVertex.containsKey(u)){
                indexOfVertex.put(u, vertexSet.size());
                vertexSet.add(u);
            }
            if (!indexOfVertex.containsKey(v)){
                indexOfVertex.put(v, vertexSet.size());
                vertexSet.add(v);
            }

        }
        // get the ordered pairs or edges, then get the index of the vertex from hashmap. increment the matrix_ij if found.
        int n = vertexSet.size();
        int[][] adjMatrix = new int[n][n];
        for (String[] edge : edges) {
            int uIndex = indexOfVertex.get(edge[0]);
            int vIndex = indexOfVertex.get(edge[1]);
            adjMatrix[uIndex][vIndex]++;
            // Increment the opposite if it is undirected and the pairs are not the same.
            if(!isDirected && uIndex != vIndex){
                adjMatrix[vIndex][uIndex]++;
            }
        }

        System.out.println("Adjecency Matrix:");
        for (int i = 0; i < n; i++) {
            System.out.print("[ ");
            for (int j = 0; j < n; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println("]");
        }
        

        sc.close();
    }
}
