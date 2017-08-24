package nowcoder.toutiao;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/8/22 19:56.
 * Description:
 * 给定一个数组序列, 需要求选出一个区间, 使得该区间是所有区间中经过如下计算的值最大的一个：
 区间中的最小数 * 区间所有数的和
 最后程序输出经过计算后的最大值即可，不需要输出具体的区间。如给定序列  [6 2 1]则根据上述公式, 可得到所有可以选定各个区间的计算值:
 [6] = 6 * 6 = 36;
 [2] = 2 * 2 = 4;
 [1] = 1 * 1 = 1;
 [6,2] = 2 * 8 = 16;
 [2,1] = 1 * 3 = 3;
 [6, 2, 1] = 1 * 9 = 9;
 从上述计算可见选定区间 [6] ，计算值为 36， 则程序输出为 36。
 区间内的所有数字都在[0, 100]的范围内;
 */
public class RangeMax {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = Integer.parseInt(input.nextLine().trim());

        int[] array = new int[number];

        String lineNumber = input.nextLine();
        String[] numberStr = lineNumber.split("\\s+");
        for (int i = 0; i < numberStr.length; i++) {
            array[i] = Integer.parseInt(numberStr[i]);
        }


        Arrays.sort(array);

//        System.out.println(Arrays.toString(array));

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }

//        System.out.println(sum);


        int result = 0;
        for (int i = 0; i < array.length; i++) {

            int sum1 = sum;
            for (int j = 0; j < i; j++) {
                sum1 -= array[j];
            }

            int max = array[i] * sum1;
//            System.out.println("max:" + max);

            if (max > result) {
                result = max;
            }

        }

        System.out.println(result);

    }
}
