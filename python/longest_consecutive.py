from typing import List
from warnings import warn

class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        consecutives = dict[int, (int, int)]()
        longest = (0, 1)
        for n in nums:
            min_value = (consecutives.get(n - 1) and consecutives[n - 1][0]) or n
            max_value = (consecutives.get(n + 1) and consecutives[n + 1][1]) or n
            consecutives[n] = (min_value, max_value)
            consecutives[min_value] = (consecutives[min_value][0], max_value)
            consecutives[max_value] = (min_value, consecutives[max_value][1])
            if ((consecutives[n][1] - consecutives[n][0]) > (longest[1] - longest[0])):
                longest = consecutives[n]
            print(consecutives)
        return list(range(longest[0], longest[1] + 1))
 
solution = Solution()
print(solution.longestConsecutive([100, 4, 101, 1, 3, 2, 8, 98, 102, 99, 7, 5, 6, 1, 2]))
