package jianzhioffer;

/**
 * Created by darcy
 * 2017/3/28--23:58
 * Description:
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class P8_RotateAndGetMiniNumber {

    public int minNumberInRotateArray(int [] array) {
        if (array == null || array.length == 0) {
            throw new RuntimeException("Wrong array.");
        }

        if (array.length == 1) {
            return array[0];
        }

        int i = 0;
        for (; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                break;
            }
        }

        return i + 1;
    }

    /*

  中间元素大于第一个元素，则中间元素位于前面的递增子数组，此时最小元素位于中间元素的后面。
  我们可以让第一个指针left指向中间元素。  移动之后，第一个指针仍然位于前面的递增数组中。

  中间元素小于第一个元素，则中间元素位于后面的递增子数组，此时最小元素位于中间元素的前面。
  我们可以让第二个指针right指向中间元素。

   */

    public int minNumberInRotateArray2(int[] array) {
        if (array == null || array.length == 0) {
            throw new RuntimeException("Wrong Array");
        }

        int left = 0;
        int right = array.length;
        int mid = 0;


        while (array[left] >= array[right]) {

            if (right - left == 1) {
                mid = right;
                break;
            }

            mid = left + (right - left) / 2;

            // 如果left == mid == right
            if (array[left] == array[mid] && array[mid] == array[right]) {
                return minNumber(array, left, right);
            }

            if (array[mid] >= left) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return array[mid];

    }

    private int minNumber(int[] array, int left, int right) {
        int result = array[0];
        for (int i = left + 1; i <= right; i++) {
            if (array[left] < result) {
                result = array[left];
            }
        }
        return result;

        /*for (int i = left; i < right; i++) {
            if (array[i] < array[i + 1]) {
                return array[i + 1];
            }
        }*/
    }


    public static int minNumberInRotateArray3(int [] array) {
        int low = 0 ; int high = array.length - 1;
        while(low < high){
            int mid = low + (high - low) / 2;
            if(array[mid] > array[high]){
                low = mid + 1;
            }
            // 中间元素比最高元素还相等，那么可能情况之一就是两个都是最大元素;
            else if(array[mid] == array[high]){
                high = high - 1;
            }else{ // mid element < high element; 已经在后面的有序子数组中;
                high = mid;
            }
        }
        return array[low];
    }

    public static void main(String[] args) {
        int[] array = {5, 6, 7, 8, 1, 2, 3, 4};
        int result = minNumberInRotateArray3(array);
        System.out.println(result);
    }
}
