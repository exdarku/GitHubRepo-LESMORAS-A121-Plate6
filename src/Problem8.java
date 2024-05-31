/* Laurence Lesmoras - A121
 * 
 * Problem 8 - Discrete Structure 2
 * Write a Java program that checks whether two graphs are isomorphic or not, given a set of vertices.
 */

import java.util.*;

public class Problem8 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("Graph Isomorphism Checker\nLaurence Lesmoras");
        System.out.println("==================================================");

        System.out.print("Enter the number of vertices in the graphs: ");
        int numVertices = scanner.nextInt();
        scanner.nextLine();

        int[][] adjacencyMatrix1 = new int[numVertices][numVertices];
        int[][] adjacencyMatrix2 = new int[numVertices][numVertices];

        System.out.println("Enter edges for Graph 1 (e.g., \"ab\"). (Type 'end' to finish):");
        inputEdges(scanner, adjacencyMatrix1, numVertices);

        System.out.println("Enter edges for Graph 2 (e.g., \"ab\"). (Type 'end' to finish):");
        inputEdges(scanner, adjacencyMatrix2, numVertices);

        if (areIsomorphic(adjacencyMatrix1, adjacencyMatrix2, numVertices)) {
            System.out.println("The graphs are isomorphic.");
        } else {
            System.out.println("The graphs are not isomorphic.");
        }
        scanner.close();
    }

    private static void inputEdges(Scanner scanner, int[][] adjacencyMatrix, int numVertices) {
        Map<String, Integer> vertexIndexMap = new HashMap<>();
        int index = 0;
    
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("end")) break;
            if (input.length() != 2) {
                System.out.println("Invalid input. Please enter edges in the format \"ab\".");
                continue;
            }
            String u = input.substring(0, 1), v = input.substring(1);
    
            if (!vertexIndexMap.containsKey(u)) {
                vertexIndexMap.put(u, index++);
            }
            if (!vertexIndexMap.containsKey(v)) {
                vertexIndexMap.put(v, index++);
            }
    
            int uIndex = vertexIndexMap.get(u);
            int vIndex = vertexIndexMap.get(v);
    
            if(uIndex >= numVertices || vIndex >= numVertices){
                System.out.println("Vertex index exceeds the number of vertices. Please enter valid edges.");
                continue;
            }
    
            adjacencyMatrix[uIndex][vIndex]++;
            adjacencyMatrix[vIndex][uIndex]++;
        }
    }
    

    private static boolean areIsomorphic(int[][] adjacencyMatrix1, int[][] adjacencyMatrix2, int numVertices) {
        int[] permutation = new int[numVertices];
        for (int i = 0; i < numVertices; i++) permutation[i] = i;

        do {
            if (checkPermutation(adjacencyMatrix1, adjacencyMatrix2, permutation, numVertices)) return true;
        } while (nextPermutation(permutation));

        return false;
    }

    private static boolean checkPermutation(int[][] adjacencyMatrix1, int[][] adjacencyMatrix2, int[] permutation, int numVertices) {
        for (int i = 0; i < numVertices; i++)
            for (int j = 0; j < numVertices; j++)
                if (adjacencyMatrix1[i][j] != adjacencyMatrix2[permutation[i]][permutation[j]])
                    return false;
        return true;
    }

    private static boolean nextPermutation(int[] permutation) {
        int n = permutation.length, i = n - 2;
        while (i >= 0 && permutation[i] >= permutation[i + 1]) i--;
        if (i < 0) return false;
        int j = n - 1;
        while (permutation[j] <= permutation[i]) j--;
        swap(permutation, i, j);
        reverse(permutation, i + 1, n - 1);
        return true;
    }

    private static void swap(int[] permutation, int i, int j) {
        int temp = permutation[i];
        permutation[i] = permutation[j];
        permutation[j] = temp;
    }

    private static void reverse(int[] permutation, int start, int end) {
        while (start < end) swap(permutation, start++, end--);
    }
}
