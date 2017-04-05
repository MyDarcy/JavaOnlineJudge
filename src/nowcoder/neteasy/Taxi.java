package nowcoder.neteasy;

import java.util.Scanner;

/**
 * Created by csqiang on 2017/3/26.
 *
 * @Author mr.darcy
 * Description:
 */
public class Taxi {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int taxiNumber = Integer.parseInt(input.nextLine().trim());

        // 横坐标;
        String[] tXString = input.nextLine().trim().split("\\s+");

        // 纵坐标;
        String[] tYString = input.nextLine().trim().split("\\s+");

        // office坐标;
        String[] officeStringArray = input.nextLine().trim().split("\\s+");

        // 时间;
        String[] timeStringArray = input.nextLine().trim().split("\\s+");

        int[] tX = new int[tXString.length];
        for (int i = 0; i < tX.length; i++) {
            tX[i] = Integer.parseInt(tXString[i]);
        }

        int[] tY = new int[tYString.length];
        for (int i = 0; i < tY.length; i++) {
            tY[i] = Integer.parseInt(tYString[i]);
        }

        int officeX = Integer.parseInt(officeStringArray[0]);
        int officeY = Integer.parseInt(officeStringArray[1]);

        int walkTime = Integer.parseInt(timeStringArray[0]);
        int taxiTime = Integer.parseInt(timeStringArray[1]);

        int timeTime = walkTime * (Math.abs(officeX) + Math.abs(officeY));
        for (int i = 0; i < tX.length; i++) {
            // 先走路，后打车；
            int time1 = walkTime * (Math.abs(tX[i]) + Math.abs(tY[i]))
                    + taxiTime * (Math.abs(tX[i] - officeX) + Math.abs(tY[i] - officeY));
//            int time2 = taxiTime * (Math.abs(tX[i]) + Math.abs(tY[i]))
//                    + walkTime * (Math.abs(tX[i] - officeX) + Math.abs(tY[i] - officeY));
//
//            timeTime = (time1 < time2)?
//                    (time1 < timeTime ? time1:timeTime) : (time2 < timeTime ? time2 : timeTime);

           /* int time3 = walkTime * (Math.abs(tX[i] - officeX) + Math.abs(tY[i] - officeY));

            timeTime = (time1 < time3)?
                    (time1 < timeTime ? time1:timeTime) : (time3 < timeTime ? time3 : timeTime);
*/
            if (time1 < timeTime) {
                timeTime = time1;
            }
        }

        System.out.println(timeTime);
    }
}

/*
2
-2 -2
0 -2
-4 -2
15 3

1
3
0
5 0
10 20

25
1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5
1 1 1 1 1 2 2 2 2 2 3 3 3 3 3 4 4 4 4 4 5 5 5 5 5
5 5
7 2
 */