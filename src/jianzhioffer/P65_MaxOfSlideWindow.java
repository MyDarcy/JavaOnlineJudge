package jianzhioffer;

import java.util.*;

/**
 * Created by darcy
 * 2017/5/30--10:18
 * Description:
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，
 * 他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}，
 * {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 */
public class P65_MaxOfSlideWindow {

    /**
     * 思路: 一个队列维护先入先出，一个容量为size的优先级队列(堆)取当前最大元素。然后不断的头部删除尾部加入...
     * 注意优先级队列的remove(element)函数只会删除重复元素的一个。
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        if (num == null || size > num.length || size <= 0) {
            return new ArrayList<>();
        }

        Deque<Integer> queue = new LinkedList<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < size - 1; i++) {
            queue.addLast(num[i]);
            maxHeap.add(num[i]);
        }
        // 已经处理size -1个;
        int index = size - 1;
        while (index < num.length) {
            queue.addLast(num[index]);
            maxHeap.add(num[index]);
            result.add(maxHeap.peek());

            Integer integer = queue.removeFirst();
            maxHeap.remove(integer);
            index++;
        }

        return result;
    }


    /**
     * 思路: 只把可能成为最大值的元素插入到双端队列中。如果新待插入的元素比queue中末尾元素大，那么就需要把
     * 队列中尾元素删除，因为其和当前元素一起不可能组成成员size个元素中的最大值。而它可能作为最大元素的情况在
     * 处理当前元素之前就可能出现过。同时如果队列的头部元素距离当前元素超过size，那么就需要把头部元素删除。
     *
     * 同时，队列中存放的是index而不是具体的元素值。
     *
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows2(int [] num, int size) {
        if (num == null || size > num.length || size <= 0) {
            return new ArrayList<>();
        }

        Deque<Integer> queue = new LinkedList<Integer>();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            while (!queue.isEmpty() && num[i] >= num[queue.getLast()]) {
                queue.removeLast();
            }
            queue.addLast(i);
        }

        for (int i = size; i < num.length; i++) {
            result.add(num[queue.getFirst()]);
            while (!queue.isEmpty() && num[i] >= num[queue.getLast()]) {
                queue.removeLast();
            }
            // 因为每次处理当前元素的时候都会尝试去移除头部距离当前超过size的元素。
            if (!queue.isEmpty() && queue.getFirst() <= (i - size)) {
                queue.removeFirst();
            }
            // 加入了新的index;但是这一轮代表的size个元素的最大值还没有加入到结果集合中。
            queue.addLast(i);
        }
        result.add(num[queue.getFirst()]);
        return result;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        for (int i = 0; i < 10; i++) {
            min.add(i);
        }
        min.add(0);
        min.add(0);
        System.out.println("size:" + min.size());
        min.remove(0);
        System.out.println("size:" + min.size());
    }
}
