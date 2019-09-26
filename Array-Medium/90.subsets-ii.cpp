/*
 * @lc app=leetcode id=90 lang=cpp
 *
 * [90] Subsets II
 *
 * https://leetcode.com/problems/subsets-ii/description/
 *
 * algorithms
 * Medium (43.61%)
 * Likes:    1077
 * Dislikes: 53
 * Total Accepted:    224.9K
 * Total Submissions: 513.6K
 * Testcase Example:  '[1,2,2]'
 *
 * Given a collection of integers that might contain duplicates, nums, return
 * all possible subsets (the power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * Example:
 * 
 * 
 * Input: [1,2,2]
 * Output:
 * [
 * ⁠ [2],
 * ⁠ [1],
 * ⁠ [1,2,2],
 * ⁠ [2,2],
 * ⁠ [1,2],
 * ⁠ []
 * ]
 * 题目大意：和78题类似，只不过这次数组里有重复的元素
 * 解法1：二叉树的中序遍历。
 * 在考虑怎么去除二叉数中效果等价的分支的时候花了很久，后来想到
 * 可以直接在重复元素的时候自己指定走的方向，这样就不用考虑到底
 * 舍弃哪个分支了。加上重复元素有n个，那么就有[0,n]--[n,0]这n
 * 种分支方法
 * 解法2：
 * 直接用循环。从78角度出发，从上一次的结果开始，在上一次的子集
 * 集合基础上（假设有k个），把当前元素加到这k各子集里，再和原来
 * 的k个元素合起来，构成有2k个元素的集合的集合，就是结果了。
 * 对于重复元素，只需要加到上次新加入的那些集合中，而不是所以k个
 * 集合中。
 * 解法3：
 * 记录所有相同的元素，之前没有重复的时候是在原集合基础上加一个当前
 * 元素，多k个子集，现在变成加入1--cnt个当前元素，构成k*cnt+k个
 * 
 */
class Solution
{
public:
vector<vector<int>> subsetsWithDup(vector<int> &nums){
    //这是论坛上的另一种写法
     sort(nums.begin(),nums.end());
    vector<vector<int>> ret={{}};
    vector<int> tmp;
    for(int i=0;i<nums.size();){
       int count=0;
       while(count+i<nums.size()&&nums[count+i]==nums[i])count++;

        int cnt=ret.size();
        
        
        for(int j=0;j<cnt;j++){
            vector<int>tmp=ret[j];
            for(int c=0;c<count;c++){
                 tmp.push_back(nums[i]);
                ret.push_back(tmp);
            }
           
        }
        i+=count;//这里要注意

    }
    return ret;
 }
 vector<vector<int>> subsetsWithDup2(vector<int> &nums){
     sort(nums.begin(),nums.end());
    vector<vector<int>> ret={{}};
    vector<int> tmp;
    int last=0;
    for(int i=0;i<nums.size();i++){
       
        int cnt=ret.size();
        //如果是重复的就不应该在上回的所有ret元素中加新元素
        //只需要在最近一次与当前元素相同的后加的那些元素上加就行了
        
        int j=0;
        if(i>0&&nums[i]==nums[i-1]){
            j=last;
        }
        for(;j<cnt;j++){
            vector<int>tmp=ret[j];
            tmp.push_back(nums[i]);
            ret.push_back(tmp);
        }
        last=cnt;

    }
    return ret;
 }
    vector<vector<int>> subsetsWithDup1(vector<int> &nums)
    {

        vector<vector<int>> ret;
        vector<int> tmp;

        if (nums.empty())
        {
            ret.push_back(tmp);
            return ret;
        }

        sort(nums.begin(), nums.end());
        dfs(tmp, ret, nums, 0);

        return ret;
    }
    void dfs(vector<int> tmp, vector<vector<int>> &ret, vector<int> nums, int index)
    {
        if (index == nums.size())
        {
            ret.push_back(tmp);
            return;
        }
        if (index + 1 < nums.size() && nums[index] == nums[index + 1])
        {
            int cnt = 1,i=index;
            while (i + 1 < nums.size() && nums[i] == nums[i + 1])
            {
                cnt++;
                i++;
            }
            for(int j=0;j<=cnt;j++){
                int q=cnt-j;
                for(int m=0;m<q;m++)
                    tmp.push_back(nums[index]);
                 dfs(tmp, ret, nums, i+1);
                 //要把之前插入的删掉
                 for(int m=0;m<q;m++)
                    tmp.pop_back();
            }
        }
        else
        {
            dfs(tmp, ret, nums, index + 1);
            tmp.push_back(nums[index]);
            dfs(tmp, ret, nums, index + 1);
        }
    }
};
