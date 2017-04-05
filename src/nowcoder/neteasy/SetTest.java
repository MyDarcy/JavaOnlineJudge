package nowcoder.neteasy;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by csqiang on 2017/3/26.
 *
 * @Author mr.darcy
 * Description:
小易最近在数学课上学习到了集合的概念,集合有三个特征：1.确定性 2.互异性 3.无序性.
小易的老师给了小易这样一个集合：
S = { p/q | w ≤ p ≤ x, y ≤ q ≤ z }
需要根据给定的w，x，y，z,求出集合中一共有多少个元素。小易才学习了集合还解决不了这个复杂的问题,需要你来帮助他。
输入描述:
输入包括一行：
一共4个整数分别是w(1 ≤ w ≤ x)，x(1 ≤ x ≤ 100)，y(1 ≤ y ≤ z)，z(1 ≤ z ≤ 100).以空格分隔
 */
public class SetTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int w = input.nextInt();
        int x = input.nextInt();
        int y = input.nextInt();
        int z = input.nextInt();

        Set<String> set = new HashSet<>();
        for (int i = w; i <= x; i++) {
            for (int j = y; j <= z; j++) {
                String str = getReductString(i, j);
                if (!set.contains(str)) {
                    set.add(str);
                }
            }
        }
        System.out.println(set.size());
    }

    private static String getReductString(int i, int j) {
        int first = i;
        int second = j;
        int temp = 0;
        while (j != 0) {
            temp = i % j;
            i = j;
            j = temp;
        }
        // 退出i 就是最大公约数;
        return first / i + " " + second / i;
    }
}
