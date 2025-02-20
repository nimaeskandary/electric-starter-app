(ns electric-starter-app.app.main
  (:require
   #?(:clj [electric-starter-app.domain.users.user-repository :as user-repository])
   [hyperfiddle.electric :as e]
   [hyperfiddle.electric-dom2 :as dom]))

;; Saving this file will automatically recompile and update in your browser

(e/defn Main [ring-request {:keys [user-repository] :as system}]
  (e/server (clojure.pprint/pprint system))
  (e/client
    (binding [dom/node js/document.body]
      (dom/h1 (dom/text "Hello from Electric Clojure"))
      (dom/p (dom/text "Source code for this page is in ")
        (dom/code (dom/text "src/electric_start_app/main.cljc")))
      (dom/p (dom/text "Make sure you check the ")
        (dom/a (dom/props {:href "https://electric.hyperfiddle.net/" :target "_blank"})
          (dom/text "Electric Tutorial")))
      (dom/p (dom/text "This is user: " (e/server (user-repository/get-user user-repository (random-uuid))))))))
