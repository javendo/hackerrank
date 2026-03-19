class TrieNode:
    def __init__(self):
        # Stores characters pointing to the next TrieNode
        self.children = {}
        # Marks the end of a valid word
        self.is_end_of_word = False

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        node = self.root
        for char in word:
            if char not in node.children:
                node.children[char] = TrieNode()
            node = node.children[char]
        node.is_end_of_word = True

    def search(self, word):
        node = self.root
        for char in word:
            if char not in node.children:
                return False
            node = node.children[char]
        return node.is_end_of_word

    def starts_with(self, prefix):
        node = self.root
        for char in prefix:
            if char not in node.children:
                return False
            node = node.children[char]
        return True # Found the prefix

trie = Trie()

# 1. Insert words into the dictionary
words = ["apple", "app", "apricot", "banana"]
for w in words:
    trie.insert(w)

# 2. Test full word search
print(f"Search 'apple': {trie.search('apple')}")   # True
print(f"Search 'app': {trie.search('app')}")       # True
print(f"Search 'appl': {trie.search('appl')}")     # False (prefix only)

# 3. Test prefix search (The Trie's superpower)
print(f"Starts with 'ap': {trie.starts_with('ap')}") # True
print(f"Starts with 'bat': {trie.starts_with('bat')}") # False
