package nowcoder.toutiao;

import java.util.*;

/**
 * Created by hzq19
 * Date on 2017/8/22 20:22.
 * Description:
 */

class Node implements Comparable<Node> {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * 存在这样的目标节点
     * @param o
     * @return
     */
    @Override
    public int compareTo(Node o) {

        if (x < o.x && y < o.y) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return x + " "  + y;
    }
}


public class XY {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = Integer.parseInt(input.nextLine().trim());

        int index = 0;
        List<Node> list = new ArrayList<>(number);
        int x = 0;
        int y = 0;
        String[] strings = null;
        while (input.hasNextLine()) {
            strings = input.nextLine().trim().split("\\s+");
            x = Integer.parseInt(strings[0]);
            y  = Integer.parseInt(strings[1]);
            list.add(new Node(x, y));
        }

//        System.out.println(list);

        List<Node> result = new LinkedList<>();
        Node nodei = null;
        Node nodej = null;

        for (int i = 0; i < list.size(); i++) {
            nodei = list.get(i);

            int j = 0;
            for ( ;j < list.size(); j++) {
                nodej = list.get(j);
                //有不满足条件的节点, 后面的语句都不会再执行.
                if (nodei.compareTo(nodej) > 0) {
                    break;
                }
            }

            if (j == list.size()) {
                result.add(nodei);
            }
        }


        Collections.sort(result, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.x > o2.x) {
                    return 1;
                } else if (o1.x < o2.x) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).x + "\t" + result.get(i).y);
        }

    }
}

/*
 5
 1 2
 5 3
 4 6
 7 5
 9 0
 */