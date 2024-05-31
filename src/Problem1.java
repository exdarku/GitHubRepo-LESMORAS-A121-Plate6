/* Laurence Lesmoras - A121
 *
 * Problem 1 - Discrete Structure 2 
 * Write a java program that receives a list of edges of a simple graph, 
 * the program should determine whether it is connected and find the number of 
 * connected components if it is not connected.
 */

import java.util.*;

public class Problem1 {

    private static void searchPath(String node, Map<String, List<String>> adjList, Set<String> visited) {
        visited.add(node);
        for (String neighbor : adjList.get(node)) 
            if (visited.add(neighbor)) 
                searchPath(neighbor, adjList, visited);
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        Map<String, List<String>> adjList = new HashMap<>();
        Set<String> vertices = new HashSet<>();
        System.out.println("===================================================================");
        System.out.println("Connected Graph Checker\nLaurence Lesmoras");
        System.out.println("===================================================================");
        System.out.println("Enter the edges (example: \"ab\"). (Type '0' to finish):");
        
        for (String input; !(input = userInput.nextLine()).equalsIgnoreCase("0"); ) {
            if (input.length() != 2) {
                System.out.println("Invalid edge format. Please use \"ab\" format."); continue;
            }
            String u = input.substring(0, 1), v = input.substring(1);
            vertices.add(u); vertices.add(v);
            adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        Set<String> visited = new HashSet<>();
        int componentCount = 0;
        for (String vertex : vertices) 
            if (visited.add(vertex)) {
                searchPath(vertex, adjList, visited);
                componentCount++;
            }

        System.out.println(componentCount == 1 ? "The graph is connected." : "The graph is not connected.\nNumber of connected components: " + componentCount);
        userInput.close();
    }
}
