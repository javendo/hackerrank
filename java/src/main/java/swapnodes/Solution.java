package swapnodes;

import java.io.IOException;
import java.util.Scanner;

public class Solution {

  private static int process(int[] r, int ir, int[][] tree, int i) {
    if (tree[i - 1][0] != -1) ir = process(r, ir, tree, tree[i - 1][0]);
    r[ir++] = i;
    if (tree[i - 1][1] != -1) ir = process(r, ir, tree, tree[i - 1][1]);
    return ir;
  }

  private static int[] print(int[][] tree) {
    int[] r = new int[tree.length];
    int ir = 0;
    ir = process(r, ir, tree, 1);
    return r;
  }

  private static void traverse(int[][] tree, int node, int depth, int factor) {
    if (depth % factor == 0) {
      int temp = tree[node - 1][0];
      tree[node - 1][0] = tree[node - 1][1];
      tree[node - 1][1] = temp;
    }
    if (tree[node - 1][0] != -1) traverse(tree, tree[node - 1][0], depth + 1, factor);
    if (tree[node - 1][1] != -1) traverse(tree, tree[node - 1][1], depth + 1, factor);
  }

  private static int[][] swapNodes(int[][] tree, int[] qrys) {
    int[][] result = new int[qrys.length][];
    for (int i = 0; i < qrys.length; i++) {
      traverse(tree, 1, 1, qrys[i]);
      result[i] = print(tree);
    }
    return result;
  }

  public static void main(String[] args) throws IOException {
    Scanner scan = new Scanner(System.in);
    int n = Integer.parseInt(scan.nextLine().trim());
    int[][] idx = new int[n][2];
    for (int i = 0; i < n; i++) {
      String[] rows = scan.nextLine().split(" ");
      for (int j = 0; j < 2; j++) {
        idx[i][j] = Integer.parseInt(rows[j].trim());
      }
    }
    int nq = Integer.parseInt(scan.nextLine().trim());
    int[] qrys = new int[nq];
    for (int i = 0; i < nq; i++) {
      qrys[i] = Integer.parseInt(scan.nextLine().trim());
    }
    int[][] result = swapNodes(idx, qrys);
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[i].length; j++) {
        System.out.print(String.valueOf(result[i][j]));
        if (j != result[i].length - 1) {
          System.out.print(" ");
        }
      }
      if (i != result.length - 1) {
        System.out.println();
      }
    }
    scan.close();
  }
}
