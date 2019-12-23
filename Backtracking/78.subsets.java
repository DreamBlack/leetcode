import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=78 lang=java
 *
 * [78] Subsets
 *
 * https://leetcode.com/problems/subsets/description/
 *
 * algorithms
 * Medium (52.83%)
 * Likes:    1948
 * Dislikes: 50
 * Total Accepted:    371.4K
 * Total Submissions: 699.5K
 * Testcase Example:  '[1,2,3]'
 *
 * Given a set of distinct integers, nums, return all possible subsets (the
 * power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * Example:
 * 
 * 
 * Input: nums = [1,2,3]
 * Output:
 * [
 * ⁠ [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 * 
 * 解题思路
 * 1、我的想法是，对于每个数有选或者不选两种情况，如dfs1
 * 
 * 2、参考思路的想法是，每次选择可以有n个，对应于Nums里n个数，
 * 而这n个数都有选或者不选两种选择
 * 
 * 3、1和2的不同之处在于，1构建的是二叉树，而2构建的是n叉数，
 * 树更矮
 * 
 * 4、但是2不是在叶节点的时候加入，而是每次在下一层开始之前加入
 * 
 * 
 * 位操作解法
 * 已知n个数共有2^n个子集。0~2^n-1之间刚好有2^n个数，数中共有n位数字
 * 第i个位置为0表示不取第i个元素，为1表示取第i个元素
 * 
 * 复杂度分析：
 * O(2^N)
 * 
 * Tips:
 * 1、java
 * new ArrayList<List<Integer>>()可以
 * 但是new ArrayList<ArrayList<Integer>>()会报错
 * 
 * 2、java里一切都是引用，要注意你在递归中的引用是否都指向的是同一个对象
 * 如果是的话，记得及时remove
 * 
 * 
 */

// @lc code=start
class Solution {
    ArrayList<List<Integer>> ret;

    public List<List<Integer>> subsets2(int[] nums) {
        // dfs回溯法
        ret = new ArrayList<List<Integer>>();
        dfs(nums, 0, new ArrayList<Integer>());
        return ret;
    }

    public void dfs1(int[] nums, int index, ArrayList<Integer> bk) {
        // 这里的bk传的是引用，所以在所有递归调用中bk引用指向的是同一个
        // arraylist对象，所以bk要添加也要及时删除
        if (index == nums.length) {
            ret.add(new ArrayList<>(bk));
            return;
        }
        bk.add(nums[index]);
        dfs1(nums, index + 1, bk);
        bk.remove(bk.size() - 1);
        dfs1(nums, index + 1, bk);
    }

    public void dfs(int[] nums, int index, ArrayList<Integer> bk) {
        // 这里的bk传的是引用，所以在所有递归调用中bk引用指向的是同一个
        // arraylist对象，所以bk要添加也要及时删除

        ret.add(new ArrayList<>(bk));
        for (int i = index; i < nums.length; i++) {
            bk.add(nums[i]);
            // System.out.println("index="+i+",bk="+bk.toString());
            dfs(nums, i + 1, bk);
            bk.remove(bk.size() - 1);
        }
    }

    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<>();
        backTracking(nums, 0, res, path);
        return res;
    }

    public void backTracking(int[] nums, int pos, List<List<Integer>> res, List<Integer> path) {
        if (pos == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        path.add(nums[pos]);
        backTracking(nums, pos + 1, res, path);
        path.remove(path.size() - 1);
        backTracking(nums, pos + 1, res, path);
    }

    public List<List<Integer>> subsets(int[] nums) {
        // 位向量法

        List<List<Integer>> res = new ArrayList<List<Integer>>();

        for (int i = 0; i < (1 << nums.length); i++) {
            List<Integer> path = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) != 0) {
                    path.add(nums[j]);
                }
            }
            res.add(path);
        }
        return res;
    }
}
// @lc code=end
