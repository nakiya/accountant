(ns dumrat.accountant.xtdb.interface
  (:require [dumrat.accountant.xtdb.core :as core]))

(def ^:private conn (atom nil))

(defn node []
  (if (nil? @conn)
    (reset! conn (core/start-xtdb!))
    @conn))