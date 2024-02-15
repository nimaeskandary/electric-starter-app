(ns dev
  (:require
    electric-starter-app.app.main
    [hyperfiddle.electric :as e]
    #?(:clj [electric-starter-app.app.server-jetty :as jetty])
    #?(:clj [electric-starter-app.app.system :as system])
    #?(:clj [shadow.cljs.devtools.api :as shadow])
    #?(:clj [shadow.cljs.devtools.server :as shadow-server])
    #?(:clj [clojure.tools.logging :as log]))
  #?(:clj (:import (org.eclipse.jetty.server Server))))

#?(:clj ;; Server Entrypoint
   (do
     (defonce ^:dynamic ^Server *jetty-server* nil)

     (def config
       {:host "0.0.0.0"
        :port 8080
        :resources-path "public/electric_starter_app"
        :manifest-path ; contains Electric compiled program's version so client and server stays in sync
        "public//electric_starter_app/js/manifest.edn"})

     (defn -main [& args]
       (log/info "Starting Electric compiler and server...")

       (shadow-server/start!)
       (shadow/watch :dev)

       (alter-var-root #'*jetty-server* (constantly (jetty/start-server!
                                                      (fn [ring-request]
                                                        (e/boot-server {} electric-starter-app.app.main/Main ring-request system/*system*))
                                                      config))))))


#?(:cljs ;; Client Entrypoint
   (do
     (def electric-entrypoint (e/boot-client {} electric-starter-app.app.main/Main nil))

     (defonce reactor nil)

     (defn ^:dev/after-load ^:export start! []
       (set! reactor (electric-entrypoint
                       #(js/console.log "Reactor success:" %)
                       #(js/console.error "Reactor failure:" %))))

     (defn ^:dev/before-load stop! []
       (when reactor (reactor)) ; stop the reactor
       (set! reactor nil))))
