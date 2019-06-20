/*
 * @lc app=leetcode id=835 lang=cpp
 *
 * [835] Image Overlap
 *
 * https://leetcode.com/problems/image-overlap/description/
 *
 * algorithms
 * Medium (52.31%)
 * Likes:    190
 * Dislikes: 275
 * Total Accepted:    11.4K
 * Total Submissions: 21.7K
 * Testcase Example:  '[[1,1,0],[0,1,0],[0,1,0]]\n[[0,0,0],[0,1,1],[0,0,1]]'
 *
 * Two images A and B are given, represented as binary, square matrices of the
 * same size.  (A binary matrix has only 0s and 1s as values.)
 * 
 * We translate one image however we choose (sliding it left, right, up, or
 * down any number of units), and place it on top of the other image.  After,
 * the overlap of this translation is the number of positions that have a 1 in
 * both images.
 * 
 * (Note also that a translation does not include any kind of rotation.)
 * 
 * What is the largest possible overlap?
 * 
 * Example 1:
 * 
 * 
 * Input: A = [[1,1,0],
 * ⁠           [0,1,0],
 * [0,1,0]]
 * B = [[0,0,0],
 * [0,1,1],
 * [0,0,1]]
 * Output: 3
 * Explanation: We slide A to right by 1 unit and down by 1 unit.
 * 
 * Notes: 
 * 
 * 
 * 1 <= A.length = A[0].length = B.length = B[0].length <= 30
 * 0 <= A[i][j], B[i][j] <= 1
 * 
 * 
 */
class Solution {
public:
    int largestOverlap(vector<vector<int>>& A, vector<vector<int>>& B) {
        
        int a=help(A,B);
       // int b=help(B,A);//忘了还有b这种反过来的情况
        return a;
    }
    int help(vector<vector<int>>& A, vector<vector<int>>& B){
        int ret=0;
        int r=A.size(),c=A[0].size();
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                int count1=0,count2=0;
                for(int p=i;p<r;p++){
                    for(int q=j;q<c;q++){
                        if(A[p][q]==1&&A[p][q]==B[p-i][q-j]){
                            count1++;
                        }
                        //忘了还有b这种反过来的情况
                        if(B[p][q]==1&&B[p][q]==A[p-i][q-j]){
                            count2++;
                        }

                    }
                }
                ret=max(ret,count1);
                ret=max(ret,count2);
            }
        }

        return ret;
    }
};

