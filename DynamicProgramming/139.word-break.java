import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=139 lang=java
 *
 * [139] Word Break
 *
 * https://leetcode.com/problems/word-break/description/
 *
 * algorithms
 * Medium (36.47%)
 * Likes:    2872
 * Dislikes: 157
 * Total Accepted:    407.3K
 * Total Submissions: 1.1M
 * Testcase Example:  '"leetcode"\n["leet","code"]'
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, determine if s can be segmented into a space-separated
 * sequence of one or more dictionary words.
 * 
 * Note:
 * 
 * 
 * The same word in the dictionary may be reused multiple times in the
 * segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet
 * code".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple
 * pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 * 解题思路1：
 * dfs
 * 之后有用例超时
 * 考虑怎么记录数据来减少时间，但是记录数据好像需要一个栈结构
 * 
 * 解题思路2：
 * dfs时用dp保存结果
 * 想了半天没搞定，后来随便改改代码居然99%+100%AC了
 * 主要问题在于一，dp[i]保存什么，二、dp[i]什么时候保存
 * 一、dp[i]存什么
 * 一开始的想法是自底向上，dp[i]表示能不能从i到0，如果能则为true
 * 但是后来发现，本题不需要知道有几种break方法，只有有一个能到0就返回了，
 * 所以dp[i]=true应该用来表示不能从i到0，这样下一次用不同的方式到i的时候
 * 如果dp[i]=true，表示此路不同，不用dfs这条路了。
 * 二、dp[i]什么时候存
 * 只有当从i走到0，并且在0的时候发现走不通的时候才能让dp[i]=true；
 * 并且从i-0所有的中间路径都是走不通的都要设置为true；因此考虑用一个数组
 * 保存dp[i]到0的路径，当发现不通的时候把路径上所有点都设置为不通
 * 
 * 解题思路3：
 * 从完全背包的角度来考虑问题
 * 背包中的物品有顺序限制，这里的意思是说如果前i个物品放进去可以达到value j，
 * 但是在此题中这i个物品的摆放是有要求的，必须和前j个字符相同，因此先循环j，
 * 后循环物品可以保证物品的摆放顺序。
 * 一个是二维的DP
 * 一个是一维的DP，其实一维的DP反而更好理解
 * 
 * 
 * Tips:
 * 1、arraylist要用Integer而不能用int
 * 2、dfs最好不要带返回值，不然很麻烦
 * 3、Boolean和boolean的区别
 */

// @lc code=start
class Solution {
    boolean ret;
    boolean[] dp;

    public boolean wordBreak4(String s, List<String> wordDict) {
        // 一维的完全背包
        // 说句子能否被空格分隔成字典中的单词，反过来就是字典能否构成这个句子
        // 就是把字典作为物品集，句子作为背包，是个完全背包问题
        // memo[i]记录能否在第i个位置后面加空格
        int n = s.length();
        boolean[] memo = new boolean[n + 1];// 注意boolean和Boolean不一样，
        // 这里如果用Boolean，if后面的判断就会报空指针的错误，因为new Boolean之后没有初始化访问的时候就会报错
        memo[0] = true;
        for (int i = 1; i <= n; i++) {
            for (String word : wordDict) {
                int len = word.length();
                // 只有当word和从i开始前len个单词相同的时候，i才有可能加空格
                if (len <= i && word.equals(s.substring(i - len, i))) {
                    // 要么是以word结尾取memo[i-len]，要么是不以i结尾以其他在i处结尾的单词
                    memo[i] = memo[i] || memo[i - len];
                }
            }
        }
        return memo[n];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        // 二维的完全背包
        int n = s.length();
        boolean[][] memo = new boolean[n + 1][wordDict.size() + 1];// 注意boolean和Boolean不一样，
        for (int j = 0; j < wordDict.size() + 1; j++) {
            memo[0][j] = true;
        }
        for (int i = 1; i <= n; i++) {
            memo[i][0] = true;
            for (int j = 1; j < wordDict.size() + 1; j++) {
                int len = wordDict.get(j - 1).length();
                if (len <= i && wordDict.get(j - 1).equals(s.substring(i - len, i))) {
                    // 第一个for是在不使用第j-1个word作为最后的分割的可行性判断
                    boolean tmp1 = false;
                    for (int k = 0; k < wordDict.size(); k++) {
                        if (memo[i][k + 1]) {
                            tmp1 = true;
                            break;
                        }
                    }
                    // 第二个for是在使用第j-1个word作为最后的分割的可行性判断
                    boolean tmp2 = false;
                    for (int k = 0; k < wordDict.size(); k++) {
                        if (memo[i - len][k + 1]) {
                            tmp2 = true;
                            break;
                        }
                    }
                    memo[i][j] = tmp1 || tmp2;
                }
            }
        }
        // for(int i=1;i<=s.length();i++){
        // for(int j=1;j<=wordDict.size();j++){
        // System.out.print(memo[i][j]+" ");
        // }
        // System.out.println();
        // }

        // 同理最后输出的时候也要在所以wordDict之间进行可行性判断
        boolean ret = false;
        for (int k = 0; k < wordDict.size(); k++) {
            if (memo[s.length()][k + 1]) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    public void dfs2(String s, int index, List<String> wordDict, ArrayList<Integer> st) {
        if (index == 0) {
            ret = true;
            return;
        }

        boolean flag = false;
        for (int i = 0; i < wordDict.size(); i++) {
            int startindex = index - wordDict.get(i).length();

            // dp[i]为true表示此路不通，不用走了
            if (startindex >= 0 && dp[startindex] == false
                    && (s.substring(startindex, index)).equals(wordDict.get(i))) {
                // 记录
                st.add(st.size(), startindex);
                dfs2(s, startindex, wordDict, st);
                flag = true;
            }
            // 一旦ret已经为true，表示已经找到了一条路，可以立即返回
            if (ret)
                return;
        }
        if (!flag) {
            // 表示此路不通
            for (int i = 0; i < st.size(); i++) {
                dp[st.get(i)] = true;
            }
            st.clear();
        }
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        // 自顶向下超时，改为自底向上
        dp = new boolean[s.length()];
        // arraylist要用Integer而不能用int
        ArrayList<Integer> st = new ArrayList<Integer>();
        dfs2(s, s.length(), wordDict, st);
        return ret;
    }

    public void dfs1(String s, int index, List<String> wordDict) {

        // dfs感觉最好不要有返回值
        if (index >= s.length()) {
            ret = true;
            return;
        }
        for (int i = 0; i < wordDict.size(); i++) {
            int endindex = wordDict.get(i).length() + index;
            if (endindex <= s.length() && (s.substring(index, endindex)).equals(wordDict.get(i))) {
                dfs1(s, endindex, wordDict);
            }
        }
    }

    public boolean wordBreak1(String s, List<String> wordDict) {
        // 自顶向下超时，而且不好保存中间结果
        dfs1(s, 0, wordDict);
        return ret;
    }
}
// @lc code=end
