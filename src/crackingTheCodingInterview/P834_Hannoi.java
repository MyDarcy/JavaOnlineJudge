package crackingTheCodingInterview;

/**
 * Author by darcy
 * Date on 17-7-7 上午10:21.
 * Description:
 * 简单hannoi问题.
 */
public class P834_Hannoi {

    public void solution(int n) {
        solution(n, 'A', 'B', 'C');
    }

    private void solution(int n, char from, char middle, char to) {
        if (n == 0) {
            return;
        }
        solution(n - 1, from, to, middle);
        System.out.printf("disk %d from %c to %c\n", n, from, to);
        solution(n - 1, middle, from, to);
    }

    public static void main(String[] args) {
        P834_Hannoi demo = new P834_Hannoi();
        demo.solution(4);
    }

}
