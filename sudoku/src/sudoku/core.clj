(ns sudoku.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(def board [[2 -1 8 -1 4 -1 -1 6 -1]
        [-1 -1 -1 8 -1 6 -1 5 1]
        [1 6 -1 -1 -1 -1 -1 7 -1]
        [-1 -1 3 4 1 -1 5 9 -1]
        [-1 2 -1 7 -1 9 -1 4 -1]
        [-1 9 1 -1 6 -5 7 -1 -1]
        [-1 1 -1 -1 -1 -1 -1 3 4]
        [8 7 -1 6 -1 4 -1 -1 -1]
        [-1 4 -1 -1 5 -1 8 -1 7]])
