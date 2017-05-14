package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/13--21:31
 * Description:
 * n 个骰子所有朝上的值的和各自出现的概率;
 */
public class P43_PointsOfNTouzi {


    private int max = 6;

    /**
     * 一堆骰子分成两部分: 左1, 右n-1; 统计到目前为止的和继续划分n-1的那一堆骰子, 直到右为1;
     * @param number
     */
    public void PrintProbability(int number) {
        if (number < 1) {
            return;
        }

        int maxSum = max * number;
        int[] probabilities = new int[maxSum - number + 1];

        probability(number, probabilities);

        double total =  Math.pow(max, number);
        for (int i = number; i <= maxSum; i++) {
            double val = probabilities[i - number] / total;
            System.out.printf(i + "\t%.10f\n", val);
        }
    }

    private void probability(int number, int[] probabilities) {
        for (int i = 1; i <= max; i++) {
            probability(number, number, i, probabilities);
        }
    }

    /**
     *
     * @param number 总的骰子个数;
     * @param currentNumber 已经使用了多少个
     * @param sum 到目前为止使用的骰子的值的总和
     * @param probabilities 每种情况下的sum;
     */
    private void probability(int number, int currentNumber, int sum, int[] probabilities) {
        if (currentNumber == 1) {
            probabilities[sum - number]++;
        } else {
            for (int i = 1; i <= max; i++) {
                probability(number, currentNumber - 1, sum + i, probabilities);
            }
        }
    }


    /**
     * 两个数组来存储骰子每个sum出现的次数;
     * 一次循环中，第一个数组的第n个数表示骰子和为n出现的次数;
     * 下一次循环中，加上一个新骰子，此时出现的和为n的次数应该是上一轮循环中骰子总和为n -1 ,
     * n -2, n -3, n -4, n - 5, n - 6的次数的和(因为新骰子的值可以是1, 2, 3, 4, 5, 6);
     *
     * TODO not sovled...
     * @param number
     */
    public void PrintProbability2(int number) {
        if (number < 1) {
            return;
        }

        int maxSum = number * max;
        int[][] probabilities = new int[2][maxSum + 1];

        int flag = 0;
        // 第一轮开始设置 前面6个;
        for (int i = 1; i <= max; i++) {
            probabilities[flag][i] = 1;
        }

        for (int k = 2; k <= number; k++) {
            /*for (int i = 0; i < k; i++) {
                probabilities[1 - flag][i] = 0;
            }*/

            // 2 ~ 12;
            for (int i = k; i <= k * max; i++) {
//                probabilities[1 - flag][i] = 0;
                for (int j = 1; j <= i && j <= max; j++) {
                    probabilities[1 - flag][i] += probabilities[flag][i - j];
                }
            }
            flag = 1 - flag;
        }

        double total =  Math.pow(max, number);
        for (int i = number; i <= maxSum; i++) {
            double val = probabilities[flag][i - number] / total;
            System.out.printf(i + "\t%.10f\n", val);
        }
    }


    public static void main(String[] args) {
        P43_PointsOfNTouzi demo = new P43_PointsOfNTouzi();
        long start = System.currentTimeMillis();
        demo.PrintProbability(10);
        System.out.println();
        demo.PrintProbability2(10);
        System.out.println(System.currentTimeMillis() - start);

    }
}
