"""
Question
Given a string s, find the length of the longest substring without repeating characters.
Assume s contains only lower-case English letters.
Test Cases
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
"""


class Solution:
        def lengthOfLongestSubstring(self, s: str) -> int:
            left = right = 0
            repeated = {}
            max_length = 0
            for i, c in enumerate(s):
                if c in repeated and repeated[c] + 1 > left:
                    left = repeated[c] + 1
                repeated[c] = i
                right += 1
                max_length = max(max_length, right - left)
            return max_length
    
s = "abcabcbb"
result = 3
print(Solution().find_longest_substring(s) == result)

s = "bbbbb"
result = 1
print(Solution().find_longest_substring(s) == result)

s = "pwwkew"
result = 3
print(Solution().find_longest_substring(s) == result)