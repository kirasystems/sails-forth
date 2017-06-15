(task-options!
  pom {:project     'sparkfund/sails-forth
       :version     "0.6.0-alpha2"
       :description "A Salesforce library"})

(set-env!
 :resource-paths #{"src"}
 :source-paths #{"test"}
 :dependencies
 '[[adzerk/boot-jar2bin "1.1.0" :scope "build"]
   [adzerk/boot-test "1.2.0" :scope "test"]
   [big-solutions/boot-mvn "0.1.5"]
   [cheshire "5.5.0"]
   [clj-http "2.0.0"]
   [clj-time "0.11.0"]
   [com.github.jsqlparser/jsqlparser "0.9.5"]
   [org.clojure/clojure "1.9.0-alpha10"]
   [org.clojure/test.check "0.9.0" :scope "test"]
   [sparkfund/spec-coverage "0.2.0" :scope "test"]])

(require '[adzerk.boot-jar2bin :refer :all]
         '[adzerk.boot-test :as bt]
         '[boot-mvn.core :refer [mvn]]
         '[clojure.java.io :as io]
         '[spec-coverage.boot :as cover])

(deftask deps
  [])

(def no-integration '(-> % meta :integration not))

(deftask integration
  []
  (bt/test))

(deftask test
  []
  (bt/test 
    :filters [no-integration]))

(deftask spec-coverage
  []
  (cover/spec-coverage 
    :filters [no-integration]
    :instrument 'spec-coverage.instrument/in-n-outstrument))

(deftask spec-coverage-integration
  []
  (cover/spec-coverage
    :instrument 'spec-coverage.instrument/in-n-outstrument))
