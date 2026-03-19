def binary_search(arr, target):
    left = 0
    right = len(arr) - 1

    while left <= right:
        # Calculate the middle index
        # Pro-tip: (left + right) // 2 can cause overflow in some languages.
        # Use left + (right - left) // 2 to be safe/senior-level.
        mid = left + (right - left) // 2
        
        # 1. Check if the middle element is our target
        if arr[mid] == target:
            return mid # Found it! Return the index
        
        # 2. If target is greater, ignore the left half
        elif arr[mid] < target:
            left = mid + 1
            
        # 3. If target is smaller, ignore the right half
        else:
            right = mid - 1

    # If we exit the loop, the target isn't in the array
    return -1

data = [1, 3, 4, 6, 7, 9, 10, 14, 28]
target = 9
index = binary_search(data, target)

if index != -1:
    print(f"Target {target} found at index: {index}")
else:
    print("Target not found.")
