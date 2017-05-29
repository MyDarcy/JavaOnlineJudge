package jianzhioffer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by darcy
 * 2017/5/29--0:30
 * Description:
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 */
public class P62_SerializeDeserializeTree {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    /**
     * 前序遍历, 所有的叶子节点都认为是有null, null的左右节点。
     * @param root
     * @return
     */
    String Serialize(TreeNode root) {
        if (root == null) {
            return "#,";
        }
        // 因为+= 底层也是使用StringBuilder来实现的，所以这里就不需要构建StringBuilder了。
        String result = root.val + ",";
        result += Serialize(root.left);
        result += Serialize(root.right);
        return result;
    }

    TreeNode Deserialize(String str) {
        String[] strArray = str.split(",");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < strArray.length; i++) {
            queue.add(strArray[i]);
        }
        return preOrder(queue);
    }

    /**
     * 反序列化也是使用前序遍历，
     * @param queue
     * @return
     */
    private TreeNode preOrder(Queue<String> queue) {
        // 空树也是有元素的。
        String element = queue.remove();
        if (element.equals("#")) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.valueOf(element));
        head.left = preOrder(queue);
        head.right = preOrder(queue);
        return head;
    }

    public static void main(String[] args) {
        String str = "1, 2, 3,";
        String[] strArray = str.split(",");
        System.out.println(strArray.length);

    }

    // solution2
    private int index = -1;
    String Serialize2(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            sb.append("#,");
            return sb.toString();
        }
        sb.append(root.val + ",");
        sb.append(Serialize2(root.left));
        sb.append(Serialize2(root.right));
        return sb.toString();
    }

    TreeNode Deserialize2(String str) {
        if (str == null) {
            return null;
        }
        String[] strArray = str.split(",");
        return Deserialize2((strArray));
    }

    private TreeNode Deserialize2(String[] strArray) {
        index++;
        TreeNode node = null;
        if (!strArray[index].equals("#")) {
            node = new TreeNode(Integer.valueOf(strArray[index]));
            node.left = Deserialize2(strArray);
            node.right = Deserialize2(strArray);
        }
        return node;
    }
}
