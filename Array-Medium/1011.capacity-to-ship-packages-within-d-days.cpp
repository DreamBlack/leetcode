/*
 * @lc app=leetcode id=1011 lang=cpp
 *
 * [1011] Capacity To Ship Packages Within D Days
 *
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/
 *
 * algorithms
 * Medium (52.77%)
 * Likes:    254
 * Dislikes: 10
 * Total Accepted:    9.5K
 * Total Submissions: 17.9K
 * Testcase Example:  '[1,2,3,4,5,6,7,8,9,10]\n5'
 *
 * A conveyor belt has packages that must be shipped from one port to another
 * within D days.
 * 
 * The i-th package on the conveyor belt has a weight of weights[i].  Each day,
 * we load the ship with packages on the conveyor belt (in the order given by
 * weights). We may not load more weight than the maximum weight capacity of
 * the ship.
 * 
 * Return the least weight capacity of the ship that will result in all the
 * packages on the conveyor belt being shipped within D days.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * Output: 15
 * Explanation: 
 * A ship capacity of 15 is the minimum to ship all the packages in 5 days like
 * this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 * 
 * Note that the cargo must be shipped in the order given, so using a ship of
 * capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6,
 * 7), (8), (9), (10) is not allowed. 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: weights = [3,2,2,4,1,4], D = 3
 * Output: 6
 * Explanation: 
 * A ship capacity of 6 is the minimum to ship all the packages in 3 days like
 * this:
 * 1st day: 3, 2
 * 2nd day: 2, 4
 * 3rd day: 1, 4
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: weights = [1,2,3,1,1], D = 4
 * Output: 3
 * Explanation: 
 * 1st day: 1
 * 2nd day: 2
 * 3rd day: 3
 * 4th day: 1, 1
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= D <= weights.length <= 50000
 * 1 <= weights[i] <= 500
 * 
 * 题目大意：
 * 有一批货物，一艘船。按货物顺序装载到船上，求船的最小承载能力，
 * 使得能在D天内把所有货物搬空
 * 解题思路：
 * 肯定和累加和有关
 * 1、写的好痛苦，很快就把代码写出来了，但是代码很多问题
 * 先是死循环了，找半天发现是for (int i = 0; i < weights.size(); i++)
 * 循环里面有个i-2，导致i一直小于n
 * 2、后来count算的不对，发现是最后一个数自成一组的时候没有加上
 * 3、又发现有些用例出错，比如[3,2,2,4,1,4],n=3.最后一组是[1,4]
 * 算法不能出来最后一个数和别的组合的情况
 * 
 * 总而言之，都是在处理最后元素的时候没考虑清楚
 * 写完之后时间又太长。
 * 后来忍不住看了网上的解法，时间少的就是用了二分搜索而已
 * 其实自己应该能想到的。这里要注意的有两点
 * 1、函数divide,day用来给定船的容量计算几天运完，day参考的网上
 * 的方法，比我写的简洁多了。记住多用减法，少用加法！！！
 * 2、二分搜索要做一点改进。本来是left=mid+1,right=mid-1
 * 这里如注释所说，为了满足题意，right=mid
 * 3、默认是向下取整的要用ceil((double)a/b)，否则相当于对整数
 * 取整，没啥卵用
 */
class Solution
{
public:
    int shipWithinDays(vector<int> &weights, int D)
    {
        int maxw = -1, sum = 0;
        for (int i = 0; i < weights.size(); i++)
        {
            maxw = max(weights[i], maxw);
            sum += weights[i];
        }
        int cai =(sum % D>0)?sum/D+1:sum/D;
        int left = max(cai, maxw); //ceil(),floor()向上向下取整
        int right=sum;
        int mid=0;
        int count=0;
        while(left<right){//这里是小于,防止left>right
           mid=(left+right)/2;
            count=days(weights,mid);
           
            if(count>D){//分组过多，mid过小
                left=mid+1;
                
            }else {//分组不够，mid过大
                
                right=mid;//改了这里，本来是right=mid
                /*
                话说答案有两种可能，有多种分法都满足D天运完，
                这样要选最小的mid；
                所以这里应该使right=mid，让mid始终包含在内。这样即使
                之前还有更小的mid能保证找到；如果没有也能返回现在这个；

                没有刚好D天运完的，但是可以让某一天分成两次运，
                divide算出来的会是小于D的。
                由于二分搜索里面没有==D再输出的判定，相当于在小于D
                的里面找那个满足条件的最小的，仍然是可以的
                 */
            }
           
        }
        return left;
    }
    int days(vector<int> &weights, int mid) {
        int cap=mid;
        int count=1;//这里应该为1否则会少一个
        for(int i=0;i<weights.size();i++){
            if(cap>=weights[i]){
                //能装下
                cap-=weights[i];
            }else{
                //装不下，放到第二天装
                count++;
                cap=mid-weights[i];
            }
        }
        return count;
    }
    int divide(vector<int> &weights, int mid)
    {
        int count = 0;
       
            
            int ret = mid;
            //看看能不能分
            for (int i = 0; i < weights.size(); i++)
            {
                if (i == weights.size() - 1)
                {
                    count++; //没有处理好i==n-1时候的东西，导致死循环了
                }
                else
                {
                    int leijia = weights[i++];
                    while (leijia <= ret && i < weights.size())
                    { //这里应该是<=
                        leijia += weights[i++];
                    }
                    count++;
                    //下面这句本来没有考虑i==n-1，导致死循环了。因为如果本来i==n-1，+1之后再减2会一直循环
                    if (i == weights.size() && leijia <= ret)
                        break; //如果最后一个数和其它的已经加过了,直接退出
                    i = i - 2; //因为之后for里面i要++（写错）
                }
            }
        return count;//返回能分成几份
    }
};
