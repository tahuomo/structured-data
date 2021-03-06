(ns structured-data)

(defn do-a-thing [x]
  (let [y (+ x x)]
  (Math/pow y y)))

(defn spiff [v]
   (+ (get v 0) (get v 2)))

(defn cutify [v]
  (conj v "<3"))

(defn spiff-destructuring [v]
  (let [[x y z] v]
       (+ x z)))

(defn point [x y]
  [x y])

(defn rectangle [bottom-left top-right]
  [bottom-left top-right])

(defn width [rectangle]
  (let [[[x1] [x2]] rectangle]
   (- x2 x1)))

(defn height [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
   (- y2 y1)))

(defn square? [rectangle]
  (== (width rectangle) (height rectangle)))

(defn area [rectangle]
  (* (width rectangle) (height rectangle)))

(defn contains-point? [rectangle point]
  (let [[[x1 y1] [x2 y2]] rectangle
       [p1 p2] point]
      (and (<= x1 p1 x2)
           (<= y1 p2 y2))))

(defn contains-rectangle? [outer inner]
  (let [[p1 p2] inner]
     (and (contains-point? outer p1)
          (contains-point? outer p2))))

(defn title-length [book]
  (count (:title book)))

(defn author-count [book]
  (count (:authors book)))

(defn multiple-authors? [book]
  (< 1 (author-count book)))

(defn add-author [book new-author]
  (assoc book :authors (conj (:authors book) new-author)))

(defn alive? [author]
  (not (contains? author :death-year)))

(defn element-lengths [collection]
   (map count collection))

(defn second-elements [collection]
   (let [get-second (fn [x] (get x 1))]
        (map get-second collection)))

(defn titles [books]
   (map :title books))

(defn monotonic? [a-seq]
  (or (apply <= a-seq)
      (apply >= a-seq)))

(defn stars [n]
  (apply str (repeat n "*")))

(defn toggle [a-set elem]
  (if (contains? a-set elem)
     (disj a-set elem)
     (conj a-set elem)))

(defn contains-duplicates? [a-seq]
  (not (== (count a-seq) (count (set a-seq)))))

(defn old-book->new-book [book]
  (assoc book :authors (set (:authors book))))

(defn has-author? [book author]
  (contains? (:authors book) author))

(defn authors [books]
  (apply clojure.set/union (map :authors books)))

(defn all-author-names [books]
  (set (map :name (authors books))))

(defn author->string [author]
  (let [byear (:birth-year author)
        dyear (:death-year author)
        years (str " (" byear " - " dyear ")")]
       (if (contains? author :birth-year)
           (str (:name author) years)
           (:name author))))

(defn authors->string [authors]
 (apply str (interpose ", " (map author->string authors) )))

(defn book->string [book]
   (str (:title book) ", written by " (authors->string(:authors book))))

(defn books->string [books]
  (let [cnt (count books)
        books_string (apply str (interpose ", " (map book->string books)))]
    (cond
       (== 0 cnt) "No books."
       (== 1 cnt) (str "1 book. " books_string ".")
       :else (str cnt " books. " books_string "."))))

(defn books-by-author [author books]
  (filter (fn [book] (has-author? book author)) books))

(defn author-by-name [name authors]
  (let [au (filter (fn [x] (= name (:name x))) authors)]
  (if (< 0 (count au))
      (first au)
      nil)))

(defn living-authors [authors]
  (filter (fn [a] (alive? a)) authors))

(defn has-a-living-author? [book]
  (not(empty? (living-authors(:authors book)))))

(defn books-by-living-authors [books]
  (filter (fn [b] (has-a-living-author? b)) books))

; %________%
