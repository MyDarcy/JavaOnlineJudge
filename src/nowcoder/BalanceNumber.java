package nowcoder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 前后分成两部分各自相乘，如果相等则该数是平衡数；
 *
 * @Author mr.darcy
 * @Author 2017/3/23
 * @Version 1.0
 */
public class BalanceNumber {

    public static boolean isBalanceNumber(int number) {
        String str = String.valueOf(number);
        int numberLength = str.length();
        int[] digitArray = new int[numberLength];
        char[] charArray = str.toCharArray();

        // 将int 处理成一个个的数字;
        for (int i = 0; i < charArray.length; i++) {
            digitArray[i] = charArray[i] - '0';
        }

        /*int multiplyNumber = 1;
        for (int i = 0; i < digitArray.length; i++) {


        }*/

        /*if (digitArray.length == 1) {
            return false;
        }

        int[] left = new int[digitArray.length - 1];
        int[] right = new int[digitArray.length - 1];*/

        for (int i = 0; i < digitArray.length - 1; i++) {
            int number1 = 1;
            for (int j = 0; j <= i; j++) {
                number1 *= digitArray[j];
            }

            int number2 = 1;
            for (int j = i + 1; j < digitArray.length; j++) {
                number2 *= digitArray[j];
            }
            if (number1 == number2) {
                return true;
            }
        }
        return false;


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        ArrayList<Integer> numbers = new ArrayList<>();
        while (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            boolean answer = isBalanceNumber(number);
            if (answer) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

}
