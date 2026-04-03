class Solution:
    def findMin(self, nums: List[int]) -> int:
        left = 0
        right = len(nums) - 1
        if right == 0:
            return nums[right]
        while left < right:
            mid = left + (right - left) // 2
            if nums[mid] == nums[left]:
                return nums[mid] if nums[mid] < nums[right] else nums[right]
            elif nums[mid] < nums[left]:
                right = mid
            elif nums[mid] > nums[right]:
                left = mid
            else:
                return nums[left]

s = Solution()
print(s.findMin([3,4,5,1,2]))
print(s.findMin([4,5,6,7,0,1,2]))
print(s.findMin([11,13,15,17]))
print(s.findMin([1]))
print(s.findMin([1,2]))
