#include<vector>
#include<iostream>
#include<string>
/*
题目：从1数到100，如果遇见了3的倍数要说Fizz，5的倍数就说Buzz，如果即是3的倍数又是5的倍数就说FizzBuzz
注意点：
1、int to string :include<string>中to_string()函数
   string to int: include<stdilib.h>string s=n.atoi()
2、vector初始化：vector<string> s(n)初始化大小为n的string数组
效率问题：
1、首先对于每个数可以只调用一次isFizz和isBuzz
2、由于3的倍数输出Fizz,5的输出Buzz。3且5的输出FizzBuzz。可以选判断是否是3的倍数，是就输出Fizz；
再判断是否是5的倍数，是就接着输出Buzz，对于判断过后没有过任何输出的，就直接输出数字，这就是
fizzBuzz2的解法
*/
using namespace std;
bool isFizz(int n) {
	if (n % 3 == 0)
		return true;
	return false;
}
bool isBuzz(int n) {
	if (n % 5 == 0) {
		return true;
	}
	return false;
}
vector<string> fizzBuzz(int n) {
	vector<string> s(n);
	for (int i = 1; i <= n; i++) {
		if (isFizz(i) && isBuzz(i)) {
			s[i-1]="FizzBuzz";
		}
		else if (!isFizz(i) && !isBuzz(i)) {
			s[i - 1] =  to_string(i);
		}
		else if (isFizz(i)) {
			s[i - 1] = "Fizz";
		}
		else {
			s[i - 1] = "Buzz";
		}
	}
	return s;
}
vector<string> fizzBuzz2(int n) {
	vector<string> res(n);
	for (int i = 1; i <= n; i++) {
		if (i % 3 == 0) res[i - 1] += "Fizz";
		if (i % 5 == 0) res[i - 1] += "Buzz";
		if (!res[i - 1].size()) res[i - 1] += to_string(i);
	}
	return res;
}
int main() {
	int n = 15;
	vector<string> s = fizzBuzz(n);
	
	for (int i = 0; i < n; i++) {
		printf("%s", s[i]);
	}
	return 0;
}