class Solution:
    result = []

    def threeSum(self, nums: List[int]) -> List[List[int]]:
        self.result = set()

        if len(nums) < 3:
            return []
        else:
            nums.sort()
            for i in range(len(nums) - 2):
                self.find_pair_with_sum(nums, i, i + 1, len(nums) - 1)

        return list(map(lambda i: list(i), list(self.result)))

    def find_pair_with_sum(self, nums, i, j, k):
        while j < k:
            v_i, v_j, v_k = nums[i], nums[j], nums[k]
            if v_j + v_k == -v_i:
                self.result.add((v_i, v_j, v_k))
                j += 1
            elif v_j + v_k > -v_i:
                k -= 1
            else:
                j += 1


    def binary_search(self, nums, i, j, k, lim_min, lim_max):
        if nums[i] + nums[j] + nums[k] > 0 and j - lim_min > 1:
            return self.binary_search(nums, i, j - int((j - i) / 2), k, lim_min, j)
        elif nums[i] + nums[j] + nums[k] < 0 and lim_max - j > 1:
            return self.binary_search(nums, i, j + int((k - j) / 2), k, j, lim_max)
        else:
            return (i, j, k)
