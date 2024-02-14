(ns electric-starter-app.system
  (:require
   [electric-starter-app.domain.users.postgres-user-repository :as user-repository]
   [com.stuartsierra.component :as component]))

(defn- dev-system [config]
  (component/system-map
    :user-repository (user-repository/->PostgresUserRepository config)))

(defn start-dev-system [] (component/start (dev-system {})))

(defonce ^:dynamic *system* (start-dev-system))
