# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        possible_head = ListNode(None, head)
        next_head = possible_head
        for i in range(n):
            if next_head == None:
                return None
            next_head = next_head.next

        while next_head.next:
            next_head = next_head.next
            possible_head = possible_head.next

        if possible_head.next == head:
            return possible_head.next.next
        else:
            possible_head.next = possible_head.next.next
            return head

def toList(head: Optional[ListNode]):
    l = [] if not head else [head.val]
    next_val = head
    if next_val:
        while next_val.next:        
            next_val = next_val.next
            l.append(next_val.val)
    return l

def fromList(l: list) -> Optional[ListNode]:
    if list == []:
        return None
    else:
        head = r = ListNode(l[0], None)
        for i in l[1:]:
            next_r = ListNode(i, None)
            r.next = next_r
            r = next_r
        return head

s = Solution()

print(toList(s.removeNthFromEnd(fromList([1, 2]), 2)) == [2])
print(toList(s.removeNthFromEnd(fromList([1]), 1)) == [])
print(toList(s.removeNthFromEnd(fromList([1, 2, 3, 4, 5]), 2)) == [1,2,3,5])
print(toList(s.removeNthFromEnd(fromList([1, 2]), 1)) == [1])



