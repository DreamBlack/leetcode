import java.util.List;

import sun.security.util.Length;

/*
 * @lc app=leetcode id=120 lang=java
 *
 * [120] Triangle
 *
 * https://leetcode.com/problems/triangle/description/
 *
 * algorithms
 * Medium (40.66%)
 * Likes:    1388
 * Dislikes: 161
 * Total Accepted:    206.5K
 * Total Submissions: 500.9K
 * Testcase Example:  '[[2],[3,4],[6,5,7],[4,1,8,3]]'
 *
 * Given a triangle, find the minimum path sum from top to bottom. Each step
 * you may move to adjacent numbers on the row below.
 * 
 * For example, given the following triangle
 * 
 * 
 * [
 * ⁠    [2],
 * ⁠   [3,4],
 * ⁠  [6,5,7],
 * ⁠ [4,1,8,3]
 * ]
 * 
 * 
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * 
 * Note:
 * 
 * Bonus point if you are able to do this using only O(n) extra space, where n
 * is the total number of rows in the triangle.
 * 
 * 解题思路：
 * 1、递归
 * 注意事项：result插值为MAX_INT而不是0,；
 * 循环初始值last包含[0][0]而不是0
 * 有一个用例超时
 * 
 * 2、看可不可以用memo记录一些数据
 * 由自顶向下改为自定向上记录数据
 * 这里自底向上可以把所有情况都考虑到
 */

// @lc code=start
class Solution {
    private int result;

    
    
    public int minimumTotal(List<List<Integer>> triangle) {
        //自底向上
       
        result = Integer.MAX_VALUE; //注意点2
        int r = triangle.size();
        if (r == 0)
            return 0;
        List<Integer>dp=triangle.get(r-1);
        for(int i=r-2;i>=0;i--){
            
            for(int j=0;j<=i;j++){
                dp.set(j,triangle.get(i).get(j)+Math.min(dp.get(j),dp.get(j+1)));
            }
        }

        return dp.get(0);
    }
    /**
     * 
     * @param r当前行，r=2
     * @param c当前是第几个数
     * @param last
     * @param triangle
     */
    void dfs1(int r, int c, int last, List<List<Integer>> triangle) {
        if (r == triangle.size() - 1) {
            result = Math.min(result, last);
            return;
        }
        int nownum = (r + 1) * (r + 2) / 2;
        int t = last + triangle.get(r + 1).get(c + r + 1 - nownum);

        dfs1(r + 1, c + r + 1, t, triangle);

        
        t = last + triangle.get(r + 1).get(c + r + 2 - nownum);
        dfs1(r + 1, c + r + 2, t, triangle);
        
    }

    public int minimumTotal1(List<List<Integer>> triangle) {
        // 递归
       
        result = Integer.MAX_VALUE; //注意点2
        int r = triangle.size();
        if (r == 0)
            return 0;
        //注意点1：这里初始值是包括[0][0]位置点的
        dfs1(0, 0, triangle.get(0).get(0), triangle);
        return result;
    }

}
// @lc code=end
