package eopi.ch10_binarytree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author by darcy
 * Date on 17-9-22 下午2:57.
 * Description:
 */
public class P10_12_ReconstructTree {

  public static BinaryTreeNode<Integer> binaryTreeFromPreorderlnorder(
      List<Integer> preorder, List<Integer> inorder) {
    Map<Integer, Integer> nodeToInorderIdx = new HashMap<Integer, Integer>();

    // 中序结果中值与index的映射.
    for (int i = 0; i < inorder.size(); ++i) {
      nodeToInorderIdx.put(inorder.get(i), i);
    }
    return binaryTreeFromPreorderInorderHelper(
        preorder, 0, preorder.size(), 0, inorder.size(), nodeToInorderIdx);
  }

  // Builds the subtree with preorder.subList(preorderStart , preorderEnd) and
  // inorder.subList(inorderStart , inorderEnd).
  private static BinaryTreeNode<Integer> binaryTreeFromPreorderInorderHelper(
      List<Integer> preorder, int preorderStart, int preorderEnd,
      int inorderStart, int inorderEnd,
      Map<Integer, Integer> nodeToInorderldx) {
    if (preorderEnd <= preorderStart || inorderEnd <= inorderStart) {
      return null;
    }
    int rootInOrderIndex = nodeToInorderldx.get(preorder.get(preorderStart));
    int leftSubtreeSize = rootInOrderIndex - inorderStart;
    return new BinaryTreeNode<>(
        preorder.get(preorderStart),
        // Recursively builds the left subtree.
        binaryTreeFromPreorderInorderHelper(
            preorder, preorderStart + 1, preorderStart + 1 + leftSubtreeSize,
            inorderStart, rootInOrderIndex, nodeToInorderldx),
        // Recursively builds the right subtree.
        binaryTreeFromPreorderInorderHelper(
            preorder, preorderStart + 1 + leftSubtreeSize, preorderEnd,
            rootInOrderIndex + 1, inorderEnd, nodeToInorderldx));
  }
}
