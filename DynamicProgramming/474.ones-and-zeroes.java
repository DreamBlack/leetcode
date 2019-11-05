/*
 * @lc app=leetcode id=474 lang=java
 *
 * [474] Ones and Zeroes
 *
 * https://leetcode.com/problems/ones-and-zeroes/description/
 *
 * algorithms
 * Medium (40.49%)
 * Likes:    669
 * Dislikes: 160
 * Total Accepted:    36.5K
 * Total Submissions: 89.3K
 * Testcase Example:  '["10","0001","111001","1","0"]\n5\n3'
 *
 * In the computer world, use restricted resource you have to generate maximum
 * benefit is what we always want to pursue.
 * 
 * For now, suppose you are a dominator of m 0s and n 1s respectively. On the
 * other hand, there is an array with strings consisting of only 0s and 1s.
 * 
 * Now your task is to find the maximum number of strings that you can form
 * with given m 0s and n 1s. Each 0 and 1 can be used at most once.
 * 
 * Note:
 * 
 * 
 * The given numbers of 0s and 1s will both not exceed 100
 * The size of given string array won't exceed 600.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 * Output: 4
 * 
 * Explanation: This are totally 4 strings can be formed by the using of 5 0s
 * and 3 1s, which are “10,”0001”,”1”,”0”
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: Array = {"10", "0", "1"}, m = 1, n = 1
 * Output: 2
 * 
 * Explanation: You could form "10", but then you'd have nothing left. Better
 * form "0" and "1".
 * 
 * 解题思路1findMaxForm1：
 * 本来是想用我的常用的取或者不取的dp，但是写到最后发现，每次不能取max，还要考虑剩余可用的
 * 01的数量
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][]dp=new int[strs.length+1][m+1][n+1];
        //dp[i][j][p]表示前i个物品能构成的体积小于等于j，重量小于等于p的个数的最大值
        //则i要么放要么不放，最大值要么是放，要么是不放的最大值
        
        for(int i=1;i<strs.length+1;i++){//对于每一个物品
            int nowm=0,nown=0;
            for(int q=0;q<strs[i-1].length();q++){
                if(strs[i-1].charAt(q)=='0'){
                    nowm++;
                }else{
                    nown++;
                }
            }
            // for(int j=m;j>=nowm;j--){
            //     for(int p=n;p>=nown;p--){
            //         //必须从右边往左边构造，防止覆盖
            //         dp[j][p]=Math.max(dp[j-nowm][p-nown]+1,dp[j][p]);
            //     }
            // }
            //如果要写成三维的dp，必须把0也考虑进去，因为会遇到p-nown=0和j-nowm=0的情况
            //
            for(int j=0;j<=m;j++){
                for(int p=0;p<=n;p++){
                    int tmp=0;
                    if(j-nowm>=0&&p-nown>=0){
                        tmp=dp[i-1][j-nowm][p-nown]+1;
                    }
                    dp[i][j][p]=Math.max(tmp,dp[i-1][j][p]);
                }
            }
        }
        return dp[strs.length][m][n];
    }
    
}
// @lc code=end

