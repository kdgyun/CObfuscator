#!/bin/bash
PATH=/bin:/usr/bin:/usr/local/bin:$PATH

if [ ! -d src/cobf/js/c-tokenizer/node_modules ] ; then
 npm ci --prefix src/cobf/js/c-tokenizer
fi

node src/cobf/js/c-tokenizer/example/tokens.js< $1 > $2
