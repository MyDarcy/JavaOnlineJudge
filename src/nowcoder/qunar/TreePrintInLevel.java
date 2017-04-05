package nowcoder.qunar;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by csqiang on 2017/4/1.
 *
 * @Author mr.darcy
 * Description:
 */

class Node {
    String val;
    Node left;
    Node right;

    Node(String val) {
        this.val = val;
    }
}

public class TreePrintInLevel {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Integer number = Integer.parseInt(input.nextLine().trim());
        String[] pre = input.nextLine().trim().split("\\s+");
        String[] in = input.nextLine().trim().split("\\s+");

        Node root = rebuildTree(pre, 0, pre.length - 1, in, 0, in.length - 1);

        Deque<Node> queue = new LinkedList<>();
        queue.addLast(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Node node = queue.pollFirst();
            sb.append(node.val + " ");
            if (node.left != null) {
                queue.addLast(node.left);
            }
            if (node.right != null) {
                queue.addLast(node.right);
            }
        }
        System.out.println(sb.toString().trim());
    }

    private static Node rebuildTree(String[] pre, int fromPre, int toPre, String[] in, int fromIn, int toIn) {
        if (fromPre > toPre || fromIn > toIn) {
            return null;
        }

        String rootValue = pre[fromPre];
        Node node = new Node(rootValue);
        node.left = node.right = null;

        int rootValueIndex = fromIn;
        while (true) {
            if (rootValue.equals(in[rootValueIndex])) {
                break;
            }
            rootValueIndex++;
        }

        int leftSubTreeNodeNumber = rootValueIndex - fromIn;

        node.left = rebuildTree(pre, fromPre + 1, fromPre + leftSubTreeNodeNumber,
                                    in, fromIn, fromIn + leftSubTreeNodeNumber -1);

        node.right = rebuildTree(pre, fromPre + leftSubTreeNodeNumber + 1, toPre,
                                    in, fromIn + leftSubTreeNodeNumber + 1, toIn);

        return node;

    }
}

/*
7
1 2 4 5 3 6 7
4 2 5 1 6 3 7

5
1 2 4 5 3
4 2 5 1 3
 */
