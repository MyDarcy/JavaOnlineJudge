package nowcoder.baidu;

/**
 * Created by hzq19
 * Date on 2017/9/19 21:23.
 * Description:
 */
// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED

import java.util.*;

public class Police {
  // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
  public int minimumNumberOfGunShots(int num, int shotDegrade, int remDegrade,
                                     int[] health) {
    // WRITE YOUR CODE HERE
    int diff = shotDegrade - remDegrade;
    // PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);

    if (num == 1) {
      return num % shotDegrade == 0 ? num / shotDegrade : num / shotDegrade + 1;
    }

    Arrays.sort(health);

    // 不满足条件, 下一轮循环.
    int count = 0;
    while (true) {

      // 一开始就没有活的....
      if (health[num - 1] <= 0) {
        break;
      }

      count++;
      for (int i = num - 1; i >= 0; i--) {
        if (i == num - 1) {
          health[i] -= shotDegrade;
        } else {
          health[i] -= remDegrade;
        }
      }

      int temp = health[num - 1];
      int j = num - 2;
      while (j >= 0 && health[j] > 0 && health[j] > temp) {
        health[j + 1] = health[j]; // 后移动;
        j--;
      }

      health[j + 1] = temp;

      if (health[num - 1] <= shotDegrade && health[num - 2] <= remDegrade) {
        //已经结束了.
        if (health[num - 1] <= 0) {
          break;

          // 还需要开一枪的.
        } else {
          count++;
          break;
        }
      }
    }

    return count;


  }
  // METHOD SIGNATURE ENDS


	/*private int max(int[] health) {
	    int result = 0;
	    for(int i = 0; i < health.length; i++) {
	        if(health[i] > result) {
	            result = health[i];
	        }
	    }
	    return result;
	}

	private int secondMax(int[] health) {
	    int maxResult = 0;

	}*/
}
