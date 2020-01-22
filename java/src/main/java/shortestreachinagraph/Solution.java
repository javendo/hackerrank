package shortestreachinagraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
  public static class Graph {
    private final List<List<Integer>> edges;
    private final int size;
    private static final int WEIGHT = 6;

    public Graph(int size) {
      this.size = size;
      this.edges = new ArrayList<List<Integer>>(size);
      for (int i = 0; i < size; i++) {
        this.edges.add(new ArrayList<Integer>());
      }
    }

    public void addDirectionalEdge(int origin, int destination) {
      edges.get(origin).add(destination);
    }

    public void addEdge(int first, int second) {
      addDirectionalEdge(first, second);
      addDirectionalEdge(second, first);
    }

    private void shortestReach(int weight, int[] distances, List<Integer> e) {
      List<Integer> acc = new ArrayList<>();
      e.stream().forEach(i -> {
        if (distances[i] == -1) {
          distances[i] = weight;
          acc.addAll(edges.get(i));
        }
      });
      if (acc.size() > 0) shortestReach(weight + WEIGHT, distances, acc);
    }

    public int[] shortestReach(int startId) {
      int[] distances = new int[size];
      for (int i = 0; i < distances.length; i++) {
        distances[i] = -1;
      }
      shortestReach(WEIGHT, distances, edges.get(startId));
      return distances;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int queries = scanner.nextInt();

    for (int t = 0; t < queries; t++) {

      // Create a graph of size n where each edge weight is 6:
      Graph graph = new Graph(scanner.nextInt());
      int m = scanner.nextInt();

      // read and set edges
      for (int i = 0; i < m; i++) {
        int u = scanner.nextInt() - 1;
        int v = scanner.nextInt() - 1;

        // add each edge to the graph
        graph.addEdge(u, v);
      }

      // Find shortest reach from node s
      int startId = scanner.nextInt() - 1;
      int[] distances = graph.shortestReach(startId);
      boolean space = false;

      for (int i = 0; i < distances.length; i++) {
        if (i != startId) {
          if (space) System.out.print(" ");
          System.out.print(distances[i]);
          space = true;
        }
      }
      System.out.println();
    }

    scanner.close();
  }
}
