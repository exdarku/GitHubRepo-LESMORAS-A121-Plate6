/* Laurence Lesmoras - A121
 * 
 * Problem 7 - Discrete Structure 2
 * Write a java program that accepts vertex pairs associated to the edges of an undirected graph and the number of times 
 * each edge appears. The program should construct an incidence matrix for the graph.
 */

import java.util.*;

public class Problem7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("Incidence Matrix Constructor\nLaurence Lesmoras");
        System.out.println("==================================================");

        System.out.print("Enter the number of vertices: ");
        int vertices = sc.nextInt();
        sc.nextLine(); // consume the newline

        Set<String> vertexSet = new HashSet<>();
        Map<String, Integer> indexOfVertex = new HashMap<>();
        List<String[]> edges = new ArrayList<>();

        System.out.println("Enter the edges (e.g., \"ab\"). (Type 'end' to finish):");

        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("end")) {
                break;
            }

            if (input.length() != 2) {
                System.out.println("Invalid edge format. Please use the format \"ab\".");
                continue;
            }

            String u = input.substring(0, 1);
            String v = input.substring(1);

            vertexSet.add(u);
            vertexSet.add(v);

            edges.add(new String[]{u, v});
        }

        if (vertexSet.size() != vertices) {
            System.out.println("The number of unique vertices in the edges does not match the number of vertices provided.");
            System.exit(0);
        }

        int vertexIdx = 0;
        for (String vertex : vertexSet) {
            indexOfVertex.put(vertex, vertexIdx++);
        }

        int[][] incidenceMatrix = new int[vertices][edges.size()];

        // get the vertex and the index of ordered pairs (edges), then increment it to the specifc row_col in incidenceMatrix.
        // loop from 0 col and get the row through the ordered pairs
        for (int i = 0; i < edges.size(); i++) {
            String[] edge = edges.get(i);
            int uIndex = indexOfVertex.get(edge[0]);
            int vIndex = indexOfVertex.get(edge[1]);
            incidenceMatrix[uIndex][i]++;
            if (!edge[0].equals(edge[1])) {
                incidenceMatrix[vIndex][i] += 1;
            }
        }

        System.out.println("Incidence Matrix:");
        for (int i = 0; i < vertices; i++) {
            System.out.print("[ ");
            for (int j = 0; j < edges.size(); j++) {
                System.out.print(incidenceMatrix[i][j] + " ");
            }
            System.out.println("]");
        }

        sc.close();
    }
}
