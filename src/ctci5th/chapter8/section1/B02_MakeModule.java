package ctci5th.chapter8.section1;

import java.util.Arrays;

/**
 * Created by darcy
 * 2017/6/23--0:47
 * Description:
 * 交换数组中最大数和最小数.
 */
public class B02_MakeModule {

    // 放在一个函数中实现， 省略...

    public int getMinIndex(int[] array) {
        int minIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public int getMaxIndex(int[] array) {
        int maxIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public void swap(int[] array, int minIndex, int maxIndex) {
        int temp = array[minIndex];
        array[minIndex] = array[maxIndex];
        array[maxIndex] = temp;
    }

    // get...
    public void solution(int[] array) {
        int minIndex = getMinIndex(array);
        int maxIndex = getMaxIndex(array);
        swap(array, minIndex, maxIndex);
    }


    public static void main(String[] args) {
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        System.out.println(Arrays.toString(array));
        B02_MakeModule demo = new B02_MakeModule();
        demo.solution(array);
        System.out.println(Arrays.toString(array));

    }
}
