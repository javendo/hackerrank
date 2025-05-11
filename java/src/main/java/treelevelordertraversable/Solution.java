package treelevelordertraversable;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;
import java.util.Scanner;

class Node {
  Node left;
  Node right;
  int data;

  Node(int data) {
    this.data = data;
    left = null;
    right = null;
  }

  public String toString() {
    return "Node(" + left + ", " + right + ", " + data + ")";
  }
}

public class Solution {

  public static StringBuffer levelOrder(Queue<Node> q) {
    StringBuffer sb = new StringBuffer();
    while (q.size() > 0) {
      Node n = q.poll();
      sb.append(" ").append(n.data);
      Optional.ofNullable(n.left).ifPresent(e -> q.offer(e));
      Optional.ofNullable(n.right).ifPresent(e -> q.offer(e));
    }
    if (sb.length() > 0) sb.deleteCharAt(0);
    return sb;
  }

  public static void levelOrder(Node root) {
    Queue<Node> q = new ArrayDeque<Node>();
    Optional.ofNullable(root).ifPresent(e -> {
        q.offer(root);
      });
    System.out.println(levelOrder(q).toString());
  }

  public static Node insert(Node root, int data) {
    if(root == null) {
      return new Node(data);
    } else {
      Node cur;
      if(data <= root.data) {
        cur = insert(root.left, data);
        root.left = cur;
      } else {
        cur = insert(root.right, data);
        root.right = cur;
      }
      return root;
    }
  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int t = scan.nextInt();
    Node root = null;
    while(t-- > 0) {
      int data = scan.nextInt();
      root = insert(root, data);
    }
    scan.close();
    levelOrder(root);
  }
}
