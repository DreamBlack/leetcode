/*
 * @lc app=leetcode id=56 lang=cpp
 *
 * [56] Merge Intervals
 *
 * https://leetcode.com/problems/merge-intervals/description/
 *
 * algorithms
 * Medium (36.56%)
 * Likes:    2544
 * Dislikes: 200
 * Total Accepted:    410.1K
 * Total Submissions: 1.1M
 * Testcase Example:  '[[1,3],[2,6],[8,10],[15,18]]'
 *
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * Example 1:
 * 
 * 
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into
 * [1,6].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * 
 * NOTE: input types have been changed on April 15, 2019. Please reset to
 * default code definition to get new method signature.
 * 
 * 解法1：
 * 先排序
 * 解法2：
 * 构成图，然后用图的邻接表。
 */
class Solution {
public:
    vector<vector<int>> merge(vector<vector<int>>& intervals) {
        int begin, end;
        vector<vector<int>> ret;
        //二维数组的sort默认按第一列排序
        sort(intervals.begin(),intervals.end());
        if(intervals.empty())return ret;
        begin=intervals[0][0];
        end=intervals[0][1];
        ret.push_back(intervals[0]);
        for(int i=1;i<intervals.size();i++){
            if(intervals[i][1]<begin){
                ret.insert(ret.begin(),intervals[i]);
                begin=intervals[i][0];
               
            }else if(intervals[i][0]>end){
                ret.push_back(intervals[i]);
                end=intervals[i][1];
            }else if(intervals[i][0]<=begin){
                begin=intervals[i][0];
                end=max(end,intervals[i][1]);//这里更新也不能忘
                ret[0][0]=begin;
                ret[0][1]=max(intervals[i][1],ret[0][1]);
            }else{
                int count=ret.size();
                for(int j=0;j<count;j++){
                    if(intervals[i][0]>=ret[j][0]&&intervals[i][0]<=ret[j][1]){
                        ret[j][1]=max(intervals[i][1],ret[j][1]);
                        end=max(end,ret[j][1]);//不能忘更新end
                        break;
                    }
                }
                
            }
        }
        return ret;
    }
};

