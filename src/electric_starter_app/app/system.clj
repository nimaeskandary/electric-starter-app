(ns electric-starter-app.app.system
  (:require
   [electric-starter-app.domain.users.postgres-user-repository :as user-repository]
   [com.stuartsierra.component :as component]))

(defn dev-system-map [config]
  (component/system-map
    :user-repository (user-repository/->PostgresUserRepository config)))

(def dependency-map {})

(defn create-dev-system [config] (component/system-using (dev-system-map config) dependency-map))

(defonce ^:dynamic *system* nil)
