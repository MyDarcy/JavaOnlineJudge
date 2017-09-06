package eopi.ch17_dp;

/**
 * Author by darcy
 * Date on 17-9-6 下午9:25.
 * Description:
 * <p>
 * 背包问题
 */
public class PX2_BagProblems {

  /**
   * 子问题定义：F[i][j]表示前i件物品中选取若干件物品放入剩余空间为j的背包中所能得到的最大价值。
   *
   * 其中F[i-1][j]表示前i-1件物品中选取若干件物品放入剩余空间为j的背包中所能得到的最大价值；
   *
   * 而F[i-1][j-C[i]]+W[i]表示前i-1件物品中选取若干件物品放入剩余空间为j-C[i]的背包中所能取得
   * 的最大价值加上第i件物品的价值。
   * &&&&根据第i件物品放或是不放入&&&&确定遍历到第i件物品时的状态F[i][j]。
   * 设物品件数为N，背包容量为V，第i件物品体积为C[i]，第i件物品价值为W[i]。
   * @param values
   * @param weights
   * @param capacity
   * @return
   */
  public static int DP(int[] values, int[] weights, int capacity) {
    int len = values.length;
    //开辟大小为len+1,capacity+1大小的空间
    int[][] V = new int[len + 1][capacity + 1];
    //前0个物品，容量为多少价值都是0
    for (int i = 0; i < capacity; i++) {
      V[0][i] = 0;
    } //容量为0，前多少个物品的价值都是0
    for (int i = 0; i < len; i++) {
      V[i][0] = 0;
    }
    for (int space = 1; space <= capacity; space++) {
      for (int i = 1; i <= values.length; i++) {
        //当前的物品的容量小于等于剩余容量，则判断加入当前物品后总容量与未加入容量的大小比较
        if (weights[i - 1] <= space) {
          V[i][space] = Math.max(values[i - 1] + V[i - 1][space - weights[i - 1]], V[i - 1][space]);
        } else {
          //如果当前物品的容量大于剩余容量，则一定不能加入该物品，则最大价值与前n-1个的最大价值相同
          V[i][space] = V[i - 1][space];
        }
      }
    }
    for (int[] itemSpace : V) {
      for (int j : itemSpace) {
        System.out.printf("%3d", j);
      }
      System.out.println();
    }
    return V[len][capacity];
  }

  public static void main(String[] args) {
    int Weight[] = {2, 3, 1, 4, 6, 5};
    int Value[] = {5, 6, 5, 1, 19, 7};
    int nCapacity = 10;
    System.out.println(DP(Value, Weight, nCapacity));
  }
}
