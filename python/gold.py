import itertools

gold = [[0, 6, 0],
        [5, 8, 7],
        [0, 9, 0],]

rows = len(gold)
cols = 0
if rows > 0: cols = len(gold[0])

def traverse_gold():
    if (rows == 0 or cols == 0):
        return 0
    return max([dfs(row, col, [[0 for i in range(cols)] for j in range(rows)]) for row, col in itertools.product(range(rows), range(cols))])
        

def dfs(row, col, visited):
    if (row < 0 or col < 0 or row >= rows or col >= cols or gold[row][col] == 0 or visited[row][col]): return 0
    visited[row][col] = 1
    return gold[row][col] + max([dfs(row+1, col, visited), dfs(row-1, col, visited), dfs(row, col+1, visited), dfs(row, col-1, visited)])

print(traverse_gold())
