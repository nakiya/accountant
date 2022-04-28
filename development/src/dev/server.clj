(ns dev.server
  (:require [dumrat.accountant.rest-api.main :as main]))

(defn start! [port]
  (main/start! port))

(defn stop! []
  (main/stop!))

(comment
  (start! 6003)
  (stop!))
