package jianzhioffer;

import java.util.*;

/**
 * Created by darcy
 * 2017/5/30--0:00
 * Description:
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 */
public class P64_MedianNumberOfStream {

    /**
     * 思路:
     * 整个数据分成两部分，两部分数据个数相差不能查过1, num in left < num in right。
     * 左部分数据以最大堆组织，右边部分以最小堆组织。那么左边部分就能直接取到最大值，右边就能直接取到最小值。
     * 当前已处理元素的个数为奇数个：
     * 新元素入小根堆(right)。但是要要保证right部分的元素都要比left部分的数据大，那么一旦新元素比left中的最大值小，
     * 那么新元素就要先入最大堆(left)， 然后将最大堆的堆顶元素入最小堆。
     * 当前已处理元素为偶数个：
     * 新元素入大根堆。同样要保证left部分的元素要比right部分的元素小。那么一旦新元素比最小堆堆顶元素大，那么新
     * 元素先入最小堆，然后最小堆堆顶元素出，进入最大堆。
     */

    // 模拟最小堆。默认的排序策略小根堆。
    PriorityQueue<Integer> min = new PriorityQueue<>();

    // 模拟大根堆。重写排序策略实现大根堆。\
    // 注意这个PriorityQueue的构造器只有JDK1.8中才提供。
    PriorityQueue<Integer> max = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });

    /**
     * 数据总数目是偶数时插入小根堆，数目总数目最大时候插入到大根堆中。
     * 但是也要保证插入到小根堆(确定最小值)中的数据都比大根堆(确定最大)中的数据大。
     *
     * @param num
     */
    public void Insert(Integer num) {
        // 偶数个入小根堆。
        if (((min.size() + max.size()) & 1) == 0) { // 比取模效率高。

            // 满足这个条件不能直接放新元素放入小根堆。
            // num 比max的最大要小，那么直接放入min中会不满足min中所有元素都要比max中所有元素大的要求。
            if (max.size() > 0 && num < max.peek()) {
                max.add(num);
                num = max.remove();
            }
            min.add(num);

            // 奇数个入大根堆
        } else {
            // 满足这个条件不能直接将新元素放入大根堆中。
            if (min.size() > 0 && num > min.peek()) {
                min.add(num);
                num = min.remove();
            }
            max.add(num);
        }
    }

    public Double GetMedian() {
        int size = min.size() + max.size();
        if (size == 0) {
            throw new RuntimeException("no input");
        }
        double result = 0;

        // 总数为奇数个，那么min中有 size / 2 + 1个。
        if ((size & 1) == 1) {
            result = min.peek();
        } else {
            result = (min.peek() + max.peek()) / 2.0;
        }
        return result;

    }

    /*
    参考：
    链接：https://www.nowcoder.com/questionTerminal/9be0172896bd43948f8a32fb954e1be1
    来源：牛客网*/

    ArrayList<Integer> al = new ArrayList<Integer>();

    public void Insert2(Integer num) {
        al.add(num);
        Collections.sort(al);
    }

    public Double GetMedian2() {
        int mid = al.size() / 2;
        if ((al.size() & 1) == 0) {
            Integer n1 = al.get(mid);
            Integer n2 = al.get(mid - 1);
            double s = (Double.valueOf(n1 + "") + Double.valueOf(n2 + "")) / 2;
            return s;
        } else {
            double s = Double.valueOf(al.get(mid) + "");
            return s;
        }
    }

    public static void main(String[] args) {

    }
}

