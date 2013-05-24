(defproject hll-test "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [cascalog/cascalog-core "1.10.2-SNAPSHOT"]
                 [al-jabr "0.1.0-SNAPSHOT"]]
  :profiles {:provided {:dependencies [[org.apache.hadoop/hadoop-core "0.20.2"]]}})
