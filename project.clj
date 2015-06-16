(defproject mattgillooly/drake-mysql "0.0.1"
  :description "Write MySQL steps directly in your Drakefile"
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [org.clojure/clojure-contrib "1.2.0"]   ;; for clojure.contrib.sql
                 [org.clojure/java.jdbc "0.0.6"]         ;; jdbc 
                 [mysql/mysql-connector-java "5.1.6"]    ;; mysql driver
                 [propertea "1.2.3"]
                 [factual/drake-interface "0.0.1"]])
