# Electric Starter App

A fork of https://github.com/hyperfiddle/electric-starter-app, to play around with making an application using [electric](https://github.com/hyperfiddle/electric)

# Table of Contents
1. [Changelog](#changelog)
2. [Development](#development)
3. [Instructions](#instructions)

## Changelog

* added an implementation of components for dependency injection
* created a `UserRepository` component, making use of it to display a dummy user in the web view
* added malli type checking to user repo functions

## TODOs

* refactor the jetty code that shipped with this to be a component
* implement `UserRepository` for real using postgres

## Development

### Dependencies

* [java 21](https://adoptium.net/installation/)
* [cljfmt](https://github.com/weavejester/cljfmt) 

### Dev Environment

I'm using Intellij Idea Community Edition and the Cursive plugin.

To make things resolve correctly during development
* add `deps.edn` as a new deps project
* under `Aliases` in the Clojure Deps tool window, select just `dev`

### Formatting

* run `bin/format.sh`

## Instructions

Dev build:

* start a repl `clj -A:dev`
* run `(start-app)` 
* see `src-dev/user.clj` for more

Prod build:

```shell
clj -X:build:prod build-client
clj -M:prod -m prod
```

Uberjar:
```
clj -X:build:prod uberjar :build/jar-name "target/app.jar"
java -cp target/app.jar clojure.main -m prod
```
