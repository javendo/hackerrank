class Solution:
    def longestPalindrome(self, s: str) -> str:
        # Helper function to expand around a center and return the length
        # of the palindrome, as well as its left and right boundaries.
        def expandAroundCenter(left: int, right: int) -> (int, int, int):
            L, R = left, right
            # While within bounds and characters match, expand outwards
            while L >= 0 and R < len(s) and s[L] == s[R]:
                L -= 1
                R += 1
            # Return (start_index, end_index, length) of the palindrome found
            return L + 1, R - 1, R - L - 1

        if not s or len(s) < 1:
            return ""

        start = 0
        end = 0

        for i in range(len(s)):
            # Case 1: Palindrome with odd length (center is a single character)
            left1, right1, len1 = expandAroundCenter(i, i)
            # Case 2: Palindrome with even length (center is between two characters)
            left2, right2, len2 = expandAroundCenter(i, i + 1)

            # Determine the longest palindrome found from this center 'i'
            if len1 > len2:
                current_len = len1
                current_start = left1
                current_end = right1
            else:
                current_len = len2
                current_start = left2
                current_end = right2

            # Update the overall longest palindrome found so far
            if current_len > (end - start + 1):
                start = current_start
                end = current_end

        # Extract and return the final longest palindromic substring
        return s[start : end + 1]

# Example Usage:
# sol = Solution()
# print(sol.longestPalindrome("babad")) # Output: "bab" or "aba"
# print(sol.longestPalindrome("cbbd"))  # Output: "bb"
# print(sol.longestPalindrome("a"))     # Output: "a"
# print(sol.longestPalindrome("ac"))    # Output: "a"
