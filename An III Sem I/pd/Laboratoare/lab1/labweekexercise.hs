-- Informatics 1 - Functional Programming
-- Lab Week Exercise
--
-- Week 2 - due: Friday, Oct 2nd, 5pm
--
-- Insert your name and matriculation number here:
-- Name:
-- Nr. :


import Test.QuickCheck


-- Exercise 3:

double :: Int -> Int
double x = x + x

square :: Int -> Int
square x = x ^ 2

-- Exercise 4:

isTriple :: Int -> Int -> Int -> Bool
isTriple a b c = a * a + b * b == c * c


-- Exercise 5:

leg1 :: Int -> Int -> Int
leg1 x y = x ^ 2 - y ^ 2

leg2 :: Int -> Int -> Int
leg2 x y = 2 * y * x

hyp :: Int -> Int -> Int
hyp x y = x ^ 2 + y ^ 2


-- Exercise 6:

prop_triple :: Int -> Int -> Bool
prop_triple x y = isTriple (leg1 x y) (leg2 x y) (hyp x y)
