/*
In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.

Return that maximum distance to closest person.

Example 1:

Input: [1,0,0,0,1,0,1]
Output: 2
Explanation:
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.
Example 2:

Input: [1,0,0,0]
Output: 3
Explanation:
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.
解题思路：
1、我的方法
记录相隔的两个1之间的距离d，取d/2最大的那个；
需要注意的是边界情况,如第一个数不为1，则要算上第一个1；如最后一个数为0，要再算上最后一个1在的位置n-i-1
Approach #1: Next Array [Accepted]
Intuition

Let left[i] be the distance from seat i to the closest person sitting to the left of i. Similarly, let right[i] be the distance to the closest person sitting to the right of i. This is motivated by the idea that the closest person in seat i sits a distance min(left[i], right[i]) away.

Algorithm

To construct left[i], notice it is either left[i-1] + 1 if the seat is empty, or 0 if it is full. right[i] is constructed in a similar way.

*/

#include<algorithm>
#include<vector>
using namespace std;
int maxDistToClosest(vector<int>& seats) {
	int last1 = -1, findlast = -1, maxdis = -1;
	for (int i = 0; i < seats.size(); i++) {
		if (findlast == -1 && seats[i] == 1) {
			findlast = 1;
			last1 = i;
			maxdis = i;//第一个数不为1
		}
		else {
			if (seats[i] == 1 && findlast == 1) {
				maxdis = max(maxdis,(i-last1)/2);
				last1 = i;
			}
		}
		if (i == seats.size() - 1 && seats[i] == 0) {
			maxdis = max(maxdis, i - last1);//最后一个数为0
		}
	}
	
	return maxdis;
}
int maxDistToClosest2(vector<int>& seats) {
	int n = seats.size();
	vector<int>left(n,n);//i左边最近的一个人到自己的距离
	vector<int>right(n,n);//i右边最近的一个人到自己的距离
	for (int i = 0; i < n; i++) {
		if (seats[i] == 1)left[i] = 0;
		if (i > 0 && seats[i] == 0)left[i] = left[i - 1] + 1;
	}
	for (int i = n - 1; i >= 0; i--) {
		if (seats[i] == 1)right[i] = 0;
		if (i < n-1 && seats[i] == 0)right[i] = right[i + 1] + 1;
	}
	int maxdis = -1;
	for (int i = 0; i < n; i++) {
		if(seats[i]==0)
			maxdis = max(maxdis,min(left[i],right[i]));//对空座位来说，最近的到i的人坐的位置，选空座位中离最近的1最大的就是了
	}
	return maxdis;
}