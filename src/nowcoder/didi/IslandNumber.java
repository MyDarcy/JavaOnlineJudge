package nowcoder.didi;

import java.util.Scanner;

/**
 * Created by darcy
 * 2017/5/7--20:04
 * Description:
 */
public class IslandNumber {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] nm = input.nextLine().split("\\s+");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        String[] strings = new String[n + 2];
        strings[0] = makeStr(m);
        for (int i = 1; i <= n; i++) {
            strings[i] = '0' + input.nextLine().trim() + '0';
        }
        strings[n + 1] = makeStr(m);
        boolean[][] visited = new boolean[n + 2][m + 2];

        int reuslt = solution(strings, n, m, visited);
//        System.out.println(reuslt);

    }

    private static int solution(String[] array, int n, int m, boolean[][] visited) {

        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (array[i].charAt(j) == '1' && visited[i][j]) {
                    continue;
                }
                if (array[i].charAt(j) == '1') {
                    count++;
                    // left
                    if (array[i].charAt(j - 1) == '1' && visited[i][j - 1] == false)
                        visit(array, i, j - 1, visited);
                    // right
                    if (array[i].charAt(j + 1) == '1' && visited[i][j + 1] == false)
                         visit(array, i, j + 1, visited);
                    // up
                    if (array[i - 1].charAt(j) == '1' && visited[i - 1][j] == false)
                        visit(array, i - 1, j, visited);
                    // down
                    if (array[i + 1].charAt(j) == '1' && visited[i + 1][j] == false)
                        visit(array, i + 1, j, visited);
                }
            }
        }
        return count;
    }

    private static void visit(String[] array, int i, int j, boolean[][] visited) {
        if (array[i].charAt(j) == '0') {
            return;
        }
        visited[i][j] = true;
        if (array[i].charAt(j - 1) == '1' && visited[i][j - 1] == false)
            visit(array, i, j - 1, visited);
        // right
        if (array[i].charAt(j + 1) == '1' && visited[i][j + 1] == false)
            visit(array, i, j + 1, visited);
        // up
        if (array[i - 1].charAt(j) == '1' && visited[i - 1][j] == false)
            visit(array, i - 1, j, visited);
        // down
        if (array[i + 1].charAt(j) == '1' && visited[i + 1][j] == false)
            visit(array, i + 1, j, visited);
    }

    private static String makeStr(int m) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m + 2; i++) {
            sb.append('0');
        }
        return sb.toString();
    }
}

/*
4 5
11000
11000
00100
00011
 */