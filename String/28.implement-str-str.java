/*
 * @lc app=leetcode id=28 lang=java
 *
 * [28] Implement strStr()
 *
 * https://leetcode.com/problems/implement-strstr/description/
 *
 * algorithms
 * Easy (32.85%)
 * Likes:    1272
 * Dislikes: 1670
 * Total Accepted:    573K
 * Total Submissions: 1.7M
 * Testcase Example:  '"hello"\n"ll"'
 *
 * Implement strStr().
 * 
 * Return the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack.
 * 
 * Example 1:
 * 
 * 
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * 
 * 
 * Clarification:
 * 
 * What should we return when needle is an empty string? This is a great
 * question to ask during an interview.
 * 
 * For the purpose of this problem, we will return 0 when needle is an empty
 * string. This is consistent to C's strstr() and Java's indexOf().
 * 
 * 解题思路
 * 1、普通方法
 * 2、kmp
 * 
 * 复杂度
 * 1、O(N*M)
 * 
 * 边界
 * 
 * 编码
 * 
 * 用例
 * 
 */

// @lc code=start
class Solution {
    /**
     * KMP 出发点：利用模式串本身的信息，使得当匹配失败的时候， 不要让j每次都从0开始再次匹配。
     * 方法：next[j]记录当第j个字符和大串不匹配的时候，大串 中字符下次应该和模式串中哪个字符再比较，即j应该回退到第next[j] 处再比较
     * 具体：next[j]是关于P[0-J-1]中前缀和后缀串最长重复串的信息的记录，
     * next数组中第j个元素记录的是P[0,k-1]==P[j-2-k,j-1]中最大的k。 k也是P[0,K-1]串的长度，下次应该从第k个开始匹配
     * 这样当j与主串不匹配的时候，j应该回退到k所在的位置即next[j]
     * 
     * next数组的求解： 1、j==0,next[0]=-1，k=-1特殊标记表示如果第0个元素都不匹配i也要+1 2、j==1,next[1]=0,k=0
     * 3、如果needle.charAt(j)==needle.charAt(k)，next[j+1]=next[j]+1=k+1;
     * 否则，回退，k=next[k];
     * 
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        int lenH = haystack.length();
        int lenN = needle.length();
        if (lenN == 0) {
            return 0;
        }
        if (lenH < lenN) {
            return -1;
        }
        // 构建next数组
        int j = 0, k = -1;
        int[] next = new int[needle.length()];
        next[0] = -1;

        while (j < needle.length() - 1) {
            if (k == -1 || needle.charAt(j) == needle.charAt(k)) {
                j++;
                k++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
        // 利用next数组对j进行回退
        int i = 0;
        j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j >= needle.length()) {
            return i - needle.length();
        } else {
            return -1;
        }

    }

    /**
     * 网友简洁写法
     * 
     * @param haystack
     * @param needle
     * @return
     */
    public int strStrConcise(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        int lenH = haystack.length();
        int lenN = needle.length();
        if (lenN == 0) {
            return 0;
        }
        if (lenH < lenN) {
            return -1;
        }
        for (int i = 0;; i++) {
            for (int j = 0;; j++) {
                if (j == lenN) {
                    return i;
                } else if (i + j == lenH) {// ==lenH
                    return -1;
                } else if (haystack.charAt(i + j) != needle.charAt(j)) {// 这个要放最后，否则会越界
                    break;
                }
            }
        }
    }

    /**
     * 我写的方法，要注意的是，每次i和j不匹配的时候得让i也变得从头开始匹配
     * 
     * @param haystack
     * @param needle
     * @return
     */

    public int strStrMy(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        int lenH = haystack.length();
        int lenN = needle.length();
        if (lenN == 0) {
            return 0;
        }
        if (lenH < lenN) {
            return -1;
        }
        int i = 0;
        while (i < lenH) {
            int j = 0;
            int tmp = i;
            while (j < lenN && i < lenH && haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }
            if (j == lenN) {
                return i - lenN;
            } else {
                i = tmp + 1;
            }
        }
        return -1;
    }
}
// @lc code=end
