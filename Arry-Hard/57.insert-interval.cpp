/*
 * @lc app=leetcode id=57 lang=cpp
 *
 * [57] Insert Interval
 *
 * https://leetcode.com/problems/insert-interval/description/
 *
 * algorithms
 * Hard (31.75%)
 * Likes:    1016
 * Dislikes: 131
 * Total Accepted:    196.9K
 * Total Submissions: 618.4K
 * Testcase Example:  '[[1,3],[6,9]]\n[2,5]'
 *
 * Given a set of non-overlapping intervals, insert a new interval into the
 * intervals (merge if necessary).
 * 
 * You may assume that the intervals were initially sorted according to their
 * start times.
 * 
 * Example 1:
 * 
 * 
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with
 * [3,5],[6,7],[8,10].
 * 
 * NOTE: input types have been changed on April 15, 2019. Please reset to
 * default code definition to get new method signature.
 * 解题思路：
 * 这题开始的时候考虑了好久，分成几种情况，每种情况怎么组合，
 * 结果写出来还一堆乱七八糟的错误
 * 看了人家的想法才知道，根本不用考虑那么多情况，就是遍历intervals，
 * 如果和当前元素没有交集，直接将intervals[i]插入ret；
 * 之后对于有交集的区间进行处理（关键就是这个有交集的范围如何定义），
 * 将这个区间合并，也就是找到这个区间的min和max，插入；
 * 最后将剩下的再插入ret。
 * 
 * 关于Intervals和newInterval的有交集区间怎么定义，
 * 第一个intervals[i][1]>=newInterval[0](有交集，必然是和newInterval的左端有交)；
 * 第一个intervals[i][0]<=newInterval[1](有交集，比如是和newInterval的右端有重合)
 * 
 * 
 * 
 * tips:
 * 尽量不要分类讨论，分类特别麻烦的一般都不是好解法。

 */
class Solution {
public:
    vector<vector<int>> insert(vector<vector<int>>& intervals, vector<int>& newInterval) {
        vector<vector<int>>ret;
        int i=0;
        while(i<intervals.size()&&intervals[i][1]<newInterval[0]){
            ret.push_back(intervals[i++]);
        }
        //处理有交集的部分
        while(i<intervals.size()&&intervals[i][0]<=newInterval[1]){//这里有==
            newInterval[0]=min(intervals[i][0],newInterval[0]);
            newInterval[1]=max(intervals[i][1],newInterval[1]);
            i++;
        }
        ret.push_back(newInterval);
        while(i<intervals.size()){
            ret.push_back(intervals[i++]);
        }
        

        
        return ret;
    }
   
};

