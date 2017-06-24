package crackingTheCodingInterview;

/**
 * Created by darcy
 * 2017/6/24--1:29
 * Description:
 * 有一副由NxN矩阵表示的图像，这里每个像素用一个int表示，请编写一个算法，
 * 在不占用额外内存空间的情况下(即不使用缓存矩阵)，将图像顺时针旋转90度。
 * 给定一个NxN的矩阵，和矩阵的阶数N,请返回旋转后的NxN矩阵,保证N小于等于500，图像元素小于等于256。
 * [[1,2,3],[4,5,6],[7,8,9]],3
 * 返回：[[7,4,1],[8,5,2],[9,6,3]]
 */
public class P816_TransferNNMatrix {
    /**
     * 思路: 每次处理一个外层，总共需要处理的就是 length/2层。每一次层中进行length - 1 - cycle个循环.
     *
     * @param mat
     * @param n
     * @return
     */
    public int[][] transformImage(int[][] mat, int n) {
        // write code here
        for (int cycle = 0; cycle < n / 2; cycle++) {
            int first = cycle;
            int last = n - 1 - cycle;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int temp = mat[first][i]; // 存储上边的元素.
                mat[first][i] = mat[last - offset][first]; // left -> top
                mat[last - offset][first] = mat[last][last - offset]; // bottom -> left
                mat[last][last - offset] = mat[i][last]; // right -> bottom
                mat[i][last] = temp; // top -> right
            }
        }
        return null;
    }

    /**
     * 思路2: 先次对角线翻转，然后上下翻转。
     */
    public static int[][] transformImage2(int[][] mat, int n) {
        if (mat == null) {
            return null;
        }
        int temp = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                temp = mat[i][j];
                mat[i][j] = mat[n - j - 1][n - i - 1];
                mat[n - j - 1][n - i - 1] = temp;
            }
        }


        for (int i = 0; i < (n / 2); ++i) {
            for (int j = 0; j < n; ++j) {
                temp = mat[i][j];
                mat[i][j] = mat[n - i - 1][j];
                mat[n - i - 1][j] = temp;
            }

        }

        return mat;
    }

    public static void main(String[] args) {

        P816_TransferNNMatrix demo = new P816_TransferNNMatrix();

        int[][] array1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] array2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        print(array1);
        demo.transformImage(array1, array1.length);
        print(array1);

        print(array2);
        demo.transformImage(array2, array2.length);
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
