package leetcode;

/**
 * Author by: darcy
 * Created on 17-3-3-下午10:15.
 * Description:
 * 思路: 遍历第每行, 当前行的当前列如果为1并且和前一个不相等, 那么计数+1; 该行遍历完毕, 乘以2;
 *       然后遍历每一列;
 */
public class EdgeNumbers {
    public int islandPerimeter(int[][] grid) {
        if(grid == null || grid[0] == null)
            return 0;
        int vertical = grid.length;
        int horizontal = grid[0].length;
        int sum = 0;

        // 先遍历行;
        for(int i = 0; i < vertical; i++) {
            // int previous = grid[i][0];
            int previous = 0;
            int count = 0;
            for (int j = 0; j < horizontal; j++) {
                if (grid[i][j] == 1 && grid[i][j] != previous) {
                    count++;
                }
                previous = grid[i][j];
            }
            sum += count * 2;
        }

        // 先遍历列;
        for(int i = 0; i < horizontal; i++) {
            // int previous = grid[0][i];
            int previous = 0;
            int count = 0;
            for(int j = 0; j < vertical; j++) {
                if (grid[j][i] == 1 && grid[j][i] != previous) {
                    count++;
                }
                previous = grid[j][i];
            }
            sum += count * 2;
        }
        return sum;

    }

    public int islandPerimeter2(int[][] grid)  {
        int islands = 0;
        int neighbors = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    islands++;
                    if (i < grid.length - 1 && grid[i + 1][j] == 1)
                        neighbors++;
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1)
                        neighbors++;
                }
            }
        }
        return 4 * islands - 2 * neighbors;
    }
}
