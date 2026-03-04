class Solution:
    def maxArea(self, height: List[int]) -> int:
        left = 0
        right = len(height) - 1
        max_water = 0

        while left != right:
            max_water = max(max_water, self.area(height, left, right))
            if height[right] > height[left]:
                left += 1
            else:
                right -= 1

        return max_water

    def area(self, height, left, right):
        y = min(height[left], height[right])
        x = right - left 
        print(f"{x}, {y}, {x * y}")
        return x * y
       

s = Solution()
print(s.maxArea([1,8,6,2,5,4,8,3,7]) == 49)
print(s.maxArea([8,7,2,1]) == 7)
