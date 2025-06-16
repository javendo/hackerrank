class Node:
    def __init__(self, value, left, right):
        self.value = value
        self.left = left
        self.right = right
        self.max_sum = 0

class Solution:
    def __init__(self):
        self.max_sum = 0
        
    def run(self, tree):
        if tree.left is None and tree.right is None:
            return tree.value
        elif tree.left is None:
            return tree.value + self.run(tree.right)
        elif tree.right is None:
            return tree.value + self.run(tree.left)
        else:
            left = self.run(tree.left)
            right = self.run(tree.right)
            self.max_sum = max(self.max_sum, tree.value + left + right)
            return tree.value + max(left, right)

node100 = Node(100, None, None)
node50 = Node(50, None, None)
node2 = Node(2, node100, node50)
node14 = Node(14, None, None)
node40 = Node(40, None, None)
node15 = Node(15, node40, None)
node0 = Node(0, node14, node15)
tree = Node(5, node2, node0)

solution = Solution()
solution.run(tree)

print(solution.max_sum)