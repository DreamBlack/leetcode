#include<vector>
#include<iostream>
#include<string>
/*
��Ŀ����1����100�����������3�ı���Ҫ˵Fizz��5�ı�����˵Buzz���������3�ı�������5�ı�����˵FizzBuzz
ע��㣺
1��int to string :include<string>��to_string()����
   string to int: include<stdilib.h>string s=n.atoi()
2��vector��ʼ����vector<string> s(n)��ʼ����СΪn��string����
Ч�����⣺
1�����ȶ���ÿ��������ֻ����һ��isFizz��isBuzz
2������3�ı������Fizz,5�����Buzz��3��5�����FizzBuzz������ѡ�ж��Ƿ���3�ı������Ǿ����Fizz��
���ж��Ƿ���5�ı������Ǿͽ������Buzz�������жϹ���û�й��κ�����ģ���ֱ��������֣������
fizzBuzz2�Ľⷨ
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