(ns kinopoisk.server.db.data
	(:require [clojure.java.jdbc :as jdbc]
    	      [clojure.java.jdbc.sql :as sql]
            [kinopoisk.server.db.protocol :as protocol]
            [kinopoisk.server.db.connector :as connector]))

(defn insert-user-sql [username password]
    (jdbc/insert-values
      :users 
      [:username :password]
      [username password]))

(defn insert-user [username password]
    (clojure.java.jdbc/with-connection
      connector/db
      (clojure.java.jdbc/transaction      
      (insert-user-sql username password))))

(defn insert-film-sql [title description]  
    (jdbc/insert-values
      :films 
      [:title :description]
      [title description]))

(defn insert-film [title description]
    (clojure.java.jdbc/with-connection
      connector/db
      (clojure.java.jdbc/transaction      
      (insert-film-sql title description))))

(defn select-all-films []
    (jdbc/query connector/db
    (sql/select * :films)))

(defn select-user [username]
    (first (jdbc/query connector/db
    (sql/select * :users (sql/where {:username username})))))

(defn update-password [username new-password]
 )