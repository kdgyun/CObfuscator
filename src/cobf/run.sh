#!/bin/bash
PATH=/bin:/usr/bin:/usr/local/bin
node src/cobf/js/node_modules/c-tokenizer/example/tokens.js< $1 > $2
