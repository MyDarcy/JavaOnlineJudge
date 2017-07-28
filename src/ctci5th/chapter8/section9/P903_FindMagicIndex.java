package ctci5th.chapter8.section9;

/**
 * Author by darcy
 * Date on 17-7-28 上午10:52.
 * Description:
 * <p>
 * 已序的数组中, 如果a[index] = index; 那么就称这样的index为magic number,
 * 找出magic　number.
 */
public class P903_FindMagicIndex {

    /**
     * brute solutions.
     *
     * @param numbers
     * @return
     */
    public static int iterateM(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == i) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 利用已排序特性, 进行二分查找.
     *
     * @param numbers
     * @return
     */
    public static int binaryM(int[] numbers) {
        return binaryM(numbers, 0, numbers.length - 1);
    }

    private static int binaryM(int[] numbers, int start, int end) {
        if (end < start || start < 0 || end >= numbers.length) {
            return -1;
        }

        int mid = start + (end - start) / 2;
        // number[5] = 3 < 5(index)
        if (numbers[mid] < mid) {
            return binaryM(numbers, mid + 1, end);
            // number[3] = 5 > 3
        } else if (numbers[mid] > mid) {
            return binaryM(numbers, start, mid - 1);
        } else {
            return mid;
        }
    }

    public static int binaryMWithSameNumber(int[] numbers) {
        return binaryMWithSameNumber(numbers, 0, numbers.length - 1);
    }

    /**
     * 先比较midIndex和midValue是否相等.
     * @param numbers
     * @param start
     * @param end
     * @return
     */
    private static int binaryMWithSameNumber(int[] numbers, int start, int end) {
        if (start > end || start < 0 || end >= numbers.length) {
            return -1;
        }

        int midIndex = start + (end - start) / 2;
        int midValue = numbers[midIndex];
        if (midIndex == midValue) {
            return midIndex;
        }

        // 当前的midValue是midIndex的值，那么往其左边搜索的时候, 左边的值一定是 <= midValue的.
        int left = Integer.min(midIndex - 1, midValue);
        int leftValue = binaryMWithSameNumber(numbers, start, left);
        if (leftValue > 0) {
            return leftValue;
        }

        int right = Integer.max(midIndex + 1, midValue);
        int rightValue = binaryMWithSameNumber(numbers, right, end);
        return rightValue;
    }

    public static void main(String[] args) {
        int[] numbers = {-40, -20, -1, 1, 2, 3, 5, 7, 9, 12, 13};
        int result = iterateM(numbers);
        System.out.println(result);

        int result2 = binaryM(numbers);
        System.out.println(result2);

        int[] numbers3 = {-10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13};
        System.out.println(iterateM(numbers3)); // 2
        System.out.println(binaryM(numbers3)); // 7

        System.out.println(binaryMWithSameNumber(numbers3));
    }


}
