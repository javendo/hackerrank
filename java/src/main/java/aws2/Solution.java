package aws2;

// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

// CLASS BEGINS, THIS CLASS IS REQUIRED
class Solution {
  // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
  int minimumDays(int rows, int columns, int[][] grid, int numOfOnes, int acc) {
    int allUpdated = rows * columns;
    if (numOfOnes == allUpdated) {
      return acc;
    }
    else {
      int numOfOnesUpdated = 0;
      int[][] updated = new int[rows][columns];
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
          if (grid[i][j] == 1) updated[i][j] = 1;
          if (grid[i][j] == 1) {
            numOfOnesUpdated++;
            if (j != columns - 1) updated[i][j + 1] = 1;
            if (j != 0) updated[i][j - 1] = 1;
            if (i != rows - 1) updated[i + 1][j] = 1;
            if (i != 0) updated[i - 1][j] = 1;
          }
        }
      }
      return minimumDays(rows, columns, updated, numOfOnesUpdated, acc + 1);
    }
  }

  int minimumDays(int rows, int columns, List<List<Integer>> grid) {
    int[][] aGrid = new int[rows][columns];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        aGrid[i][j] = grid.get(i).get(j);
      }
    }
    return minimumDays(rows, columns, aGrid, 0, -1);
  }

  // METHOD SIGNATURE ENDS

  public static void main(String[] args) {
    Solution s = new Solution();
    List<List<Integer>> servers = new ArrayList<>();
    servers.add(Arrays.asList(new Integer[] {0, 1, 1, 0, 1}));
    servers.add(Arrays.asList(new Integer[] {0, 1, 0, 1, 0}));
    servers.add(Arrays.asList(new Integer[] {0, 0, 0, 0, 1}));
    servers.add(Arrays.asList(new Integer[] {0, 1, 0, 0, 0}));
    int d = s.minimumDays(4, 5, servers);
    System.out.println(d);
  }

}
