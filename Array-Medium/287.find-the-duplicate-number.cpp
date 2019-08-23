/*
 * @lc app=leetcode id=287 lang=cpp
 *
 * [287] Find the Duplicate Number
 *
 * https://leetcode.com/problems/find-the-duplicate-number/description/
 *
 * algorithms
 * Medium (49.61%)
 * Likes:    2649
 * Dislikes: 301
 * Total Accepted:    203.3K
 * Total Submissions: 404K
 * Testcase Example:  '[1,3,4,2,2]'
 *
 * Given an array nums containing n + 1 integers where each integer is between
 * 1 and n (inclusive), prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,3,4,2,2]
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [3,1,3,4,2]
 * Output: 3
 * 
 * Note:
 * 
 * 
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n^2).
 * There is only one duplicate number in the array, but it could be repeated
 * more than once.
 * 解题思路1：
 * 总结题目要求A：只读B：空间O(1)C:时间N^2以内（那就是Nlogn）
 * 既然不能用多余空间也不能改变数组，那就不能用排序还有数据结构了
 * 那么可选的就是二分法、双指针法。
 * 问题1：二分法是针对有序的而言，本题什么有序呢
 * 误区：陷入序列和是递增的的误区，找不到查找的目标
 * 正确思路：要返回的数1-n本身是递增的，这样要查找的就是重复的那个
 * 
 * 解决问题1后，问题就变成了，怎么判断mid是不是重复的
 * 考虑ABC的限制，选择顺序访问数组，记录mid出现的次数count和
 * 比mid大的数出现的次数countlarge。
 * 出口：count>1;
 * 去左边：countlarge>n-mid
 * 去右边：countlarge<n-mid
 * 
 * 解题思路2：使用循环链表
 * 从index=0开始，对应的value为nums[0]，因为nums中存在重复数据，
 * 因此可以利用index将其构成链表，且是一个循环链表，环的入口点即
 * 是重复的那个值。
 * 1、环如下
 * index 0 , 1, 2, 3, 4, 5, 6
 * value:2, 6, 4, 1, 3, 1, 5
 * 0 - > 2 - > 4 -> 3 -> 1 -> 6 -> 5-> [1- >6-> 5 ->1 链表环]
 * 注意链表头结点0不在数组中，此外链表中的元素即为nums中的元素
 * 2、双指针法判断有没有环（肯定有），并找到相遇点
 * 3、根据相遇点到环入口点的距离 = 链表起始点到环入口点的距离
 * 使用两个指针分别指向起始点和相遇点，同速行走，相遇处即为入口点
 * 而这个入口点就是环的入口
 */
class Solution {
public:
    int findDuplicate(vector<int>& nums) {
        int slow=nums[0];
        int fast=nums[0];
        //找到相遇点
        do{
            slow=nums[slow];
            fast=nums[nums[fast]];
        }while (slow!=fast);
        //找到环入口点
        int ptr1=nums[0];
        int ptr2=slow;
        while (ptr1!=ptr2)
        {
            ptr1=nums[ptr1];
            ptr2=nums[ptr2];
        }
        return ptr1;
        
    }
    int findDuplicate1(vector<int>& nums) {
        int head=1,tail=nums.size()-1;
        int sumA=0;
        for(int i=0;i<nums.size();i++){
            sumA+=nums[i];
        }
        int sum=(head+tail)*tail/2;
        while (head<=tail)
        {
            int mid=(head+tail)/2;
            int count=0,countlarge=0;
            for(int i=0;i<nums.size();i++){
                if(nums[i]==mid)
                    count++;
                if(nums[i]>mid)
                    countlarge++;
            }
            if(count>1)return mid;
            //关键在于这里用什么情况去判断减少一个部分的解空间
            //这里就应该立足题意
            if(nums.size()-1-mid<countlarge){
                //如果比mid大的个数比正常情况下多的话，证明重复的数比mid大
                //应该到[mid+1,tail]中找
                head=mid+1;
            }else{
                //如果比mid大的数比正常情况下下，应该到[head,mid-1]中找
                tail=mid-1;
            }
        }
        
        return -1;
    }
};

