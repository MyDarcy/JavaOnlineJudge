package ctci5th.chapter8.section9;

import ctci5th.lib.AssortedMethods;

import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.*;



public class P902_MatrixPathDP {
	public static int size = 10;
	public static int[][] maze = new int[size][size];
	
	public static boolean isFree(int x, int y) {
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
		if (isAtOrigin || getPath(x, y - 1, path) || getPath(x - 1, y, path)) { 
			Point p = new Point(x, y);
			path.add(p);
			return true;
		}
		
		return false;
	}

	/**
	 * DP, 在原来算法的基础之上, 加入了缓存, 存中间结果,
	 * 因为遍历(x, y)的左节点(x, y-1)和上节点(x-1, y)都会遍历节点
	 * (x-1, y-1)
	 * @param x
	 * @param y
	 * @param path
	 * @param cache
	 * @return
	 */
	public static boolean getPath(int x, int y, ArrayList<Point> path, Hashtable<Point, Boolean> cache) {
		/* If out of bounds or not available, return.*/
		if (y < 0 || x < 0 || !isFree(x, y)) {
			return false;
		}
		Point p = new Point(x, y);
		
		/* If we've already visited this cell, return. */
		if (cache.containsKey(p)) { 
			return cache.get(p);
		}	
		
		boolean isAtOrigin = (x == 0) && (y == 0);
		boolean success = false;
		
		/* If there's a path from the start to my current location, add my location.*/
		if (isAtOrigin || getPath(x, y - 1, path, cache) || getPath(x - 1, y, path, cache)) {
			path.add(p);
			success = true;
		}
		
		cache.put(p, success); // Cache result
		return success;
	}
	
	public static void main(String[] args) {
		maze = AssortedMethods.randomMatrix(size, size, 0, 5);
		AssortedMethods.printMatrix(maze);
		ArrayList<Point> path = new ArrayList<Point>();
		Hashtable<Point, Boolean> cache = new Hashtable<Point, Boolean>();
		boolean success = getPath(size - 1, size - 1, path, cache);
		
		if (success) {
			/*String s = AssortedMethods.listOfPointsToString(path);
			System.out.println("Path: " + " " + s);	*/

			for (Point p : path) {
				System.out.println("(" + p.x + "," + p.y + ", " + maze[p.x][p.y] + ")");
			}
		} else {
			System.out.println("No path exists.");
		}
	}

}
