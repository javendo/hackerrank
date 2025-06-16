class Mru:
    def put(self, key, value = None):
        if len(self.cache) >= self.size and key not in self.cache.keys(): self.cache.pop(self.__mru)
        self.cache[key] = value
        self.__mru = key

    def get(self, key):
        self.__mru = key
        return cache[key]

    def print_cache(self):
        print(sorted(self.cache.keys()))

    def __init__(self, size):
        self.elements = [None] * 4
        self.cache = {}
        self.size = size
        self.__mru = None


mru = Mru(4)
mru.put("A")
mru.put("B")
mru.put("C")
mru.put("D")
mru.put("E")
mru.put("C")
mru.put("D")
mru.put("B")
mru.print_cache()
