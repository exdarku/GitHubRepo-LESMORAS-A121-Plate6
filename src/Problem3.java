/* Laurence Lesmoras - A121
 * 
 * Problem 3 - Discrete Structure 2
 * Write a java program that will determine if a graph has a cycle or not
 */

import java.util.*;

public class Problem3 {
    private static boolean hasCycleUndirected(String node, String parent, Map<String, List<String>> adjList, Set<String> visited) {
        visited.add(node);

        for (String nextNode : adjList.get(node)) {
            if (!visited.contains(nextNode)) {
                if (hasCycleUndirected(nextNode, node, adjList, visited)) {
                    return true;
                }
            } else if (!nextNode.equals(parent)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasCycleDirected(String node, Map<String, List<String>> adjList, Set<String> visited, Set<String> recStack) {
        if (recStack.contains(node)) 
            return true;

        visited.add(node);
        recStack.add(node);

        for (String nextNode : adjList.get(node)) 
            if (hasCycleDirected(nextNode, adjList, visited, recStack)) 
                return true;

        recStack.remove(node);
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("Graph Cycle Checker\nLaurence Lesmoras");
        System.out.println("==================================================");

        System.out.print("Is the graph directed? (yes/no): ");
        String graphType = scanner.next();
        boolean isDirected = graphType.equalsIgnoreCase("yes");

        Map<String, List<String>> adjList = new HashMap<>();

        System.out.println("Enter the edges (e.g. \"ab\") (type 'end' to finish):");

        scanner.nextLine();

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("end")) {
                break;
            }
            if (input.length() != 2) {
                System.out.println("Invalid edge format. Please use \"ab\" format.");
                continue;
            }

            String u = input.substring(0,1);
            String v = input.substring(1);

            adjList.putIfAbsent(u, new ArrayList<>());
            adjList.putIfAbsent(v, new ArrayList<>());

            adjList.get(u).add(v);
            if (!isDirected) {
                adjList.get(v).add(u);
            }
        }

        boolean hasCycle = false;

        if (isDirected) {
            Set<String> visited = new HashSet<>();
            Set<String> recStack = new HashSet<>();
            for (String node : adjList.keySet()) {
                if (!visited.contains(node) && hasCycleDirected(node, adjList, visited, recStack)) {
                    hasCycle = true;
                    break;
                }
            }
        } else {
            Set<String> visited = new HashSet<>();
            for (String node : adjList.keySet()) {
                if (!visited.contains(node) && hasCycleUndirected(node, null, adjList, visited)) {
                    hasCycle = true;
                    break;
                }
            }
        }

        if (hasCycle) {
            System.out.println("The graph has a cycle.");
        } else {
            System.out.println("The graph does not have a cycle.");
        }

        scanner.close();
    }
}
