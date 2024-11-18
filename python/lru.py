from collections import deque

class Lru:
    def __lru(self, key):
        removed = None
        if key in self.elements:
            self.elements.remove(key)
        elif len(self.elements) >= self.size:
            removed = self.elements.pop()
        self.elements.appendleft(key)
        return removed

    def put(self, key, value = None):
        removed = self.__lru(key)
        if removed is not None: self.cache.pop(removed)
        self.cache[key] = value or key

    def get(self, key):
        if key in self.elements:
            self.elements.remove(key)
            self.elements.appendleft(key)
            return self.cache[key]
        else:
            return None

    def print_cache(self):
        print(sorted(self.cache.keys()))

    def __init__(self, size):
        self.elements = deque([None] * size) #it should be a liked list
        self.cache = {}
        self.size = size


lru = Lru(4)
lru.put("A")
lru.put("B")
lru.put("C")
lru.put("D")
lru.put("E")
lru.put("D")
lru.put("F")
lru.print_cache()

lru1 = Lru(4)
lru1.put(1)
lru1.put(2)
lru1.get(3)
lru1.get(1)
lru1.put(4)
lru1.put(5)
lru1.put(6)
lru1.print_cache()

lru2 = Lru(3)
lru2.put(1)
lru2.put(2)
lru2.put(3)
lru2.put(4)
lru2.put(1)
lru2.put(2)
lru2.put(5)
lru2.put(1)
lru2.put(2)
lru2.put(3)
lru2.print_cache()