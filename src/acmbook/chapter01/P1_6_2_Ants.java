package acmbook.chapter01;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author hezhiqiang05
 * @version 1.0
 * @created 2019/1/3-下午11:26
 *
 * 问题描述: n只蚂蚁已每秒1cm的速度在长为Lcm的竿子上爬行；当蚂蚁爬到竿子的端点时就会掉落。由于竿子
 * 太细，当两只蚂蚁相遇的时候，他们不能交错通过，只能各自反方向的爬回去。对于每只蚂蚁，我们知道他距离竿子左端的
 * 距离xi, 但是不知道他当前的朝向。请确定所有蚂蚁落下竿子所需的最短和最长的时间；
 *
 * 思路：当两只蚂蚁相遇时，当他们保持原样交错而过继续前进也不会有任何问题，这样看来，可以认为每只蚂蚁都是独立运动的；
 * 要求最短最长时间，都是只需要求到端点的最大、最小距离即可。
 *
 */
public class P1_6_2_Ants {

    public static void solution(int L, int[] positions) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < positions.length; i++) {
            min = Integer.min(min, Integer.min(positions[i], L - positions[i]));
            max = Integer.max(max, Integer.max(positions[i], L - positions[i]));
        }
        System.out.println("array statistics:" + Arrays.stream(positions).boxed().collect(Collectors.summarizingInt(Integer::intValue)));
        System.out.println("min:" + min + ", max:" + max);
    }

    public static void main(String[] args) {
        Random random = ThreadLocalRandom.current();
        int size = 20;
        int[] positions = IntStream.generate(() -> random.nextInt(70) + 30)
                .limit(size)
                .toArray();
        System.out.println(Arrays.toString(positions));
        int L = 101;
        solution(L, positions);
    }

}
