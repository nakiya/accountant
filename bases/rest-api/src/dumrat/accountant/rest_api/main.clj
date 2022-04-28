(ns dumrat.accountant.rest-api.main
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [clojure.tools.logging.readable :as log]
            [dumrat.accountant.rest-api.api :as api])
  (:gen-class))

(def ^:private server-ref (atom nil))

(defn start!
  [port]
  (if-let [_server @server-ref]
    (log/warn "Server already running? (stop!) it first.")
    (do
      (log/info "Starting server on port: " port)
      ;(api/init)
      (reset! server-ref
              (run-jetty api/app
                         {:port port
                          :join? false})))))

(defn stop! []
  (if-let [server @server-ref]
    (do (.stop server)
        (reset! server-ref nil))
    (log/warn "No server")))

(defn -main [& _args]
  (start! (Integer/valueOf
           (or (System/getenv "port")
               "6003")
           10)))