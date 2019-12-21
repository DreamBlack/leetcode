import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode id=131 lang=java
 *
 * [131] Palindrome Partitioning
 *
 * https://leetcode.com/problems/palindrome-partitioning/description/
 *
 * algorithms
 * Medium (42.59%)
 * Likes:    1278
 * Dislikes: 51
 * Total Accepted:    193K
 * Total Submissions: 440.1K
 * Testcase Example:  '"aab"'
 *
 * Given a string s, partition s such that every substring of the partition is
 * a palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * Example:
 * 
 * 
 * Input: "aab"
 * Output:
 * [
 * ⁠ ["aa","b"],
 * ⁠ ["a","a","b"]
 * ]
 * 
 * 解题思路：
 * 1、带返回值的回溯
 * 直接dfs，不带memo，时间复杂度O(2^N-1)，空间O(N)
 * 用map记录每个string对应的返回值
 * 
 * 2、不带返回值的回溯
 * 不带返回值，那么记录信息都在函数参数里。res是返回结果，list记录当前路径上
 * 的分割结果。每到一个叶节点都对应一条路径，每一条路径都对应res中一个结果。
 * 
 * 
 * 3、dp
 * ?
 * 
 * about java:
 * 1、泛型定义的时候
 * 不可以写成List<List<String>>=new ArrayList<ArrayList<String>>();
 * 因为泛型Arraylist<integer>并不是List<integer>的子类，所以要写成List<List<String>>=new ArrayList<List<String>>();
 * 2、List<List<String>>作为函数返回值的问题
 * 由于List<List<String>>tmp=backtracking(s.substring(i));
 * 返回的tmp其实是backtracking中res指向的对象的指针，也就是说二者指向的是同一块
 * 内存空间
 * res.add(j+index,new ArrayList<>(tmp.get(j)));1
 * 
 * res.add(j+index,tmp.get(j));2
 * 
 * res.get(j+index).add(0, s.substring(0, i));3
 * 2会让res.get(j+index)==tmp.get(j)
 * 3对res.get(j+index)的改动都会同样作用到tmp.get(j)上
 * 所以虽然没有显示的对tmp.get(j)做任何修改，它也变了，
 * 而上一次递归最后memo.put(s,res)这个res就等于此时的tmp
 * 因此虽然没有修改memo，memo中元素也随着下次递归中res一起改变了
 * 如果2改为1，res中第j+index个元素就和tmp第j个元素也和memo中元素没有关系了，
 * 就结果正确
 * 
 * 第二种改法
 * 或者这么换，tmp中第j个元素虽然还是会和res中第i+index个元素一起变化
 * 但是由于tmp切断了和递归返回值ttmp也就是memo的关系，memo中的元素就不会
 * 和这次递归中的res一起变化了
 * List<List<String>>ttmp=backtracking(s.substring(i));
 * List<List<String>>tmp=new ArrayList<List<String>>();
 * for(List<String>t:ttmp){
 *      tmp.add(new ArrayList<String>(t));
 * }
 * 
 * 总结：
 * 也就是说如果函数返回值为引用、或者集合的元素为对象引用的时候一定要注意
 * 多用new，少用辅助
                
 */

// @lc code=start
class Solution {

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        List<String> list = new ArrayList<>();
        backtracking(s, 0, res, list);
        return res;
    }

    void backtracking(String s, int pos, List<List<String>> res, List<String> list) {
        if (pos == s.length()) {
            // 到叶节点的时候把当前路径上的结果加入res
            res.add(new ArrayList<>(list));
        } else {
            for (int i = pos; i < s.length(); i++) {
                if (isParlindrome(s.substring(pos, i + 1))) {
                    // 記下标记
                    list.add(s.substring(pos, i + 1));
                    backtracking(s, i + 1, res, list);
                    // 删除标记
                    list.remove(list.size() - 1);
                }
            }
        }
    }

    Map<String, List<List<String>>> memo;

    public List<List<String>> partition1(String s) {
        memo = new HashMap<String, List<List<String>>>();
        return backtracking1(s);
    }

    List<List<String>> backtracking1(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        // if(s.length()==1){
        // ArrayList<String>tmp=new ArrayList<>();
        // tmp.add(s);
        // res.add(tmp);
        // memo.put(s, res);
        // return res;
        // }
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        for (int i = 1; i < s.length(); i++) {
            if (isParlindrome(s.substring(0, i))) {
                List<List<String>> tmp = backtracking1(s.substring(i));
                int index = res.size();
                for (int j = 0; j < tmp.size(); j++) {
                    res.add(j + index, new ArrayList<>(tmp.get(j)));
                    res.get(j + index).add(0, s.substring(0, i));
                }

            }
        }
        // 不要忘了元素本身
        if (isParlindrome(s)) {
            List<String> tmp = new ArrayList<>();
            tmp.add(s);
            res.add(res.size(), tmp);
        }
        memo.put(s, res);
        return res;
    }

    private boolean isParlindrome(String s) {
        if (s.length() <= 1) {
            return true;
        }
        boolean ret = true;
        int head = 0, tail = s.length() - 1;
        while (head < tail) {
            if (s.charAt(head++) != s.charAt(tail--)) {
                ret = false;
                break;
            }
        }
        return ret;
    }

    private boolean isParlindrome2(String s) {
        return s.equals(new StringBuffer(s).reverse().toString());
    }
}
// @lc code=end
