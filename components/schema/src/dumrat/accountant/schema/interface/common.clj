(ns dumrat.accountant.schema.interface.common
  (:require [malli.core :as m]
            [malli.error :as me]))

(defn validate [schema data]
  (m/validate schema data))

(defn explain [schema data]
  (me/humanize (m/explain schema data)))