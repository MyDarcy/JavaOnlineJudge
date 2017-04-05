package leetcode;

/**
 * Author by: darcy
 * Created on 17-3-4-下午3:37.
 * Description:
 */
public class Number_7_ReverseNumber {
    /*public int reverse(int x) {
        boolean positive = x >= 0 ? true : false;
        int maxInt = Integer.MAX_VALUE;
        int minInt = Integer.MIN_VALUE;
        String maxIntStr = "" + maxInt;
        String minIntStr = "" + minInt;
        String numberStr = "" + Math.abs(x);
        String reverseNumberStr = new StringBuilder(numberStr).reverse().toString().trim();
        int result = 0;
        if (positive) {
            if (reverseNumberStr.compareTo(maxIntStr) > 0) {
                return 0;
            } else {
                result = Integer.parseInt(reverseNumberStr);
            }
        }
        if (!positive) {
            if (reverseNumberStr.compareTo(minIntStr) > 0) {
                return 0;
            } else {
                result = -Integer.parseInt(reverseNumberStr);
            }
        }
        return result;
    }*/

    /*
        普通方法;
     */
    public int reverse(int x) {
        boolean positive = x >= 0 ? true : false;
        int sum = 0;
        int newSum = 0;
        int positiveX = Math.abs(x);
        int lastDigit = 0;
        while (positiveX != 0) {
            lastDigit = positiveX % 10;
            newSum = sum * 10 + lastDigit;
            if ((newSum - lastDigit) / 10 != sum) {
                return 0;
            }
            sum = newSum;
            positiveX = positiveX / 10;
        }
        return positive? sum : -sum;
    }

}
