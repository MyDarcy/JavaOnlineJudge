package jianzhioffer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darcy
 * 2017/5/3--0:13
 * Description:
 * 字符串的组合问题:
 * a, b, c(给定) ==> a, b, c, ab, bc, ac, abc (组合结果)
 */
public class P28_StringCombination {

    private List<Character> list = new ArrayList<>();

    /**
     * 假设我们想在长度为n的字符串中求m个字符的组合。
     * 我们先从头扫描字符串的第一个字符。针对第一个字符，我们有两种选择：
     * 第一是把这个字符放到组合中去，接下来我们需要在剩下的n-1个字符中选取m-1个字符；
     * 第二是不把这个字符放到组合中去，接下来我们需要在剩下的n-1个字符中选择m个字符。
     * 这两种选择都很容易用递归实现。
     *
     * @param chars
     */
    public void solution(char[] chars) {
        for (int i = 1; i <= chars.length; i++) {
            solution(chars, 0, i);
        }
    }

    /**
     * @param chars
     * @param index  chars数组中从index处的字符中开始选择;
     * @param number 选择number个;
     */
    private void solution(char[] chars, int index, int number) {
        if (number == 0) {
            /*list.stream().forEach(System.out::print);*/
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i));
            }
            System.out.println();

            // 结束了;
            return;
        }

        if (index == chars.length) {
            return;
        }

        // 指定字符作为组合的一部分;
        list.add(chars[index]);
        // 从m-1个中找n-1个;
        solution(chars, index + 1, number - 1);
        list.remove(list.size() - 1);

        // 从m-1个中找n个;
        solution(chars, index + 1, number);

    }

    public static void main(String[] args) {
        char[] chars = "abc".toCharArray();
        P28_StringCombination demo = new P28_StringCombination();
        demo.solution(chars);

        System.out.println();

        demo.subset(5);
    }

    /**
     * 用位运算来实现求组合
     * http://blog.csdn.net/hackbuteer1/article/details/7462447
     */

    private char str[] = "abcde".toCharArray();


    void print_subset(int n, int s) {
        System.out.print("{");
        for (int i = 0; i < n; ++i) {
            if ((s & (1 << i)) != 0)        // 判断s的二进制中哪些位为1，即代表取某一位
                System.out.print(str[i]);   // 或者a[i]
        }
        System.out.print("}\n");
    }

    void subset(int n) {
        System.out.println(1 << n);

        // i=0的时候就是为空的输出;
        for (int i = 1; i < (1 << n); ++i) {

            // i的取值代表的的是选择哪几个字符;
            print_subset(n, i);
        }
    }

}
