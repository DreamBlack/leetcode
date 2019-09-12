/*
 * @lc app=leetcode id=34 lang=cpp
 *
 * [34] Find First and Last Position of Element in Sorted Array
 *
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 *
 * algorithms
 * Medium (34.17%)
 * Likes:    1922
 * Dislikes: 97
 * Total Accepted:    344.4K
 * Total Submissions: 1M
 * Testcase Example:  '[5,7,7,8,8,10]\n8'
 *
 * Given an array of integers nums sorted in ascending order, find the starting
 * and ending position of a given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * 
 * 解题思路：
 * 1、本来想二分查找，找到之后再往左右两边找，但是这样好像容易退化
 * 为O(N)
 * 2、后来看了答案是两次二分查找，就想先找最右的target-1，和
 * 在最左边的target+1，然后中间的就是要求的。但是后来发现这种方法
 * 最后的处理比较烦，要讨论target,target-1,target+1到底在数组
 * 中存不存在的问题，分类情况太多
 * 3、由2出发想到，那我直接找左边的target和右边的target不就行了，
 * 最后只要对target存不存在进行讨论就简单的多了。
 */
class Solution {
public:
    vector<int> searchRange(vector<int>& nums, int target) {
        if(nums.empty())
            return vector<int>({-1,-1});
        int a=binarysearchright(0,nums.size()-1,nums,target);
        int b=binarysearchleft(0,nums.size()-1,nums,target);
        //对应二分查找里三个找不到的情况
        if(a>=nums.size()||b<0||nums[a]!=target){
            //found
            a=-1;
            b=-1;
        }
        return vector<int>({b,a});
    }
    int binarysearchright(int left,int right,vector<int>nums,int target){
        while(left<=right){
            //由于这里没有讨论left==right的情况，所以如果没找到
            //target有可能返回left=-1，也有可能返回right=n，也
            //有可能返回的是中间会插入的位置
            //这三个值就是找不到的情况
            int mid=(left+right)/2;
            if(nums[mid]==target){
                if(mid<nums.size()-1){
                    if(nums[mid+1]>target)
                        return mid;
                    else{
                        left=mid+1;
                    }
                }else{
                    return mid;
                }
            }else if(nums[mid]<target){
                left=mid+1;
                
            }else{
                right=mid-1;
            }
        }
        return left;
    }
    int binarysearchleft(int left,int right,vector<int>nums,int target){
        while(left<=right){
            int mid=(left+right)/2;
            if(nums[mid]==target){
                if(mid>0){
                    if(nums[mid-1]<target)
                        return mid;
                    else{
                        right=mid-1;
                    }
                }else{
                    return mid;
                }
            }else if(nums[mid]<target){
                left=mid+1;
                
            }else{
                right=mid-1;
            }
        }
        return left;
    }
};

