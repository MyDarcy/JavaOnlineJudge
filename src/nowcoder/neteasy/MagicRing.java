package nowcoder.neteasy;

import java.util.Scanner;

/**
 * Created by csqiang on 2017/3/25.
 *
 * @Author mr.darcy
 * Description:
 *
小易拥有一个拥有魔力的手环上面有n个数字(构成一个环),当这个魔力手环每次使用魔力的时候就会发生一种奇特的变化：每个数字会变成自己跟后面一个数字的和(最后一个数字的后面一个数字是第一个),一旦某个位置的数字大于等于100就马上对100取模(比如某个位置变为103,就会自动变为3).现在给出这个魔力手环的构成，请你计算出使用k次魔力之后魔力手环的状态。
输入描述:
输入数据包括两行：
第一行为两个整数n(2 ≤ n ≤ 50)和k(1 ≤ k ≤ 2000000000),以空格分隔
第二行为魔力手环初始的n个数，以空格分隔。范围都在0至99.
 */
public class MagicRing {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String firstLine = input.nextLine().trim();

        String secondLine = input.nextLine().trim();

        int n = Integer.parseInt(firstLine.split("\\s+")[0]);
        int k = Integer.parseInt(firstLine.split("\\s+")[1]);

        String[] stringArray = secondLine.split("\\s+");
        int[] intArray = new int[n];
        for (int i = 0; i < n; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }

        // k是0到50的范围，n是 20亿的范围，考虑溢出;

        int[] axiliaryArray = new int[n];
        for (int i = 0; i < n; i++) {
            if (i != n - 1) {
                axiliaryArray[i] = intArray[i] + k * intArray[i + 1];
            } else {
                axiliaryArray[i] = intArray[i] + k * intArray[0];
            }
        }

        for (int i = 0; i < n; i++) {
            axiliaryArray[i] = axiliaryArray[i] - 100 * ((int) (axiliaryArray[i] / 100));
        }

        for (int i = 0; i < n; i++) {
            System.out.print(axiliaryArray[i] + " ");
        }

    }
}
