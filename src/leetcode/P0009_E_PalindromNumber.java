package leetcode;

/**
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 *
 * Example 1:
 *
 * Input: 121
 * Output: true
 * Example 2:
 *
 * Input: -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * Example 3:
 *
 * Input: 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 */
public class P0009_E_PalindromNumber {

  public boolean isPalindrome(int x) {
    if (x < 0) {
      return false;
    }
    String numStr = String.valueOf(x);
    int length = numStr.length();
    for (int i = 0; i < length / 2; i++) {
      if (numStr.charAt(i) != numStr.charAt(length - i - 1)) {
        return false;
      }
    }
    return true;
  }

  /**
   * x==rev is for the scenery of even digits, x==rev/10 for the odd digits.
   * @param x
   * @return
   */
  public boolean isPalindrome2(int x) {
    // 负数， 一位数；
    if (x<0 || (x!=0 && x%10==0)) return false;
    int reverse = 0;
    // compare half of the digits in x, so don't need to deal with overflow.
    while (x>reverse){
      reverse = reverse*10 + x%10;
      x = x/10;
    }
    return (x==reverse || x==reverse/10);
  }

  public static void main(String[] args) {
    P0009_E_PalindromNumber instance = new P0009_E_PalindromNumber();
    System.out.println(instance.isPalindrome(-90));
    System.out.println(instance.isPalindrome(90));
    System.out.println(instance.isPalindrome(909));
    System.out.println(instance.isPalindrome(9009));
  }

}
