import heapq

def find_k_largest(nums, k):
    # 1. Initialize a min-heap with the first K elements
    # We use a Min-Heap so the SMALLEST of the "Top K" is at the top.
    min_heap = nums[:k]
    heapq.heapify(min_heap)
    
    # 2. Iterate through the rest of the numbers
    for i in range(k, len(nums)):
        # If the current number is larger than the smallest in our heap
        if nums[i] > min(min_heap):
            # Remove the smallest and push the new larger number
            heapq.heapreplace(min_heap, nums[i])
            
    # 3. The heap now contains the K largest elements
    return min_heap

# Unsorted list of numbers
data = [4, 3, 6, 17, 66, 23, 8, 10, 11]
k = 3

# Running the test
top_k = find_k_largest(data, k)

print(f"The {k} largest elements are: {top_k}")
# Output should be: (order in heap may vary, but values will be correct)
