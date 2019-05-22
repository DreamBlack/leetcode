/*
In a deck of cards, every card has a unique integer.  You can order the deck in any order you want.

Initially, all the cards start face down (unrevealed) in one deck.

Now, you do the following steps repeatedly, until all cards are revealed:

Take the top card of the deck, reveal it, and take it out of the deck.
If there are still cards in the deck, put the next top card of the deck at the bottom of the deck.
If there are still unrevealed cards, go back to step 1.  Otherwise, stop.
Return an ordering of the deck that would reveal the cards in increasing order.

The first entry in the answer is considered to be the top of the deck.



Example 1:

Input: [17,13,11,2,3,5,7]
Output: [2,13,3,11,5,17,7]
Explanation:
We get the deck in the order [17,13,11,2,3,5,7] (this order doesn't matter), and reorder it.
After reordering, the deck starts as [2,13,3,11,5,17,7], where 2 is the top of the deck.
We reveal 2, and move 13 to the bottom.  The deck is now [3,11,5,17,7,13].
We reveal 3, and move 11 to the bottom.  The deck is now [5,17,7,13,11].
We reveal 5, and move 17 to the bottom.  The deck is now [7,13,11,17].
We reveal 7, and move 13 to the bottom.  The deck is now [11,17,13].
We reveal 11, and move 17 to the bottom.  The deck is now [13,17].
We reveal 13, and move 17 to the bottom.  The deck is now [17].
We reveal 17.
Since all the cards revealed are in increasing order, the answer is correct.


Note:

1 <= A.length <= 1000
1 <= A[i] <= 10^6
A[i] != A[j] for all i != j

解题思路：
这题想了好久。。。一直在想怎么最快的解决，其实这只是一道模拟题啊
约瑟夫环
*/
#include<vector>
#include<map>
#include<set>
#include<iterator>
#include<queue>
using namespace std;
vector<int> deckRevealedIncreasing(vector<int>& deck) {
	/*
	用一个队列模拟翻牌的顺序，将按序翻出的牌按从小到大赋值即可
	*/
	sort(deck.begin(), deck.end());
	vector<int>res(deck.size(),-1);
	queue<int>q;//q存的是下标
	for (int i = 0; i < deck.size(); i++) {
		q.push(i);//队列中保持的是翻牌的顺序
	}
	for (int i = 0; i < deck.size(); i++) {
		//出掉queue的头，模拟出牌的顺序
		int d1 = q.front();
		q.pop();
		//这里是第i次出牌，应该出第i大的元素，正是deck[i]。而这个元素应该放在queue中的第d1个里面
		res[d1] = deck[i];

		//把d1后面的元素pop掉，并放到末尾，模拟出牌的顺序
		int d2 = q.front();
		q.pop();
		q.push(d2);

	}
	return res;
}