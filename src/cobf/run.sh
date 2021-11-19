#!/bin/bash
PATH=/bin:/usr/bin:/usr/local/bin:$PATH
DIR="$( cd "$( dirname "$0" )" && pwd -P )"

if [ ! -d {DIR}/js/c-tokenizer/node_modules ] ; then
 npm ci --prefix ${DIR}/js/c-tokenizer
fi

node ${DIR}/js/c-tokenizer/example/tokens.js< $1 > $2

