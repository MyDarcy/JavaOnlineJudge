package eopi.ch16_recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-8-28 上午9:58.
 * Description:
 * <p>
 * 求树的直径: 树中两个节点的最长距离.
 * <p>
 * <p>
 * The diameter of a tree is defined to be the length of a longest path in the tree.
 * Design an efficient algorithm to compute the diameter of a tree.
 * <p>
 * Hint: The longest path may or may not pass through the root.
 */

class TreeNode {
  List<Edge> edges = new ArrayList<>();
}

class Edge {
  public TreeNode root;
  public double length;

  public Edge(TreeNode root, double length) {
    this.root = root;
    this.length = length;
  }
}

class HeightAndDiameter {
  public Double height;
  public Double diameter;

  public HeightAndDiameter(Double height, Double diameter) {
    this.height = height;
    this.diameter = diameter;
  }
}

public class P12_TreeDiameter {


  /**
   * BFS算法找到diameter.
   *
   * BFS in a graph with n vertices and m edges has time complexity O(m+n). Therefore,
   * the brute-force algorithm has O(n(m+n)) = O(n^2) time complexity since the number of
   * edges in a tree is one less than the number of vertices
   *
   * @param root
   * @return
   */
  public static double bruteForceSolution(TreeNode root) {

    return 0;
  }

  /**
   * 分治算法.
   * 1. 如果最长路径不经过root,那么最长路径两端的两个节点一定在同一棵子树上.
   * 2. 如果最长路径经过root, 那么最长路径两端涉及到的两个节点一定是root左右子树上的节点.
   *
   * Consider a longest path in the tree. Either it passes through the root or it does not
   * pass through the root.
   * • If the longest path does not pass through the root, it must be entirely within one
   * of the subtrees. Therefore, in this case, the longest path length in the tree is the
   * maximum of the diameters of the subtrees.
   * • If the longest path does pass through the root, it must be between a pair of
   * nodes in distinct subtrees that are furthest from the root. The distance from the
   * root to the node in the ith subtree Ti that is furthest from it is fi = hi + li, where
   * hi is the height of Ti, and li is the length of the edge from the root to the root of
   * Ti.
   *
   *
   * Since the time spent at each node is proportional to the number of its children, the
   * time complexity is proportional to the size of the tree, i.e.,O(n)
   *
   * @param root
   * @return
   */
  public static double solution(TreeNode root) {
    return root == null ? diameterAndHeight(root).diameter : 0.0;
  }

  private static HeightAndDiameter diameterAndHeight(TreeNode root) {
    double diameter = Double.MIN_VALUE;
    // 存储最大的两个高.
    double[] heights = {0.0, 0.0};
    for (Edge e : root.edges) {
      HeightAndDiameter heightAndDiameter = diameterAndHeight(e.root);
      if (heightAndDiameter.height + e.length > heights[0]) {
        heights[1] = heights[0];
        heights[0] = heightAndDiameter.height + e.length;
      } else if (heightAndDiameter.height + e.length > heights[1]) {
        heights[1] = heightAndDiameter.height + e.length;
      }
      diameter = Math.max(diameter, heightAndDiameter.diameter);
    }

    return new HeightAndDiameter(heights[0], Math.max(diameter, heights[0] + heights[1]));
  }

}

/*
Variant: Consider a computer network organized as a rooted tree. A node can send a
message to only one child at a time, and it takes one second for the child to receive the
message. The root periodically receives a message from an external source. It needs
to send this message to all the nodes in the tree. The root has complete knowledge of
how the network is organized. Design an algorithm that computes the sequence of
transfers that minimizes the time taken to transfer a message from the root to all the
nodes in the tree.
 */
