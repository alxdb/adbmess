(ns adbmess.data
  (:require [qbits.alia :as alia])
  (:use [qbits.hayt :exclude [group-by update]]))

(defonce cluster (alia/cluster))
(defonce session (alia/connect cluster))

(def keyspace :adbmess)
(def generate-keyspace-query
  (create-keyspace keyspace (if-not-exists)
                   (with {:replication {:class "SimpleStrategy" :replication_factor 3}})))

(def user-table :users)
(def generate-user-table-query
  (create-table user-table (if-not-exists)
                (column-definitions [[:username :varchar]
                                     [:primary-key :username]])))

(def message-table :messages)
(def generate-message-table-query
  (create-table message-table (if-not-exists)
                (column-definitions [[:id :uuid]
                                     [:sender :varchar]
                                     [:content :varchar]
                                     [:primary-key :id]])))

(def initialization-queries
  [generate-keyspace-query
   (use-keyspace keyspace)
   generate-user-table-query
   generate-message-table-query])

(defn execute-query
  [query]
  (alia/execute session query))

(defn initialize-database
  []
  (map execute-query initialization-queries))

(defn user-login
  [username]
  ())

