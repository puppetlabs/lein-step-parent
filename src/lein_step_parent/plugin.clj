(ns lein-step-parent.plugin
  (:require [cemerick.pomegranate.aether :as aether])
  (:import (java.util.zip ZipFile)
           (java.io InputStreamReader)))

(defn- get-managed-deps-from-parent-coords
  [coords {:keys [repositories offline?]}]
  (let [resolved-parent-artifact (first (first (aether/resolve-dependencies
                                                 :coordinates [coords]
                                                 :repositories repositories
                                                 :offline? offline?)))
        artifact-jar (:file (meta resolved-parent-artifact))
        artifact-zip (ZipFile. artifact-jar)
        project-clj-stream (InputStreamReader.
                             (.getInputStream
                               artifact-zip
                               (.getEntry artifact-zip "project.clj")))]
    (binding [*ns* (find-ns 'leiningen.core.project)]
      (load-reader project-clj-stream)
      (let [resolved (resolve 'leiningen.core.project/project)]
        (:managed-dependencies @resolved)))))

(defn middleware [project]
  (if-let [parent-project (:parent-project project)]
    (assoc project
      :managed-dependencies (get-managed-deps-from-parent-coords
                              (:coords parent-project)
                              project))
    project))