(ns dumrat.accountant.ledger.ledger
  (:require [xtdb.api :as xt]
            [dumrat.accountant.xtdb.interface :as db])
  (:import [java.util UUID]))

(defn q [query & args]
  (apply xt/q (xt/db (db/node)) query args))

;;Ledger

(defn get-ledger-by-name [name]
  (ffirst
   (q '{:find [?ledger]
        :where [[id :ledger ?ledger]
                [id :name n]]
        :in [n]} name)))

(defn get-ledger-by-id [id]
  (xt/entity (xt/db (db/node)) id))

(defn ledger-name-exists? [name]
  (get-ledger-by-name name))

(defn save-ledger! [ledger]
  (let [id (UUID/randomUUID)]
    (xt/submit-tx (db/node) [[::xt/put {:xt/id id :xt/name (:name ledger) :ledger ledger}]])
    (xt/sync (db/node))
    (get-ledger-by-id id)))

(defn get-ledgers []
  (q '{:find [?name ?id]
       :where [[?id :ledger ledger] [?id :name ?name]]}))

(comment
  (doseq [x (get-ledgers)]
    (xt/submit-tx (db/node) [[::xt/evict (second x)]]))

  (get-ledgers)
  
  (try
    (xt/submit-tx (db/node) [[::xt/put {"Hi" "there"}]])
    (catch xtdb.IllegalArgumentException e (str e)))
  ;;
  )
