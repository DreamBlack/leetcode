/*
 * @lc app=leetcode id=238 lang=cpp
 *
 * [238] Product of Array Except Self
 *
 * https://leetcode.com/problems/product-of-array-except-self/description/
 *
 * algorithms
 * Medium (55.03%)
 * Likes:    2284
 * Dislikes: 191
 * Total Accepted:    262.1K
 * Total Submissions: 474.7K
 * Testcase Example:  '[1,2,3,4]'
 *
 * Given an array nums of n integers where n > 1,  return an array output such
 * that output[i] is equal to the product of all the elements of nums except
 * nums[i].
 * 
 * Example:
 * 
 * 
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * 
 * 
 * Note: Please solve it without division and in O(n).
 * 
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does
 * not count as extra space for the purpose of space complexity analysis.)
 * 
 * 解题思路：
 * 1、1写完才发现题目中说不能用除法
 * 2、2是看了答案才想出来的。自己觉得不用除法不可能所以甚至没太多思考。
 * 其实重点在于用空间记录数据以减少时间消耗，以及有了L[i]之后L[i+1]的递推
 * 3、空间O(1)又是欺骗性的利用了输出空间记录数据
 */
class Solution {
public:
    vector<int> productExceptSelf1(vector<int>& nums) {
        int product=1;
        int count=0,index=-1;
        for(int i=0;i<nums.size();i++){
            if(nums[i]==0){
                count++;//记录0的个数
                index=i;
            }else
                product*=nums[i];
        }
        vector<int> ret(nums.size(),0);
        if(count>1)return ret;//如果有多于一个0，那么结果为0
        //否则，下面是只有1个0或者没有0的情况
        if(count==1){
            ret[index]=product;
        }else{
            for(int i=0;i<nums.size();i++){
                ret[i]=product/nums[i];
            
            }
        }
        
        return ret;
    }
     vector<int> productExceptSelf2(vector<int>& nums) {
         //i上的结果等于i左边的所有元素的乘积*i右边的所有元素的乘积
         /*
         刚看到这个思路的时候想，那不是O(N^2)的，算每一个元素左右元素乘积的话，
         后来看解题思路才发现，并不需要n^2.因为得到l[i]后，L[i+1]=l[i]*nums[i]
         同理r[i-1]=r[i]*nums[i]。这样只需要两个额外的数组记录这些信息就可以了
         最后ret[i]=l[i]*r[i];
         时间O(N)，空间O(N)
         */
         vector<int>l(nums.size(),1);
         vector<int>r(nums.size(),1);
         for(int i=1;i<nums.size();i++){
             l[i]=l[i-1]*nums[i-1];
         }
         for(int i=nums.size()-2;i>=0;i--){
             r[i]=r[i+1]*nums[i+1];
         }
         vector<int>ret(nums.size(),1);
         for(int i=0;i<nums.size();i++){
             ret[i]=l[i]*r[i];
         }
         return ret;
     }
      vector<int> productExceptSelf(vector<int>& nums) {
          /*
          有了2的解法，空间O(1)的很快就出来了。因为题目说返回结果不算空间复杂度
          那就可以利用结果的空间记录L，之后On-the-fly的计算R，然后结果就出来了
          */
          vector<int>ret(nums.size(),1);
         for(int i=1;i<nums.size();i++){
             ret[i]=ret[i-1]*nums[i-1];
         }
         int count=1;
        for(int i=nums.size()-1;i>=0;i--){
             ret[i]=ret[i]*count;
             count=count*nums[i];
         }
         return ret;
      }
};

