package contacts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {

  public static class ChainOfChar {
    public final Map<Character, ChainOfChar> next = new HashMap<>();
    public final char character;
    public int weight = 0;
    public ChainOfChar(char character) {
      this(character, 1);
    }
    public ChainOfChar(char character, int weight) {
      this.character = character;
      this.weight = weight;
    }
  }

  private static ChainOfChar root = new ChainOfChar((char)0, 0);
  
  private static void add(String s) {
    ChainOfChar previous = root;
    for (Character c : s.toCharArray()) {
      if (previous.next.containsKey(c)) {
        previous = previous.next.get(c);
        previous.weight++;
      }
      else {
        ChainOfChar actual = new ChainOfChar(c);
        previous.next.put(c, actual);
        previous = actual;
      }
    }
  }

  private static int find(String s) {
    ChainOfChar previous = root;
    for (Character c : s.toCharArray()) {
      previous = previous.next.get(c);
      if (previous == null) return 0;
    }
    return previous.weight;
  }

  static List<Integer> contacts(String[][] queries) {
    List<Integer> l = new ArrayList<>();
    for (String[] line : queries) {
      if ("add".equals(line[0])) {
        add(line[1]);
      }
      else if ("find".equals(line[0])) {
        l.add(find(line[1]));
      }
    }
    return l;
  }

  public static void main(String[] args) throws IOException {
    Scanner scan = new Scanner(System.in);
    int rows = Integer.valueOf(scan.nextLine());
    String[][] queries = new String[rows][2];
    for (int i = 0; i < rows; i++) {
      String line = scan.nextLine();
      String[] row = line.split(" ");
      for (int j = 0; j < 2; j++) {
        String item = row[j];
        queries[i][j] = item;
      }
    }
    contacts(queries).stream().forEach(System.out::println);
    scan.close();
  }
}
