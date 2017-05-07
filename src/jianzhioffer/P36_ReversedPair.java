package jianzhioffer;

import com.sun.xml.internal.bind.v2.runtime.output.C14nXmlOutput;

import java.util.Random;

/**
 * Created by darcy
 * 2017/5/7--16:26
 * Description:
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 */
public class P36_ReversedPair {

    /**
     * @param array
     * @return
     */
    public int InversePairs(int[] array) {
        if (array == null || array.length == 0) {
            throw new RuntimeException("Wrong parameters");
        }
        int[] copy = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        int count = solution(array, copy, 0, array.length - 1);

        return count;
    }

    private int solution(int[] array, int[] copy, int start, int end) {

        if (start == end) {
            copy[start] = array[start];
            return 0;
        }
        int middle = start +  (end - start) / 2;
        int left = solution(copy, array, start, middle);
        int right = solution(copy, array, middle + 1, end);

        int i = middle;
        int j = end;
        int copyIndex = end;
        int count = 0;
        while (i >= start && j >=  middle + 1) {

            // 前面已序的数组后后面已序的数组中特定的字符大; 那么逆序对个个数;
           if (array[i] > array[j]) {
                copy[copyIndex--] = array[i--];
                count += (j - middle);
            } else {
                copy[copyIndex--] = array[j--];
            }
        }

        for (; i >= start; i--) {
            copy[copyIndex--] = array[i];
        }

        for (; j >= middle  + 1; j--) {
            copy[copyIndex--] = array[j];
        }

        /*for (int s = start; s <= end; s++) {
            array[s] = copy[s];
        }*/

        return count + left + right;

    }

    /**
     *
     */
    public int InversePairs2(int[] array) {
        if (array == null || array.length == 0) {
            throw new RuntimeException("Wrong parameters");
        }
        if (array.length == 1) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
//                    System.out.println(array[i] + "\t" + array[j]);

                    count++;
                }
            }
        }
        return count;
    }



    /**
     * 链接：https://www.nowcoder.com/questionTerminal/96bd6684e04a44eb80e6a68efc0ec6c5
     * @param array
     * @return
     */

    public int InversePairs3(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int[] copy = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        int count = InversePairsCore(array, copy, 0, array.length - 1);//数值过大求余
        return count;

    }

    private int InversePairsCore(int[] array, int[] copy, int low, int high) {
        if (low == high) {
            return 0;
        }
        int mid = (low + high) >> 1;
        int leftCount = InversePairsCore(copy, array, low, mid) % 1000000007;
        int rightCount = InversePairsCore(copy, array, mid + 1, high) % 1000000007;
        int count = 0;
        int i=mid;
        int j = high;
        int locCopy = high;
        while (i >= low && j > mid) {
            if (array[i] > array[j]) {
                count += j - mid;
                copy[locCopy--] = array[i--];
                if (count >= 1000000007)//数值过大求余
                {
                    count %= 1000000007;
                }
            } else {
                copy[locCopy--] = array[j--];
            }
        }
        for (; i >= low; i--) {
            copy[locCopy--] = array[i];
        }
        for (; j > mid; j--) {
            copy[locCopy--] = array[j];
        }
        /*for (int s = low; s <= high; s++) {
            array[s] = copy[s];
        }*/
        return (leftCount + rightCount + count) % 1000000007;
    }


    public static void main(String[] args) {
        P36_ReversedPair demo = new P36_ReversedPair();

        int[] array = {7, 5, 6, 4};
        int[] array2 = {7, 5, 6, 4};
        int[] array3 = {7, 5, 6, 4};


        Random random = new Random(31);
        array = new int[1000];
        array2 = new int[1000];
        array3 = new int[1000];
        for (int i = 0; i < 1000; i++) {
            array[i] = array2[i] = array3[i] = random.nextInt(100000);
        }


        int count = demo.InversePairs(array);

        int count2 = demo.InversePairs2(array2);

        int count3 = demo.InversePairs3(array3);
        System.out.println(count + "\t" + count2 + "\t" + count3);
    }
}
/*
[364,637,341,406,747,995,234,971,571,219,993,
407,416,366,315,301,601,650,418,355,460,505,
360,965,516,648,727,667,465,849,455,181,486,
149,588,233,144,174,557,67,746,550,474,162,
268,142,463,221,882,576,604,739,288,569,256,
936,275,401,497,82,935,983,583,523,697,478,
147,795,380,973,958,115,773,870,259,655,446,
863,735,784,3,671,433,630,425,930,64,266,235,
187,284,665,874,80,45,848,38,811,267,575]
 */