from collections import deque

def bfs(graph, start_node):
    visited = {start_node} # Mark start immediately
    queue = deque([start_node])

    while queue:
        node = queue.popleft()
        print(f"Processing: {node}")
        
        for neighbor in graph.get(node, []):
            if neighbor not in visited:
                visited.add(neighbor) # Mark IMMEDIATELY
                queue.append(neighbor)

diamond_graph = {
    'A': ['B', 'C'],
    'B': ['D'],
    'C': ['D'],
    'D': ['E'],
    'E': []
}

bfs(diamond_graph, 'A')
