(ns user
  (:require
    [dev]
    [electric-starter-app.app.system :as system]
    [com.stuartsierra.component :as component]
    [malli.dev :as malli-dev]
    [shadow.cljs.devtools.server :as shadow-server]))

(def system-config
  {:user-repository {}})

(defn init-system [] (alter-var-root #'system/*system* (constantly (system/create-dev-system system-config))))
(defn start-system [] (alter-var-root #'system/*system* component/start-system))
(defn stop-system [] (alter-var-root #'system/*system* #(when % (component/stop-system %) nil)))
(defn restart-system [] (stop-system) (init-system) (start-system))

(defn start-app []
  (init-system)
  (start-system)
  (malli-dev/start!)
  (dev/-main))

(defn stop-app []
  (when dev/*jetty-server* (.stop dev/*jetty-server*))
  (shadow-server/stop!)
  (stop-system))
