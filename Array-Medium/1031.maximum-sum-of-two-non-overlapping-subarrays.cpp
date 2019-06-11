/*
 * @lc app=leetcode id=1031 lang=cpp
 *
 * [1031] Maximum Sum of Two Non-Overlapping Subarrays
 *
 * https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/description/
 *
 * algorithms
 * Medium (55.47%)
 * Likes:    150
 * Dislikes: 8
 * Total Accepted:    5.6K
 * Total Submissions: 10.2K
 * Testcase Example:  '[0,6,5,2,2,5,1,9,4]\n1\n2'
 *
 * Given an array A of non-negative integers, return the maximum sum of
 * elements in two non-overlapping (contiguous) subarrays, which have lengths L
 * and M.  (For clarification, the L-length subarray could occur before or
 * after the M-length subarray.)
 * 
 * Formally, return the largest V for which V = (A[i] + A[i+1] + ... +
 * A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:
 * 
 * 
 * 0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
 * 0 <= j < j + M - 1 < i < i + L - 1 < A.length.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
 * Output: 20
 * Explanation: One choice of subarrays is [9] with length 1, and [6,5] with
 * length 2.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
 * Output: 29
 * Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9]
 * with length 2.
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
 * Output: 31
 * Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8]
 * with length 3.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * L >= 1
 * M >= 1
 * L + M <= A.length <= 1000
 * 0 <= A[i] <= 1000
 * 
 * 
 * 
 * 
 * 
 * 解题思路：
 * 非重叠子数组的最大和问题
 * 法1：
 * 这题写的可以说是非常痛苦了，花了很多时间，最开始想找长度为L和M的最大及次大的和
 * 的子数组，然后分类讨论。但是写的过程中发现最大、次大可能重合，这样就不符合题意了
 * 后来又试图换了一种写法，记录长度为L和M的数组的长度，对L的最大，在M中找剩余可能的不重叠
 * 的最大和；反之亦然，后来发现还是有用例过不了，因为有可能和最大的并不是L最大或者M
 * 最大中的某一个，反而可能是不重叠的次大部分。又失败了。最后改成对于L中每一个，M
 * 中每一个，找最大的。这样复杂度很高。
 * 法2：
 * 没想起了，看了人家的解题思路
 * 将问题分为两部分L在前,M在后和M在前L在后，这样找两种情况中的最大值即可
 * 分为这种情况的好处就是，可以对于某个L，所有不重叠的可能的M就在L+M（包含）后面。
 * 而且也不用额外的空间，只需要保存最大的值
 */
class Solution {
public:
    int maxSumTwoNoOverlap1(vector<int>& A, int L, int M) {

        for(int i=1;i<A.size();i++){
            A[i]=A[i]+A[i-1];
        }
        vector<int> ls(A.size(),0);
        vector<int> ms(A.size(),0);
        for(int i=L-1;i<A.size();i++){
            if(i==L-1)
                ls[i]=A[i];
            else
                ls[i]=A[i]-A[i-L];//注意这里是i-L而不是i-L-1
            
        }
       
       for(int i=M-1;i<A.size();i++){
            if(i==M-1)
                ms[i]=A[i];
            else
                ms[i]=A[i]-A[i-M];
           
        }
    int ret=0;
        for(int i=L-1;i<A.size();i++){
            int maxlpl=-1;
            for(int j=M-1;j<=i-L;j++){
                if(ms[j]!=-1&&maxlpl<ms[j]){
                    maxlpl=ms[j];
                }
             }
      
             for(int j=i+M;j<A.size();j++){
                if(ms[j]!=-1&&maxlpl<ms[j]){//这里是&&而不是||
                    maxlpl=ms[j];
                }
             }
            if(maxlpl+ls[i]>ret)
             ret=maxlpl+ls[i];
        }
        return ret;
    }
    int maxSumTwoNoOverlap(vector<int>& A, int L, int M) {
        for(int i=1;i<A.size();i++){
            A[i]=A[i]+A[i-1];
        }
        int maxl=A[L-1],maxm=A[M-1],ret=A[M+L-1];//初始化，让循环中跳过
        for(int i=L+M;i<A.size();i++){//从L+M开始
            maxl=max(maxl,A[i-M]-A[i-M-L]);//先L后M
            maxm=max(maxm,A[i-L]-A[i-M-L]);//先M后L
            ret=max(ret,max(maxl+A[i]-A[i-M],maxm+A[i]-A[i-L]));
        }
        return ret;
    }

};

