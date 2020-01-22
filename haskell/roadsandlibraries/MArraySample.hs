module MArraySample where

import Control.Monad.ST
import Data.Array
import Data.Array.ST

myMArray = newArray (1, 10) 0 :: ST s (STArray s Int Int)

buildPair = do arr <- newArray (1, 10) 0 :: ST s (STArray s Int Int)
               a <- readArray arr 1
               writeArray arr 1 64
               b <- readArray arr 1
               return (a, b)

main = print $ runST buildPair
