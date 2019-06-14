/*
 * @lc app=leetcode id=78 lang=cpp
 *
 * [78] Subsets
 *
 * https://leetcode.com/problems/subsets/description/
 *
 * algorithms
 * Medium (52.83%)
 * Likes:    1948
 * Dislikes: 50
 * Total Accepted:    371.4K
 * Total Submissions: 699.5K
 * Testcase Example:  '[1,2,3]'
 *
 * Given a set of distinct integers, nums, return all possible subsets (the
 * power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * Example:
 * 
 * 
 * Input: nums = [1,2,3]
 * Output:
 * [
 * ⁠ [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 * 
 * 解题思路
 * 1、刚开始是用的暴力解法，速度特别慢，空间消耗也特别多
 * 2、后来看别的解法，知道是一个树的递归回溯问题，接下来可以看77. Combinations据说是一个问题
 */
class Solution
{
public:
vector<vector<int>> subsets(vector<int> &nums)
    {
        //取或者不取问题，是一个树的递归回溯问题
        //https://blog.csdn.net/u014110320/article/details/82013148树画的不错
        vector<vector<int>> ret;
        vector<int> list;
        // ret.push_back(list);//空集，不用放，最后一个都不取就是空集
        recurive(ret,0,nums,list);
        return ret;
    }
    void recurive(vector<vector<int>>& ret,int index,vector<int> nums,vector<int> &list){
        //ret结果,index当前取或者不取第几个元素,nums为数组,list当前子集
        //递归退出条件，index到nums.size()-1
        //注意必须传引用
        if(index>=nums.size()){
            ret.push_back(list);
            return ;
        }
        //取
        list.push_back(nums[index]);
        recurive(ret,index+1,nums,list);
        //不取，就是把之前放的删掉
        list.pop_back();
        recurive(ret,index+1,nums,list);
    }
    vector<vector<int>> subsets1(vector<int> &nums)
    {
        /*
        这个是消耗很大的算法，自底向上将元素小的子集两两合并，得到元素更多的子集，直到
        得到真子集。但是时间消耗特别大
        改进1：合并的时候，如果已经求出了该层所有个数则停止，但是该层个数咋求
        */
        vector<vector<int>> ret;
        int end = 0, start = 0;
        for (int i = 0; i < nums.size(); i++)
        {
            vector<int> tmp;
            tmp.push_back(nums[i]);
            end++;
            ret.push_back(tmp);
        }
        for (int i = 1; i < nums.size()-1; i++)
        {
            int thisnum = 0;
            fun(i + 1, start, end, ret, thisnum);
            start = end;
            end = end + thisnum;
        }
        vector<int> v(0);
        ret.push_back(v);
        if(nums.size()!=1)ret.push_back(nums);//等于1会重复放两次
        return ret;
    }
    void fun(int n, int i, int j, vector<vector<int>> &ret, int &thisnum)
    {
        //n本次每个子集中元素的个数，i，j上一层的子集的开头和结尾，如0,4
        //ret已有的子集结果,thisnum记录本次子集个数
        //该函数的作用是，将上一层的子集两两合并，并取唯一的放入集合
        for (int p = i; p < j - 1; p++)
        {
           
            for (int q = i + 1; q <= j; q++)
            {
                //这里开始错了，写到了上一个for里面，会导致，tmp元素个数一直在
                //增加，所以tmp.size()>n下面的插入不会被执行
                set<int> tmp(ret[p].begin(), ret[p].end());
                
                //和后面的两两和并
                for (int m = 0; m < ret[q].size(); m++)
                {
                    tmp.insert(ret[q][m]);
                }
                //判断有无重复
                if (tmp.size()== n)
                {
                    bool isdup = false;
                    for (int l = j; l < ret.size(); l++)
                    {
                        vector<int> now;
                        bool flag = true;
                        now.assign(tmp.begin(), tmp.end());
                        for (int z = 0; z < now.size(); z++)
                        {
                            if (now[z] != ret[l][z])
                            {
                                flag = false;
                                break; //这个是不重复的
                            }
                        }
                        if (flag)
                        {
                            //有重复,和重复与否无关，就是少量一个
                            isdup = true;
                            break;
                            
                        }
                        
                    }

                    if (!isdup)
                    {
                        vector<int> t;
                        t.assign(tmp.begin(), tmp.end());
                        ret.push_back(t);
                        thisnum++;
                    }
                    
                }
            }
        }
    }
};
