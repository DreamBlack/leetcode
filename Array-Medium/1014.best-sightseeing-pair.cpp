/*
 * @lc app=leetcode id=1014 lang=cpp
 *
 * [1014] Best Sightseeing Pair
 *
 * https://leetcode.com/problems/best-sightseeing-pair/description/
 *
 * algorithms
 * Medium (50.18%)
 * Likes:    243
 * Dislikes: 11
 * Total Accepted:    9.3K
 * Total Submissions: 18.5K
 * Testcase Example:  '[8,1,5,2,6]'
 *
 * Given an array A of positive integers, A[i] represents the value of the i-th
 * sightseeing spot, and two sightseeing spots i and j have distance j - i
 * between them.
 * 
 * The score of a pair (i < j) of sightseeing spots is (A[i] + A[j] + i - j) :
 * the sum of the values of the sightseeing spots, minus the distance between
 * them.
 * 
 * Return the maximum score of a pair of sightseeing spots.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [8,1,5,2,6]
 * Output: 11
 * Explanation: i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 2 <= A.length <= 50000
 * 1 <= A[i] <= 1000
 * 
 * 解题思路
 * 1、动态规划
 * 考虑之后发现，以下算法皆不可
 * 分治：找分治部分的最大，最小，再结合考虑。如果这样可以，那直接
 * 一次把整个数组的最大最小找出来不就行了？
 * 排序：原序列的顺序很重要，不可以改变
 * hash：原数组中每个数可能出现多次。
 * 双指针：没有顺序
 * 所以只能动态规划了。下面的问题就是，dp[i]表示什么，肯定不是
 * 表示结果那么简单。将其分为两种情况，dp1[i]表示，当A[i]作为
 * 两个目的地的中间点时的最大分数；dp2[i]表示，当A[i]作为终点
 * 时的最大分数。最后返回max(dp2[i-1],dp1[i-1])即可
 * 2、从题意出发，一点点数学头脑
 * 要求找最大的A[i]+A[j]+i-j，这里面既有j又有i，将i,j分开写
 * 就是找max（A[i]+i+A[j]-j）。这样i,j就分开了，只要保证i<j。
 * 就可以保存max（A[i]+i）,同时找max(A[j]-j)
 */
class Solution {
public:
int maxScoreSightseeingPair(vector<int>& A) {
        int maxi=A[0],result=0;
        for(int i=1;i<A.size();i++){
            result=max(result,maxi+A[i]-i);
            maxi=max(maxi,A[i]+i);
        }
        return result;
    }
    int maxScoreSightseeingPair2(vector<int>& A) {
        int dp1=0,dp2=0;
        for(int i=1;i<A.size();i++){
            dp2=max(dp2,max(dp1,A[i-1])+A[i]-1);
            dp1=max(A[i-1]-1,dp1-1);
        }
        return max(dp2,dp1);
    }
};

