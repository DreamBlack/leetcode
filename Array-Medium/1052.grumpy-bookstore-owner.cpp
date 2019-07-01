/*
 * @lc app=leetcode id=1052 lang=cpp
 *
 * [1052] Grumpy Bookstore Owner
 *
 * https://leetcode.com/problems/grumpy-bookstore-owner/description/
 *
 * algorithms
 * Medium (51.05%)
 * Likes:    86
 * Dislikes: 8
 * Total Accepted:    5.7K
 * Total Submissions: 11K
 * Testcase Example:  '[1,0,1,2,1,1,7,5]\n[0,1,0,1,0,1,0,1]\n3'
 *
 * Today, the bookstore owner has a store open for customers.length minutes.
 * Every minute, some number of customers (customers[i]) enter the store, and
 * all those customers leave after the end of that minute.
 * 
 * On some minutes, the bookstore owner is grumpy.  If the bookstore owner is
 * grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0.  When the
 * bookstore owner is grumpy, the customers of that minute are not satisfied,
 * otherwise they are satisfied.
 * 
 * The bookstore owner knows a secret technique to keep themselves not grumpy
 * for X minutes straight, but can only use it once.
 * 
 * Return the maximum number of customers that can be satisfied throughout the
 * day.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * Output: 16
 * Explanation: The bookstore owner keeps themselves not grumpy for the last 3
 * minutes. 
 * The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 +
 * 5 = 16.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= X <= customers.length == grumpy.length <= 20000
 * 0 <= customers[i] <= 1000
 * 0 <= grumpy[i] <= 1
 * 
 * 
 * 解题思路：
 * 又有那种最大最小问题的感觉，怎么利用这持续的X，肯定不是放在
 * grumpy[i]=1且，customers[i]最大的地方，
 * 找grumpy[i]=1的，和最大长为X的子序列
 * 1、original
 * 先把所有grumpy[i]=0的和算出来为ret，之后对所有长度为X的子数组，
 * 计算其grumpy[i]=1的和（之前没加进去过），将最大的和maxret和ret
 * 加起来即是结果
 * 2、改进
 * 这个问题总是让人联想到求最大子序列和的问题，但是不同的是，本题
 * grumpy[i]=1的那些位置才能算做和，grumpy[i]=0的话，就表示已经加过
 * 一次。
 * 
 */
class Solution {
public:
    int maxSatisfied(vector<int>& customers, vector<int>& grumpy, int X) {
        int ret=0;
        for(int i=0;i<customers.size();i++){
            if(grumpy[i]==0){
                ret+=customers[i];
            }
        }
        int maxret=0,tempret=0;
        int firstindex=0;
        //求前x个数中grumpy[i]=1的和
        for(int j=0;j<X;j++){
            tempret+=customers[j]*grumpy[j];
        }
        maxret=tempret;
        //改进了两层循环，利用上一次的和
        for(int i=X;i<customers.size();i++){
            tempret=tempret-customers[firstindex]*grumpy[firstindex]+customers[i]*grumpy[i];
            maxret=max(maxret,tempret);
            firstindex=i-X+1;
        }
        return maxret+ret;
    }
    int maxSatisfied1(vector<int>& customers, vector<int>& grumpy, int X) {
        int ret=0;
        for(int i=0;i<customers.size();i++){
            if(grumpy[i]==0){
                ret+=customers[i];
            }
        }
        int maxret=ret,tempret=ret;
        for(int i=0;i<=customers.size()-X;i++){
            for(int j=i;j<i+X;j++){
                if(grumpy[j]==1){
                    tempret+=customers[j];
                }
            }
            maxret=max(maxret,tempret);
            tempret=ret;
        }
        return maxret;
    }
};

