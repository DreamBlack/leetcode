/*
 * @lc app=leetcode id=14 lang=java
 *
 * [14] Longest Common Prefix
 *
 * https://leetcode.com/problems/longest-common-prefix/description/
 *
 * algorithms
 * Easy (34.11%)
 * Likes:    1991
 * Dislikes: 1657
 * Total Accepted:    637.3K
 * Total Submissions: 1.8M
 * Testcase Example:  '["flower","flow","flight"]'
 *
 * Write a function to find the longest common prefix string amongst an array
 * of strings.
 * 
 * If there is no common prefix, return an empty string "".
 * 
 * Example 1:
 * 
 * 
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * 
 * 
 * Note:
 * 
 * All given inputs are in lowercase letters a-z.
 * 
 * 解题思路：
 * 遍历数组，记录最短的字符串的长度为m；之后对于1-m判断，n个字符串
 * 的第i位字符是否相同，不相同直接返回，相同的话记录字符到result中
 * 1、水平扫描
 * 2、垂直扫描
 * 3、分治
 * 4、二分搜索
 * 5、
 * 
 * 
 * 复杂度
 * O(M*N)
 * 
 * 边界
 * 字符串数组为null，字符串数组为空
 * 
 */

// @lc code=start
class TireNode {
    private TireNode[] links;
    private final int R = 26;// 每个结点可能有的最多的子节点的个数
    private boolean isEnd;// 是否是终端结点
    private int size;

    public TireNode() {
        links = new TireNode[R];
    }

    public boolean containsKey(char c) {
        return links[c - 'a'] != null;
    }

    public TireNode get(char ch) {
        return links[ch - 'a'];
    }

    public void put(char ch, TireNode node) {
        links[ch - 'a'] = node;
        size++;
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public int getLinks() {
        return size;
    }
}

class Tire {
    private TireNode root;

    public Tire() {
        root = new TireNode();
    }

    public void insert(String word) {
        TireNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {

                node.put(currentChar, new TireNode());// 注意这里是new一个新的结点，而不是node
            }
            node = node.get(currentChar);
        }
        // System.out.println(node.getLinks());
        node.setEnd();
    }

    /**
     * 查看string word是否在字典树中
     * 
     * @param word
     * @return
     */
    private TireNode searchPrefix(String word) {
        TireNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (node.containsKey(curLetter)) {
                node = node.get(curLetter);
            } else {
                return null;
            }
        }
        return node;
    }

    /**
     * 这个是搜单词
     * 
     * @param word
     * @return
     */
    public boolean search(String word) {
        TireNode node = searchPrefix(word);
        return node != null && node.isEnd();// 注意这里只有node是终端结点，即到这儿有个单词的时候才是真的找到了

    }

    /**
     * 这个是搜前缀
     * 
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        TireNode node = searchPrefix(prefix);
        return node != null;
    }

    public String searchLCP(String word) {
        TireNode node = root;
        StringBuilder prefix = new StringBuilder();// 学会用stringbuilder
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            System.out.print(word.charAt(i));
            if (node.containsKey(curLetter) && node.getLinks() == 1 && !node.isEnd()) {
                // 这里判断条件必需再加上两个
                node = node.get(curLetter);

                prefix.append(curLetter);
            } else {
                return prefix.toString();
            }
        }
        return prefix.toString();
    }
}

class Solution {
    /**
     * 字典树的方法
     * 
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        Tire root = new Tire();
        for (int i = 1; i < strs.length; i++) {
            root.insert(strs[i]);
        }
        // System.out.println(root.startsWith("fl"));
        return root.searchLCP(strs[0]);
    }

    /**
     * 二分查找的方法，必然是在有序的范围内查找。 此题在已知strs中最短字符串长度len的情况下，最长公共前缀的 长度肯定在[0,len-1]中
     * 写二分查找的时候有两个需要注意的点： 1、left,right的范围必须包含了解的范围 2、注意不要陷入死循环，第一个是排除中位数的逻辑，
     * 第二个是包含中位数的逻辑。看看取左/右中位数的时候，第二个逻辑 会不会陷入死循环
     * 
     * @param strs
     * @return
     */
    public String longestCommonPrefixBinarySearch(String[] strs) {
        if (strs == null) {
            return null;
        }
        if (strs.length == 0) {
            return "";
        }
        int len = Integer.MAX_VALUE;
        for (String s : strs) {
            if (s != null && s.length() < len) {// 注意为空的处理
                len = s.length();
            } else if (s == null) {
                len = 0;
            }
        }

        int left = 0, right = len;
        boolean rflag = true;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            String prefix = strs[0].substring(0, mid);
            if (prefix == "") {// 一定要先判断，不然indexof("")结果恒为0
                return "";
            }
            rflag = true;
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].indexOf(prefix) != 0) {
                    rflag = false;
                    break;
                }
            }
            if (!rflag) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        return strs[0].substring(0, left);
    }

    /**
     * 分治
     * 
     * @param strs
     * @return
     */
    public String longestCommonPrefixDivide(String[] strs) {
        if (strs == null) {
            return null;
        }
        if (strs.length == 0) {
            return "";
        }
        return lcp(strs, 0, strs.length - 1);
    }

    public String lcp(String[] strs, int left, int right) {
        if (left == right) {
            return strs[left];
        }
        int mid = (left + right) / 2;
        String retleft = lcp(strs, left, mid);
        String retright = lcp(strs, mid + 1, right);
        if (retleft == "" || retright == "") {
            return "";
        }
        while (retright.indexOf(retleft) != 0) {
            retleft = retleft.substring(0, retleft.length() - 1);
            if (retleft == "") {
                return "";
            }
        }
        return retleft;
    }

    public String longestCommonPrefixHS2(String[] strs) {
        // 水平扫描法，使用indexOf的答案
        /**
         * 当n个串都相同的时候是最坏的情况 时间O（N） 空间O(1)
         */
        if (strs == null) {
            return null;
        }
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(result) != 0) {
                result = result.substring(0, result.length() - 1);
                if (result == "") {// 必须在这里退出，否则如果result=="",indexof会返回0
                    return "";
                }
            }

        }
        return result;
    }

    public String longestCommonPrefixHscani(String[] strs) {
        // 水平扫描法，我写的
        if (strs == null) {
            return null;
        }
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            for (int j = 0; j < result.length() && j < strs[i].length(); j++) {
                if (result.charAt(j) != strs[i].charAt(j)) {
                    result = strs[i].substring(0, j);
                    break;
                }
            }
            if (result.length() > strs[i].length()) {
                result = strs[i];// 这里不能忘，否则如果strs[i]是result的前缀会报错["aa","a"]
            }
            if (result == "") {
                return result;
            }
        }
        return result;
    }

    /**
     * //我写的，属于垂直扫描的方法 最好情况只需要n*minLen的比较 最坏情况与水平扫描
     * 
     * @param strs
     * @return
     */
    public String longestCommonPrefix1(String[] strs) {

        if (strs == null) {
            return null;
        }
        String result = "";
        if (strs.length == 0) {
            return result;
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int len = Integer.MAX_VALUE;
        for (String s : strs) {
            if (s != null && s.length() < len) {// 注意为空的处理
                len = s.length();
            } else if (s == null) {
                len = 0;
            }
        }
        for (int i = 0; i < len; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != c) {
                    return result;
                }
            }
            result += c;
        }
        return result;
    }
}
// @lc code=end
