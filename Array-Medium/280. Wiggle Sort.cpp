/*
题目描述：
Given an unsorted array num, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]….

Example:

Input: nums = [3,5,2,1,6,4] 
Output: One possible answer is [3,5,1,6,2,4]
--------------------- 
解题思路：
简单题，遍历一遍，偶数个并且并右边的大就交换；
奇数个并且比右边的小就交换

*/
#include<vector>
using namespace std;
void wiggleSort(vector<int>& nums) {
    for(int i=0;i<nums.size()-1;i++){
        if(i%2==0){
            if(nums[i]>nums[i+1]){
                int temp=nums[i];
                nums[i]=nums[i+1];
                nums[i+1]=nums[i];
            }
        }else{
             if(nums[i]<nums[i+1]){
                int temp=nums[i];
                nums[i]=nums[i+1];
                nums[i+1]=nums[i];
            }
        }
    }
}