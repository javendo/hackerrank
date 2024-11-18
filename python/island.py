import itertools
island_map = [[1, 1, 1, 1, 1, 1, 1, 0],
              [1, 0, 1, 0, 0, 1, 1, 0],
              [1, 1, 1, 0, 1, 1, 1, 0],
              [1, 0, 0, 0, 0, 1, 0, 1],
              [1, 1, 1, 1, 1, 1, 1, 0]]

rows = len(island_map)
cols = 0
if rows > 2: cols = len(island_map[0])

def traverse_island():
    if (rows > 2 and cols > 2):
        return sum(filter(lambda x: x == 1, [dfs(row, col) for row, col in itertools.product(range(1, rows-1), range(1, cols-1))]))
    else:
        return 0

def dfs(row, col):
    if island_map[row][col] == 1 or island_map[row][col] == -1: return 2
    if row < 0 or col < 0 or row >= rows or col >= cols: return 0
    island_map[row][col] = -1
    return int(dfs(row-1, col) and dfs(row+1, col) and dfs(row, col-1) and dfs(row, col+1) and 1)

print(traverse_island())
