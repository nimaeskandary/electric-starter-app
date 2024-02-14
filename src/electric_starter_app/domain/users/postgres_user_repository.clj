(ns electric-starter-app.domain.users.postgres-user-repository
  (:require
    [electric-starter-app.domain.users.model :refer [User]]
    [electric-starter-app.domain.users.user-repository :as proto]
    [com.stuartsierra.component :as component]
    [malli.core :as m]
    [malli.instrument :as mi]))

(defn- save-user [_ user] user)
(m/=> save-user [:=> [:cat :map User] User])

(defn- get-user [_ user-id] {:username "jdoe"
                             :email    "jdoe@test.com"
                             :id       user-id})
(m/=> get-user [:=> [:cat :map :uuid] User])

(defrecord PostgresUserRepository [config])

(extend-type PostgresUserRepository
  component/Lifecycle
  (start [this] (clojure.pprint/pprint this) this)
  (stop [this] (clojure.pprint/pprint this) this))

(extend-type PostgresUserRepository
  proto/UserRepository
  (save-user [this user] (save-user this user))
  (get-user [this user-id] (get-user this user-id)))

(mi/collect!)
(mi/instrument!)
