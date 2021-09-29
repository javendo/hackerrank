#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'rotateLeft' function below.
#
# The function is expected to return an INTEGER_ARRAY.
# The function accepts following parameters:
#  1. INTEGER d
#  2. INTEGER_ARRAY arr
#
def rotateLeft(d, arr):
    return LinkedList(arr).rotateLeft(d).asList()

# Node of a doubly linked list
class LinkedList:
    def __init__(self, arr):
        if (arr == None or len(arr) == 0):
            self.head = Node()
            self.size = 0
        else:
            head, *tail = arr
            self.head = Node(data=head)
            previous = self.head
            for e in tail:
                previous.next = Node(prev = previous, data = e)
                previous = previous.next
            previous.next = self.head
            self.head.previous = previous
            self.size = len(arr)

    def rotateLeft(self, i):
        pointcut = self.head
        k = 0 if self.size == 0 else i % self.size
        for j in range(k):
            pointcut = pointcut.next
        self.head = pointcut
        return self

    def asList(self):
        l = []
        actual = self.head
        for i in range(self.size):
            l.append(actual.data)
            actual = actual.next
        return l

class Node:
	def __init__(self, next=None, prev=None, data=None):
		self.next = next # reference to next node in DLL
		self.prev = prev # reference to previous node in DLL
		self.data = data

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')
    first_multiple_input = input().rstrip().split()
    n = int(first_multiple_input[0])
    d = int(first_multiple_input[1])
    arr = map(int, input().rstrip().split())
    result = rotateLeft(d, list(arr))
    fptr.write(' '.join(map(str, result)))
    fptr.write('\n')
    fptr.close()
