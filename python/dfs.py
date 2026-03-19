def dfs(graph, node, visited=set()):
    visited.add(node)
    print(node) # Process the node

    for neighbor in graph[node]:
        if neighbor not in visited:
            dfs(graph, neighbor, visited)

diamond_graph = {
    'A': ['B', 'C'],
    'B': ['D'],
    'C': ['D'],
    'D': ['E'],
    'E': []
}

dfs(diamond_graph, 'A')
