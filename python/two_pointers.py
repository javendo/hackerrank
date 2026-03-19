def two_pointers(arr, target):
    # 1. Initialize pointers at both ends
    left = 0
    right = len(arr) - 1

    while left < right:
        current_sum = arr[left] + arr[right]
        
        # 2. Check if we found the target
        if current_sum == target:
            return [left, right] # Success! Return indices
        
        # 3. If sum is too small, move left pointer to increase it
        elif current_sum < target:
            left += 1
            
        # 4. If sum is too large, move right pointer to decrease it
        else:
            right -= 1

    # If the loop finishes, no pair was found
    return [-1, -1]

# Sorted list of numbers
data = [1, 3, 4, 6, 8, 9, 10]

# Target sum we are looking for
target_sum = 13

# Running the test
result = two_pointers(data, target_sum)

if result != [-1, -1]:
    print(f"Indices found: {result} (Values: {data[result[0]]} + {data[result[1]]} = {target_sum})")
else:
    print("No pair found with that sum.")
