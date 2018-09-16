(ns sudoku.core-test
  (:require [clojure.test :refer :all]
            [sudoku.core :refer :all]))

(def sudoku-puzzle-with-replaced-values
  [[2 [1 2 3 4 5 6 7 8 9] 8 [1 2 3 4 5 6 7 8 9] 4 [1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9] 6 [1 2 3 4 5 6 7 8 9]]
   [[1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9] 8 [1 2 3 4 5 6 7 8 9] 6 [1 2 3 4 5 6 7 8 9] 5 1]
   [1 6 [1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9] 7 [1 2 3 4 5 6 7 8 9]]
   [[1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9] 3 4 1 [1 2 3 4 5 6 7 8 9] 5 9 [1 2 3 4 5 6 7 8 9]]
   [[1 2 3 4 5 6 7 8 9] 2 [1 2 3 4 5 6 7 8 9] 7 [1 2 3 4 5 6 7 8 9] 9 [1 2 3 4 5 6 7 8 9] 4 [1 2 3 4 5 6 7 8 9]]
   [[1 2 3 4 5 6 7 8 9] 9 1 [1 2 3 4 5 6 7 8 9] 6 -5 7 [1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9]]
   [[1 2 3 4 5 6 7 8 9] 1 [1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9] 3 4]
   [8 7 [1 2 3 4 5 6 7 8 9] 6 [1 2 3 4 5 6 7 8 9] 4 [1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9]]
   [[1 2 3 4 5 6 7 8 9] 4 [1 2 3 4 5 6 7 8 9] [1 2 3 4 5 6 7 8 9] 5 [1 2 3 4 5 6 7 8 9] 8 [1 2 3 4 5 6 7 8 9] 7]])

(def one-to-nine-puzzle
  (repeat 9 (range 1 10)))

(defn empty-values-replaced?
  "Utility function to check if all -1's in the board are replaced"
  [board]
  (if (and (sequential? board) (every? sequential? board))
    (every? (fn [row]
              (not (some #(= % -1) row)))
            board)
    (throw (IllegalArgumentException.))))

(deftest empty-values-replaced?-test
  (is (false? (empty-values-replaced? sudoku-puzzle))
      "Checking for an unsolved puzzle")
  (is (true? (empty-values-replaced? [[1 2] [3 4]]))
      "Checking against for a minimal true test case")
  (is (thrown? IllegalArgumentException
               (empty-values-replaced? (range 1 82)))
      (str "Checking if exception is thrown when"
           " an illegal value is passed to the function")))

(deftest replace-nil-values-test
  (is (empty-values-replaced? (replace-nil-values sudoku-puzzle))
      (str "Checking if any empty value still remains"
           " after removing the null values"))
  (is (= (replace-nil-values sudoku-puzzle) sudoku-puzzle-with-replaced-values)
      (str "Checking if the board returned by the function has replaced the"
           " -1's with [1 .. 9]")))
