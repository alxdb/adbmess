(ns adbmess.handlers
  (:use [hiccup.core]
        [hiccup.page]))

(def header-links
  [:div {:class "header-links"}
   [:ul
    [:li [:a {:href "/"} "Home"]]]])

(defn make-page
  [title & body]
  (html5 {:lang "en"}
         [:head [:title (str title " - adbmess")]]
         (include-css "/css/gruvbox.css")
         (include-js "/js/login.js")
         [:link {:rel "icon" :type "image/png" :href "/images/favicon-32x32.png"}]
         header-links
         body))

(def welcome-no-user
  (make-page
   "Welcome"
   [:h1 "Welcome!"]
   [:p1 "Welcome friend."]
   [:p1 "please login:"]
   [:form {:action "/login" :method "POST"}
    [:p "username: " [:input {:type "text" :name "username"}]]
    [:p [:input {:type "submit" :value "submit username"}]]]
   [:p1 "no password needed. I trust you."]))

(defn home
  [request]
  (make-page
   "Home"
   [:h1 "Home"]
   [:p "request:"]
   [:p1 (str request)]))

(defn welcome
  [request]
  (make-page
   "Welcome"
   [:h1 "Welcome!"]
   [:p1 "Welcome friend."]
   [:p1 "please login:"]
   [:form {:action "/login" :method "POST"}
    [:p "username: " [:input {:type "text" :name "username"}]]
    [:p [:input {:type "submit" :value "submit username"}]]]
   [:p1 "no password needed. I trust you."]))

(defn secret
  [request]
  (make-page
   "Secret"
   [:h1 "Secret..."]
   [:p "Shhh! Don't tell anyone about this page..."]))

(defn not-found
  [request]
  (make-page
   "Not Found"
   [:h1 "@£$#!!!"]
   [:p "Sorry, page not found"]))

