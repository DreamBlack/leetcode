import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode id=140 lang=java
 *
 * [140] Word Break II
 *
 * https://leetcode.com/problems/word-break-ii/description/
 *
 * algorithms
 * Hard (28.16%)
 * Likes:    1330
 * Dislikes: 295
 * Total Accepted:    188.2K
 * Total Submissions: 649.1K
 * Testcase Example:  '"catsanddog"\n["cat","cats","and","sand","dog"]'
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, add spaces in s to construct a sentence where each word is
 * a valid dictionary word. Return all such possible sentences.
 * 
 * Note:
 * 
 * 
 * The same word in the dictionary may be reused multiple times in the
 * segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 * 
 * 解题思路：
 * 1、dfs+memo
 * dfs返回解空间中所有可能的句子。map记录每个string，可以被分解为单词表中的哪些
 * 单词。
 * 2、dp+dfs
 * 先用dp，后在dp的表里进行dfs。那么在dp的时候势必要记下可以到达当前点的路径
 */

// @lc code=start
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<Integer>[] starts = new List[s.length() + 1];
        starts[0] = new ArrayList<Integer>();
        for (int i = 1; i <= s.length(); i++) {
            for (String word : wordDict) {
                int wordLen = word.length();

                if (wordLen <= i && word.equals(s.substring(i - wordLen, i))) {
                    if (starts[i - wordLen] == null) {
                        continue;// 这句话不能少，因为要想starts[i].add(i-wordLen);
                        // 这句话执行，则第i-wordLen也必须是可达的
                    }
                    if (starts[i] == null) {
                        starts[i] = new ArrayList<>();
                    }
                    starts[i].add(i - wordLen);
                }
            }
            // if(starts[i]!=null){
            // System.out.println("i="+i+",starts=");
            // System.out.println(starts[i]);
            // }
        }
        List<String> res = new ArrayList<>();
        if (starts[s.length()] == null) {
            return res;
        }
        dfs(res, "", s, starts, s.length());// 从末尾s.length开始，而不是0
        return res;
    }

    private void dfs(List<String> res, String path, String s, List<Integer>[] starts, int end) {
        if (end == 0) {
            res.add(path.substring(1));
        }
        for (Integer start : starts[end]) {
            String word = s.substring(start, end);
            dfs(res, " " + word + path, s, starts, start);
        }
    }

    Map<String, LinkedList<String>> map;

    public List<String> wordBreak1(String s, List<String> wordDict) {
        map = new HashMap<String, LinkedList<String>>();
        return dfs1(s, wordDict);
    }

    List<String> dfs1(String s, List<String> words) {
        LinkedList<String> res = new LinkedList<>();
        // 退出语句
        if (s.length() == 0) {
            res.add("");// 这里必须加一个元素再返回，否则for(String t:tmp){这里根本不会执行，因为是empty(),但不是Null
            return res;
        }
        if (map.containsKey(s)) {
            return map.get(s);
        }
        for (int j = 0; j < s.length(); j++) {
            if (words.contains(s.substring(0, j + 1))) {
                List<String> tmp = dfs1(s.substring(j + 1), words);
                for (String t : tmp) {
                    res.add(s.substring(0, j + 1) + (t == "" ? "" : " ") + t);
                }

            }
        }
        map.put(s, res);
        return res;

    }
}
// @lc code=end
