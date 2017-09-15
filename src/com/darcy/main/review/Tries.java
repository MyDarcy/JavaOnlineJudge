package com.darcy.main.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-14 下午10:18.
 * Description:
 *
 * Trie树.
 *
 * next域的指针才代表本TrieNode代表往下走的当前字符.
 *
 */
public class Tries {

  static class TrieNode {
    List<String> words = new ArrayList<>();
    TrieNode[] next = new TrieNode[26];

    public TrieNode() {}
  }

  public void insertNode(TrieNode root, String wd) {
    if (wd.length() == 0) {
      return;
    }

    if (root == null) {
      root = new TrieNode();
    }

    char[] chars = wd.toCharArray();
    Arrays.sort(chars);

    TrieNode next = root;
    int i = 0;
    // 节点的next域.
    while (i < chars.length) {
      if (next.next[chars[i] - 'a'] == null) {
        TrieNode tn = new TrieNode();
        next.next[chars[i] - 'a'] = tn;
      }
      // 下一层节点.
      next = next.next[chars[i] - 'a'];
      i++;
    }
    next.words.add(wd);
  }

  public void searchNode(TrieNode root, String wd) {
    char[] chars = wd.toCharArray();
    Arrays.sort(chars);
    int i = 0;
    TrieNode iter = root;
    while (i < chars.length) {
      if (iter.next[chars[i] - 'a'] != null) {
        iter = iter.next[chars[i] - 'a'];
        i++;
      } else {
        break;
      }
    }

    if (i == chars.length) {
      for (String word : iter.words) {
        System.out.print(word + "\t");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Tries tries = new Tries();
    TrieNode root = new TrieNode();
    tries.insertNode(root, "hehao");
    tries.insertNode(root, "ehaoh");
    tries.insertNode(root, "haohe");
    tries.insertNode(root, "aoheh");
    tries.insertNode(root, "facri");
    tries.insertNode(root, "et");
    tries.insertNode(root, "oheha");

    tries.searchNode(root, "oheha");

  }

}
