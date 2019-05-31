/*
 * @lc app=leetcode id=442 lang=cpp
 *
 * [442] Find All Duplicates in an Array
 *
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/description/
 *
 * algorithms
 * Medium (60.91%)
 * Likes:    1062
 * Dislikes: 113
 * Total Accepted:    97.9K
 * Total Submissions: 160.7K
 * Testcase Example:  '[4,3,2,7,8,2,3,1]'
 *
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements
 * appear twice and others appear once.
 * 
 * Find all the elements that appear twice in this array.
 * 
 * Could you do it without extra space and in O(n) runtime?
 * 
 * Example:
 * 
 * Input:
 * [4,3,2,7,8,2,3,1]
 * 
 * Output:
 * [2,3]
 * 
 */
class Solution {
public:
    vector<int> findDuplicates(vector<int>& nums) {
        vector<int>res;
        for(int i=0;i<nums.size();i++){

            if(nums[i]!=0&&nums[i]!=i+1){
                if(nums[nums[i]-1]==nums[i])
                {
                    res.push_back(nums[i]);
                    nums[i]=0;//这里一开始忘记标记了，否则会重复输出
                }
                else
                    {
                        swap(nums[i],nums[nums[i]-1]);
                        i--;
                    }
            }
        }
        return res;
    }
    vector<int> findDuplicates2(vector<int>& nums) {
        //方法2 ：标记法，出现就标记为-，如果没标记之前就已经是负数，表示存在不止一次
        vector<int>res;
        for(int i=0;i<nums.size();i++){
            if(nums[abs(nums[i])-1]<0)
                res.push_back(abs(nums[i]));
            nums[abs(nums[i])-1]=-nums[abs(nums[i])-1];
        }
       
        return res;
    }
    vector<int> findDuplicates3(vector<int>& nums) {
        //方法3 ：数值法，每出现一次个数+n，如果最后值大于2n肯定就出现不止一次
        vector<int>res;
        int n=nums.size();
        for(int i=0;i<nums.size();i++){
            int idx=(nums[i]-1)%n;
            nums[idx]+=n;
        }
        for(int i=0;i<n;i++){
            if(nums[i]>2*n){
                res.push_back(i+1);
            }
        }
       
        return res;
    }
     vector<int> findDuplicates4(vector<int>& nums) {
         //方法4：在1的基础上，不是标记为0，而是最后遍历的时候Nums[i]!=i+1就输出
        vector<int>res;
        for(int i=0;i<nums.size();i++){

           
                if(nums[nums[i]-1]!=nums[i])
                {
                   
                        swap(nums[i],nums[nums[i]-1]);
                        i--;
                }
            
        }
        for(int i=0;i<nums.size();i++){
            if(nums[i]!=i+1)
                res.push_back(nums[i]);//注意这里输出不是i，而是Nums[i]
        }
        return res;
    }
};

