class Solution:
    def insert(self, intervals: List[List[int]], newInterval: List[int]) -> List[List[int]]:
        merged_interval = newInterval
        res = []
        for i in intervals:
            comparison = self.compare(i, merged_interval)
            print(comparison)
            if comparison == 0:
                merged_interval = [min(i[0], merged_interval[0]), max(i[1], merged_interval[1])]
            elif comparison == -1:
                res.append(i)
            else:
                res.append(merged_interval)
                merged_interval = i
        if len(res) == 0 or res[-1] != merged_interval:
            res.append(merged_interval)
        return res


    def compare(self, interval1: List[int], interval2: List[int]) -> int:
        if interval2[1] < interval1[0]:
            return 1
        elif interval1[1] < interval2[0]:
            return -1   
        else:
            return 0