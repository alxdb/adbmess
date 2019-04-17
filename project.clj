(defproject adbmess "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ring "1.7.1"]
                 [compojure "1.6.1"]
                 [hiccup "1.0.5"]
                 [org.slf4j/slf4j-simple "1.7.26"] ;; Alia messes with logger for some reason
                 [cc.qbits/alia "4.3.1"]
                 [cc.qbits/alia-async "4.3.1"]
                 [cc.qbits/hayt "4.1.0"]]
  :repl-options {:init-ns adbmess.core}
  :profiles {:dev {:main adbmess.core/-dev-main}}
  :main adbmess.core)
