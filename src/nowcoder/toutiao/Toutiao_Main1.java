package nowcoder.toutiao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/8/22 19:34.
 * Description:
 * P为给定的二维平面整数点集。定义 P 中某点x，如果x满足 P 中任意点都不在 x 的右上方区域内（横纵坐标都大于x），
 * 则称其为“最大的”。求出所有“最大的”点的集合。（所有点的横坐标和纵坐标都不重复, 坐标轴范围在[0, 1e9) 内）
 * 如下图：实心点为满足条件的点的集合。
 * <p>
 * 输入
 * 第一行输入点集的个数 N， 接下来 N 行，每行两个数字代表点的 X 轴和 Y 轴。
 * 输出
 * 输出“最大的” 点集合， 按照 X 轴从小到大的方式输出，每行两个数字分别代表点的 X 轴和 Y轴。
 * <p>
 * 样例输入
 * 5
 * 1 2
 * 5 3
 * 4 6
 * 7 5
 * 9 0
 * 样例输出
 * 输出结果按照 x 轴排序，如上例输出为：
 * 4 6
 * 7 5
 * 9 0
 */
public class Toutiao_Main1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Point[] points = new Point[N];

        for (int i = 0; i < N; i++) {
            points[i] = new Point(in.nextInt(), in.nextInt());   //坐标赋值
        }
        solve(points);
    }

    private static void solve(Point[] points) {
        List<Point> result = new ArrayList<>();
        sort_by_y(points); //按照y进行排序

        int max_x = 0;
        for (int i = 0; i < points.length; i++) {
            if (max_x < points[i].x) {
                max_x = points[i].x;
                result.add(points[i]);
            }
        }
        sort_by_x(result);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).x + " " + result.get(i).y);
        }
    }

    //结果按x递增输出-插入排序
    private static void sort_by_x(List<Point> result) {
        for (int i = 0; i < result.size(); i++) {
            Point temp = result.get(i);
            int j = i - 1;
            for (; j >= 0 && result.get(j).x > temp.x; j--) {
                result.set(j + 1, result.get(j));
            }
            result.set(j + 1, temp);
        }
        //for (int i=0;i<result.size();i++){
        //System.out.print(" "+result.get(i).x);
        //}
    }

    //按照y递减排序-插入排序
    private static void sort_by_y(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            Point temp = points[i];
            int j = i - 1;
            for (; j >= 0 && points[j].y < temp.y; j--) {
                points[j + 1] = points[j];
            }
            points[j + 1] = temp;
        }
        //for (int i=0;i<points.length;i++){
        //System.out.println(points[i].x+" "+points[i].y);
        //}
        //System.out.println();
        //System.out.println();
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
