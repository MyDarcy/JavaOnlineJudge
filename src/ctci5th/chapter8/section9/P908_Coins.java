package ctci5th.chapter8.section9;

/**
 * Author by darcy
 * Date on 17-7-30 下午4:03.
 * Description:
 * 给定数目不限的硬币,币值为1, 5, 10, 25分,
 * 那么n分有多少种表示方法.
 */
public class P908_Coins {
  /**
   * 子问题求解: 假设n=100
   * 100换零以后, 可以包含4, 3, 2, 1, 0个25分硬币.
   * P(100) =
   *     P(100, 使用0个25分) +
   *     P(75, 使用1个25分) +
   *     P(50, 使用2个25分) +
   *     P(25, 使用3个25分) +
   *     1(使用4个25分就只有一种情况)
   * 既然100跟25分的关系可以如上列觉, 那么上面的每一个子问题P()跟其他币值的关系同样可以列列举.
   * 展开就形成了一个树状结构.
   *
   * @param n
   * @param currencyKind
   * @return
   */
  public static int makeChanges(int n, int currencyKind) {
    int nextCurrencyKind = 0;
    switch (currencyKind) {
      case 25:
        nextCurrencyKind = 10;
        break;
      case 10:
        nextCurrencyKind = 5;
        break;
      case 5:
        nextCurrencyKind = 1;
        break;
      case 1:
        return 1;
    }

    int counts = 0;
    for (int i = 0; i * currencyKind <= n; i++) {
      counts += makeChanges(n - i * currencyKind, nextCurrencyKind);
    }

    return counts;
  }


}
