(ns dumrat.accountant.schema.ledger
  (:require [malli.core :as m]
            [malli.error :as me]))

(def ledger-schema
  [:map
   [:type :keyword]
   [:id {:optional true} [:string {:min 1}]]
   [:name [:string {:min 1 :max 128}]]
   [:description {:optional true} [:string {:min 1}]]])

(comment
  (m/validate ledger-schema {:type :ledger :name "Ledger 1"})
  (me/humanize (m/explain ledger-schema {:type :ledger :name nil}))
  (m/validate ledger-schema {:type :ledger :name ""})
  ;
  )