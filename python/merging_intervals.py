def solution(p_intervals):
    sorted_intervals = sort_intervals(p_intervals)
    temp_interval = None
    list_solution = []
    for interval in sorted_intervals:
        if temp_interval is not None:
            if overlap(temp_interval, interval):
                temp_interval = merge_intevals(temp_interval, interval)
            else:
                list_solution.append(temp_interval)
                temp_interval = interval
        else:
            temp_interval = interval

    list_solution.append(temp_interval)
            
    return list_solution


def overlap(interval_1, interval_2):
    return interval_1[0] <= interval_2[0] <= interval_1[1]

def merge_intevals(interval_1, interval_2):
    return [interval_1[0], interval_2[1]]


def sort_intervals(p_interval):
    return sorted(p_interval)


intervals = [[2, 6], [8, 10], [1, 3], [15, 18]]
result = [[1, 6], [8, 10], [15, 18]]

print(solution(intervals))
print(solution(intervals) == result)

intervals = [[1, 4], [4, 5]]
result = [[1, 5]]

print(solution(intervals))
print(solution(intervals) == result)
