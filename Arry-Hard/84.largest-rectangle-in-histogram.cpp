/*
 * @lc app=leetcode id=84 lang=cpp
 *
 * [84] Largest Rectangle in Histogram
 *
 * https://leetcode.com/problems/largest-rectangle-in-histogram/description/
 *
 * algorithms
 * Hard (32.17%)
 * Likes:    2376
 * Dislikes: 62
 * Total Accepted:    199.5K
 * Total Submissions: 615.8K
 * Testcase Example:  '[2,1,5,6,2,3]'
 *
 * Given n non-negative integers representing the histogram's bar height where
 * the width of each bar is 1, find the area of largest rectangle in the
 * histogram.
 * 
 * 
 * 
 * 
 * Above is a histogram where width of each bar is 1, given height =
 * [2,1,5,6,2,3].
 * 
 * 
 * 
 * 
 * The largest rectangle is shown in the shaded area, which has area = 10
 * unit.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input: [2,1,5,6,2,3]
 * Output: 10
 * 
 * 解题思路：
 * 解法1：
 * O(NlogN)
 * 一定区间内的最大面积由最短的那个板决定，找到最短的板之后，分别处理
 * 左边和右边，用递归的思想。
 * 
 * 解法2：动态规划
 * 对于bar i，计算以它为高的矩形面积，关键在于知道可能的宽度，也就是说
 * 要找到一个范围，在这个范围里，bar长度要>=height[i]。因此对于每个
 * bar i，找到其对应的最左和最右的满足条件的bar就可以知道对应的面积。
 * 一般要用o(n^2)，但可以使用动态规划的思想利用已知结果，不再从左右两边
 * 去找，而是跳跃性的找。
 * https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/28902/5ms-O(n)-Java-solution-explained-(beats-96)
 * 
 * 解法3：递增栈
 * 一开始也想到了用递增栈，但是每次遇到比栈顶元素小的会一次性清空栈
 * 其实不应该清空，只把比当前大的弹出，否则会导致算的宽度小。之后对于
 * 每次弹出的元素，要找到其宽度
 */

// @lc code=start
class Solution {
public:
int largestRectangleArea(vector<int>& heights) {
   
    if(heights.empty())return 0;
    int area=0,maxarea=0;
    heights.push_back(0);//加一个空元素，这样就不用在遍历完数组之后再判断一次!empty()了
     stack<int>s;
    for(int i=0;i<heights.size();i++){
        if(s.empty()||heights[i]>heights[s.top()]){
            //因为要根据下标计算width，所以栈里面应该放下标而不是数值
            s.push(i);
        }else{
            while(!s.empty()&&heights[i]<heights[s.top()]){
                //不能一次性清空，因为短板可以跨越很远，而不仅仅是当前这个栈的大小
                int t=s.top();

                s.pop();
                int height=heights[t];
                int width=0;
                if(s.empty()){
                    //如果栈空了,表面该元素是第一个或者
                    //之前的所有元素都比当前元素大，宽度应从0算起
                    width=i-0;
                   
                }else{
                    //这里不是i-t，而是s.top-1，因为栈里面的元素不是在物理上
                    //相邻的，但在栈里相邻表示这两个相邻元素之间的所有元素都
                    //和右边那个元素大小相同
                    width=i-s.top()-1;

                }
                 maxarea=max(maxarea,width*height);
            }
            s.push(i);
        }
        
    }
   
    return maxarea;
}
    int largestRectangleArea1(vector<int>& heights) {
        int ret=0;
        if(heights.empty())return 0;
        ret=max(ret,partion(heights,0,heights.size()-1));
        return ret;
    }
    int partion(vector<int>& heights,int left,int right){
        
        if(left>right)return 0;
        if(left==right)return heights[left];
        int minindex=left;
        for(int i=left+1;i<=right;i++){
            if(heights[i]<heights[minindex]){
                minindex=i;
            }
        }
        int ret=(right-left+1)*heights[minindex];
        int leftret=partion(heights,left,minindex-1);
        int rightret=partion(heights,minindex+1,right);
        if(ret>leftret){
            return(ret<rightret)?rightret:ret;;
        }else{
            return (leftret<rightret)?rightret:leftret;
        }
        return ret;
    }
};
// @lc code=end

