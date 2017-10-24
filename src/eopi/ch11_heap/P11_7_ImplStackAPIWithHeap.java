package eopi.ch11_heap;

import java.util.PriorityQueue;

/**
 * Author by darcy
 * Date on 17-9-24 下午6:29.
 * Description:
 * <p>
 * 用堆来实现一个stack.
 * 思路: 就是要记录每个元素的插入顺序, 记录一个时间戳, 每次插入一个元素, 都+1,
 * 然后将堆组织为按照时间戳排定的小顶堆.
 */
public class P11_7_ImplStackAPIWithHeap {

  public static class ValueWithRank {
    int value;
    int rank;

    public ValueWithRank(int value, int rank) {
      this.value = value;
      this.rank = rank;
    }
  }

  /**
   * 实现一个栈.
   */
  public static class Stack {
    private int timestamp = 0;
    private PriorityQueue<ValueWithRank> minHeap =
        new PriorityQueue<>((v1, v2) -> Integer.compare(v2.rank, v1.rank));

    /**
     * push, pop的复杂度都是O(logN)
     *
     * @param number
     */
    public void push(Integer number) {
      minHeap.add(new ValueWithRank(number, timestamp++));
    }

    public Integer pop() {
      return minHeap.remove().value;
    }

    public Integer peek() {
      return minHeap.peek().value;
    }
  }

  public static void main(String[] args) {
    Stack stack = new Stack();
    for (int i = 0; i < 10; i++) {
      stack.push(i);
    }

    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());

  }

}

/*
Variant: How would you implement a queue API using a heap?
// 实现一个队列也是记录其插入顺序. 时间戳从Integer.maxInteger开始记录. 插入一个元素则递减.
 */
