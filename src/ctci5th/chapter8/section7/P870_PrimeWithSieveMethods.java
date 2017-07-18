package ctci5th.chapter8.section7;

/**
 * Author by darcy
 * Date on 17-7-18 上午9:58.
 * Description:
 * 用埃托拉斯特尼方法找出所有的质数;
 * 即去掉1..N中所有能被2 3 5 7 11...等质数整除的数.
 */
public class P870_PrimeWithSieveMethods {

    public static boolean[] sieveMethod(int max) {
        boolean[] numbers = new boolean[max + 1];
        init(numbers);

        int prime = 2;
        while (prime <= max) {
            cross(numbers, prime);

            prime = getNextPrime(numbers, prime);

        }

        return numbers;
    }

    private static int getNextPrime(boolean[] numbers, int prime) {
        int i = prime + 1;
        while (i < numbers.length && !numbers[i]) {
            i++;
        }
        return i;
    }

    private static void cross(boolean[] numbers, int prime) {
        /**
         * 从这里开始算起.
         */
        int i = prime * prime;
        for (; i < numbers.length; i += prime) {
            numbers[i] = false;
        }
    }

    private static void init(boolean[] numbers) {
        for (int i = 2; i < numbers.length; i++) {
            numbers[i] = true;
        }
    }

    public static void main(String[] args) {

    }

}
