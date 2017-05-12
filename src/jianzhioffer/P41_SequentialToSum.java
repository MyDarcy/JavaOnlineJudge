package jianzhioffer;

import java.util.ArrayList;

/**
 * Created by darcy
 * 2017/5/13--0:16
 * Description:
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,
 * 他马上就写出了正确答案是100。但是他并不满足于此,
 * 他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
 * 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 *
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 */
public class P41_SequentialToSum {

    /**
     * first 到 last范围内的值相加和sum进行比较;
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        if (sum <= 0) {
            return null;
        }

        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        int first = 1;
        int last = 2;
        int middle = sum / 2;
        int result = first + last;
        while (first <= middle && first < last) {
            if (result == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = first; i <= last; i++) {
                    list.add(i);
                }

                lists.add(list);
                /*// 这里不能同时first++ 和last++;
                last++;
                result += last;*/

                // 注意前后值都加1后整个序列的和是加了 number+1的;
                first++;
                last++;
                result += last - first + 1;
            } else if (result < sum) {
                last++;
                result += last;
            } else {
                first++;
                result = result - first + 1;
            }
        }

        return lists;
    }

    public ArrayList<ArrayList<Integer>> FindContinuousSequence2(int sum) {
        if (sum <= 0) {
            return null;
        }

        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        int first = 1;
        int last = 2;
        int middle = sum / 2;
        int result = first + last;
        while (first <= middle) {
            if (result == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = first; i <= last; i++) {
                    list.add(i);
                }
                lists.add(list);
            }

            while (result > sum && first < middle) {
                result -= first;
                first++;
                if (result == sum) {
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int i = first; i <= last; i++) {
                        list.add(i);
                    }
                    lists.add(list);
                }
            }
            last++;
            result += last;
        }
        return lists;
    }

    public static void main(String[] args) {
        P41_SequentialToSum demo = new P41_SequentialToSum();
        ArrayList<ArrayList<Integer>> lists = demo.FindContinuousSequence(15);
        if (lists == null) {
            return;
        }
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i));
        }
    }

}
