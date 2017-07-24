package ctci5th.chapter8.section7;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author by darcy
 * Date on 17-7-19 下午9:09.
 * Description:
 *
 * 有些数的素因子只有3, 5, 7 找出其中的第k个数.
 *
 */
public class P877_KthNumberWithinNumbersWithFacorOf3_5_7 {

    /**
     * 思路: 维护三个列表,每个列表都是3x, 5x, 7x的数,并且是按照递增序排列的,所以要找出当前最小值,比较两次即可.
     * 从取出的最小值value是从queue3中取出时, 其可以表示为3-suffix,需要将3 * 3-suffix, 5 * 3-suffix, 7 * 3-suffix
     * 分别入三个不同的队列. 如果是从queue5中取出的,那么其可以表示为5-suffix,那么需要将 5 * 5-suffix, 7 * 5-suffix
     * 入不同的队列中, 因为3 * 5-suffix的在之前已经处理过了, 它可以表示为 5 * 3-suffix的数,表示从queue3中取出来,*5；
     * 然后放入queue5中, 避免了将重复数据多次放入的问题. 如果是从queue7中取出来的最小值, 它可以表示为 7-suffix, 那么需要将
     * 7 * 7-suffix放入queue7中, 因为 3 * 7-suffix == 7 * 3-suffix已经放入过队列中,
     * 同理, 5 * 7-suffix也放入过队列中了(虽然不一定被处理了).
     *
     * 队列中维护最小值,同时避免重复放入的问题.
     *
     * @param k
     * @return
     */
    public static int getKthNumber(int k) {
        if (k < 0) {
            return 0;
        }

        int value = 0;
        Queue<Integer> queue3 = new LinkedList<>();
        Queue<Integer> queue5 = new LinkedList<>();
        Queue<Integer> queue7 = new LinkedList<>();
        queue3.add(1);

        for (int i = 0; i <= k; i++) {
            int v3 = queue3.size() > 0 ? queue3.peek() : Integer.MAX_VALUE;
            int v5 = queue5.size() > 0 ? queue5.peek() : Integer.MAX_VALUE;
            int v7 = queue7.size() > 0 ? queue7.peek() : Integer.MAX_VALUE;

            value = Math.min(v3, Math.min(v5, v7));
            if (value == v3) {
                queue3.remove();
                queue3.add(3 * value);
                queue5.add(5 * value);
            } else if (value == v5) {
                queue5.remove();
                queue5.add(5 * value);
            } else if (value == v7) {
                queue7.remove();
            }
            queue7.add(7 * value);
        }

        return value;
    }

    public static void main(String[] args) {
        for (int i = 1; i <100; i++) {
            System.out.println(getKthNumber(i));
        }
    }

}
