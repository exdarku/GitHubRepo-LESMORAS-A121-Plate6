/* Laurence Lesmoras - A121
 * 
 * Problem 4 - Discrete Structure 2
 * Write a java program, given the pair of vertex associated to the edges of an undirected graph, it will output the degree of 
 * vertex.
 */

import java.util.*;

public class Problem4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("Degree of vertices\nLaurence Lesmoras");
        System.out.println("==================================================");

        Map<String, Integer> degreeMap = new HashMap<>();
        System.out.println("Enter the edges (e.g. \"ab\") (type 'end' to finish):");

        for (String input; !(input = sc.nextLine()).equalsIgnoreCase("end"); ) {
            if (input.length() != 2) {
                System.out.println("Invalid edge format. Please use \"ab\" format.");
                continue;
            }
            degreeMap.put(input.substring(0, 1), degreeMap.getOrDefault(input.substring(0, 1), 0) + 1);
            degreeMap.put(input.substring(1), degreeMap.getOrDefault(input.substring(1), 0) + 1);
        }

        degreeMap.forEach((vertex, degree) -> System.out.println("deg(" + vertex + ") = " + degree));
        sc.close();
    }
}
