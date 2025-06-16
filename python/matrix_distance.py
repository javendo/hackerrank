from collections import deque

class Solution:
    def updateMatrix(self, mat: List[List[int]]) -> List[List[int]]:
        directions = [(-1, 0), (1, 0), (0, 1), (0, -1)]
        len_x, len_y = len(mat), len(mat[0])
        queue = deque()
        visited = [[0 if mat[x][y] == self.to_be_visited(queue, x, y) else float('inf') for y in range(len_y)] for x in range(len_x)]
        while queue:
            x, y = queue.popleft()
            for dir_x, dir_y in directions:
                new_point_x, new_point_y = x + dir_x, y + dir_y
                if 0 <= new_point_x < len_x and 0 <= new_point_y < len_y and visited[new_point_x][new_point_y] > visited[x][y] + 1:
                    visited[new_point_x][new_point_y] = visited[x][y] + 1
                    queue.append((new_point_x, new_point_y))
        return visited

    def to_be_visited(self, queue, x, y):
        queue.append((x, y))
        return 0
