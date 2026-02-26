class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        return max(self.maxProductFunc(nums), self.maxProductFunc(reversed(nums)))
 
    def maxProductFunc(self, nums: List[int]) -> int:
        max = float('-inf')
        temp_max = None

        for i in nums:
            temp_max = (1 if not temp_max else temp_max) * i

            if temp_max == 0:
                temp_max = None

            if temp_max and temp_max > max:
                max = temp_max

            if not temp_max and 0 > max:
                max = 0

        return max

s = Solution()
print(s.maxProduct([2, 3, -2, 4]) == 6)
print(s.maxProduct([-2, 0, -1]) == 0)
print(s.maxProduct([0, 2]) == 2)
print(s.maxProduct([2,-5,-2,-4,3]) == 24)
