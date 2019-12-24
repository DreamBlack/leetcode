import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=77 lang=java
 *
 * [77] Combinations
 *
 * https://leetcode.com/problems/combinations/description/
 *
 * algorithms
 * Medium (49.64%)
 * Likes:    1072
 * Dislikes: 59
 * Total Accepted:    245K
 * Total Submissions: 478.9K
 * Testcase Example:  '4\n2'
 *
 * Given two integers n and k, return all possible combinations of k numbers
 * out of 1 ... n.
 * 
 * Example:
 * 
 * 
 * Input: n = 4, k = 2
 * Output:
 * [
 * ⁠ [2,4],
 * ⁠ [3,4],
 * ⁠ [2,3],
 * ⁠ [1,2],
 * ⁠ [1,3],
 * ⁠ [1,4],
 * ]
 * 
 * 解题思路：
 * 1、回溯法
 * 改进：
 *      1、不需要标记数组visited
 *       答：因为这种按序访问就不会走之前的那个元素的路
 *      2、for(int i=pos;i<n;i++)
 * 当path里面有m个数的时候，之后还需要加k-m个数，当前i到n最多还有n-i+1个
 * 如果n-i+1<k-m，那么这条路径没有必要走下去了，因为即使走到底也凑不够m个数
 * 所以可以改写为~(i>n-(k-m)+1).
 * 注意这种写法里进行下次回溯的时候比如又加了一个pos，所以
 * 应该是m=path().size()+1
 * ~(i>n-(k-m-1)+1)=~(i>n-k+m+2)=i<=n-k+m+2=i<n-k+m+1=i<n-(k-m)+1
 * 2、递归法
 * 问题分解为两个子问题
 *  C ( n, k ) = [C ( n - 1, k - 1)+k] + C ( n - 1, k ) 
 * 当前问题可分解为，取第n个元素和子问题C ( n - 1, k - 1)所有解，
 * 或者不取第k个元素，直接取子问题C ( n - 1, k ) 的解。
 * 是不是很像动态规划的状态转移方程
 * 3、动态规划法
 * 
 * 
 * 注意点：
 * 1、这到题是要考虑去重的
 * 所以子节点处理的时候要注意不要将重复的路径算进去
 * 2、递归时候的参数到底怎么写
 * 这里应该是i+1，而不是pos+1.如果是pos+1那些个循环也没有意义了，因为每次都走一样的路径
 * path里add的应该和下一次递归里的参数相同
 * 3、对于二维的集合
 * addall,add什么的都没啥用
 * 记住一定要用for循环去深拷贝！！！！
 */

// @lc code=start
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        // dp解法
        List<List<Integer>>[] dp = new List[k + 1];

        List<List<Integer>> pre = new ArrayList<List<Integer>>();
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i && j <= k; j++) {
                if (j == 0) {
                    dp[j] = new ArrayList<List<Integer>>();
                    dp[j].add(new ArrayList<>());// 记住这里一定要在[0]new一个不然就是null而不是[]
                    pre = new ArrayList<List<Integer>>(dp[j]);
                } else if (i == j) {

                    List<Integer> path = new ArrayList<>();
                    for (int q = 1; q <= i; q++) {
                        path.add(q);
                    }
                    dp[j] = new ArrayList<List<Integer>>(Arrays.asList(path));
                } else {

                    List<List<Integer>> res = new ArrayList<List<Integer>>();
                    for (int p = 0; p < pre.size(); p++) {// 这些地方一定要用for深拷贝
                        res.add(new ArrayList<>(pre.get(p)));
                    }
                    int nown = i;
                    res.forEach((obj) -> obj.add(nown));
                    pre = new ArrayList<List<Integer>>();
                    for (int p = 0; p < dp[j].size(); p++) {
                        res.add(new ArrayList<>(dp[j].get(p)));
                        pre.add(new ArrayList<>(dp[j].get(p)));
                    }

                    dp[j] = new ArrayList<List<Integer>>();
                    dp[j].addAll(res);
                }

            }
        }

        return dp[k];

    }

    public List<List<Integer>> combine2(int n, int k) {

        if (k == 0 || n == k) {
            List<Integer> path = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                path.add(i + 1);
            }

            return new ArrayList<List<Integer>>(Arrays.asList(path));
        }
        List<List<Integer>> res = combine2(n - 1, k - 1);
        res.forEach((obj) -> obj.add(n));
        res.addAll(combine2(n - 1, k));
        return res;
    }

    public List<List<Integer>> combine1(int n, int k) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (k > n)
            return res;
        List<Integer> path = new ArrayList<>();
        backTracking(n, k, 0, path, res);
        return res;
    }

    void backTracking(int n, int k, int pos, List<Integer> path, List<List<Integer>> res) {
        // pos记录的是当前该从第几个元素访问
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            // System.out.println("add array: "+path);
        } else {
            for (int i = pos; i < n - (k - path.size()) + 1; i++) {// 没错，i从pos开始，这样保证有序
                path.add(i + 1);
                backTracking(n, k, i + 1, path, res);// 下一个pos=i+1，而不是pos+1
                path.remove(path.size() - 1);
            }
        }
    }
}
// @lc code=end
