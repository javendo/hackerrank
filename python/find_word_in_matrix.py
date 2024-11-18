#Given a two dimensional array of letters, find a given word written in any of the 8 directions.

#I.e.
#EXAMPLE

#Input: UBER
#Input:

# A  U  I  K  F  W  N
# W  Q  B  O  L  X  P
# T  L  A  E  R  E  S
# Y  Z  X  E  R  L  W

# A  U  R  R-  F  W  N
# W  B  E  O  E-  X  P
# T  E  B  E  R  B-  S
# Y  R  U  E  R  L  U-

#Output: true
#- - -

#Input: UBER
#Input

# U B
# E R

#Output: false
from collections import deque

def find_word(matrix, word):
    if matrix is None or len(matrix) == 0:
        return False
    
    letter_of_word = deque(list(word)) # U B E R
    found = False
    for row in range(len(matrix)):
        for col in range(len(matrix[0])):
            for step_row in [-1, 0, 1]: 
                for step_col in [-1, 0, 1]:
                    found = found or traverse(matrix, row, col, letter_of_word.copy(), step_row, step_col)
    return found
                
def traverse(matrix, row, col, letter_of_word, step_row, step_col):
    if len(letter_of_word) == 0:
        return True
    if step_row == 0 or step_col == 0 or row < 0 or col < 0 or len(matrix) <= row or len(matrix[0]) <= col:
        return False
    if matrix[row][col] == letter_of_word[0]:
        letter_of_word.popleft()
        return traverse(matrix, row + step_row, col + step_col, letter_of_word, step_row, step_col)
    else:
        return traverse(matrix, row + step_row , col + step_col, letter_of_word, step_row, step_col)

first_sample = [["A", "U", "I", "K", "F", "W", "N"],
                ["W", "Q", "B", "O", "L", "X", "P"],
                ["T", "L", "A", "E", "R", "E", "S"],
                ["Y", "Z", "X", "E", "R", "L", "W"]]
second_sample = [["U", "B"],
                 ["E", "R"]]
third_sample = [["A", "R", "I", "K", "F", "W", "N"],
                ["W", "Q", "E", "O", "L", "X", "P"],
                ["T", "L", "A", "B", "R", "E", "S"],
                ["Y", "Z", "X", "E", "U", "L", "W"]]
fourth_sample = [["A", "K", "I", "R", "F", "W", "N"],
                 ["W", "Q", "E", "O", "L", "X", "P"],
                 ["T", "B", "A", "X", "R", "E", "S"],
                 ["U", "Z", "X", "E", "U", "L", "W"]]
print(find_word(first_sample, "UBER")) #True
print(find_word(second_sample, "UBER")) #False
print(find_word(third_sample, "UBER")) #True
print(find_word(fourth_sample, "UBER")) #True
print(find_word([], "UBER")) #False
print(find_word(None, "UBER")) #False

#O(n*m)