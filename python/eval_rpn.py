from collections import deque

class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        operation = {'+': lambda x, y : x + y, '-': lambda x, y : x - y, '*': lambda x, y : x * y, '/': lambda x, y : x / y}
        stack = deque()
        for token in tokens:
            if Solution.is_operator(token):
                y = int(stack.pop())
                x = int(stack.pop())
                token = int(operation[token](x, y))
            stack.append(token)
        return int(stack.pop())

    @staticmethod
    def is_operator(token: str) -> bool:
        return token == '+' or token == '-' or token == '*' or token == '/'
