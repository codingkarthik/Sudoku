(ns sudoku.core-test
  (:require [clojure.test :refer :all]
            [sudoku.core :refer :all]))

(def sd (into [] (range 1 10))) ;; sd stands for single digits

(def sudoku-puzzle-with-replaced-values
  [[2 sd 8
    sd 4 sd
    sd 6 sd]
   [sd sd sd
    8 sd 6
    sd 5 1]
   [1 6 sd
    sd sd sd
    sd 7 sd]
   [sd sd 3
    4 1 sd
    5 9 sd]
   [sd 2 sd
    7 sd 9
    sd 4 sd]
   [sd 9 1
    sd 6 -5
    7 sd sd]
   [sd 1 sd
    sd sd sd
    sd 3 4]
   [8 7 sd
    6 sd 4
    sd sd sd]
   [sd 4 sd
    sd 5 sd
    8 sd 7]])

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

(defn sd-without-n
  [n]
  (into [] (remove #(= % n) sd)))

(def sudoku-puzzle-with-replaced-values
  [[2 sd 8
    sd 4 sd
    sd 6 sd]
   [sd sd sd
    8 sd 6
    sd 5 1]
   [1 6 sd
    sd sd sd
    sd 7 sd]
   [sd sd 3
    4 1 sd
    5 9 sd]
   [sd 2 sd
    7 sd 9
    sd 4 sd]
   [sd 9 1
    sd 6 -5
    7 sd sd]
   [sd 1 sd
    sd sd sd
    sd 3 4]
   [8 7 sd
    6 sd 4
    sd sd sd]
   [sd 4 sd
    sd 5 sd
    8 sd 7]])

(def sd-without-2 (into [] (remove #(= % 2) (into [] (range 1 10)))))

(def sd-without-2 (into [] (remove #(= % 2) sd)))

(def board-after-replacing-first-value-of-puzzle
  [[2 sd-without-2 8
    sd-without-2 4 sd-without-2
    sd-without-2 6 sd-without-2]
   [sd-without-2 sd-without-2 sd-without-2
    8 sd 6
    sd 5 1]
   [1 6 sd-without-2
    sd sd sd
    sd 7 sd]
   [sd-without-2 sd 3   ;;; Second row
    4 1 sd
    5 9 sd]
   [sd 2 sd
    7 sd 9
    sd 4 sd]
   [sd 9 1
    sd 6 -5
    7 sd sd]
   [sd-without-2 1 sd             ;;; Third row
    sd-without-2 sd sd
    sd-without-2 3 4]
   [8 7 sd
    6 sd 4
    sd sd sd]
   [sd 4 sd
    sd 5 sd
    8 sd 7]])

(deftest clear-row-possiblities-test
  (let [test-row [8 sd 9 1 5 3 6 2 sd]
        sd-without-8 (sd-without-n 8)
        expected-outcome (replace {sd sd-without-8} test-row)]
    (is (= expected-outcome (clear-row-possiblities 8 test-row))
        "Checking if the value is removed from the possible values")
    (is (= expected-outcome (clear-row-possiblities 8 expected-outcome))
        "Checking that anything does not replace for a row with replaced values")))
