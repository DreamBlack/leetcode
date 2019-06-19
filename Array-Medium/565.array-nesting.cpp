/*
 * @lc app=leetcode id=565 lang=cpp
 *
 * [565] Array Nesting
 *
 * https://leetcode.com/problems/array-nesting/description/
 *
 * algorithms
 * Medium (52.49%)
 * Likes:    562
 * Dislikes: 79
 * Total Accepted:    36K
 * Total Submissions: 68.3K
 * Testcase Example:  '[5,4,0,3,1,6,2]'
 *
 * A zero-indexed array A of length N contains all integers from 0 to N-1. Find
 * and return the longest length of set S, where S[i] = {A[i], A[A[i]],
 * A[A[A[i]]], ... } subjected to the rule below.
 * 
 * Suppose the first element in S starts with the selection of element A[i] of
 * index = i, the next element in S should be A[A[i]], and then A[A[A[i]]]… By
 * that analogy, we stop adding right before a duplicate element occurs in
 * S.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: A = [5,4,0,3,1,6,2]
 * Output: 4
 * Explanation: 
 * A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
 * 
 * One of the longest S[K]:
 * S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * N is an integer within the range [1, 20,000].
 * The elements of A are all distinct.
 * Each element of A is an integer within the range [0, N-1].
 * 
 * 
 */
class Solution
{
public:
    int arrayNesting1(vector<int> &nums)
    {
        /*
        找规律后发现nums[i]==nums[i]=i的，只能自己玩，结果为1；
        nums[i]==nums[i]!=i的，只能构成长度为2的循环，结果为2；
        否则
        1、一开始的想法是，所有这种情况的个数就是要返回的结果数
        2、后来样例有的过不了，发现不能简单这么做，因为剩下的可能
        会构成多个环，所有应该记录每个环的长度取最大的
         */
        vector<int> nums2(nums.size());   //记录值i被放在了数组的那个位置
        vector<int> flag(nums.size(), 1); //用来标记有没有处理过
        //因为只要能构成循环，不论从哪个开始都能构成长度相同的循环
        for (int i = 0; i < nums.size(); i++)
        {
            nums2[nums[i]] = i;
        }
        int ret = 1, maxcount = 0, maxret = 1;
        for (int i = 0; i < nums.size(); i++)
        {
            if (flag[i] != -1)
            {
                if (nums[i] == nums2[i])
                {
                    if (nums[i] == i)
                    {
                        ret = 1;
                    }
                    else
                    {
                        ret = 2;
                    }
                }
                else
                {
                    //这里开始错误对于[1,2,0,4,5,3]这种类型
                    /*
                结果不应该是6，而是3，因为前三个和后三个连不起来
                所以还是要用个while循环记录到底能连几个
                 */
                    int temp = nums[i], j = temp, count = 1;
                    while (nums[j] != temp)
                    {
                        flag[j] = -1;
                        count++;
                        j = nums[j];
                    }

                    maxcount = max(maxcount, count);
                }
                flag[i] = -1;
                maxret = max(ret, maxret);
            }
        }
        return max(maxret, maxcount);
    }
    int arrayNesting(vector<int> &nums)
    {
        /*
        其实可以不用nums2这个数组的
        也可以不用flag数组，直接在Nums上改
         */

        
       
        int ret = 1, maxcount = 0, maxret = 1;
        for (int i = 0; i < nums.size(); i++)
        {
            if (nums[i] != -1)
            {
                //看了solution这里也可以不用多余的判断了

                // if (nums[i] == i)
                // {
                //     ret = 1;
                // }
                // else if (i == nums[nums[i]])
                // {
                //     ret = 2;
                // }

                // else
                // {
                    //这里开始错误对于[1,2,0,4,5,3]这种类型
                    /*
                结果不应该是6，而是3，因为前三个和后三个连不起来
                所以还是要用个while循环记录到底能连几个
                 */
                vector<int>records;
                    int temp = nums[i], j = temp, count = 1;
                    while (j!=-1&&nums[j] != temp)
                    {
                        records.push_back(j);
                        count++;
                        j = nums[j];
                    }

                    maxcount = max(maxcount, count);
                    for(int j=0;j<records.size();j++){
                        nums[records[j]]=-1;
                    }
                // }
                nums[i] = -1;
                maxret = max(ret, maxret);
            }
        }
        return max(maxret, maxcount);
    }
};
