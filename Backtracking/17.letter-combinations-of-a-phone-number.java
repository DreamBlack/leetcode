import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode id=17 lang=java
 *
 * [17] Letter Combinations of a Phone Number
 *
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 *
 * algorithms
 * Medium (42.91%)
 * Likes:    2959
 * Dislikes: 356
 * Total Accepted:    500.7K
 * Total Submissions: 1.1M
 * Testcase Example:  '"23"'
 *
 * Given a string containing digits from 2-9 inclusive, return all possible
 * letter combinations that the number could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given
 * below. Note that 1 does not map to any letters.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * 
 * Note:
 * 
 * Although the above answer is in lexicographical order, your answer could be
 * in any order you want.
 * Tips:
 * 1、由数组初始化集合
 * List<String>slist=new ArrayList<>(Arrays.asList("1","2"));
 * 不需要中括号
 * dict.add(new ArrayList<>(Arrays.asList('a','b','c')))和dict.add(Arrays.asList('a','b','c'))
 * 的区别；
 * 前者add的是一个普通的collections下的arraylist;
 * 后者add的是一个内部类Array类型，是不可以修改的list
 * 2、字符
 * java中字符类型为char（小写），但是由于定义集合的时候泛型参数必须写为
 * 类，所以要用包装类Character
 * 3、获取集合元素
 * 用get(index)，修改用set(i,object)，获得某个值的下标用indexof(object)
 * 4、获取string元素
 * 用charat(index)
 * 
 * 解题思路：
 * 1、思路解释，向面试官求证
 * 一共大概有3^n种结果，其中n为输入数字字符串的长度。使用深度优先搜索
 * 加回溯的方法，对于字符串中每一个数字都有3或者4个路径可以选择。
 * 当字符串中所有字符都处理完的时候证明到了叶节点，返回当前的结果，并回推
 * 到上一步，继续进行搜索。
 * 2、复杂度分析
 * O（3^n）--O(4^n)，空间复杂度为O(N)，栈最深的即为树的长度
 * 上面写错啦，看了答案应该是O(3^X*4^Y)，X+Y=N
 * 空间复杂度也写错啦，看了答案应该是 3^X*4^Y，毕竟这些结果都保存下来也是需要这么多空间的
 * 3、设计边界条件
 * 需要注意的是字符串为空时，需要返回[]，而不是[""]
 * 4、编码
 * 5、运行测试用例
 * 
 * 在电话号码从数组到字符的转换使用了两种方法
 * 1、使用二维List
 * 2、直接计算
 * 3、看了答案发现，这种明显是key,value的形式完全可以用map来记录
 */

// @lc code=start
class Solution {
    ArrayList<List<Character>> dict;
    List<String> res;
    //这里不可以用菱形语法，因为必须声明是哪个类的匿名内部类
    Map<String, String> phone = new HashMap<String, String>() {
        {
            put("2", "abc");
            put("3", "def");
            put("4", "ghi");
            put("5", "jkl");
            put("6", "mno");
            put("7", "pqrs");
            put("8", "tuv");
            put("9", "wxyz");
        }
    };

    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        if (digits.length() == 0) {
            return res;
        }
        backTracking(0, "", digits);
        return res;
    }

    void backTracking(int num, String path, String digits) {
        if (num == digits.length()) {
            res.add(path);
        } else {
            for (int i = 0; i < phone.get(digits.substring(num, num + 1)).length(); i++) {
                backTracking(num + 1, path + phone.get(digits.substring(num, num + 1)).charAt(i), digits);
            }
        }
    }

    public List<String> letterCombinations1(String digits) {
        // dict=new ArrayList<List<Character>>();

        // dict.add(new ArrayList<>(Arrays.asList('a','b','c')));
        // dict.add(new ArrayList<>(Arrays.asList('d','e','f')));
        // dict.add(new ArrayList<>(Arrays.asList('g','h','i')));
        // dict.add(new ArrayList<>(Arrays.asList('j','k','l')));
        // dict.add(new ArrayList<>(Arrays.asList('m','n','o')));
        // dict.add(new ArrayList<>(Arrays.asList('p','q','r','s')));
        // dict.add(new ArrayList<>(Arrays.asList('t','u','v')));
        // dict.add(new ArrayList<>(Arrays.asList('w','x','y','z')));

        res = new ArrayList<>();
        if (digits.length() == 0) {
            return res;
        }
        backTracking2(0, "", digits);
        return res;

    }

    void backTracking1(int num, String path, String digits) {
        if (num == digits.length()) {
            res.add(path);
        } else {
            for (int i = 0; i < dict.get(digits.charAt(num) - '2').size(); i++) {
                backTracking(num + 1, path + (dict.get(digits.charAt(num) - '2')).get(i), digits);
            }
        }
    }

    void backTracking2(int num, String path, String digits) {
        if (num == digits.length()) {
            res.add(path);
        } else {
            int offset = (digits.charAt(num) - '2' > 5) ? 1 : 0;
            int types = (digits.charAt(num) - '2' == 5 || digits.charAt(num) - '2' == 7) ? 4 : 3;
            for (int i = 0; i < types; i++) {
                int asii = 'a' + (digits.charAt(num) - '2') * 3 + offset + i;
                backTracking(num + 1, path + (char) asii, digits);
            }
        }
    }
}
// @lc code=end
