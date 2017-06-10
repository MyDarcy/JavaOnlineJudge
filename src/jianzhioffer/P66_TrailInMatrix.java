package jianzhioffer;

/**
 * Created by darcy
 * 2017/6/3--22:29
 * Description:
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 * 例如 a b c e s f c s a d e e  3 * 4的矩阵中包含一条字符串"bcced"的路径，
 * 但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，
 * 路径不能再次进入该格子。
 *
 *
 * 思路：在矩阵中一个节点为起点(这里需要遍历整个矩阵)，如果矩阵的当前元素是str中的第一个元素，那么就
 * 遍历下去，看看上下左右的元素是否是第二个元素，这样递归下去，但是如果不是，那么就需要回溯，需要一个辅助数组来记录。
 */
public class P66_TrailInMatrix {

    private int strIndex = 0;
    private boolean[] visited;


    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || rows < 1 || cols < 1 || str == null) {
            return false;
        }

        visited = new boolean[matrix.length];

        // 以任意的位置为起点。
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasPath(matrix, rows, cols, i, j, str)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param matrix
     * @param rows
     * @param cols
     * @param row
     * @param col
     * @param str
     * @return
     */
    private boolean hasPath(char[] matrix, int rows, int cols, int row, int col, char[] str) {
        if (strIndex == str.length) {
            return true;
        }

        boolean result = false;

        if (row >= 0 && row < rows && col >= 0 && col < cols
                && matrix[row * cols + col] == str[strIndex]
                && !visited[row * cols + col]) {
            strIndex++;
            visited[row * cols + col] = true;

            // 当前位置的上下左右。
            result = hasPath(matrix, rows, cols, row - 1, col, str)
                    || hasPath(matrix, rows, cols, row + 1, col, str)
                    || hasPath(matrix, rows, cols, row, col - 1, str)
                    || hasPath(matrix, rows, cols, row, col + 1, str);

            if (!result) {
                --strIndex;
                visited[row * cols + col] = false;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        P66_TrailInMatrix demo = new P66_TrailInMatrix();
        char[] matrix = "ABCESFCSADEE".toCharArray();
        char[] str = "ABCCED".toCharArray();

        boolean result = demo.hasPath(matrix, 3, 4, str);
        System.out.println(result);
    }

}
