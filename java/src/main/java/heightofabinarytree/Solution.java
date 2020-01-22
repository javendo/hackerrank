package heightofabinarytree;

import java.util.Optional;
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
}

class Solution {
  private static int height(Node n, int h) {
    return Math.max(Optional.ofNullable(n.left).map(e -> height(e, h + 1)).orElse(h),
                    Optional.ofNullable(n.right).map(e -> height(e, h + 1)).orElse(h));
  }

  public static int height(Node root) {
    return height(root, 0);
  }

  public static Node insert(Node root, int data) {
    if (root == null) {
      return new Node(data);
    }
    else {
      Node cur;
      if (data <= root.data) {
        cur = insert(root.left, data);
        root.left = cur;
      }
      else {
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
    while (t-- > 0) {
      int data = scan.nextInt();
      root = insert(root, data);
    }
    scan.close();
    int height = height(root);
    System.out.println(height);
  }
}
