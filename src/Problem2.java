/* Laurence Lesmoras - A121
 * 
 * Problem 2 - Discrete Structure 2
 * Write a java program that accepts an adjacency matrix of a graph. The program should list the edges of this graph and give 
 * the number of times each edge appears.
 */

import java.util.*;

public class Problem2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("Count number of edges an edge appears\nLaurence Lesmoras");
        System.out.println("==================================================");
        System.out.print("Enter the number of vertices: ");
        int vertices = sc.nextInt(); sc.nextLine();  // consume newline

        char[] vertexNames = new char[vertices];
        for (int i = 0; i < vertices; i++) vertexNames[i] = (char) ('a' + i);

        int[][] adjMatrix = new int[vertices][vertices];
        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < vertices; i++) {
            String[] row = sc.nextLine().split(" ");
            if (row.length != vertices) {
                System.out.println("Invalid Input, Please enter the matrix again with " + vertices + " columns."); i--;  // re-enter the same row
                continue;
            }
            for (int j = 0; j < vertices; j++) adjMatrix[i][j] = Integer.parseInt(row[j]);
        }

        Map<String, Integer> edgeCount = new HashMap<>();
        for (int i = 0; i < vertices; i++)
            for (int j = i; j < vertices; j++)
                if (adjMatrix[i][j] > 0) {
                    String edge = vertexNames[i] + "" + vertexNames[j];
                    edgeCount.put(edge, edgeCount.getOrDefault(edge, 0) + adjMatrix[i][j]);
                }

        System.out.println("Edges List and Number Times Appears:");
        edgeCount.forEach((edge, times) -> System.out.println("Edge " + edge + ": " + times + " times"));
        sc.close();
    }
}
