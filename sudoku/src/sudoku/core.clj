(ns sudoku.core
  (:gen-class))

(def sudoku-puzzle
  [[2 -1 8 -1 4 -1 -1 6 -1]
   [-1 -1 -1 8 -1 6 -1 5 1]
   [1 6 -1 -1 -1 -1 -1 7 -1]
   [-1 -1 3 4 1 -1 5 9 -1]
   [-1 2 -1 7 -1 9 -1 4 -1]
   [-1 9 1 -1 6 -5 7 -1 -1]
   [-1 1 -1 -1 -1 -1 -1 3 4]
   [8 7 -1 6 -1 4 -1 -1 -1]
   [-1 4 -1 -1 5 -1 8 -1 7]])

(defn replace-nil-values
  "Utility function to replace -1 with [123456789] in every row"
  [board]
  (let [single-digits (range 1 10)]
    (map #(replace {-1 single-digits} %) board)))
