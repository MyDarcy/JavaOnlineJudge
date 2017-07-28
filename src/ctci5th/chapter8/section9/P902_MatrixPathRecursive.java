package ctci5th.chapter8.section9;

import ctci5th.lib.AssortedMethods;

import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.*;


/**
 * 矩阵中为０的位置(特定的位置)不能走.试着找出一条路径从左上定点到右下定点.
 *
 * 拓展: 如何找出所有这样的路径...
 */

public class P902_MatrixPathRecursive {
	public static int[][] maze = new int[10][10];
	
	public static boolean isFree(int x, int y) {
		// 不为０的位置才可以走.
		if (maze[x][y] == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean getPath(int x, int y, ArrayList<Point> path) {
		// If out of bounds or not available, return.
		if (y < 0 || x < 0 || !isFree(x, y)) {
			return false;
		}
		
		boolean isAtOrigin = (x == 0) && (y == 0);
		
		// If there's a path from the start to my current location, add my location.
		// (x, y)的左节点和上节点分别是(x -1, y)和(x, y -1)
		// 递归会一直在if()处递归调用, 直到一条路径到达了(0, 0)，　那么作为第一个节点加入到List中, 然后回溯到起始调用
		// 节点, 递归调用经过的节点会加入到List中.
		// 所以, 节点加入List的顺序和遍历的顺序是相反的.
		if (isAtOrigin || getPath(x, y - 1, path) || getPath(x - 1, y, path)) { 
			Point p = new Point(x, y);
			path.add(p);
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		int size = 5;
		maze = AssortedMethods.randomMatrix(size, size, 0, 5);
		
		AssortedMethods.printMatrix(maze);
		
		ArrayList<Point> path = new ArrayList<Point>();
		// 从后往前走.
		boolean success = getPath(size - 1, size - 1, path);
		if (success) {
			/*String s = AssortedMethods.listOfPointsToString(path);
			System.out.println(s);*/

			for (Point p : path) {
				System.out.println("(" + p.x + "," + p.y + ", " + maze[p.x][p.y] + ")");
			}

		} else {
			System.out.println("No path found.");
		}
	}

}
