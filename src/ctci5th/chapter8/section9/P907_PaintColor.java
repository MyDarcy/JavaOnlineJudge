package ctci5th.chapter8.section9;

/**
 * Author by darcy
 * Date on 17-7-30 下午3:39.
 * Description:
 * 给定一个二维数组, 一个点,　一个新的颜色值, 将新颜色填入这个点的周围区域, 直到原来点的颜色值全部改变.
 */
public class P907_PaintColor {

  enum Color {
    Black, White, Red, Yellow, Green
  }

  public static boolean paintFill(Color[][] screen, int x, int y, Color newColor) {
    // 屏幕左上角为起点.
    if (screen[y][x] == newColor) {
      return false;
    }

    return paintFill(screen, x, y, screen[y][x], newColor);

  }

  private static boolean paintFill(Color[][] screen, int x, int y, Color oldColor, Color newColor) {
    if (x < 0 || x >= screen[0].length || y < 0 || y >= screen.length) {
      return false;
    }
    if (screen[y][x] == oldColor) {
      screen[y][x] = newColor;
      paintFill(screen, x, y - 1, oldColor, newColor); // 上
      paintFill(screen, x, y + 1, oldColor, newColor); // 下
      paintFill(screen, x - 1, y, oldColor, newColor); // 左
      paintFill(screen, x + 1, y, oldColor, newColor); // 右
    }
    return true;
  }

  private static String color2Char(Color c) {
    switch(c) {
      case Black:
        return "B";
      case White:
        return "W";
      case Red:
        return "R";
      case Yellow:
        return "Y";
      case Green:
        return "G";
    }
    return "X";
  }

  public static void printScreen(Color[][] screen) {
    for (int i = 0; i < screen.length; i++) {
      for (int j = 0; j < screen[0].length; j++) {
        System.out.print(color2Char(screen[i][j]));
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int N = 10;
    Color[][] screen = new Color[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        screen[i][j] = Color.Black;
      }
    }
    for (int i = 0; i < 100; i++) {
      screen[randomInt(N)][randomInt(N)] = Color.Green;
    }
    printScreen(screen);
    paintFill(screen, 2, 2, Color.White);
    System.out.println();
    printScreen(screen);
  }

  public static int randomInt(int n) {
    return (int) (Math.random() * n);
  }

}
