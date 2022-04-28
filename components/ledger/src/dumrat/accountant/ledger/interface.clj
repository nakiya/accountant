(ns dumrat.accountant.ledger.interface
  (:require [dumrat.accountant.ledger.ledger :as ledger]))

(defn ledger-name-exists? [name]
  (ledger/ledger-name-exists? name))

(defn save-ledger! [ledger]
  (ledger/save-ledger! ledger))

(defn get-ledgers []
  (ledger/get-ledgers))

