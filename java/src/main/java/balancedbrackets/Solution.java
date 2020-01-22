package balancedbrackets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.Stack;

public class Solution {

  // Complete the isBalanced function below.
  static String isBalanced(String s) {
    String open = "([{";
    String close = ")]}";
    Stack<Character> stack = new Stack<>();
    for (Character c : s.toCharArray()) {
      if (open.indexOf(c.charValue()) != -1) {
        stack.push(c);
      }
      else {
        if (stack.size() < 1 || open.indexOf(stack.pop().charValue()) != close.indexOf(c.charValue())) {
          return "NO";
        }
      }
    }
    return stack.size() == 0 ? "YES" : "NO";
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    Scanner scan = new Scanner(System.in);
    int t = scan.nextInt();
    List<String> l = new ArrayList<>();
    while (t-- > 0) {
      String s = scanner.nextLine();
      l.add(isBalanced(s));
    }
    scan.close();
    l.forEach(System.out::println);
  }
}
