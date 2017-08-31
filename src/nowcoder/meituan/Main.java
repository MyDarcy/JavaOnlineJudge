package nowcoder.meituan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by hzq19
 * Date on 2017/8/31 19:31.
 * Description:
 *
 * 分层的结构，
 * 1个节点
 * 2个节点
 * 3个节点
 * ...
 *
 *
 *
 */
public class Main {

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();

        list.add(Arrays.asList(    1));
        list.add(Arrays.asList(   2, 1));
        list.add(Arrays.asList(  1, 2, 1));
        list.add(Arrays.asList( 2, 1, 2, 10));
        list.add(Arrays.asList(2 ,1 , 1, 2, 1));

        int result = solution(list);
        System.out.println(result);

    }

    private static int solution(List<List<Integer>> list) {

        if (list == null || list.size() == 0) {
            return 0;
        }

        // 按照1...n初始化每一层的值。
        List<List<Integer>> result = new ArrayList<>();
        /*for (int i = 0; i < list.size(); i++) {
            List<Integer> level = new ArrayList<>();
            Collections.fill(level, 0);


        }*/

        // 第0层的结果
        // result代表缓存结果。
        result.add(list.get(0));

        // 迭代第一层到第n层。
        for (int i = 1; i < list.size(); i++) {

            int levelSize = list.get(i).size();

            List<Integer> currentLevleResult = new ArrayList<>();

            // 当前层目前还没有值。
            for (int j = 0; j < levelSize; j++) {
                // 每一层的第一个节点只能是上一层的第一个节点的和+当前第一个节点的值。
                // 其他节点则有两种可能
                if (j == 0) {
                    currentLevleResult.add(result.get(i - 1).get(0) + list.get(i).get(0));

                    // 当前层的最后一个节点
                } else if (j == i) {
                    currentLevleResult.add(result.get(i - 1).get(j - 1) + list.get(i).get(j));

                    // 当前层的非最后一个节点
                } else {
                    // 从上一层到这一层两条路径。
                    // 选择累加和最小的。
                    currentLevleResult.add(
                            Math.min(result.get(i - 1).get(j - 1) + list.get(i).get(j),
                                    result.get(i - 1).get(j) + list.get(i).get(j)));
                }
            }
            result.add(currentLevleResult);
        }

        Integer min = result.get(result.size() - 1).stream().min(Integer::compareTo).get();
        return min;
    }



}
