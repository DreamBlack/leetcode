/*
 * @lc app=leetcode id=969 lang=cpp
 *
 * [969] Pancake Sorting
 * Given an array A, we can perform a pancake flip: We choose some positive integer k <= A.length, then reverse the order of the first k elements of A.  We want to perform zero or more pancake flips (doing them one after another in succession) to sort the array A.

Return the k-values corresponding to a sequence of pancake flips that sort A.  Any valid answer that sorts the array within 10 * A.length flips will be judged as correct.

 

Example 1:

Input: [3,2,4,1]
Output: [4,2,4,3]
Explanation: 
We perform 4 pancake flips, with k values 4, 2, 4, and 3.
Starting state: A = [3, 2, 4, 1]
After 1st flip (k=4): A = [1, 4, 2, 3]
After 2nd flip (k=2): A = [4, 1, 2, 3]
After 3rd flip (k=4): A = [3, 2, 1, 4]
After 4th flip (k=3): A = [1, 2, 3, 4], which is sorted. 
Example 2:

Input: [1,2,3]
Output: []
Explanation: The input is already sorted, so there is no need to flip anything.
Note that other answers, such as [3, 3], would also be accepted.
 

Note:

1 <= A.length <= 100
A[i] is a permutation of [1, 2, ..., 
解题思路：
好久不写代码，发生了很多笔误，导致莫名其妙的错误，找半天才发现
总之一定要先有整体思路，考虑好每个变量的功能，省的自己都忘了要干嘛
 */
class Solution {
public:
    vector<int> pancakeSort(vector<int>& A) {
        int n=A.size();
        vector<int> nums;
        //count用来计算从左到右还没有排好序的数的个数
        //pos用来记录未排序数组中最大的数所在的下标
       int count=n-1,pos=-1;
      
        for(int j=n;j>1;j--){
            for(int i=0;i<=count;i++){
                if(A[i]==j){
                    pos=i;//找到未排序数组中最大的数所在的下标
                }
            }
            if(pos!=count){//最大数不在末尾
                if(count==1){//如果最后只剩两个数，而最大数在头部的话,reverse(2)即可
                    nums.push_back(2);
                    break;
                }
                nums.push_back(pos+1);//把最大数reverse到头部
                reverse(A.begin(),A.begin()+pos+1);
                
                nums.push_back(count+1);//再将所有count个数reverse回来
                reverse(A.begin(),A.begin()+count+1);
               
               
            }
             count--;
        }
        return nums;
    }
};

