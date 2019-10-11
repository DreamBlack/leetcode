/*
 * @lc app=leetcode id=5 lang=cpp
 *
 * [5] Longest Palindromic Substring
 *
 * https://leetcode.com/problems/longest-palindromic-substring/description/
 *
 * algorithms
 * Medium (27.87%)
 * Likes:    4495
 * Dislikes: 406
 * Total Accepted:    677K
 * Total Submissions: 2.4M
 * Testcase Example:  '"babad"'
 *
 * Given a string s, find the longest palindromic substring in s. You may
 * assume that the maximum length of s is 1000.
 * 
 * Example 1:
 * 
 * 
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "cbbd"
 * Output: "bb"
 * 
 * 题目大意，找数组中最长回文子串
 * 解题思路1：
 * 所有可能的子串个数为n^2个，想不出来o(n)的解，所有初步思路是处理每一个可能
 * 的子串。对于单个子串如果用普通的判断是否回文的方法可能要到n^3级别，因此思路
 * 就是利用以前的信息（动态规划），对于每个串用o(1)的时间判断是否回文。
 * 1、构建一个上三角的矩阵，初值全为0，表示第j行到第i列的不回文（j<=i）
 * 2、对角线全设置为1
 * 3、处理每一列
 *  如果s[i]!=s[j],不可能回文，设0；
 *  如果s[i]==s[j]，要看中间的会不会回文，即sj+1-si-1是不是回文的，
 *      a.如果是j+1==i，表示当前串只有两个元素，sjsi(j+1=i)，
 *      二者有相等，肯定回文
 *      b.否则，看即sj+1-si-1是否回文reocrd[j+1][i-1]==1，肯定回文，
 *      不回文否则
 * 解法2：最长公共子串的应用
 * 找s和reverse的最长公共子串，但是由于最长公共子串本身可能是不回文的，
 * 所以找到之后要判断是否回文，不回文的话要找次长的
 * 解法3：暴力
 * 时间O(N^3)，空间O(1)
 * 解法4：动态规划的空间提升
 * 由解法1而来，其空间为平方级别，但是每次只用了reocrd[j+1][i-1]的结果，
 * 因此可以只保存一列，而不是所有的矩阵，空间O（n）
 * 解法5：动态规划的空间提升2
 * 作为回文肯定有一个center，这个center可能是单个字符（奇数串），也可能是
 * 两个字符（偶数串），而这样的center一共就有n+(n-1)=2n-1个。
 * 可以对这2n-1个中心，依次处理，找到其最长的回文串并记录.
 * 时间o(n^2)空间O(1)
 * 解法5：manacher's算法
 * 时间o(n)
 * 在解法5的基础上。
 * 1、先在每个元素之间插入#，这样就都可以作为奇数长的回文串处理。
 * 2、用R记录当前最长的回文串所能达到的最远下标；C记录当前最长回文串的中心元素
 * 所在下标；R=-1,C=-1;数组z记录回文半径
 * 3、从左到右进行更新：
 *   if i>R，从i开始向左右扩展，同时更新R和C；
 *   else   
 *      计算当前最长回文串的左边界CL，i关于C对称的元素i'对于的左边界；
 *      if cl<pl，则根据i和i'互为对称可知z[i]=z[i']
 *      if cl>pl，则为R-i+1；
 *      if cl==pl，则要从R往两边扩展
 */

// @lc code=start
class Solution {
public:
string longestPalindrome(string s) {
    string ss="#";
    for(int i=0;i<s.size();i++){
        ss+=s[i];
        ss+="#";
    }
    vector<int>rd(ss.size(),0);
    int R=-1,C=-1;
    int maxr=0;
    string rets="";
    for(int i=0;i<ss.size();i++){
        if(R<=i){
            rd[i]=1;
        }else{
            rd[i]=min(rd[2*C-i],R-i+1);
        }
        while(i+rd[i]<ss.size()&&i-rd[i]>-1){
            if(ss[i-rd[i]]==ss[i+rd[i]]){
                rd[i]++;
            }else{
                break;
            }
        }
        if(i+rd[i]>R){
            R=i+rd[i]-1;
            C=i;
        }
        
        if(maxr<rd[i]){
            rets=ss.substr(i-rd[i]+1,2*rd[i]-1);
            maxr=rd[i];
        }
    }
    string ret="";
    for(int i=0;i<rets.size();i++){
        if(rets[i]!='#'){
            ret+=rets[i];
        }
    }
    return ret;
}
string longestPalindrome4(string s) {
    if(s.empty())return s;
    if(s.size()==1)return s;
    int n=s.size();
    int start=0,end=0;
    for(int i=0;i<n;i++){
        int len1=expandAroundCenter(s,i,i);
        int len2=expandAroundCenter(s,i,i+1);
        int len=max(len1,len2);
        if(len>end-start){
            start=i-(len-1)/2;
            end=i+len/2;
        }
    }
    return s.substr(start,end-start+1);
}
int expandAroundCenter(string s,int left,int right){
    int L=left,R=right;
    while(L>=0&&R<s.size()&&s[L]==s[R]){
        L--;
        R++;
    }
    return R-L-1;
}
    string longestPalindrome1(string s) {
        if(s.empty())return s;
        if(s.size()==1)return s;
        int n=s.size();
        string ret="";
        vector<int>record(n,0);
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                if(i==j){
                    record[j]=1;
                }else{
                    if(s[i]==s[j]){
                        //相等才可能回文
                        if(j+1<i&&record[j+1]==1){
                            record[j]=1;
                        }else if(j+1>=i){
                            record[j]=1;
                        }else{
                            record[j]=0;
                        }
                    }else{
                        record[j]=0;
                    }
                    
                }
                if(record[j]==1){
                    string tmp=s.substr(j,i-j+1);
                    if(tmp.size()>ret.size()){
                        ret.assign(tmp);
                    }
                }
            }
            
        }
        return ret;
    }
};
// @lc code=end

