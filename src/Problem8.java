/* Laurence Lesmoras - A121
 * 
 * Problem 8 - Discrete Structure 2
 * Write a Java program that checks whether two graphs are isomorphic or not, given a set of vertices.
 */

import java.util.*;

public class Problem8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===================================================================");
        System.out.println("Graph Isomorphism Checker\nLaurence Lesmoras");
        System.out.println("===================================================================");
        System.out.print("Enter number of vertices: ");
        int vertices = scanner.nextInt(); scanner.nextLine();
        
        int[][] matrix1 = new int[vertices][vertices], matrix2 = new int[vertices][vertices];
        Map<String, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
        
        inputEdges(scanner, matrix1, map1, "Graph 1");
        inputEdges(scanner, matrix2, map2, "Graph 2");

        if (map1.size() != vertices || map2.size() != vertices) {
            System.out.println("Vertex count mismatch.");
        } else {
            System.out.println(areIsomorphic(matrix1, matrix2, vertices) ? "Graphs are isomorphic." : "Graphs are not isomorphic.");
        }

        scanner.close();
    }

    private static void inputEdges(Scanner scanner, int[][] matrix, Map<String, Integer> vertexMap, String graphName) {
        System.out.println("Enter edges for " + graphName + " (\"ab\" format, 'end' to finish):");
        int index = 0;
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("end")) break;
            if (input.length() != 2) {
                System.out.println("Invalid input. Use \"ab\" format.");
                continue;
            }
            String u = input.substring(0, 1), v = input.substring(1);
            vertexMap.putIfAbsent(u, index++);
            vertexMap.putIfAbsent(v, index++);
            int uIdx = vertexMap.get(u), vIdx = vertexMap.get(v);
            matrix[uIdx][vIdx]++; matrix[vIdx][uIdx]++;
        }
    }

    private static boolean areIsomorphic(int[][] matrix1, int[][] matrix2, int vertices) {
        int[] permutation = new int[vertices];
        for (int i = 0; i < vertices; i++) permutation[i] = i;
        do {
            if (checkPermutation(matrix1, matrix2, permutation, vertices)) return true;
        } while (nextPermutation(permutation));
        return false;
    }

    private static boolean checkPermutation(int[][] matrix1, int[][] matrix2, int[] permutation, int vertices) {
        for (int i = 0; i < vertices; i++)
            for (int j = 0; j < vertices; j++)
                if (matrix1[i][j] != matrix2[permutation[i]][permutation[j]]) return false;
        return true;
    }

    private static boolean nextPermutation(int[] permutation) {
        int length = permutation.length, i = length - 2, j = length - 1;
        while (i >= 0 && permutation[i] >= permutation[i + 1]) i--;
        if (i < 0) return false;
        while (permutation[j] <= permutation[i]) j--;
        swap(permutation, i, j);
        reverse(permutation, i + 1, length - 1);
        return true;
    }

    private static void swap(int[] permutation, int i, int j) {
        int temp = permutation[i]; permutation[i] = permutation[j]; permutation[j] = temp;
    }

    private static void reverse(int[] permutation, int start, int end) {
        while (start < end) swap(permutation, start++, end--);
    }
}
