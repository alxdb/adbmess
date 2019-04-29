(ns adbmess.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [adbmess.handlers :as handlers]
            [adbmess.data :as data]))

(defroutes app
  (GET "/" [] handlers/home)
  (POST "/" [] handlers/home)
  (route/resources "/")
  (route/not-found handlers/not-found))

(defn -main
  [port-number]
  (data/initialize-database)
  (jetty/run-jetty (wrap-params app) {:port (Integer. port-number)}))

(defn -dev-main
  [port-number]
  (data/initialize-database)
  (jetty/run-jetty (-> app
                       wrap-params
                       wrap-reload)
                   {:port (Integer. port-number)}))
