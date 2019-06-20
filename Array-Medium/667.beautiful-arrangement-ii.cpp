/*
 * @lc app=leetcode id=667 lang=cpp
 *
 * [667] Beautiful Arrangement II
 *
 * https://leetcode.com/problems/beautiful-arrangement-ii/description/
 *
 * algorithms
 * Medium (51.93%)
 * Likes:    221
 * Dislikes: 526
 * Total Accepted:    18.3K
 * Total Submissions: 35.2K
 * Testcase Example:  '3\n2'
 *
 * 
 * Given two integers n and k, you need to construct a list which contains n
 * different positive integers ranging from 1 to n and obeys the following
 * requirement: 
 * 
 * Suppose this list is [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 -
 * a3|, |a3 - a4|, ... , |an-1 - an|] has exactly k distinct integers.
 * 
 * 
 * 
 * If there are multiple answers, print any of them.
 * 
 * 
 * Example 1:
 * 
 * Input: n = 3, k = 1
 * Output: [1, 2, 3]
 * Explanation: The [1, 2, 3] has three different positive integers ranging
 * from 1 to 3, and the [1, 1] has exactly 1 distinct integer: 1.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: n = 3, k = 2
 * Output: [1, 3, 2]
 * Explanation: The [1, 3, 2] has three different positive integers ranging
 * from 1 to 3, and the [2, 1] has exactly 2 distinct integers: 1 and 2.
 * 
 * 
 * 
 * Note:
 * 
 * The n and k are in the range 1 
 * 
 * 
 */
class Solution
{
public:
    vector<int> constructArray(int n, int k)
    {
        /*
        解法3：
            看了网友解法
            https://blog.csdn.net/sunlanchang/article/details/77621300
            还有一种思路是1,n-1,2,n-2,....这么排列，这样差值就是n-2,n-3,n-4
            实践起来就是1，n-1,2,n-2,……， 
         */
        vector<int> ret;
        for (int i = 1, j = n; i <= j;)
        {
            if (k > 1)
            {
                if (k % 2 == 0)
                {
                    ret.push_back(i++);
                }
                else
                {
                    ret.push_back(j--);
                }
                k--;
            }
            else
            {

                if (k % 2 == 0)
                    ret.push_back(i++);
                else
                    ret.push_back(j--);
            }
        }
        return ret;
    }
    vector<int> constructArray1(int n, int k)
    {
        if (n == 1)
            return {1};
        if (n == 2)
            return {1, 2};
        vector<int> ret(n, 1);
        for (int i = 0; i < n; i++)
        {
            ret[i] = i + 1;
        }

        if (k == 1)
            return ret;
        int first = 1, index = 0;
        ;
        ret[0] = -1;
        for (int i = k; i >= 1; i--)
        {
            //开始写的是先考虑first+i在不在
            /*
            后来测试发现有用例过不了比如n=5,k=2，按原代码结果是
            [1,3,4,2,5]，会出现差值个数大于k的情况。后来想了想
            应该优先选first-i的，这样把小的尽量放在前面，防止后面
            出现差值大于k的情况

            解法2：
            一开始用了set，后来用正负表示index=i对应的数值i+1有没有被用过
            如果nums[i]>0，表示i+1没有被用过；
            如果Nums[i]<0，表示i+1被用过。
            这个记录需要在标记的时候格外注意

            解法3：
            看了网友解法
            https://blog.csdn.net/sunlanchang/article/details/77621300
            还有一种思路是1,n-1,2,n-2,....这么排列，这样差值就是n-2,n-3,n-4
             */
            if (first - i > 0 && first - i <= n && ret[first - i - 1] > 0)
            {
                ++index; //这个最好写到外面
                //index对应的元素的正负表示，该位置+1的值有没有用过
                //如果该位置本来就为正，表示第一次使用，否则保持不变
                ret[index] = (ret[index] > 0) ? first - i : (-first + i); //这里是不能影响原来index位置对应的数index+1它是否被使用过的情况
                //如果该位置用过了就要将该位置的元素置为负数
                ret[first - i - 1] = -ret[first - i - 1];
            }
            else if (first + i > 0 && first + i <= n && ret[first + i - 1] > 0)
            {
                ++index;
                ret[index] = (ret[index] > 0) ? first + i : -first - i;
                ret[first + i - 1] = -ret[first + i - 1];
            }
            first = abs(ret[index]);
        }
        int temp = index;
        for (int i = 0; i < n; i++)
        {
            if (ret[i] > 0)
            {

                ret[++index] = i + 1;
            }

            else
            {
                ret[i] = -ret[i];
            }
        }

        return ret;
    }
};
