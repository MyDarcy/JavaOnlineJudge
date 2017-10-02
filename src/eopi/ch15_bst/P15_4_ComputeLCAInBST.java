package eopi.ch15_bst;

/**
 * Author by darcy
 * Date on 17-10-1 下午10:22.
 * Description:
 * <p>
 * LCA: lowest common ancestor
 * <p>
 * Design an algorithm that takes as input a BST and two nodes, and returns the LCA
 * of the two nodes. For example, for the BST in Figure 15.1 on Page 255, and nodes C
 * and G, your algorithm should return B. Assume all keys are distinct. Nodes do not
 * have references to their parents.
 * <p>
 * Hint: Take advantage of the BST property
 */
public class P15_4_ComputeLCAInBST {

  /**
   * 四种情况:
   * • If the root's key is the same as that stored at s or at b, we are done—the root is
   * the LCA. (root的值跟s或者b的值相等.)
   * • If the key at s is smaller than the key at the root, and the key at b is greater than
   * the key at the root, the root is the LCA. (s.key < root.key < b.key)
   * • If the keys at s and b are both smaller than that at the root, the LCA must he in
   * the left subtree of the root. (root.key > s.key, b.key)
   * • If both keys are larger than that at the root, then the LCA must he in the right
   * subtree of the root. (root.key < s.key, b.key)
   * <p>
   * 时间复杂度O(H)
   *
   * @param root
   * @param s
   * @param b    这里s.key < b.key
   * @return
   */
  public static BSTNode<Integer> solution(BSTNode<Integer> root,
                                          BSTNode<Integer> s,
                                          BSTNode<Integer> b) {
    BSTNode<Integer> iter = root;
    while (iter.data < s.data || iter.data > b.data) {

      // 外层循环的第一个条件满足. 那么第二个条件一定满足, 因为传入参数的时候已经规定s.key < b.key
      // 那么进入右子树中.
      while (iter.data < s.data) {
        iter = iter.right;
      }

      // 外层循环的第一个条件不满足, 但是第二个条件满足.
      // 那么进入左子树.
      while (iter.data > b.data) {
        iter = iter.left;
      }
    }

    return iter;
  }

  public static void main(String[] args) {
    BSTNode<Integer> s = new BSTNode<>(11);
    BSTNode<Integer> b = new BSTNode<>(23);
    BSTNode<Integer> result = solution(BSTNode.HEAD, s, b);

    System.out.println(result.data);
  }

}
