# lein-step-parent

#### NOTE: If you are using Cursive, you need at least version 1.4.0-eap2-15 in order for this plugin to work!

## What is this?

It's a leiningen plugin.  It's a hacky knock-off of
[lein-parent](https://github.com/achin/lein-parent), with fewer features.

[![Clojars Project](http://clojars.org/lein-step-parent/latest-version.svg)](http://clojars.org/lein-step-parent)

## Sounds dumb.  Why?

There are some capabilities of lein 2.7.1 and `lein-parent` that do not
yet work properly in the IntelliJ Cursive Clojure plugin (as of version
1.4.0-eap2-15).  If you try to load a project that uses `lein-parent`
via the `:coords` mechanism for specifying the parent artifact coordinates,
Cursive will throw an error and fail to load the project.

This plugin will handle parent `:coords` properly both on the CLI and
in IntelliJ.

## Why not just stick with this one forever, then?

Because it only supports a small, targeted subset of the capabilities of
`lein-parent`.

## What does it support?

It only supports inheriting the `:managed-dependencies` section from the
parent project - no other keys.  And if you do anything fancy in your
parent project (fancy macros, lein profiles, etc.), it may just not work at
all.  So don't do anything fancy for now.

## Blergh.  OK, how do I use it?

#### NOTE: If you are using Cursive, you need at least version 1.4.0-eap2-15 in order
for this plugin to work!

Specify your parent project configuration just like you would for
`lein-parent`:

```clj
:parent-project {:coords [my-cool-namespace/parent "1.0.0"]
                 :inherits [:managed-dependencies]}
```

Then add `lein-step-parent` to your plugins list:

```clj
:plugins [[lein-step-parent "0.x.x"]]
```

Latest version is:

[![Clojars Project](http://clojars.org/lein-step-parent/latest-version.svg)](http://clojars.org/lein-step-parent)

## What happens when Cursive is fixed?

We kill this with fire, and remove all traces of it from our `project.clj` files.
