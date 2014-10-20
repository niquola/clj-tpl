(defproject ivf "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :source-paths  ["lib/route-map/src" "src"]
  :dependencies [[org.clojure/clojure "1.7.0-alpha2"]
                 [http-kit "2.1.16"]
                 [org.clojure/java.jdbc "0.3.5"]
                 [garden "1.2.1"]
                 [honeysql "0.4.3"]
                 [clj-time "0.8.0"]
                 [cheshire "5.3.1"]
                 [hiccup "1.0.5"]
                 [prismatic/schema "0.3.1"]
                 [prismatic/plumbing "0.3.4"]
                 [compojure "1.1.9"]]
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
