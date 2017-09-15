package com.darcy.main.review;

/**
 * Author by darcy
 * Date on 17-9-15 上午10:06.
 * Description:
 *
 * 桶排序.
 *
 */
public class BucketSort {

  static class BucketNode {
    int key;
    BucketNode next;

    public BucketNode(int key) {
      this.key = key;
    }

    public BucketNode() {
    }
  }

  /**
   * 桶排序.
   * @param keys
   * @param bucketSize
   */
  public void bucketSort(int[] keys, int bucketSize) {
    BucketNode[] buckets = new BucketNode[bucketSize];
    for (int i = 0; i < bucketSize; i++) {
      buckets[i] = new BucketNode();
      buckets[i].key = 0;
      buckets[i].next = null;
    }

    for (int i = 0; i < keys.length; i++) {
      BucketNode node = new BucketNode(keys[i]);
      int index = keys[i] / 10;
      BucketNode p = buckets[index];
      if (p.key == 0) {
        buckets[index].next = node;
        (buckets[index].key)++;
      } else {
        BucketNode pre = p;
        // p.next本身就是维护了p指针.
        while (p.next != null && p.next.key <= node.key) {
          p = p.next;
        }

        node.next = p.next;
        p.next = node;

        // 第一个节点就是记录此链表中有多少值.
        (buckets[index].key)++;
      }
    }

    for (int i = 0; i < bucketSize; i++) {
      for(BucketNode node = buckets[i].next; node != null; node = node.next) {
        System.out.print(node.key + " ");
      }
    }
  }

  public static void main(String[] args) {
    int[] array = {45, 16, 20, 7, 90, 18, 76, 19, 58, 99, 2, 8};
    BucketSort bucketSort = new BucketSort();
    int bucketSize = 10;
    bucketSort.bucketSort(array, bucketSize);

  }
}
