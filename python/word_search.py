class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        rows, cols = len(board), len(board[0])
        visited = [[0] * cols for _ in range(rows)]
        visit_id = 0

        def dfs(r, c, i, visit_id):
            if i == len(word):
                return True

            if (
                r < 0 or r >= rows or
                c < 0 or c >= cols or
                board[r][c] != word[i] or
                visited[r][c] == visit_id
            ):
                return False

            visited[r][c] = visit_id

            return (
                dfs(r, c + 1, i + 1, visit_id) or
                dfs(r, c - 1, i + 1, visit_id) or
                dfs(r + 1, c, i + 1, visit_id) or
                dfs(r - 1, c, i + 1, visit_id)
            )

        for r in range(rows):
            for c in range(cols):
                visit_id += 1  # new DFS = new "visited universe"
                if dfs(r, c, 0, visit_id):
                    return True

        return False

s = Solution()
print(s.exist([["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], "ABCCED") == True)
print(s.exist([["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], "SEE") == True)
print(s.exist([["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], "ABCB") == False)
print(s.exist([["A","B","C","E"],["S","F","E","S"],["A","D","E","E"]], "ABCESEEEFS") == True)
