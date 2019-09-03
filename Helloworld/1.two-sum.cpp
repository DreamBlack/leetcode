/*
 * @lc app=leetcode id=1 lang=cpp
 *
 * [1] Two Sum
 *
 * https://leetcode.com/problems/two-sum/description/
 *
 * algorithms
 * Easy (44.45%)
 * Likes:    11710
 * Dislikes: 404
 * Total Accepted:    2.1M
 * Total Submissions: 4.7M
 * Testcase Example:  '[2,7,11,15]\n9'
 *
 * Given an array of integers, return indices of the two numbers such that they
 * add up to a specific target.
 * 
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * 
 * Example:
 * 
 * 
 * Given nums = [2, 7, 11, 15], target = 9,
 * 
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * 
 * 解题思路：
 * 首先要返回的是下标，所以排序的方法是不可以的
 * 解法1：用map
 * 要注意的是，6=3+3这种情况
 * 
 * 解法2：
 * 要想出一个空间O(1)，时间也O(1)的来。
 * 哈哈，这个是想不出啦。证明也是没有的
 * 但是法1要在两次扫描才能成功，有没有一次扫描就能成功的呢
 * 2写的非常搓，在访问每一个数的时候把对应的target-nums[i]
 * 标记一下
 * 
 * 解法3：★★★
 * 其实不需要标记，只需要没有一个数的时候同时访问nums[i]和
 * target-nums[i]，总会匹配到的
 */
class Solution
{
public:
    vector<int> twoSum(vector<int> &nums, int target)
    {
        vector<int> ret;
        map<int, int> mp;
        for (int i = 0; i < nums.size(); i++)
        {
            
            if (mp.count(target - nums[i]) == 1 && mp[target - nums[i]] != i)
            {
                ret.push_back(mp[target - nums[i]]);
                ret.push_back(i);
                break;
            }
            mp[nums[i]] = i;//放在下面，不然会漏掉6=3+3
        }
        return ret;
    }
    vector<int> twoSum2(vector<int> &nums, int target)
    {
        vector<int> ret;

        map<int, int> mp;
        for (int i = 0; i < nums.size(); i++)
        {
            if (target % 2 == 0 && nums[i] == target / 2)
            {
                //重复的情况单独处理
                if (mp.count(nums[i]) == 1 && mp[nums[i]] != i)
                {
                    ret.push_back(mp[nums[i]]);
                    ret.push_back(i);
                    break;
                }
                else
                {
                    mp[nums[i]] = i;
                }
            }
            else if (mp.count(nums[i]) == 1 && mp[nums[i]] == -1)
            {
                //如果
                ret.push_back(mp[target - nums[i]]);
                ret.push_back(i);
                break;
            }
            else
            {
                //进map的时候同时将对对应的那个加入
                mp[nums[i]] = i;
                mp[target - nums[i]] = -1;
            }
        }

        return ret;
    }
    vector<int> twoSum1(vector<int> &nums, int target)
    {
        vector<int> ret;
        //要返回的是Index所以排序再来搞的方法是不行的
        //答案只有一个，证明数组中的数是没有重复的(错，有可能有的)，用map
        //出现重复数的时候只有一种情况target/2和target/2
        map<int, int> mp;
        for (int i = 0; i < nums.size(); i++)
        {

            mp[nums[i]] = i;
        }
        for (int i = 0; i < nums.size(); i++)
        {
            if (mp.count(target - nums[i]) == 1 && mp[target - nums[i]] != i)
            {
                //要加上并且，防止6=3+3这种情况出现
                ret.push_back(i);
                ret.push_back(mp[target - nums[i]]);
                break;
            }
        }
        return ret;
    }
};
