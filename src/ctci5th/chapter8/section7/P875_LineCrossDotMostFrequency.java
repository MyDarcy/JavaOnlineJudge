package ctci5th.chapter8.section7;

import java.util.ArrayList;
import java.util.HashMap;

import static ctci5th.chapter8.section7.Question.createPoints;
import static ctci5th.chapter8.section7.Question.findBestLine;

/**
 * Author by darcy
 * Date on 17-7-19 下午9:03.
 * Description:
 *
 * 二维平面上, 找出经过点数最多的线;
 *
 * 统计每条直线出现的次数.
 */

class GraphPoint {
    public double x;
    public double y;
    public GraphPoint(double x1, double y1) {
        x = x1;
        y = y1;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

class Line {
    public static double epsilon = .0001;
    public double slope;
    public double intercept;

    private boolean infinite_slope = false;

    public Line(GraphPoint p, GraphPoint q) {
        if (Math.abs(p.x - q.x) > epsilon) { // if x抯 are different
            slope = (p.y - q.y) / (p.x - q.x); // compute slope
            intercept = p.y - slope * p.x; // y intercept from y=mx+b
        } else {
            infinite_slope = true;
            intercept = p.x; // x-intercept, since slope is infinite
        }
    }

    public boolean isEquivalent(double a, double b) {
        return (Math.abs(a - b) < epsilon);
    }

    public void Print() {
        System.out.println("y = " + slope + "x + " + intercept);
    }

    public static double floorToNearestEpsilon(double d) {
        int r = (int) (d / epsilon);
        return ((double) r) * epsilon;
    }

    public boolean isEquivalent(Object o) {
        Line l = (Line) o;
        if (isEquivalent(l.slope, slope) && isEquivalent(l.intercept, intercept) && (infinite_slope == l.infinite_slope)) {
            return true;
        }
        return false;
    }
}

class Question {

    /* Count lines within an array of lines which are "equivalent" (slope and y-intercept are within an epsilon value) to a given line */
    public static int countEquivalentLines(ArrayList<Line> lines, Line line) {
        if (lines == null) {
            return 0;
        }

        int count = 0;
        for (Line parallelLine : lines) {
            if (parallelLine.isEquivalent(line)) {
                count++;
            }
        }
        return count;
    }

    /* Check hashmap for lines that are equivalent. Note that we need to check one epsilon above and below the actual slope
     * since we're defining two lines as equivalent if they're within an epsilon of each other.
     */
    public static int countEquivalentLines(HashMap<Double, ArrayList<Line>> linesBySlope, Line line) {
        double key = Line.floorToNearestEpsilon(line.slope);
        int count = countEquivalentLines(linesBySlope.get(key), line);
        count += countEquivalentLines(linesBySlope.get(key - Line.epsilon), line);
        count += countEquivalentLines(linesBySlope.get(key + Line.epsilon), line);
        return count;
    }

    /* insert line into hashmap */
    public static void insertLine(HashMap<Double, ArrayList<Line>> linesBySlope, Line line) {
        ArrayList<Line> lines = null;
        double key = Line.floorToNearestEpsilon(line.slope);
        if (!linesBySlope.containsKey(key)) {
            lines = new ArrayList<Line>();
            linesBySlope.put(key, lines);
        } else {
            lines = linesBySlope.get(key);
        }
        lines.add(line);
    }


    public static Line findBestLine(GraphPoint[] points) {
        Line bestLine = null;
        int bestCount = 0;
        HashMap<Double, ArrayList<Line>> linesBySlope = new HashMap<Double, ArrayList<Line>>();

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                Line line = new Line(points[i], points[j]);
                insertLine(linesBySlope, line);

				/* count lines that are equivalent to current line */
                int count = countEquivalentLines(linesBySlope, line);

				/* if better than current line, replace it */
                if (count > bestCount) {
                    bestLine = line;
                    bestCount = count;
                }
            }
        }

        return bestLine;
    }

    public static GraphPoint[] createPoints() {
        int n_points = 100;
        System.out.println("Points on Graph\n***************");
        GraphPoint[] points = new GraphPoint[n_points - 1];
        for (int i = 0; i < n_points / 2; i++) {
            GraphPoint p = new GraphPoint(i, 2.3 * ((double)i) + 20.0);
            points[i] = p;
            System.out.println(p.toString());
        }
        for (int i = 0; i < n_points / 2 - 1; i++) {
            GraphPoint p = new GraphPoint(i, 3.0 * ((double)i) + 1.0);
            points[n_points / 2 + i] = p;
            System.out.println(p.toString());
        }
        System.out.println("****************\n");
        return points;
    }


}


public class P875_LineCrossDotMostFrequency {

    public static void main(String[] args) {
        GraphPoint[] points = createPoints();
        Line line = findBestLine(points);
        line.Print();
    }

}
