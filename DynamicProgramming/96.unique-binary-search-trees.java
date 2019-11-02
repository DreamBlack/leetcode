/*
 * @lc app=leetcode id=96 lang=java
 *
 * [96] Unique Binary Search Trees
 *
 * https://leetcode.com/problems/unique-binary-search-trees/description/
 *
 * algorithms
 * Medium (47.65%)
 * Likes:    2133
 * Dislikes: 82
 * Total Accepted:    227.1K
 * Total Submissions: 471.1K
 * Testcase Example:  '3'
 *
 * Given n, how many structurally unique BST's (binary search trees) that store
 * values 1 ... n?
 * 
 * Example:
 * 
 * 
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 * 
 * ⁠  1         3     3      2      1
 * ⁠   \       /     /      / \      \
 * ⁠    3     2     1      1   3      2
 * ⁠   /     /       \                 \
 * ⁠  2     1         2                 3
 * 
 * 
 */

// @lc code=start
class Solution {
   /**
    * 有n个二叉树结点的形态有catalan数个数个，这些形态每个都可以对应一种二叉搜索树的填充方法
    * 所以也是catalan个数个
    * 根据catalan数的公式计算即可，用数组dp保留中间结果的记录，减少重复计算
    * @param n
    * @return
    */
    public int numTrees(int n) {
        int[] dp=new int[n+1];
        dp[0]=dp[1]=1;
        for(int i=2;i<=n;i++){
            int tmp=0;
            for(int j=0;j<i;j++){
                tmp+=dp[j]*dp[i-1-j];
            }
            dp[i]=tmp;
        }
        return dp[n];
    }
}
// @lc code=end

