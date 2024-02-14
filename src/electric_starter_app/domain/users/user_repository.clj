(ns electric-starter-app.domain.users.user-repository)

(defprotocol UserRepository
  (save-user [this user])
  (get-user [this user-id]))
