import java.util.List;

/*
 * @lc app=leetcode id=47 lang=java
 *
 * [47] Permutations II
 *
 * https://leetcode.com/problems/permutations-ii/description/
 *
 * algorithms
 * Medium (42.08%)
 * Likes:    1397
 * Dislikes: 48
 * Total Accepted:    291.8K
 * Total Submissions: 675.5K
 * Testcase Example:  '[1,1,2]'
 *
 * Given a collection of numbers that might contain duplicates, return all
 * possible unique permutations.
 * 
 * Example:
 * 
 * 
 * Input: [1,1,2]
 * Output:
 * [
 * ⁠ [1,1,2],
 * ⁠ [1,2,1],
 * ⁠ [2,1,1]
 * ]
 *  解题思路：
 * 1、思路解释，向面试官求证
 * 有重复元素，所以先排序，这样方便跳过重复元素。
 * 之后对于第i个元素，有n-i种选择的方法，依次遍历这些选择。
 * 每次选取了n个元素时，便对应一个结果。为了防止一个元素被访问多次
 * 需要用visited数组标记访问过的元素，防止对同一个元素的重复访问；
 * 为了防止结果中出现一些相同的排列，需要跳过同一层中的相同的元素。
 * 2、复杂度分析
 * 不是特别好算，但是肯定是n!。不论空间还是时间
 * 3、设计边界条件
 * 如果数组为空，应该返回[]
 * 以及数组指向的指针为Null
 * 4、编码
 * 5、运行测试用例
 * 
 * 另一种网友的思路是，不需要保存index，因为，每次都是在0-n-1这n个数
 * 当中选择，index在代码中也并没有起太大的作用。除了返回的时候。
 * 但其实返回的时候可以用path.size()==nums.length代替，这样index就半点
 * 用都没有了。
 * 其次是对去重部分的改进。当前的想法是同样的元素，在树的同一层不用
 * 重复访问。网友的想法是，如果之前的与之相同的元素没有被访问，那当前
 * 元素也不可以访问。确保树上从根到叶节点的路径上相同元素可以同时被访问；
 * 但是兄弟结点直接，相同元素只能访问一次
 * 
 * 
 */

// @lc code=start
class Solution {
    List<List<Integer>> result;
    boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        visited = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        backTracking(nums, path);
        return result;
    }

    void backTracking(int[] nums, List<Integer> path) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) {
                    continue;
                }
                if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                    continue;
                }

                path.add(nums[i]);
                visited[i] = true;
                backTracking(nums, path);
                path.remove(path.size() - 1);
                visited[i] = false;

            }
        }
    }

    public List<List<Integer>> permuteUnique1(int[] nums) {
        result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        visited = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        backTracking1(nums, 0, path);
        return result;
    }

    void backTracking1(int[] nums, int index, List<Integer> path) {
        if (index == nums.length) {
            result.add(new ArrayList<>(path));
        } else {
            for (int i = 0; i < nums.length; i++) {
                /*
                 * 如果把while写在这里，就不一定是backTracking之后才运行过来的 （因为ifvisited会跳过一些元素），这样就会使得一条根到叶节点
                 * 的路径上也跳过了重复元素，这显然是不对的。 所以直接写在backTracking返回后的地方，确定是返回后跳过了
                 * 兄弟结点中相同的。这里还需要注意判别条件是nums[i]==nums[i+1]
                 * 而不是nums[i]==nums[i-1]了。当前i已经访问过了，应该决定i+1 要不要访问，而不是i要不要访问
                 */
                if (!visited[i]) {
                    path.add(nums[i]);
                    visited[i] = true;
                    backTracking1(nums, index + 1, path);
                    path.remove(path.size() - 1);
                    visited[i] = false;
                    while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                        i++;
                    }
                }
            }
        }
    }
}
// @lc code=end
