package nowcoder.shangtang;

import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/10/12 20:48.
 * Description:
 *
 * 四个杯子的容量分别是S1 S2 S3 S4
 * 可以将每个杯子接满水; 也可以全部倒掉; 也可以A往B倒, 知道A空或者B满.
 * 那么最少要多少个步骤才能使得四个杯子装的水分别是T1 T2 T3 T4.
 *
 *
 */
public class Main3 {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int S1 = input.nextInt();
    int S2 = input.nextInt();
    int S3 = input.nextInt();
    int S4 = input.nextInt();

    int T1 = input.nextInt();
    int T2 = input.nextInt();
    int T3 = input.nextInt();
    int T4 = input.nextInt();

    input.close();


  }

}


