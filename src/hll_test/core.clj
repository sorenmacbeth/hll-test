(ns hll-test.core
  (:use cascalog.api
        al-jabr.core))

(def HLL* (mk-hyper-log-log 1.0))

(defn hll-combine [l r] (.plus HLL* l r))

(defparallelagg hll-plus :init-var #'identity :combine-var #'hll-combine)

(defmapop to-hll [x] (->hll HLL* #(.getBytes % "UTF-8") x))
(defmapop from-hll [x] (.estimatedSize x))

(defn map-plus-map [to-fn from-fn] (<- [?x :> ?y] (to-fn ?x :> ?item) (hll-plus ?item :> ?reduced) (from-fn ?reduced :> ?y)))

(def hll-mpm (map-plus-map to-hll from-hll))

(defn test []
    (let [src [["foo"] ["bar"] ["baz"] ["pickle"] ["zombie"] ["winchester"]]]
      (use 'cascalog.playground)
      (bootstrap-emacs)
      (?<- (stdout)
           [?distinct-size]
           (src ?string)
           (hll-mpm ?string :> ?distinct-size))))
