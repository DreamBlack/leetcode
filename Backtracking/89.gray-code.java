import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=89 lang=java
 *
 * [89] Gray Code
 *
 * https://leetcode.com/problems/gray-code/description/
 *
 * algorithms
 * Medium (46.66%)
 * Likes:    499
 * Dislikes: 1370
 * Total Accepted:    148.9K
 * Total Submissions: 314K
 * Testcase Example:  '2'
 *
 * The gray code is a binary numeral system where two successive values differ
 * in only one bit.
 * 
 * Given a non-negative integer n representing the total number of bits in the
 * code, print the sequence of gray code. A gray code sequence must begin with
 * 0.
 * 
 * Example 1:
 * 
 * 
 * Input: 2
 * Output: [0,1,3,2]
 * Explanation:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * 
 * For a given n, a gray code sequence may not be uniquely defined.
 * For example, [0,2,3,1] is also a valid gray code sequence.
 * 
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 0
 * Output: [0]
 * Explanation: We define the gray code sequence to begin with 0.
 * A gray code sequence of n has size = 2^n, which for n = 0 the size is 2^0 =
 * 1.
 * Therefore, for n = 0 the gray code sequence is [0].
 * 
 * 解题思路
 * 1、找规律，递归实现
 * 2、使用异或
 * 找递推公式g(i)=i^(i>>1)
 * 前提已知i+1会让从右往左数第一个0开始右边的数全部取反.
 * 比较这两个数
 *   abcd0e f g 
 * ^(0abcd0e f)
 *   abcd1e'f'g'
 * ^(0abcd1e'f')
 * 二者只有一位会不一样(0,d),(1,d)
 */

// @lc code=start
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer>res=new ArrayList<>();
        for(int i=0;i<(1<<n);i++){
            res.add(i^(i>>1));
        }
        return res;
    }
    public List<Integer> grayCode1(int n) {
        
        if(n<=0){
            return new ArrayList<Integer>(Arrays.asList(new Integer[]{0}));
        }else{
            List<Integer>ret=grayCode(n-1);
            int tmp=1<<(n-1);
            for(int i=ret.size()-1;i>=0;i--){
                ret.add(tmp|ret.get(i));
            }
            return ret;
        }
    }
}
// @lc code=end

