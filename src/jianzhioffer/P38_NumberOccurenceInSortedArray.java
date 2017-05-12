package jianzhioffer;

import java.util.Arrays;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

/**
 * Created by darcy
 * 2017/5/10--0:01
 * Description:
 * 统计一个数字在排序数组中出现的次数。
 */
public class P38_NumberOccurenceInSortedArray {
    /**
     * 遍历一遍；
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK(int [] array , int k) {

        if (array == null || array.length == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == k) {
                count++;
            }
        }
        return count;
    }

    /**
     * 找特定元素第一次出现和最后一次出现的位置;
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK2(int[] array, int k) {
        if (array == null || array.length == 0) {
            throw new RuntimeException("wrong parameters");
        }

        int start = 0;
        int end = array.length - 1;
        int firstK = findFirstK(array, k, start, end);
        int lastK = findLastK(array, k, start, end);
        if (firstK == -1 || lastK == -1) {
            return 0;
        }
        return lastK - firstK + 1;
    }

    private int findFirstK(int[] array, int k, int start, int end) {
        if (start > end) {
            return -1;
        }

        int middleIndex = start + (end - start) / 2;
        int middle = array[middleIndex];
        if (middle > k) {
            end = middleIndex - 1;
        } else if (middle < k) {
            start = middleIndex + 1;
        } else { // middle == k
            if ((middleIndex > 0 && array[middleIndex - 1] != k) || middleIndex == 0) {
                return middleIndex;
            } else {
                // 前面还有重复元素;
                end = middleIndex - 1;
            }
        }

        return findFirstK(array, k, start, end);
    }

    private int findLastK(int[] array, int k, int start, int end) {
        if (start > end) {
            return -1;
        }
        int middleIndex = start + (end - start) / 2;
        int middle = array[middleIndex];
        if (middle == k) {

            // 和后面元素不想当，那么当前的middle元素就是最后一个等于k的了;
            if ((middleIndex < array.length - 1 && middle != array[middleIndex + 1]) || (middleIndex == (array.length - 1))) {
                return middleIndex;
            } else {
                // 否则，后面还有和k相等的元素;
                start = middleIndex + 1;
            }

        } else if (middle < k) {
            start = middleIndex + 1;
        } else {
            end = middleIndex -1;
        }
        return findLastK(array, k, start, end);
    }



    public int binarySearch(int[] array, int k) {
        int start = 0;
        int end = array.length - 1;
        int middle = end / 2;
        while (start < end) {
            middle = start + (end - start) / 2;
            if (array[middle] > k) {
                end = middle - 1;
            } else if (array[middle] < k) {
                start = middle + 1;
            } else {
                return middle;
            }
        }

        return start;
    }

    public static void main(String[] args) {
        Random random = new Random(31);
        int[] ints = new int[1000];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt(100);
        }
        Arrays.sort(ints);

        P38_NumberOccurenceInSortedArray demo = new P38_NumberOccurenceInSortedArray();

        for (int i = 0; i < 10; i++) {
            int rand = random.nextInt(100);
            System.out.println("test:" + rand);
            System.out.println(demo.GetNumberOfK(ints, rand));
            System.out.println(demo.GetNumberOfK2(ints, rand));
            System.out.println();
        }

    }

}
