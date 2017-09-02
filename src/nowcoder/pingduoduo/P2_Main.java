package nowcoder.pingduoduo;

import java.util.*;

/**
 * Created by hzq19
 * Date on 2017/9/2 15:47.
 * Description:
 *
 * 树形 命令的实现
 * tree 命令。
 */
public class P2_Main {

  static class File {
    String fileName;
    List<File> children;
    Integer parent;
    Integer id;

    @Override
    public String toString() {
      /*return "File{" +
          "fileName='" + fileName + '\'' +
          ", parent=" + parent +
          '}';*/
      return fileName + " " + parent;
    }
  }


  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int nodeNumber = Integer.parseInt(input.nextLine());
    int i = 0;
    List<File> files = new ArrayList<>(nodeNumber);
    String[] splits = null;
    Map<Integer, File> map = new HashMap<>();
    while (i < nodeNumber) {
      File file = new File();
      String[] split = input.nextLine().trim().split("\\s+");
      file.fileName = split[0];
      file.parent = Integer.parseInt(split[1]);
      file.id = i;
      files.add(file);
      map.put(i, file); // id -> file.
      i++;
    }

    input.close();

    /*System.out.println(files);
    System.out.println(map);*/

    // 对files根据其parent进行排序.
    Collections.sort(files, new Comparator<File>() {
      @Override
      public int compare(File o1, File o2) {
        if (o1.parent < o2.parent) {
          return -1;
        } else if (o1.parent > o2.parent) {
          return 1;
        } else {
          return 0;
        }
      }
    });

    System.out.println(files);

    File root = map.get(-1); // 获取到根节点.

    // 建立关联关系.
    for (File file : files) {
      if (file.parent == -1) {
        root = file;
        // 不为-1;
      } else {
        Integer parentId = file.parent;
        File parent = map.get(parentId);
        if (parent.children == null) {
          parent.children = new ArrayList<>();
        }
        // 当前文件添加到父节点的链表中.
        parent.children.add(file);
      }
    }

    solution(root, 0, false);
  }

  static Comparator<File> fileComparator =
      new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
          return o1.fileName.compareTo(o2.fileName);
        }
      };

  public static void solution(File root, int i, boolean last) {

    if (i == 0) {
      System.out.println(root.fileName);
    } else {
      for (int j = 0; j < i - 1; j++) {
        System.out.print("   ");
        if (!last) {
          System.out.print("|-- ");
        } else {
          System.out.print("`-- ");
        }
        System.out.println(root.fileName);
      }
    }

    List<File> children = root.children;
    if (children != null) {
      Collections.sort(children, fileComparator);
      for (int j = 0; j < children.size(); j++) {
        if (j == children.size() - 1) {
          solution(children.get(j), i + 1, true);
        } else {
          solution(children.get(j), i + 1, false);
        }
      }
    }
  }

}

/*
10
my-app -1
src 0
main 1
java 2
resources 2
webapp 2
test 1
java 6
resources 6
pom.xml 0
 */