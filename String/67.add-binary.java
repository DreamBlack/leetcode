/*
 * @lc app=leetcode id=67 lang=java
 *
 * [67] Add Binary
 *
 * https://leetcode.com/problems/add-binary/description/
 *
 * algorithms
 * Easy (40.44%)
 * Likes:    1395
 * Dislikes: 244
 * Total Accepted:    387K
 * Total Submissions: 917.7K
 * Testcase Example:  '"11"\n"1"'
 *
 * Given two binary strings, return their sum (also a binary string).
 * 
 * The input strings are both non-empty and contains only characters 1 or 0.
 * 
 * Example 1:
 * 
 * 
 * Input: a = "11", b = "1"
 * Output: "100"
 * 
 * Example 2:
 * 
 * 
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 * 
 */

// @lc code=start
class Solution {
    /**
     * concise版本
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        if(a==null||b==null){
            return a!=null?a:(b!=null?b:"");
        }
        if(a.length()==0){
            return b;
        }
        if(b.length()==0){
            return a;
        }
        StringBuilder result=new StringBuilder();
        int i=a.length()-1,j=b.length()-1,carry=0;
        while(i>=0||j>=0){//注意这里是或而不是与
            int sum=carry;
            if(i>=0){
                sum+=a.charAt(i)-'0';
            }
            if(j>=0){
                sum+=b.charAt(j)-'0';
            }
            result.append(sum%2);
            carry=sum/2;
        }
        if(carry!=0){
            result.append(carry);
        }
        return result.reverse().toString();
    }
    /**
     * 想用异或和与实现加法的，后来发现这里操作数是string gg 了
     * @param a
     * @param b
     * @return
     */
    public String addBinaryFirstSolution(String a, String b) {
        if(a==null||b==null){
            return a!=null?a:(b!=null?b:"");
        }
        if(a.length()==0){
            return b;
        }
        if(b.length()==0){
            return a;
        }
        if(b.length()>a.length()){
            return addBinary(b, a);
        }
        StringBuffer bb=new StringBuffer(b);
        while(bb.length()<a.length()){
            bb.insert(0, 0);
        }
        int inc=0;
        int length=a.length();
        StringBuffer result=new StringBuffer();
        for(int i=length-1;i>=0;i--){
            if(inc==0){
                if(a.charAt(i)=='0'&&bb.charAt(i)=='0'){
                    result.insert(0, '0');
                    inc=0;
                }
                if(a.charAt(i)=='0'&&bb.charAt(i)=='1'){
                    result.insert(0, '1');
                    inc=0;
                }
                if(a.charAt(i)=='1'&&bb.charAt(i)=='0'){
                    result.insert(0, '1');
                    inc=0;
                }
                if(a.charAt(i)=='1'&&bb.charAt(i)=='1'){
                    result.insert(0, '0');
                    inc=1;
                }
            }else{
                if(a.charAt(i)=='0'&&bb.charAt(i)=='0'){
                    result.insert(0, '1');
                    inc=0;
                }
                if(a.charAt(i)=='0'&&bb.charAt(i)=='1'){
                    result.insert(0, '0');
                    inc=1;
                }
                if(a.charAt(i)=='1'&&bb.charAt(i)=='0'){
                    result.insert(0, '0');
                    inc=1;
                }
                if(a.charAt(i)=='1'&&bb.charAt(i)=='1'){
                    result.insert(0, '1');
                    inc=1;
                }
            }
            

        }
        if(inc==1){
            result.insert(0, '1');
        }

        return result.toString();
    }
    
}
// @lc code=end

