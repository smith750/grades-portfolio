(defproject grades-reframe "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [org.omcljs/om "1.0.0-alpha47"]]

  :profiles {
    :dev {
      :dependencies [[figwheel-sidecar "0.5.4-6"]]
    }
  }

  :cljsbuild {
    :builds [ { :id "dev"
                :source-paths ["src/"]
                :figwheel true
                :compiler {  :main "grades-omnext.core"
                             :asset-path "js/target"
                             :output-to "resources/public/js/main.js"
                             :output-dir "resources/public/js/target" } } ]
  }

  )
