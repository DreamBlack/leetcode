/*
 * @lc app=leetcode id=769 lang=cpp
 *
 * [769] Max Chunks To Make Sorted
 *
 * https://leetcode.com/problems/max-chunks-to-make-sorted/description/
 *
 * algorithms
 * Medium (51.59%)
 * Likes:    462
 * Dislikes: 80
 * Total Accepted:    23.9K
 * Total Submissions: 46K
 * Testcase Example:  '[4,3,2,1,0]'
 *
 * Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we
 * split the array into some number of "chunks" (partitions), and individually
 * sort each chunk.  After concatenating them, the result equals the sorted
 * array.
 * 
 * What is the most number of chunks we could have made?
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [4,3,2,1,0]
 * Output: 1
 * Explanation:
 * Splitting into two or more chunks will not return the required result.
 * For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1,
 * 2], which isn't sorted.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [1,0,2,3,4]
 * Output: 4
 * Explanation:
 * We can split into two chunks, such as [1, 0], [2, 3, 4].
 * However, splitting into [1, 0], [2], [3], [4] is the highest number of
 * chunks possible.
 * 
 * 
 * Note:
 * 
 * 
 * arr will have length in range [1, 10].
 * arr[i] will be a permutation of [0, 1, ..., arr.length - 1].
 * 
 * 解题思路：
 * 1、解法一
 * 这题看起来很烦，不可能列出所有的排列逐个计算。只能依赖于chunk
 * 内[i,j]元素大小也要在[i,j]以内。
 * 2、看了答案，有点摸不着头脑
 * 遍历数组，记录当前数组中的最大值，如果当前值i==max，表示
 * 0-i可以作为一个chunk，或者说上一个max=i的位置到现在这个位置
 * 也可以作为一个chunk，（要想chunk个数最多，应该多分几段）此时
 * ans可以+1;否则最大值max还没到它最终位置，
 * 0-i不可以作为一个chunk。
 * 
 * 
 */
class Solution {
public:
    int maxChunksToSorted(vector<int>& arr) {
        int ans=0,maxn=0;
        for(int i=0;i<arr.size();i++){
            maxn=max(maxn,arr[i]);
            if(maxn==i)ans++;//注意这里是i而不是arr[i]
        }
        return ans;
    }
    int maxChunksToSorted1(vector<int>& arr) {
        /*
        最大的返回值chunks=arr.length（），就是当数组本来就有
        序的时候；如果每个块正确排序后串起来能得到总体正确排序的
        那证明，这个块里面的数本来就在这个块里面。反之，逆序的
        必须整体进行排序
         */
        
        //index用来记录i位置上的元素的正确位置离当前位置的距离
        for(int i=0;i<arr.size();i++){
            arr[i]=arr[i]-i;
        }
        //跳过距离差值为0的
        int i=0,ret=0;
        while(i<arr.size()&&arr[i]==0){
            ret++;
            i++;
        }
        if(i==arr.size())return ret;
        //记录错位的元素与正确位置之间的范围
        int left=i,right=left+arr[i++];
        ret++;
        for(;i<arr.size();i++){
            int templ=0,tempr=0;
            if(arr[i]>0){
                templ=i;
                tempr=i+arr[i];
            }else if(arr[i]<0){
                templ=i+arr[i];
                tempr=i;
            }else{
                templ=tempr=i;
            }
            //新的范围比当前范围大，证明该加一个chunk了
            if(right<templ){
                ret++;
                right=tempr;
                left=templ;
            }else if(right>=templ){
                //否则，重新记录当前chunk的左右值
                left=min(left,templ);
                right=max(right,tempr);
            }
            
        }
        return ret;
        
    }
};

