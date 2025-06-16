def dfs(i, j):
    if i < 0 or i > len(matriz) or j < 0 or j >= len(matriz[0]) or matriz[i][j] != 1:
        return
    matriz[i][j] = -1
    dfs(i+1, j)
    dfs(i-1, j)
    dfs(i, j+1)
    dfs(i, j+1)

num_ilhas = 0
for i in range(len(matriz)):
    for j in range(len(matriz[0]):
        if matriz[i][j] == 1:
            num_ilhas += 1
            dfs(i, j)

return num_ilhas