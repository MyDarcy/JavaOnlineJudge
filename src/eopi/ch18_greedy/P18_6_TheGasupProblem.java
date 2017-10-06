package eopi.ch18_greedy;

import java.util.List;

/**
 * Author by darcy
 * Date on 17-10-6 上午11:47.
 * Description:
 *
 *
 *
 */
public class P18_6_TheGasupProblem {

  public static class CityAndRemainingGas {
    public Integer city;
    public Integer remainingGallons;

    public CityAndRemainingGas(Integer city, Integer remainingGallons) {
      this.city = city;
      this.remainingGallons = remainingGallons;
    }
  }

  public static final int MPG = 20; // meter per gallon.

  /**
   *
   * @param gallons
   * @param distances
   * @return
   */
  public static int solution(List<Integer> gallons, List<Integer> distances) {
    int remainingGallons = 0;
    CityAndRemainingGas min = new CityAndRemainingGas(0, 0);
    final int numberCities = gallons.size();
    for (int i = 1; i < numberCities; i++) {
      remainingGallons += gallons.get(i - 1) - distances.get(i - 1) / MPG;
      if (remainingGallons < min.remainingGallons) {
        min = new CityAndRemainingGas(i, remainingGallons);
      }
    }
    return min.city;
  }

  public static void main(String[] args) {

  }

}
