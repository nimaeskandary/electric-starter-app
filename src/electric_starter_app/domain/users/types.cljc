(ns electric-starter-app.domain.users.types)

(def User
  [:map
   [:id :uuid]
   [:username :string]
   [:email :string]])

(def save-user [:=> [:cat :map User] User])
(def get-user [:=> [:cat :map :uuid] User])
