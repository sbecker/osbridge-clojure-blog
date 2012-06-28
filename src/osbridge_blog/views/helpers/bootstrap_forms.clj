(ns osbridge-blog.views.helpers.bootstrap-forms
  (:require [noir.validation :as vali])
  (:use noir.core
        [hiccup.form-helpers]))

; error item helper
(defpartial error-item [[first-error]]
  [:span.help-inline first-error])

(defpartial form [method url legend & content]
  (form-to {:class "form-horizontal"} [method url]
    [:fieldset
      [:legend legend]
      content]))

(defpartial form-input [type label fieldname value]
  [:div (if (vali/errors? (keyword fieldname)) {:class "control-group error"} {:class "control-group"})
    [:label.control-label label]
    [:div.controls
      [:input {:name fieldname :type type :value value}]
      (vali/on-error (keyword fieldname) error-item)]])

(defpartial textarea-input [label fieldname value]
  [:div (if (vali/errors? (keyword fieldname)) {:class "control-group error"} {:class "control-group"})
    [:label.control-label label]
    [:div.controls
      [:textarea {:name fieldname :style "width:400px;height:200px"} value]
      (vali/on-error (keyword fieldname) error-item)]])

