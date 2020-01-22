package findtherunningmedian;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

  private static double[] runningMedian(int[] a) {
    double[] result = new double[a.length];
    PriorityQueue<Integer> higher = new PriorityQueue<>();
    PriorityQueue<Integer> lower = new PriorityQueue<>(new Comparator<Integer>() {
        public int compare(Integer arg0, Integer arg1) {
          return -1 * arg0.compareTo(arg1);
        }
    });
    if (a.length > 0) {
      result[0] = a[0];
      Queue<Integer> median = new ArrayDeque<>(1);
      median.add(a[0]);
      for (int i = 1; i < a.length; i++) {
        int number = a[i];
        if (number >= result[i - 1]) higher.add(number); else lower.add(number);
        if (higher.size() == lower.size() + median.size()) {
          lower.add(median.poll());
          result[i] = (lower.peek() + higher.peek()) / 2.0;
        }
        else if (lower.size() == higher.size() + median.size()) {
          higher.add(median.poll());
          result[i] = (lower.peek() + higher.peek()) / 2.0;
        }
        else if (lower.size() > higher.size()) {
          median.add(lower.poll());
          result[i] = median.peek();
        }
        else {
          median.add(higher.poll());
          result[i] = median.peek();
        }
      }
    }
    return result;
  }

  public static void main(String[] args) throws IOException {
    DecimalFormat df = new DecimalFormat("#.#");
    Scanner scan = new Scanner(System.in);
    int aCount = Integer.parseInt(scan.nextLine().trim());
    int[] a = new int[aCount];
    for (int i = 0; i < aCount; i++) {
      int item = Integer.parseInt(scan.nextLine().trim());
      a[i] = item;
    }
    double[] result = runningMedian(a);
    for (int i = 0; i < result.length; i++) {
      System.out.println(df.format(result[i]));
    }
    scan.close();
  }

}
