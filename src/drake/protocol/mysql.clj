(ns drake.protocol.mysql
  (:use [drake-interface.core :only [Protocol]])
  (:require [clojure.string :as str]
            [clojure.contrib.sql :as sql]
            [propertea.core :as props]))

(defn run-mysql
  [sql options]
  (sql/with-connection options
    (sql/with-query-results rs [sql] (doall rs))))

(defn as-tsv-recs
  [rs]
  (concat 
    [(str/join "\t" (map #(str/replace-first % ":" "") (map key (first rs))))]
    (map #(str/join "\t" (map val %)) rs)))

(defn run-mysql-step
  "Runs the MySQL statement in the step's body."
  [step]
  (let [sql       (str/join " " (:cmds step))
        options   (props/read-properties (:options step))
        tsv-recs  (-> sql
                      (run-mysql options)
                      as-tsv-recs)
        outfile   (first (:outputs step))]
    (spit outfile (str/join "\n" tsv-recs))))

(defn mysql []
  (reify Protocol
    (cmds-required? [_] true)
    (run [_ step]
      (run-mysql-step step))))
