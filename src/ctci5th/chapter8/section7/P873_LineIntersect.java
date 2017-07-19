package ctci5th.chapter8.section7;

/**
 * Author by darcy
 * Date on 17-7-19 下午8:31.
 * Description:
 */
public class P873_LineIntersect {

    class Line {
        public double epsilon = 1e-6;
        public double slope;
        public double yintersect;

        public Line(double slope, double yintersect) {
            this.slope = slope;
            this.yintersect = yintersect;
        }

        /**
         * 两条线相同,包括斜率和截距;
         * 斜率是否相同, 相同,那么y轴坐标截距是否相同;
         * @param line2
         * @return
         */
        public boolean intersect(Line line2) {
            return Math.abs(slope - line2.slope) > epsilon
                    || Math.abs(yintersect - line2.yintersect) < epsilon;
        }
    }
}
