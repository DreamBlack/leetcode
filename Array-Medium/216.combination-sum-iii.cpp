/*
 * @lc app=leetcode id=216 lang=cpp
 *
 * [216] Combination Sum III
 *
 * https://leetcode.com/problems/combination-sum-iii/description/
 *
 * algorithms
 * Medium (51.54%)
 * Likes:    609
 * Dislikes: 30
 * Total Accepted:    125K
 * Total Submissions: 240.7K
 * Testcase Example:  '3\n7'
 *
 * 
 * Find all possible combinations of k numbers that add up to a number n, given
 * that only numbers from 1 to 9 can be used and each combination should be a
 * unique set of numbers.
 * 
 * Note:
 * 
 * 
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 * 
 * 
 */
class Solution {
public:
    vector<vector<int>> combinationSum3(int k, int n) {
        vector<vector<int>>result;
            vector<int>temp;
            int nnow=0,know=0;
            fun(result,temp,k,n,1,nnow,know);
            /*
            这里递归相当于从树根结点出发，走遍每一条路，
            不用for循环从每一个子树出发了，这样会造成重复

            有几点关于递归的注意事项
            1、返回值一般是void
            2、会在函数内部多次用到的一般不会作为引用的，因为
            一旦作为引用，返回的时候就不能回到希望的值，而是在
            不停改变了
            3、返回的值才用引用作为参数
             */
        
        return result;
    }
    void fun(vector<vector<int>>&result,vector<int>temp,int k,int n,int now,int  nnow,int  know){
        if(nnow==n&&know==k){
            result.push_back(temp);
            return;
        }
        if(know>k||now>9||now>n)//这里是Now>9不行，但是小于9的还是可以的。
           return ;
        
        fun(result,temp,k,n,now+1,nnow,know);//now不算
        temp.push_back(now);
        if(now>n-nnow)return;//这里进行多余的剪枝减少查询，如果
        //当前值比n-now大，那后面的都不用查询了。
        fun(result,temp,k,n,now+1,nnow+now,know+1);//算上now
    }
};

