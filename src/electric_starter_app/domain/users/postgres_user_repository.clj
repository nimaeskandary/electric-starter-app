(ns electric-starter-app.domain.users.postgres-user-repository
  (:require
   [electric-starter-app.domain.users.types :as types]
   [electric-starter-app.domain.users.user-repository :as proto]
   [com.stuartsierra.component :as component]
   [malli.core :as m]))

(defn- start [this] (clojure.pprint/pprint this) this)

(defn- stop [this] (clojure.pprint/pprint this) this)

(defn- save-user [_ user] user)

(defn- get-user [_ user-id] {:username "jdoe"
                             :email    "jdoe@test.com"
                             :id       user-id})

(defrecord PostgresUserRepository [config])

(extend-type PostgresUserRepository
  component/Lifecycle
  (start [this] (start this))
  (stop [this] (stop this)))

(extend-type PostgresUserRepository
  proto/UserRepository
  (save-use [this user] (save-user this user))
  (get-user [this user-id] (get-user this user-id)))

(m/=> save-user types/save-user)
(m/=> get-user types/get-user)
