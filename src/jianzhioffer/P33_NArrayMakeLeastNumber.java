package jianzhioffer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;

/**
 * Created by darcy
 * 2017/5/7--10:49
 * Description:
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
 * 打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，
 * 则打印出这三个数字能排成的最小数字为321323。
 */
public class P33_NArrayMakeLeastNumber {
    /**
     * 全排列;
     * @param numbers
     * @return
     */
    private String minNumber;
    public String PrintMinNumber(int [] numbers) {

        minNumber = makeString(numbers);

        if (numbers == null || numbers.length == 0) {
            throw new RuntimeException("wrong parameters");
        }

        solution(numbers, 0, numbers.length);
        return minNumber;
    }

    private String makeString(int[] numbers) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            builder.append("" + numbers[i]);
        }
        return builder.toString();
    }

    private void solution(int[] numbers, int start, int end) {
        // 每个全排列都生成一个字符串;
        if (start == end - 1) {
            String temp = makeString(numbers);
            if (temp.compareTo(minNumber) < 0) {
                minNumber = temp;
            }
        } else {
            for (int i = start; i < end; i++) {
                swap(numbers, i, start);
                solution(numbers, start + 1, end);
                swap(numbers, i, start);
            }
        }
    }

    private void swap(int[] numbers, int i, int i1) {
        int temp = numbers[i];
        numbers[i] = numbers[i1];
        numbers[i1] = temp;
    }

    /**
     * 思路: 排序,然后将首字母相同的分成一个组然后对这个分组的每个字符串逆序后排序，
     * 然后按照这个顺序再逆序拼接在一起;
     * 不过这个的复杂度和空间开销其实都不小;
     *
     * TODO not solved...
     * @param numbers
     * @return
     */
    public String PrintMinNumber2(int [] numbers) {
        String[] numbersStr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            numbersStr[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(numbersStr);
        String previous = numbersStr[0];
        int segIndex = 0;
        String result = "";

        for (int i = 1; i < numbersStr.length; i++) {
            if (previous.charAt(0) != numbersStr[i].charAt(0)) {
                result += solution2(numbersStr, segIndex, i);
                segIndex = i;
            }
            previous = numbersStr[i];
        }
        result += solution2(numbersStr, segIndex, numbersStr.length);
        return result;

    }

    private String solution2(String[] numbersStr, int start, int end) {
        // 79716 78156 没有解决长度相同是应该是正序小的放在前面;
        /*String[] temp = new String[end - start];
        for (int i = start; i < end; i++) {
            temp[i - start] = new StringBuilder(numbersStr[i]).reverse().toString();
        }
        Arrays.sort(temp);
        String result = "";
        for (int i = 0; i < temp.length; i++) {
            result += new StringBuilder(temp[i]).reverse().toString();
        }
        return result;*/

        if (end - start == 1) {
            return numbersStr[start];
        }

        String[] temp = new String[end - start];
        for (int i = start; i < end; i++) {
            temp[i - start] = numbersStr[i];
        }
        Arrays.sort(temp, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        // 注意这里是在temp上操作;
        int previous = temp.length - 1;
        String result = "";
        for (int i = temp.length - 1; i >=  1 ; i--) {
            if (temp[i].length() != temp[i - 1].length()) {
                for (int j = i; j <= previous; j++) {
                    result += temp[j];
                }
                previous = i - 1;
            }
        }
        for (int i = 0; i <= previous; i++) {
            result += temp[i];
        }
        return result;
    }


    /**
     * 思路: 通过定义Comporator来确定最终的序列的次序;
     * @param numbers
     */
    public String PrintMinNumber3(int [] numbers) {

        String[] numbersStr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            numbersStr[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(numbersStr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });

        String result = "";
        for (int i = 0; i < numbersStr.length; i++) {
            result += numbersStr[i];
        }
        return result;

    }

    public static void main(String[] args) {
        P33_NArrayMakeLeastNumber demo = new P33_NArrayMakeLeastNumber();
        int[] numbers = new int[10];
        Random random = new Random(31);
        for (int i = 0; i < 10; i++) {
            numbers[i] = random.nextInt(100000);
        }
        System.out.println(Arrays.toString(numbers));
        long time1 = System.currentTimeMillis();
        String min = demo.PrintMinNumber(numbers);
        long time2 = System.currentTimeMillis();
        String min2 = demo.PrintMinNumber3(numbers);
        long time3 = System.currentTimeMillis();
        System.out.println(min + "\t" + (time2 - time1));
        System.out.println(min2 + "\t" + (time3 - time2));
        System.out.println(min.equals(min2));

    }
}
