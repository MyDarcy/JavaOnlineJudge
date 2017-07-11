package ctci5th.chapter8.section3;

import java.util.Stack;

/**
 * Author by darcy
 * Date on 17-7-7 上午10:43.
 * Description:
 *
 * Hanoi塔问题使用栈来实现.
 */
public class P834_HannoiWithStack {

    public static void main(String[] args) {
        Tower first = new Tower(new Stack<>(), 0);
        Tower second = new Tower(new Stack<>(), 1);
        Tower third = new Tower(new Stack<>(), 2);

        int n = 3;
        for (int i = n; i >= 1; i--) {
            first.add(i);
        }

        first.moveDisks(n, second, third);
    }
}

class Tower {
    Stack<Integer> disks;
    int index;

    public Tower(Stack<Integer> disks, int index) {
        this.disks = disks;
        this.index = index;
    }

    public int index() {
        return index;
    }

    public void add(Integer disk) {
        // 保证后放入的disk小于之前放入的disk.否则抛出异常.
        if (!disks.isEmpty() && disk >= disks.peek()) {
            throw new RuntimeException("disk bigger than lower level. disk:" + disk + " peek:" + disks.peek());
        }
        disks.push(disk);
    }

    public void moveTopToDest(Tower dest) {
        Integer top = disks.pop();
        dest.add(top);
        System.out.println("Move disk " + top + " from " + index() + " to " + dest.index());
    }

    public void moveDisks(int n, Tower buffer, Tower dest) {
        if (n == 0) {
            return;
        }
        // 在当前tower上调用此方法, 将当前tower上disk移动到buffer上.
        // 此时tower上就只有一个盘子了.
        moveDisks(n - 1, dest, buffer);
        moveTopToDest(dest);
        // buffer缓冲上的盘子经由当前tower到达dest目标.
        buffer.moveDisks(n - 1, this, dest);
    }
}
