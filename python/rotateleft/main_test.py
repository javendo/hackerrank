import unittest
from main import rotateLeft

class TestRotateMethod(unittest.TestCase):
    def test_rotate(self):
        self.assertEqual(rotateLeft(1, [1, 2, 3]), [2, 3, 1])
        self.assertEqual(rotateLeft(2, [1, 2, 3]), [3, 1, 2])
        self.assertEqual(rotateLeft(4, [1, 2, 3, 4, 5]), [5, 1, 2, 3, 4])

    def test_rotate_empty_list(self):
        self.assertEqual(rotateLeft(1, []), [])
        self.assertEqual(rotateLeft(2, []), [])

    def test_not_rotate(self):
        self.assertEqual(rotateLeft(0, [1, 2, 3]), [1, 2, 3])
        self.assertEqual(rotateLeft(0, []), [])

    def test_rotate_huge(self):
        self.assertEqual(rotateLeft(100000000, [1, 2, 3]), [2, 3, 1])
        self.assertEqual(rotateLeft(100000000, []), [])

if __name__ == '__main__':
    unittest.main()