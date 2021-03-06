package jianzhioffer;

import java.util.*;

/**
 * Created by darcy
 * 2017/5/16--23:26
 * Description:
 * <p>
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是重复的数字2或者3。
 */
public class P51_DuplicateNumberInArray {

    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public boolean duplicate(int numbers[], int length, int[] duplication) {

        if (numbers == null || numbers.length <= 1) {
            return false;
        }

        Set<Integer> set = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < numbers.length; i++) {
            if (set.contains(numbers[i])) {
                result.add(numbers[i]);
            } else {
                set.add(numbers[i]);
            }
        }

        if (result.size() < 1) {
            return false;
        }

        // 返回任意一个...
        duplication[0] = result.iterator().next();

        return true;
    }

    /**
     * 注意到数组中的每一个值的范围 1~n-1;
     *
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public boolean duplicate2(int numbers[], int length, int[] duplication) {
        if (numbers == null || numbers.length <= 1) {
            return false;
        }

        boolean[] exists = new boolean[length];
        for (int i = 0; i < length; i++) {
            if (exists[numbers[i]]) {
                duplication[0] = numbers[i];
                return true;
            }
            exists[numbers[i]] = true;
        }

        return false;


    }


    /**
     * 链接：https://www.nowcoder.com/questionTerminal/623a5ac0ea5b4e5f95552655361ae0a8
     * 思路：把number[i] = k的元素k移到到位置k上去;
     * {2, 3, 1, 0, 2, 5, 3} , 首先位置0是2, 与位置0不等,于是和位置2的元素进行交换，交换后{1, 3, 2, 0, 2, 5, 3};
     * 然后位置0的元素是1, 与位置0不相等, 那么继续与位置1的元素交换，交换后{3, 2, 1, 0, 2, 5, 3};同样，位置0是3 (!=0), 继续喝
     * 和位置3的元素进行交换，这个时候位置0就成为了0 {0, 2, 1, 3, 2, 5, 3}, 继续进行下一轮迭代;
     * 构造确定位置x一定是x;
     *
     * O(n)
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public boolean duplicate3(int numbers[], int length, int[] duplication) {
        if (length <= 0 || numbers == null) {
            return false;
        }

        //判断每一个元素是否非法
        for (int i = 0; i < length; ++i) {
            if (numbers[i] <= 0 || numbers[i] > length - 1)
                return false;
        }
        for (int i = 0; i < length; ++i) {
            while (numbers[i] != i) {
                if (numbers[i] == numbers[numbers[i]]) {
                    duplication[0] = numbers[i];
                    return true;
                }
                //交换numbers[i]和numbers[numbers[i]]
                int temp = numbers[i];
                numbers[i] = numbers[temp];
                numbers[temp] = temp;
            }
        }
        return false;
    }
}
