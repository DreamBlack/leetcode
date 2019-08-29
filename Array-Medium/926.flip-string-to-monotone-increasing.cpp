/*
 * @lc app=leetcode id=926 lang=cpp
 *
 * [926] Flip String to Monotone Increasing
 *
 * https://leetcode.com/problems/flip-string-to-monotone-increasing/description/
 *
 * algorithms
 * Medium (49.39%)
 * Likes:    355
 * Dislikes: 13
 * Total Accepted:    13.8K
 * Total Submissions: 27.4K
 * Testcase Example:  '"00110"'
 *
 * A string of '0's and '1's is monotone increasing if it consists of some
 * number of '0's (possibly 0), followed by some number of '1's (also possibly
 * 0.)
 * 
 * We are given a string S of '0's and '1's, and we may flip any '0' to a '1'
 * or a '1' to a '0'.
 * 
 * Return the minimum number of flips to make S monotone increasing.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: "00110"
 * Output: 1
 * Explanation: We flip the last digit to get 00111.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "010110"
 * Output: 2
 * Explanation: We flip to get 011111, or alternatively 000111.
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: "00011000"
 * Output: 2
 * Explanation: We flip to get 00000000.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= S.length <= 20000
 * S only consists of '0' and '1' characters.
 * 
 * 
 * 解题思路
 * 1、用DFS
 * 注意点：退出条件应该是在index=n而不是index=n-1的时候，后者
 * 会漏掉对index=n-1的处理
 * 问题：内存溢出
 * 2、动态规划
 * 开始做动态规划的思路是，让dp[i]直接表示最少的需要改变的数据
 * 个数。但是发现这样保存的数据量是原因不够的，无法实现。后来
 * 突发奇想，让one[i]表示当前i处为0时需要改变的最小数据量，
 * zero[i]表示当前i处为1时需要改变的最小数据量。
 * 如果s[i]='0',s[i-1]也必须为0，所以zero[i]只与zero[i-1]以及
 * 当前s[i]的值有关；
 * 如果s[i]='1'，可以有s[i-1]='0'而来也可以由s[i-1]='1'而来
 * 3、动态规划的改进
 * 可以只保存两个数而不是两个数组
 * 4、prfix sum
 * 个数已知的有序01排列的个数是已知的，一定是a个0跟上b个1.
 * 要找到改变次数最小的有序序列，换个角度就是对于每个可能的排列，计算
 * 从当前字符串改变到目标需要改变的个数，取其中最小的。
 * 怎么计算需要改变的个数呢，就是i前面1的个数以及i后面0的个数。
 * 
 * 注意点：
 * 三目条件操作符优先级很低，必须用()
 * 
 */
class Solution
{
public:
    int minFlipsMonoIncr(string S)
    {
        //prefix sum
        int n = S.size();
        //sum长度必须为n+1，否则会漏掉00000和11111这种情况
        vector<int> sum(n+1, 0);

        for (int i = 0; i < n; i++)
        {
            sum[i+1] =sum[i]+ (S[i] == '0'?0:1);
        }
        int ret = n;
        for (int i = 0; i <= n; i++)
        {
            int ones = sum[i];
            int zeros = n - i - (sum[n] - sum[i]);
            ret = min(ret, ones + zeros);
        }
        return ret;
    }
    int minFlipsMonoIncr4(string S)
    {
        //动态规划2.空间O(1)
        int n = S.size();
        int zero, one;
        zero = (S[0] == '1') ? 1 : 0;
        one = (S[0] == '0') ? 1 : 0;
        for (int i = 1; i < n; i++)
        {
            one = min(one + ((S[i] == '0') ? 1 : 0), zero + ((S[i] == '0') ? 1 : 0));
            zero = zero + ((S[i] == '0') ? 0 : 1);
        }
        return min(zero, one);
    }
    int minFlipsMonoIncr3(string S)
    {
        //动态规划1.空间O(N)
        int n = S.size();
        vector<int> zero(n, 0);
        vector<int> one(n, 0);
        zero[0] = (S[0] == '1') ? 1 : 0;
        one[0] = (S[0] == '0') ? 1 : 0;
        for (int i = 1; i < n; i++)
        {
            one[i] = min(one[i - 1] + ((S[i] == '0') ? 1 : 0), zero[i - 1] + ((S[i] == '0') ? 1 : 0));
            zero[i] = zero[i - 1] + ((S[i] == '0') ? 0 : 1);
        }
        return min(zero[n - 1], one[n - 1]);
    }
    int minx = 0, limis = 0;
    int minFlipsMonoIncr2(string S)
    {
        int n = S.size();
        int cnt0 = 0, cnt1 = 0;
        for (int i = 0; i < n; i++)
        {
            if (S[i] == '0')
            {
                cnt0++;
            }
            else
            {
                cnt1++;
            }
        }
        minx = min(cnt0, cnt1);
        limis = minx;
        int ret = 0;

        dfs(0, S, ret);
        return minx;
    }
    void dfs(int index, string S, int cnt)
    {
        if (cnt > limis)
            return;
        //应该是index==size()的时候退出，而不是==size()-1
        if (index == S.size())
        {
            if (cnt < minx)
                minx = cnt;
            return;
        }
        if (cnt > minx)
            return;
        //1、不变

        if (index == 0 || (index > 0 && S[index] >= S[index - 1]))
            dfs(index + 1, S, cnt);
        //2、变

        S[index] = S[index] == '0' ? S[index] = '1' : S[index] = '0';
        if (index == 0 || (index > 0 && S[index] >= S[index - 1]))
            dfs(index + 1, S, cnt + 1);
    }
};
