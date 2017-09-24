package eopi.ch11_heap;

import java.util.*;

/**
 * Author by darcy
 * Date on 17-9-24 下午4:32.
 * Description:
 *
 * 计算k个最相近的星星.
 *
 * 1万亿.
 *
 * The Milky Way consists of approximately 10^12 stars, and their coordinates are stored in a file.
 * How would you compute the k stars which are closest to Earth?
 *
 */
public class P11_4_ComputeKClosestStars {

  static class Star implements Comparable<Star> {
    int x, y , z;

    public Star(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    public double distance() {
      return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public int compareTo(Star o) {
      return Double.compare(distance(), o.distance());
    }

    @Override
    public String toString() {
      return "(" + x + ", " + y + ", " + z + ")";
    }

  }

  /**
   *
   * 维持一个最大堆, 容量为size, 那么就能获取到k个最小的元素.
   * 时间复杂度O(nlogK), 空间复杂度O(k)
   *
   * @param stars
   * @param k
   * @return
   */
  public static List<Star> solution(List<Star> stars, int k) {

    PriorityQueue<Star> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());

    for (Star star : stars) {
      maxHeap.add(star);
      if (maxHeap.size() == k + 1) {
        maxHeap.remove();
      }
    }

    List<Star> result = new ArrayList<>(maxHeap);
    Collections.sort(result);
    return result;
  }

  public static void main(String[] args) {
    List<Star> stars = Arrays.asList(
        new Star(1, 2, 3),
        new Star(3, 4, 5),
        new Star(1, 6, 7),
        new Star(1, 2, 2),
        new Star(1, 5, 4),
        new Star(2, 1, 1),
        new Star(2, 2, 10),
        new Star(4, 2, 9),
        new Star(5, 1, 6),
        new Star(5, 1, 16),
        new Star(10, 1, 16)
    );

    List<Star> result = solution(stars, 3);
    System.out.println(result);
  }

}

/*
Variant: Design an O(nlogk) time algorithm that reads a sequence of n elements and
for each element, starting from the kth element, prints the kth largest element read up
to that point. The length of the sequence is not known in advance. Your algorithm
cannot use more than O(k) additional storage. What are the worst-case inputs for
your algorithm?
 */