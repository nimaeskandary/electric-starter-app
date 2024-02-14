(ns electric-starter-app.domain.users.model)

(def User
  [:map
   [:id :uuid]
   [:username :string]
   [:email :string]])
