package nowcoder.jiandatimu;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hzq19
 * Date on 2017/8/30 22:00.
 * Description:
 */

interface BlockingQueue<E>{
    public void put(E e) throws InterruptedException;
    public E take() throws InterruptedException;
}

/*
public class MyBlockingQueue<E> implements BlockingQueue<E> {
    private final int capacity = 10000;
    private final AtomicInteger count = new AtomicInteger(0);
    */
/*privateNodehead;
    private Nodelast;*//*

    private final ReentrantLocktakeLock = new ReentrantLock();
    private final Condition notEmpty = takeLock.newCondition();
    private final ReentrantLockputLock = new ReentrantLock();
    private final Condition notFull = putLock.newCondition();
    publicMyBlockingQueue() {
        last = head = new Node(null);
    }
    public void put(Object e) throws InterruptedException {
        if (e == null) throw new NullPointerException();
        int c = -1;
        Nodenode = new Node(e);
        finalReentrantLockputLock = this.putLock;
        finalAtomicInteger count = this.count;
        putLock.lockInterruptibly();
        try {
            while (count.get() == capacity) {
                notFull.await();
            }
            enqueue(node);
            c = count.getAndIncrement();
            if (c + 1 < capacity)
                notFull.signal();
        } finally {
            putLock.unlock();
        }
        if (c == 0)
            signalNotEmpty();
    }
    public Object take() throws InterruptedException {
        E x;
        int c = -1;
        finalAtomicInteger count = this.count;
        finalReentrantLocktakeLock = this.takeLock;
        takeLock.lockInterruptibly();
        try {
            while (count.get() == 0) {
                notEmpty.await();
            }
            x = dequeue();
            c = count.getAndDecrement();
            if (c > 1)
                notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
        if (c == capacity)
            signalNotFull();
        return x;
    }
    private void enqueue(Nodenode) {
        last = last.next = node;
    }
    private E dequeue() {
        Nodeh = head;
        Nodefirst = h.next;
        h.next = h; // help GC
        head = first;
        E x = first.item;
        first.item = null;
        return x;
    }
    private void signalNotEmpty() {
        finalReentrantLocktakeLock = this.takeLock;
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }
    private void signalNotFull() {
        finalReentrantLockputLock = this.putLock;
        putLock.lock();
        try {
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }
    static class Node{
        E item;
        Nodenext;
        Node(E x) {
            item = x;
        }
    }
}
*/
