(ns sudoku.core-test
  (:require [clojure.test :refer :all]
            [sudoku.core :refer :all]))

(defn empty-values-replaced?
  "Utility function to check if all -1's in the board are replaced"
  [board]
  (if (and (sequential? board) (every? sequential? board))
    (every? (fn [row]
              (not (some #(= % -1) row)))
            board)
    (throw (IllegalArgumentException.))))

(deftest empty-values-replaced?-test
  (is (false? (empty-values-replaced? sudoku-puzzle)))
  (is (true? (empty-values-replaced? [[1 2] [3 4]])))
  (is (thrown? IllegalArgumentException (empty-values-replaced? (range 1 82)))))

(deftest replace-nil-values-test
  (is (empty-values-replaced? (replace-nil-values sudoku-puzzle))))
