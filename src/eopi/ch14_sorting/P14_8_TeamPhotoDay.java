package eopi.ch14_sorting;

import java.util.*;

/**
 * Author by darcy
 * Date on 17-9-30 下午8:39.
 * Description:
 * <p>
 * 两支足球队伍排成前后两排照相, 每一排都只能是同一队的并且后面的人一定要比前面的人高(这样才不会挡住);
 * <p>
 * <p>
 * Design an algorithm that takes as input two teams and the heights of the players in the teams and
 * checks if it is possible to place players to take the photo subject to the placement constraint.
 * <p>
 * Hint: First try some concrete inputs, then make a general conclusion.
 */
public class P14_8_TeamPhotoDay {

  public static class Player implements Comparable<Player> {
    public Integer height;

    public Player(Integer height) {
      this.height = height;
    }


    @Override
    public int compareTo(Player o) {
      return Integer.compare(height, o.height);
    }
  }

  public static class Team {
    public List<Player> players;

    public Team(List<Player> ps) {
      players = new ArrayList<>(ps.size());
      for (int i = 0; i < ps.size(); i++) {
        players.add(ps.get(i));
      }
    }

    public List<Player> sort() {
      List<Player> result = new ArrayList<>(players);
      Collections.sort(result);

      return result;
    }
  }

  /**
   * 时间复杂度O(NlogN), 空间复杂度O(N)
   *
   * @param t1
   * @param t2
   * @return
   */
  public static boolean solution(Team t1, Team t2) {
    List<Player> sortedTeam1 = t1.sort();
    List<Player> sortedTeam2 = t2.sort();

    for (int i = 0; i < sortedTeam1.size() && i < sortedTeam2.size(); i++) {
      if (sortedTeam1.get(i).compareTo(sortedTeam2.get(i)) >= 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    List<Player> players1 = Arrays.asList(
        new Player(180),
        new Player(198),
        new Player(189),
        new Player(191),
        new Player(189),
        new Player(186),
        new Player(181),
        new Player(190)
    );
    Team t1 = new Team(players1);

    List<Player> players2 = Arrays.asList(
        new Player(181),
        new Player(195),
        new Player(180),
        new Player(192),
        new Player(189),
        new Player(189),
        new Player(198),
        new Player(190)
    );
    Team t2 = new Team(players2);

    boolean result = solution(t1, t2);
    System.out.println(result);
  }
}
