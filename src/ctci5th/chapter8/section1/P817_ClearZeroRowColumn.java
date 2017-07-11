package ctci5th.chapter8.section1;

/**
 * Created by darcy
 * 2017/6/25--0:43
 * Description:
 * 请编写一个算法，若N阶方阵中某个元素为0，则将其所在的行与列清零。
 * 给定一个N阶方阵int[][](C++中为vector<vector><int>>)mat和矩阵的阶数n，
 * 请返回完成操作后的int[][]方阵(C++中为vector<vector><int>>)，保证n小于等于300，矩阵中的元素为int范围内。</int></vector></int></vector>
 * 测试样例：
 * [[1,2,3],[0,1,2],[0,0,1]]
 * 返回：[[0,0,3],[0,0,0],[0,0,0]]
 */
public class P817_ClearZeroRowColumn {
    /**
     * 不能一开始就遍历原矩阵，然后将为0的行列都置为0，那么就相当于还没有处理完毕，就手动的添加了很多的0元素，
     * 极端情况:(0,0)为0， 然后第0行，第0列都置0, 然后整个矩阵都变为0了。
     * 思路：先遍历一遍矩阵，然后将为0的位置记录下来。可以用整个相同大小的矩阵记录，也可以使用两个row.length， column.length
     * 的数组记录
     * @param mat
     * @param n
     * @return
     */
    public int[][] clearZero(int[][] mat, int n) {
        // write code here

        if (mat == null || mat[0] == null || mat[0].length == 0) {
            return null;
        }

        boolean[] rowIndexs = new boolean[mat.length];
        boolean[] columnIndex = new boolean[mat[0].length];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    rowIndexs[i] = true;
                    columnIndex[j] = true;
                }
            }
        }

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (rowIndexs[i]) {
                    mat[i][j] = 0;
                }
                if (columnIndex[j]) {
                    mat[i][j] = 0;
                }

                //
                /*if (rowIndexs[i] || columnIndex[j]) {
                    mat[i][j] = 0;
                }*/
            }
        }

        return mat;
    }

    public static void main(String[] args) {

        P817_ClearZeroRowColumn demo = new P817_ClearZeroRowColumn();

        int[][] array1 = {
                {1, 0, 3},
                {0, 5, 6},
                {7, 8, 9}
        };

        int[][] array2 = {
                {1, 0, 3, 4},
                {0, 6, 7, 8},
                {9, 10, 0, 12},
                {13, 14, 15, 16}
        };

        print(array1);
        demo.clearZero(array1, array1.length);
        print(array1);

        print(array2);
        demo.clearZero(array2, array2.length);
        print(array2);
    }

    private static void print(int[][] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                System.out.printf("%3d", ints[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
