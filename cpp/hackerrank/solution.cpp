#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <iostream>
#include <fstream>
#include <unordered_map>
#include <bits/stdc++.h>
using namespace std;


void group_values(vector<int> &values, unordered_map<int, vector<vector<int>>> &m, int node, int idx) {
  int key = values[node - 1];
  if (m.find(key) == m.end()) {
    vector<vector<int>> container;
    container.reserve(2);
    container.push_back(vector<int>());
    container.push_back(vector<int>());
    m[key]= container;
  }
  m[key][idx].push_back(node);
}

void path(vector<int> &values, unordered_map<int, vector<vector<int>>> &m, vector<int> &tree, int n1, int n2, int idx) {
  if (n1 == n2) {
    group_values(values, m, n1, idx);
  }
  else if (n1 > n2) {
    group_values(values, m, n1, idx);
    path(values, m, tree, tree[n1], n2, idx);
  }
  else {
    group_values(values, m, n2, idx);
    path(values, m, tree, n1, tree[n2], idx);
  }
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  ifstream in("input_1");
  cin.rdbuf(in.rdbuf());
  //ofstream out("out.txt");
  //cout.rdbuf(out.rdbuf());
  int n, q;
  cin >> n >> q;
  vector<int> values;
  values.reserve(n);
  for (int i = 0; i < n; i++) {
    int input;
    cin >> input;
    values.push_back(input);
  }
  vector<int> tree;
  tree.reserve(n + 1);
  for (int i = 1; i < n; i++) {
    int a, b;
    cin >> a >> b;
    tree[max(a, b)] = min(a, b);
  }
  vector<int> r;
  for (int i = 0; i < q; i++) {
    int n1, n2, n3, n4;
    cin >> n1 >> n2 >> n3 >> n4;
    vector<int> p1, p2;
    unordered_map<int, vector<vector<int>>> m;
    path(values, m, tree, n1, n2, 0);
    path(values, m, tree, n3, n4, 1);
    int count = 0;
    for (auto const &e : m) {
      for (auto const &same1 : e.second[0]) {
	for (auto const &same2 : e.second[1]) {
	  if (same1 != same2) {
	    ++count;
	  }
	}
      }
    }
    cout << count << "\n";
  }
  return 0;
}
