/*
��Ŀ������
914. X of a Kind in a Deck of Cards
Easy

207

43

Favorite

Share
In a deck of cards, each card has an integer written on it.

Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards, where:

Each group has exactly X cards.
All the cards in each group have the same integer.


Example 1:

Input: [1,2,3,4,4,3,2,1]
Output: true
Explanation: Possible partition [1,1],[2,2],[3,3],[4,4]
Example 2:

Input: [1,1,1,2,2,2,3,3]
Output: false
Explanation: No possible partition.
Example 3:

Input: [1]
Output: false
Explanation: No possible partition.
Example 4:

Input: [1,1]
Output: true
Explanation: Possible partition [1,1]
Example 5:

Input: [1,1,2,2,2,2]
Output: true
Explanation: Possible partition [1,1],[2,2],[2,2]

Note:

1 <= deck.length <= 10000
0 <= deck[i] < 10000
����˼·��
1������ûע�⵽������ÿ���Ƶ�ֵ��1-10000֮�䣬��ȫ������hash�������˽�Ϊ�鷳��Map
2���������ⲻ���룬�տ�ʼ�е�����������ҵķ������ҵ���С����Ŀmin��֮���Min��ÿ�����Ӳ鿴������group����Ŀ�ܷ����������ӶȽϸ�
3���𰸵�simple��������û��ȥ����ν��min��������deck.size()�����������д�С����ȥ�ң�Ч��һ��
4��Improved������
������counter�����Լ������
*/
#include<vector>
#include<algorithm>
#include<map>
#include<iterator>
using namespace std;
bool hasGroupsSizeX(vector<int>& deck) {
	map<int, int>mp;
	int min = deck.size();
	if (deck.size() == 1)return false;
	for (int i = 0; i < deck.size(); i++) {
		if (mp.count(deck[i]) == 0) {
			mp.insert(make_pair(deck[i], 1));
		
		}
		else {
			mp[deck[i]]++;
			
		}
		
	}
	
	for (map<int, int>::iterator it = mp.begin(); it != mp.end(); it++) {
		if (it->second < min) {
			min = it->second;
		}

	}
	if (min == deck.size() && mp.size() == deck.size())return false;
	vector<int>inc;
	gcd(inc,min);
	for (int i = 0; i < inc.size(); i++) {
		int flag = 1;
		for (map<int, int>::iterator it = mp.begin(); it != mp.end(); it++) {
			if (it->second%inc[i] != 0) {
				flag = 0;
				break;
			}
		}
		if (flag == 1)return true;
	}
	
	
	return false;
}
void gcd(vector<int>&inc,int d) {
	for (int i = 2; i < d; i++) {
		if (d!=1&&d%i == 0) {
			inc.push_back(i);
			d = d / i;

		}
	}
}
bool hasGroupsSizeX2(vector<int>& deck) {
	int counter[10000] = { 0 };
	for (int i = 0; i < deck.size(); i++) {
		counter[deck[i]]++;
	}
	int g = -1;
	for (int i = 0; i < 10000; i++) {
		if (counter[i] != 0) {
			if (g == -1) {
				g = counter[i];
			}
			else {
				g = gcdTrue(g,counter[i]);
			}
		}
		
	}
	return g >= 2;
}
int gcdTrue(int x, int y) {
	return (x == 0) ? y : gcdTrue(y%x,x);
}