# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        possible_ret = next_head = head
        nth_idx = 1
        while next_head.next:
            nth_idx += 1
            next_head = next_head.next
            if nth_idx > n + 1:
                possible_ret = possible_ret.next 

        if nth_idx > n and possible_ret and possible_ret.next:
            possible_ret.next = possible_ret.next.next
        elif nth_idx == n:
            return possible_ret.next
        else:
            return None

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



