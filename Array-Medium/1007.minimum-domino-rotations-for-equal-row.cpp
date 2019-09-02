/*
 * @lc app=leetcode id=1007 lang=cpp
 *
 * [1007] Minimum Domino Rotations For Equal Row
 *
 * https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/description/
 *
 * algorithms
 * Medium (48.84%)
 * Likes:    90
 * Dislikes: 76
 * Total Accepted:    12.1K
 * Total Submissions: 24.6K
 * Testcase Example:  '[2,1,2,4,2,2]\n[5,2,6,2,3,2]'
 *
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of
 * the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on
 * each half of the tile.)
 * 
 * We may rotate the i-th domino, so that A[i] and B[i] swap values.
 * 
 * Return the minimum number of rotations so that all the values in A are the
 * same, or all the values in B are the same.
 * 
 * If it cannot be done, return -1.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 * Output: 2
 * Explanation: 
 * The first figure represents the dominoes as given by A and B: before we do
 * any rotations.
 * If we rotate the second and fourth dominoes, we can make every value in the
 * top row equal to 2, as indicated by the second figure.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
 * Output: -1
 * Explanation: 
 * In this case, it is not possible to rotate the dominoes to make one row of
 * values equal.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= A[i], B[i] <= 6
 * 2 <= A.length == B.length <= 20000
 * 
 * 
 * 解题思路：
 * 刚开始在往动态规划考虑，后来发现根本不需要啊，
 * 直接记录1-6出现的个数，然后再处理就可以了啊
 * 不过需要注意的是题目中有可能出现一个骰子两个面的值相同的
 * 情况
 */
class Solution {
public:
    int minDominoRotations(vector<int>& A, vector<int>& B) {
        /*
        要注意有一个骰子正反面值都相同的情况，这种情况下即使c的个数
        必须要比A.size()大才可能
        */
        vector<int>counter(6,0);
        for(int i=0;i<A.size();i++){
            counter[A[i]-1]++;
            counter[B[i]-1]++;
        }
        //肯定是个数最多的那个才能组成，不然根本组不成一样的
        int maxvalue=0;
        for(int i=0;i<6;i++){
            if(counter[i]>counter[maxvalue]){
                maxvalue=i;
            }
        }
        if(counter[maxvalue]<A.size())
            return -1;
        //记录A/B中，需要翻转的个数
        int a=0,b=0;
        for(int i=0;i<A.size();i++){
            if(A[i]!=maxvalue+1&&B[i]==maxvalue+1){
                a++;
            }
            if(B[i]!=maxvalue+1&&A[i]==maxvalue+1){
                b++;
            }
            //怎么转都改不了
            if(A[i]!=maxvalue+1&&B[i]!=maxvalue+1)
                return -1;
        }
        return min(a,b);

    }
};

