(ns dumrat.accountant.xtdb.core
  (:require [clojure.java.io :as io]
            [xtdb.api :as xt]
            [dumrat.accountant.env.interface :as env]))

(defn start-xtdb! []
  (letfn [(kv-store [dir]
            {:kv-store {:xtdb/module 'xtdb.rocksdb/->kv-store
                        :db-dir (io/file dir)
                        :sync? true}})]
    (xt/start-node
     {:xtdb/tx-log (kv-store (str (:database-path env/env) "dev/tx-log"))
      :xtdb/document-store (kv-store (str (:database-path env/env) "dev/doc-store"))
      :xtdb/index-store (kv-store (str (:database-path env/env) "data/dev/index-store"))})))