{-# LANGUAGE FlexibleInstances, UndecidableInstances, DuplicateRecordFields #-}

module Main where

import Data.Array
import Data.Set (Set, insert, member, empty)

input :: String
--input = "2\n3 3 2 1\n1 2\n3 1\n2 3\n6 6 2 5\n1 3\n3 4\n2 4\n1 2\n2 3\n5 6\n"
input = "1\n5 3 6 1\n1 2\n1 3\n1 4\n"
l :: [(Int, Int)]
l = [(1, 2), (1, 3), (1, 4)]
--arr = accumArray (+) 0 ((1,1),(10000,10000)) ([((i, j), 1) | (i, j) <- l] ++ [((i, j), 1) | (j, i) <- l])
--arr :: Array Int [Int]
arr = accumArray (flip (:)) [] (1,6) (l ++ [(i, j) | (j, i) <- l])

mkArr :: Int -> [(Int, Int)] -> Array Int [Int]
mkArr size edges = accumArray (flip (:)) [] (1,size) (edges ++ [(i, j) | (j, i) <- edges])

dfs :: Set Int -> Int -> Array Int [Int] -> [(Int, [Int])] -> [Int]
dfs s acc arr ((v, e:es):t)
  | member e s = dfs s acc arr ((e, es):t)
  | otherwise  = dfs (insert e s) (acc + 1) arr ((e, arr ! e ++ es):t)
dfs s acc arr ((v, []):h)
  | null (arr ! v) && acc == 0 = [1] ++ (dfs s 0 arr h)
  | otherwise                  = [acc] ++ (dfs s 0 arr h)
dfs _ acc _ _  = [acc]

solve :: [[Int]] -> [Int]
solve ([n,m,cl,cr]:t) =
  let arr = mkArr n (map (\ x -> (head x, head $ tail x)) $ take m t)
      sol = dfs empty 0 arr (assocs arr)
  in (sum . (if cl > cr
             then map (((+) cl) . ((*) cr) . (flip (-) 1))
             else map ((*) cl)) . (filter ((/=) 0))) sol : (solve $ drop m t)
solve []              = []

main :: IO ()
main = interact $ unlines . map show . solve . map (map read . words) . tail . lines
