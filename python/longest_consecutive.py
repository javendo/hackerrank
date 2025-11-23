from typing import List
from warnings import warn

class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        consecutives = dict[int, (int, int)]()
        longest = (0, 1)
        for n in nums:
            if n not in consecutives.keys():
                min_value = consecutives[n - 1][0] if consecutives.get(n - 1) else n
                max_value = consecutives[n + 1][1] if consecutives.get(n + 1) else n
                consecutives[n] = (min_value, max_value)
                consecutives[min_value] = (consecutives[min_value][0], max_value)
                consecutives[max_value] = (min_value, consecutives[max_value][1])
                if ((consecutives[n][1] - consecutives[n][0]) > (longest[1] - longest[0])):
                    longest = consecutives[n]
                print(consecutives)
        return list(range(longest[0], longest[1] + 1))
 
solution = Solution()
#print(solution.longestConsecutive([100, 4, 101, 1, 3, 2, 8, 98, 102, 99, 7, 5, 6, 1, 2]))
print(solution.longestConsecutive([-1,9,-3,-6,7,-8,-6,2,9,2,3,-2,4,-1,0,6,1,-9,6,8,6,5,2]))
