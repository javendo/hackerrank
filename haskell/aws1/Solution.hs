import Data.Monoid
import Data.Ord
import Data.List
import Data.Char
import Data.Set (Set)
import qualified Data.Set as Set

:{
:}

:m + Data.Function
:set +m


let review = [
    "I would like a better display under the sun.",
    "Waterproof, please waterproof. Waterproof is the must.",
    "The battery is to short and the display is to small.",
    "I cannot see well under the sun and I would like to swin with my kindle. Waterproof is essential",
    "Increase the display size, please.",
    "Waterproof is a must to have."
    ]
    features = [
    "display",
    "waterproof",
    "battery",
    "sun"
    ]
    take 2 $ map head $ sortBy ((flip compare `on` length) <> (compare `on` head)) . group . sort . concat $ map (Set.toList . Set.fromList . (filter (flip elem features)) . words . map toLower . filter (not . \c -> c == '.' || c == ',')) review
