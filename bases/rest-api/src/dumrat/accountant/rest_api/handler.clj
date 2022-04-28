(ns dumrat.accountant.rest-api.handler
  (:require [dumrat.accountant.ledger.interface :as ledger]
            [clojure.tools.logging.readable :as log]
            [dumrat.accountant.schema.interface.ledger :as sl]
            [dumrat.accountant.schema.interface.common :as sc]))

(defn- handle
  ([status body]
   {:status (or status 404)
    :body   body})
  ([status]
   (handle status nil)))

(defn options [_]
  (handle 200))

(defn save-ledger! [req]
  (let [ledger (get-in req [:params :ledger])
        ledger (update ledger :type keyword)]
    (if (sc/validate sl/ledger-schema ledger)
      (handle 200 {:ledger (ledger/save-ledger! ledger)})
      (handle 400 {:errors (sc/explain sl/ledger-schema ledger)}))))

(defn get-ledgers [req]
  (log/info req)
  (handle 200 (ledger/get-ledgers)))



