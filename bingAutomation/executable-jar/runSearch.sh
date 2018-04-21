#!/bin/bash

MY_DIR="/home/charlie/projects/bing-search/bing-rewards-executable"
cd $MY_DIR
java -jar bingSearch.jar | tee $MY_DIR/output.log
date >> output.log
