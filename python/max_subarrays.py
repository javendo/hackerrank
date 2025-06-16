class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        total = 0
        res = nums[0]
        for v in nums:
            if total < 0:
                total = 0
            total += v
            res = max(res, total)
        return res