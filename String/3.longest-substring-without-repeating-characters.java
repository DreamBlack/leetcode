import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 *
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 *
 * algorithms
 * Medium (28.82%)
 * Likes:    6568
 * Dislikes: 379
 * Total Accepted:    1.1M
 * Total Submissions: 3.9M
 * Testcase Example:  '"abcabcbb"'
 *
 * Given a string, find the length of the longest substring without repeating
 * characters.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: "abcabcbb"
 * Output: 3 
 * Explanation: The answer is "abc", with the length of 3. 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3. 
 * ⁠            Note that the answer must be a substring, "pwke" is a
 * subsequence and not a substring.
 * 
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * solution中用的滑动窗口思想+map的方法 滑动窗口大小[i,j】 注意map里面保存的是下次应该从哪里开始滑动，所以put的时候是j+1
     * 
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int ans = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0, j = 0; j < s.length(); j++) {
            if (map.containsKey(s.charAt(j))) {
                // 更新窗口的左边界
                i = Math.max(map.get(s.charAt(j)), i);// 要取大的，就不用remove了

            }
            // 更新map，
            map.put(s.charAt(j), j + 1);// 表示下次应该从第j+1个开始找，而不是j
            ans = Math.max(ans, j - i + 1);

        }
        return ans;

    }

    /**
     * solution中用的滑动窗口思想方法 滑动窗口大小[i,j)
     * 
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringSlidingWindow(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int i = 0, j = 0, ans = 0;
        Set<Character> set = new HashSet<>();
        while (i < s.length() && j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;

    }

    /**
     * before[i]记录以第i个字符结尾的最长不重复子串， 所以before.Indexof(s[i])==i
     * 时间O(N^2),空间O（N），indexof也算n
     * 
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringDp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        String before = "" + s.charAt(0);
        int result = 1;
        for (int i = 1; i < s.length(); i++) {
            if (before.indexOf(s.charAt(i)) != -1) {
                before = before.substring(before.indexOf(s.charAt(i)) + 1);
            }
            before += s.charAt(i);

            result = result < before.length() ? before.length() : result;
        }
        return result;
    }

    /**
     * 暴力的方法 时间O(N^3),空间O（1）
     * 
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringBruteForce(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int result = 1;
        for (int i = 0; i < s.length(); i++) {
            int cnt = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.lastIndexOf(s.charAt(j), j - 1) >= i) {// 这里条件没写清楚，应该是出现的位置在子串[i,j-1]内
                    // 前面出现过字符s[j]
                    // i=s.indexOf(s.charAt(j));不可以用这个abcabcbb，
                    // 处理到倒数第二个b的时候，indexof会返回1，这样i又从2开始循环，就死循环了
                    i = s.lastIndexOf(s.charAt(j), j - 1);
                    break;
                } else {
                    cnt++;
                }
            }
            result = result < cnt ? cnt : result;
        }
        return result;

    }
}
// @lc code=end
