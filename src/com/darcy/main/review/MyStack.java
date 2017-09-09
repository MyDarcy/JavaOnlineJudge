package com.darcy.main.review;

import java.util.Arrays;

/**
 * Author by darcy
 * Date on 17-9-9 下午1:31.
 * Description:
 */
public class MyStack<E> {
  private Object[] stack;
  private int size;
  public static final int INIT_SIZE = 10;

  public MyStack() {
    stack = new Object[INIT_SIZE];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public E peek() {
    if (isEmpty())
      return null;
    return (E) stack[size - 1];
  }

  public E pop() {
    E e = peek();
    stack[size - 1] = null;
    size--;
    return e;
  }


  public E push(E item) {
    ensureCapacity();
    stack[size++] = item;
    return item;
  }

  private void ensureCapacity() {
    if (size >= stack.length) {
      int newLength = size * 2 ;
      // 数组的复制.
      stack = Arrays.copyOf(stack, newLength);
    }
  }

  public static void main(String[] args) {
    MyStack<Integer> myStack = new MyStack<>();
    for (int i = 0; i < 11; i++) {
      myStack.push(i);
    }
    System.out.println(myStack.size);
    System.out.println(myStack.stack.length);
  }

}
