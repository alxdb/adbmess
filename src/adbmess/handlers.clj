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
         [:link {:rel "icon" :type "image/png" :href "/images/favicon-32x32.png"}]
         header-links
         body))

(def welcome-friend
  [:div
   [:h1 "Welcome!"]
   [:p1 "Welcome friend."]
   [:p1 "please login:"]
   [:form {:action "/" :method "POST" :autocomplete "off"}
    [:p "username: " [:input {:type "text" :name "username"}]]
    [:p [:input {:type "submit" :value "submit username"}]]]
   [:p1 "no password needed. I trust you."]])

(defn welcome-user
  [username]
  [:div
   [:h1 "Welcome!"]
   [:p1 (str "Welcome " username)]
   [:form {:action "/" :method "POST"}
    [:input {:type "hidden" :name "username" :value username}]]])

(defn home
  [request]
  (let [username (get-in request [:params "username"])
        content (if (nil? username) welcome-friend (welcome-user username))]
    (make-page
      "Home"
      content
      [:p "request:"]
      [:p1 (str request)])))

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
   [:p "Sorry, page not found"]
   [:p "request was:"]
   [:p (str request)]
   [:p "body:"]
   [:p (.toString (:body request))]))

