#!/usr/bin/env zsh

DIR="${0:A:h}"
cd "$DIR/.." || exit 1
cljfmt fix
