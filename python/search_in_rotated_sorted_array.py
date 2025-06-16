'''
[1,2,3,4,5,6,7,8,9,10,11,12,13,0]
[13,0,1,2,3,4,5,6,7,8,9,10,11,12]
[8,9,10,11,12,13,0,1,2,3,4,5,6,7]

[4, 5, 6] 7 [0, 1, 2]
[4] 5 [6]
[0] 1 [2]
0

'''

class Solution:
    def search(self, nums: List[int], target: int) -> int:
        left, right = 0, len(nums) - 1

        while left <= right:
            mid = (left + right) // 2
            
            if nums[mid] == target:
                return mid  # Found target
            
            # Check which half is sorted
            if nums[left] <= nums[mid]:  # Left half is sorted
                if nums[left] <= target < nums[mid]:  # Target in left half
                    right = mid - 1
                else:
                    left = mid + 1  # Search in right half
            else:  # Right half is sorted
                if nums[mid] < target <= nums[right]:  # Target in right half
                    left = mid + 1
                else:
                    right = mid - 1  # Search in left half
        
        return -1  # Target not found