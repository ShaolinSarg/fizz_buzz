(ns fizz-buzz.core-test
  (:require [clojure.test :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]
            [fizz-buzz.core :refer :all]))

(deftest fizz?-tests
  (testing "fizz? should return false"
    (testing "when given a number that is not a multiple of 3"
      (is (false? (fizz? 2)))))
  (testing "fizz? should return true"
    (testing "when given a number that is a multiple of 3"
      (is (true? (fizz? 3))))))

(deftest buzz?-tests
  (testing "buzz? should return false"
    (testing "when given a number that is not a multiple of 5"
      (is (false? (buzz? 3)))))
  (testing "buzz? should return true"
    (testing "when given a number that is a multiple of 5"
      (is (true? (buzz? 5))))))

(deftest fizz-buzz?-tests
  (testing "fizz-buzz? should return false"
    (testing "when given a number that is not a multiple of 5 and 3"
      (is (false? (fizz-buzz? 3)))
      (is (false? (fizz-buzz? 5)))))
  (testing "fizz-buzz? should return true"
    (testing "when given a number that is a multiple of 5 and 3"
      (is (true? (fizz-buzz? 15))))))

(defspec fizz?-returns-true-for-multiples-of-three
  100 ;; the number of iterations for test.check to test
  (prop/for-all [mult-three (gen/fmap #(* 3 %) gen/int)]
    (is (true? (fizz? mult-three)))))

(defspec buzz?-returns-true-for-multiples-of-five
    100 ;; the number of iterations for test.check to test
    (prop/for-all [mult-five (gen/fmap #(* 5 %) gen/int)]
                      (is (true? (buzz? mult-five)))))

(defspec fizz-buzz?-returns-true-for-multiples-of-fifteen
    100 ;; the number of iterations for test.check to test
    (prop/for-all [mult-fifteen (gen/fmap #(* 15 %) gen/int)]
                      (is (true? (fizz-buzz? mult-fifteen)))))

(deftest conv-tests
  (testing "conv"
    (testing "should return the right substituted symbol"
      (is (= 'fizz (conv 3)))
      (is (= 'buzz (conv 5)))
      (is (= 'fizz-buzz (conv 15))))
    (testing "should return the original symbol"
      (is (= 2 (conv 2)))
      (is (= 7 (conv 7))))))

(deftest fb-tests
  (testing "fb"
    (testing "should return a transformed sequence"
      (is (= '(1) (fb 1)))
      (is (= '(1 2) (fb 2)))
      (is (= '(1 2 fizz 4 buzz) (fb 5))))))
