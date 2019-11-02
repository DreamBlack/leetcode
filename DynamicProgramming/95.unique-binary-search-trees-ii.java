import java.util.List;

import javax.swing.tree.TreeNode;

import sun.reflect.generics.tree.Tree;

/*
 * @lc app=leetcode id=95 lang=java
 *
 * [95] Unique Binary Search Trees II
 *
 * https://leetcode.com/problems/unique-binary-search-trees-ii/description/
 *
 * algorithms
 * Medium (36.91%)
 * Likes:    1524
 * Dislikes: 128
 * Total Accepted:    156.6K
 * Total Submissions: 419.7K
 * Testcase Example:  '3'
 *
 * Given an integer n, generate all structurally unique BST's (binary search
 * trees) that store values 1 ... n.
 * 
 * Example:
 * 
 * 
 * Input: 3
 * Output:
 * [
 * [1,null,3,2],
 * [3,2,null,1],
 * [3,1,null,null,2],
 * [2,1,3],
 * [1,null,2,null,3]
 * ]
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:
 * 
 * ⁠  1         3     3      2      1
 * ⁠   \       /     /      / \      \
 * ⁠    3     2     1      1   3      2
 * ⁠   /     /       \                 \
 * ⁠  2     1         2                 3
 * 
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 * 
 * 解题思路：
 * 1、一开始想在96一的基础上搞，发现太复杂了
 * 2、看了答案的思路发现可以在root为i的情况下分别处理左子树和右子树
 * 95在96题号之前，证明大概率不应该用96的思路
 * ★★★★★★★★★★★★这题不会哦★★★★★★★★★★
 */
class Solution {
    public List<TreeNode> generateTee(int start,int end){
        //生成数范围在start,end之间的字数
        List<TreeNode> list=new ArrayList<TreeNode>();
        if(start>end){
            list.add(null);
            return list;
        }
        if(start==end){
            TreeNode tr=new TreeNode(start);
            list.add(tr);
            return list;
        }
        for(int i=start;i<=end;i++){
            //i做root
            List<TreeNode> left=generateTee(start, i-1);
            List<TreeNode> right=generateTee(i+1, end);
            //把所有可能的左右子树拼起来
            for(int p=0;p<left.size();p++){
                for(int q=0;q<right.size();q++){
                    TreeNode root=new TreeNode(i);
                    root.left=left.get(p);
                    root.right=right.get(q);
                    list.add(root);
                }
            }
        }
        
        return list;
    }
    public List<TreeNode> generateTrees(int n) {
        if(n==0)return new ArrayList<TreeNode>();
        return generateTee(1,n);
        
    }
}
// @lc code=end

