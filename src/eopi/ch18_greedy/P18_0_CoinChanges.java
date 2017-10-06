package eopi.ch18_greedy;

/**
 * Author by darcy
 * Date on 17-10-6 上午10:02.
 * Description:
 * <p>
 * 换零钱问题.
 * 某种货币单位下, 换一定数目的钱, 使得总的coins数目最小.
 */
public class P18_0_CoinChanges {

  public static final int[] COINS = new int[]{100, 50, 25, 10, 5, 1};

  /**
   *
   * @param cents
   * @return
   */
  public static int changeCoins(int cents) {
    int numberOfCoins = 0;
    for (int i = 0; i < COINS.length; i++) {
      int temp = cents / COINS[i];
      numberOfCoins += temp;
      cents %= COINS[i];
      System.out.println("$" + COINS[i] + " -> " + temp);
    }

    return numberOfCoins;
  }

  public static void main(String[] args) {
    int numberOfCoins = changeCoins(198);
    System.out.println(numberOfCoins);

  }

}
