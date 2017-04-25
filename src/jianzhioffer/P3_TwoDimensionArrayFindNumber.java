package jianzhioffer;

/**
 * Created by darcy
 * 2017/3/25--0:54
 * Description: 二维数组find number;
 */
public class P3_TwoDimensionArrayFindNumber {

    /*
        左下角开始查找;
     */
    public static boolean isInArray(int[][] intArray, int target) {
        int xLength = intArray.length; // x轴维度;
        int yLength = intArray[0].length; // y轴维度;

        int x = xLength - 1;
        int y = 0;
        while (x >= 0 && y < yLength) {
            if (intArray[x][y] < target) {
                y++;
            } else if (intArray[x][y] > target) {
                x--;
            } else {
                return true;
            }
        }
        return false;
    }

    /*
        右上角开始查找;
     */
    public static boolean isInArray(int[] array, int rows, int columns, int target) {
        int x = 0;
        int y = columns - 1;
        if (array != null && rows > 0 && columns > 0) {
            while (x < rows && y >= 0) {
                if (array[x * columns + y] > target) {
                    y--;
                } else if (array[x * columns + y] < target) {
                    x ++;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /*
        重构;
     */
    public static boolean isInArray2(int[] array, int rows, int columns, int target) {
        int x = 0;
        int y = columns - 1;
        boolean found = false;
        if (array != null && rows > 0 && columns > 0) {
            while (x < rows && y >= 0) {
                if (array[x * columns + y] > target) {
                    y--;
                } else if (array[x * columns + y] < target) {
                    x++;
                } else {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    public static void main(String[] args) {
        int[][] array = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 12, 13}};

        int[] array2 = {1, 2, 8, 9, 2, 4, 9, 12, 4, 7, 10, 13, 6, 8, 12, 13};

        int target = 11;

        boolean isIn = isInArray(array, target);

        System.out.println(isIn);

        boolean isIn2 = isInArray(array2, 4, 4, 10);
        System.out.println(isIn2);

    }
}
