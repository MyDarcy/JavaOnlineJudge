package eopi.ch25_honors;

import java.util.List;

/**
 * Author by darcy
 * Date on 17-10-23 上午10:22.
 * Description:
 * <p>
 * 计算最长的连续递增子数组.
 * <p>
 * Implement an algorithm that takes as input an array A of n elements, and returns the
 * beginning and ending indices of a longest increasing subarray of A. For example, if
 * A = (2,11,3,5,13, 7,19,17, 23), the longest increasing subarray is (3,5,13), and you
 * should return (2,4)
 */
public class P5_ComputeTheLongestContiguousIncreasingSubarray {

  public static class SubArray {
    int start;
    int end;

    public SubArray(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  /**
   * @param A
   * @return
   */
  public static SubArray solution(List<Integer> A) {
    int maxLength = 1;
    SubArray ans = new SubArray(0, 0);
    int i = 0;
    while (i < A.size() - maxLength) {
      // Backward check and skip if A[j - 1] >= A[j].
      boolean isSkippable = false;
      for (int j = i + maxLength; j > i; --j) {
        if (A.get(j - 1) >= A.get(j)) {
          i = j;
          isSkippable = true;
          break;
        }
      }
      // Forward check if it is not skippable.
      if (!isSkippable) {
        i += maxLength;
        while (i < A.size() && A.get(i - 1) < A.get(i)) {
          ++i;
          ++maxLength;
        }
        ans = new SubArray(i - maxLength, i - 1);
      }
    }
    return ans;
  }

  public static void main(String[] args) {

  }

}
