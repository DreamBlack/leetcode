/*
 * @lc app=leetcode id=41 lang=cpp
 *
 * [41] First Missing Positive
 *
 * https://leetcode.com/problems/first-missing-positive/description/
 *
 * algorithms
 * Hard (29.68%)
 * Likes:    2097
 * Dislikes: 646
 * Total Accepted:    244.5K
 * Total Submissions: 820.7K
 * Testcase Example:  '[1,2,0]'
 *
 * Given an unsorted integer array, find the smallest missing positive
 * integer.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,2,0]
 * Output: 3
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [3,4,-1,1]
 * Output: 2
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: [7,8,9,11,12]
 * Output: 1
 * 
 * 
 * Note:
 * 
 * Your algorithm should run in O(n) time and uses constant extra space.
 * 
 * 解题思路1：
 * intuition：根据数组中最大和最小元素区间[a,b]讨论。
 * 如果a>1，肯定返回1；否则a=1
 * 如果b-a+1==cnt，在没有重复元素的情况下，肯定返回b+1；
 * 否则，缺失的元素必然在[a,b]之间。
 * 再次遍历数组，将nums[nums[i]]置为负数，则，最后遍历一遍为
 * 正的就是缺失的最小正整数。
 * 1、先遍历一遍记录最大值和最小值，同时把所有负数置为1，作为标记
 * 2、讨论minc和minb的情况
 * 
 * 解题思路2：
 * 其实从解题思路1已经可以知道，最重要的就是下标和元素值对应起来
 * 所以不记录最大最小值，直接交换Nums[i]和nums[nums[i]-1]，再
 * 通过第二遍遍历即可.
 * 有一些边界元素需要特别考虑
 * 
 * tips:这里要充分利用空间所以是nums[i]和nums[nums[i]-1]换
 */
class Solution {
public:
      int firstMissingPositive(vector<int>& nums) {
          if(nums.empty())return 1;//注意边界
            for(int i=0;i<nums.size();){
                if(nums[i]>0&&nums[i]<=nums.size()&&nums[i]!=nums[nums[i]-1]){
                    swap(nums[i],nums[nums[i]-1]);
                    
                }else{
                    i++;
                }
            }
        int i=0;//可以把i写在外面，这样就不用两个return了
              for(;i<nums.size();i++){
                  if(nums[i]!=i+1)
                    break;
              }
              
              return i+1;
      }
    int firstMissingPositive1(vector<int>& nums) {
        int minc=INT_MAX,maxc=INT_MIN,cnt=0;
        for(int i=0;i<nums.size();i++){
            if(nums[i]>0){
                minc=min(nums[i],minc);
                maxc=max(nums[i],maxc);
                cnt++;
            }else{
                nums[i]=1;//用不用的东西标记
            }
        }
        
        if(minc==INT_MAX)return 1;
        if(minc==maxc){
            //这里是用来处理重复元素的，如[2,2,2]
            if(minc==1)return 2;
            else return 1;
        }
        if(minc>1){
            //[a,b],a>1,1每次出现过
            return 1;
        }else{
            //[1,b]之间缺，缺的最小的在[1,b]之间
            for(int i=0;i<nums.size();i++){
                if(nums[i]!=0){

                    if(abs(nums[i])<nums.size()){
                       
                        nums[abs(nums[i])]=-abs(nums[abs(nums[i])]);//为负数表示有
                         
                    }
                }
            }
            for(int i=2;i<nums.size();i++){
                if(nums[i]>=0){
                    return i;
                }
            }
        }
        if(maxc-minc+1==cnt){
            //处理[1,2],[1,2,3]这种情况
            return maxc+1;
        }
        return nums.size();
    }
};

