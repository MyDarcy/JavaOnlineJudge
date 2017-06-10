package jianzhioffer;

/**
 * Created by darcy
 * 2017/6/10--11:19
 * Description:
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，
 * 每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 */
public class P67_RobotTrace {

    private boolean[] visited;

    public int movingCount(int threshold, int rows, int cols) {
        visited = new boolean[rows * cols];
        int count = movingCount(threshold, rows, cols, 0, 0);

        return count;
    }

    private int movingCount(int threshold, int rows, int cols, int row, int col) {
        int count = 0;
        if (check(threshold, rows, cols, row, col)) {
            visited[row * cols + col] = true;
            count = 1 + movingCount(threshold, rows, cols, row - 1, col)
                    + movingCount(threshold, rows, cols, row + 1, col)
                    + movingCount(threshold, rows, cols, row, col - 1)
                    + movingCount(threshold, rows, cols, row, col + 1);
        }
        return count;
    }

    private boolean check(int threshold, int rows, int cols, int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols
                && (sumOfDigits(row) + sumOfDigits(col)) <= threshold
                && !visited[row * cols + col]) {
            return true;
        }
        return false;
    }

    private int sumOfDigits(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number = number / 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        P67_RobotTrace demo = new P67_RobotTrace();
        int count = demo.movingCount(5, 10, 10);
        System.out.println(count);
    }

}
