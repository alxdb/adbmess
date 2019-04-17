(ns adbmess.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [adbmess.handlers :as handlers]
            [adbmess.data :as data]))

(defroutes app
  (GET "/" [] handlers/home)
  (GET "/secret" [] handlers/secret)
  (route/resources "/")
  (route/not-found handlers/not-found))

(defn -main
  [port-number]
  (data/initialize-database)
  (jetty/run-jetty app {:port (Integer. port-number)}))

(defn -dev-main
  [port-number]
  (data/initialize-database)
  (jetty/run-jetty (wrap-reload app) {:port (Integer. port-number)}))
