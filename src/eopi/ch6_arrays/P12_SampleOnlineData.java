package eopi.ch6_arrays;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Author by darcy
 * Date on 17-9-17 下午12:53.
 * Description:
 *
 * 连续读取的n个数字中随机均匀的选择K个数字.
 *
 * Design a program that takes as input a size k,and reads packets,
 * continuously maintaining a uniform random subset of size k of the read packets.
 *
 * Hint: Suppose you have a procedure which selects k packets from the first
 * n > k packets asspecified. How would you deal with the (n + l)th packet?
 */
public class P12_SampleOnlineData {

  /**
   * 暴力破解算法, 缓存读取的n个字符串.然后选择k个.借用上一个算法的方式.
   * <p>
   * 空间复杂度O(n),时间复杂度O(nk)
   *
   * @param iterator
   */
  public static List<Integer> bruteForceSolution(Iterator<Integer> iterator, int k) {
    return null;
  }

  /**
   *  读到一个新的数字, 那么它被选中作为候选集的元素的概率是 k / currentNumber;
   *  这里采用了一种巧妙的想法:就是当index = random.nextInt(currentNumber) < k的时候,
   *  那么就将读取到的数字替换index处的元素.这就是被选择的概率为k/currentNumber的体现.
   *
   * 时间复杂度O(N),空间复杂度O(k)
   * 问题: 单词迭代之间并不是彼此独立的,一次迭代的后继最多只有一个元素和当前迭代不同.
   *
   *  As an example, suppose k - 2, and the packets are read in the order p,q,r,t,u,v.
   *  We keep the first two packets in the subset, which is {p,q}. We select the next packet, r,
   *  with probability 2/3. Suppose it is not selected. Then the subset after reading the first
   *  three packets is still {p,q}. We select the next packet, t, with probability 2/4. Suppose
   *  it is selected. Then we choose one of the packets in {p,q) uniformly, and replace it
   *  with t. Let q be the selected packet—now the subset is [p,t}. We select the next packetu
   *  with probability 2/5. Suppose it is selected. Then we choose one of the packets in
   *  {p,t} uniformly, and replace it with u. Let t be the selected packet—now the subset is
   *  {p,u}. We select the next packet v with probability 2/6. Suppose it is not selected. The
   *  random subset remains {p,u}
   * @param iterator
   * @return
   */
  public static List<Integer> solution(Iterator<Integer> iterator, int k) {
    List<Integer> result = new ArrayList<>(k);
    // 先读取k个.
    for (int i = 0; i < k && iterator.hasNext(); i++) {
      result.add(iterator.next());
    }

    int numberSoFar = k;
    Random randomForNextIndex = new Random();
    while (iterator.hasNext()) {
      Integer element = iterator.next();
      numberSoFar++;
      // 有 k / numberSoFar的概率使得index落在[0, k]之间,这就代表了可以将当前读取的数字移动到index位置.
      // 随着不断新加入的元素, 那么其要被置换到前面的概率就是其index落在[0,k]之前的概率.
      int indexToReplace = randomForNextIndex.nextInt(numberSoFar);
      if (indexToReplace < k) {
        result.set(indexToReplace, element);
      }
      // 结果集合:
      System.out.println(result);
    }

    return result;
  }


  public static void main(String[] args) {
    int number = 100;
    List<Integer> list = new ArrayList<>(number);
    for (int i = 0; i < number; i++) {
      list.add(i);
    }

    Iterator<Integer> iterator = list.iterator();
    List<Integer> result = solution(iterator, 5);
    System.out.println(result);
  }
}
