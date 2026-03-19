def sliding_window(arr, target):
    left = 0
    current_sum = 0
    min_length = float('inf')

    for right in range(len(arr)):
        # 1. Add the next element to the window
        current_sum += arr[right]

        # 2. Shrink the window while the condition is met
        while current_sum >= target:
            # Update your result (e.g., shortest length)
            min_length = min(min_length, right - left + 1)
            
            # Remove the leftmost element and move the pointer
            current_sum -= arr[left]
            left += 1

    return min_length if min_length != float('inf') else 0

result = sliding_window([3, 2, 5, 6, 8, 1], 8)
print(f"The smallest sub-array length is: {result}")
