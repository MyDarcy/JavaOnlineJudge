package com.darcy.main.review;

import ctci5th.chapter8.section3.p833_setofstacks.Stack;

/**
 * Author by darcy
 * Date on 17-9-9 下午1:48.
 * Description:
 */

class StackNode<E> {
  E data;
  StackNode next;

  StackNode(E data) {
    this.data = data;
  }

  StackNode(E data, StackNode next) {
    this.data = data;
    this.next = next;
  }

}

public class MyLinkedStack<E> {

  StackNode<E> top = null;

  public boolean isEmpty() {
    return top == null;
  }



  public E peek() {
    if (isEmpty()) {
      return null;
    }
    return top.data;
  }

  public void push(E data) {
    top = new StackNode<E>(data, top);
  }

  public E pop() {
    E peek = peek();
    top = top.next;
    return peek;
  }

}
