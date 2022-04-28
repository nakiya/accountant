(ns dumrat.accountant.rest-api.api
  (:require [compojure.core :refer [routes wrap-routes defroutes GET POST PUT DELETE ANY OPTIONS]]
            [ring.middleware.json :as js]
            [ring.middleware.keyword-params :as kp]
            [ring.middleware.multipart-params :as mp]
            [ring.middleware.nested-params :as np]
            [ring.middleware.params :as pr]
            [clojure.tools.logging.readable :as log]
            [dumrat.accountant.rest-api.handler :as h]
            [dumrat.accountant.rest-api.middleware :as m]))

(defroutes public-routes
  (OPTIONS "/**" [] h/options)
  (GET "/ledger/get-ledgers" [] h/get-ledgers)
  (POST "/ledger/add-ledger" [] h/save-ledger!)
  )

(def ^:private app-routes
  (routes public-routes))

(def app
  (-> app-routes
      kp/wrap-keyword-params
      pr/wrap-params
      mp/wrap-multipart-params
      js/wrap-json-params
      np/wrap-nested-params
      js/wrap-json-response
      m/wrap-cors
      m/wrap-exception-handling))