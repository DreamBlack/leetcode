/*
 * @lc app=leetcode id=695 lang=cpp
 *
 * [695] Max Area of Island
 *
 * https://leetcode.com/problems/max-area-of-island/description/
 *
 * algorithms
 * Medium (57.22%)
 * Likes:    1089
 * Dislikes: 62
 * Total Accepted:    82.9K
 * Total Submissions: 144.8K
 * Testcase Example:  '[[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]'
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's
 * (representing land) connected 4-directionally (horizontal or vertical.) You
 * may assume all four edges of the grid are surrounded by water.
 * 
 * Find the maximum area of an island in the given 2D array. (If there is no
 * island, the maximum area is 0.)
 * 
 * Example 1:
 * 
 * 
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * ⁠[0,0,0,0,0,0,0,1,1,1,0,0,0],
 * ⁠[0,1,1,0,1,0,0,0,0,0,0,0,0],
 * ⁠[0,1,0,0,1,1,0,0,1,0,1,0,0],
 * ⁠[0,1,0,0,1,1,0,0,1,1,1,0,0],
 * ⁠[0,0,0,0,0,0,0,0,0,0,1,0,0],
 * ⁠[0,0,0,0,0,0,0,1,1,1,0,0,0],
 * ⁠[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 
 * Given the above grid, return 6. Note the answer is not 11, because the
 * island must be connected 4-directionally.
 * 
 * Example 2:
 * 
 * 
 * [[0,0,0,0,0,0,0,0]]
 * Given the above grid, return 0.
 * 
 * Note: The length of each dimension in the given grid does not exceed 50.
 * 
 * 解题思路
 * 深度优先遍历的问题
 * 1、递归解法
 * 对于grid中每一个点，观察其上下左右，进行递归计算面积，将每个小块面积加起来就是整个
 * 连接起来的块的面积
 * 2、用数据结构记录
 * 对于非0点，设置一个队列，将其上下左右中非零点放入队列，然后从队列中取元素访问，重复
 * 这个过程，直到队列为空
 * 
 * 注意：要把走过的地方加上记号，不要走很多遍
 * 
 * 解题过程：刚开始就想的是递归的方法，觉得好复杂，搞了半天才想起来可以用数据结构
 * 记录一些数据。
 */
class Solution
{
public:
    int maxAreaOfIsland2(vector<vector<int>> &grid)
    {
        int row = grid.size(), col = grid[0].size();
        int maxcount=0;
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (grid[i][j] != 0)
                {
                    int count=1;
                    queue<pair<int, int>> q;
                    q.push(make_pair(i,j));
                    int indexi=i,indexj=j;
                    grid[i][j]=0;//这里一开始忘记置为0导致出错了
                    do{
                        if(!q.empty()){
                            pair<int,int>temp=q.front();
                            indexi=temp.first;
                            indexj=temp.second;
                            q.pop();
                        }
                        
                        if(indexi-1>=0&&grid[indexi-1][indexj]==1){
                            q.push(make_pair(indexi-1,indexj));
                            count++;
                            grid[indexi-1][indexj]=0;
                        }
                        if(indexi+1<row&&grid[indexi+1][indexj]==1){
                            q.push(make_pair(indexi+1,indexj));
                            count++;
                            grid[indexi+1][indexj]=0;
                        }
                        if(indexj-1>=0&&grid[indexi][indexj-1]==1){
                            q.push(make_pair(indexi,indexj-1));
                            count++;
                            grid[indexi][indexj-1]=0;
                        }
                        if(indexj+1<col&&grid[indexi][indexj+1]==1){
                            q.push(make_pair(indexi,indexj+1));
                            count++;
                            grid[indexi][indexj+1]=0;
                        }
                    }while(!q.empty());
                    if(count>maxcount){
                        maxcount=count;
                    }
                }
            }
        }
        return maxcount;
    }
    int area(int r,int c,int row,int col,vector<vector<int>> &grid){
        if(r<0||c<0||r>=row||c>=col||grid[r][c]==0){
            return 0;
        }
        grid[r][c]=0;
        return 1+area(r-1,c,row,col,grid)+area(r+1,c,row,col,grid)+
        area(r,c-1,row,col,grid)+area(r,c+1,row,col,grid);
    }
     int maxAreaOfIsland(vector<vector<int>> &grid)
    {
        int row = grid.size(), col = grid[0].size();
        int maxcount=0;
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                maxcount=max(maxcount,area(i,j,row,col,grid));
            }
        }
        return maxcount;
    }
};
