#!/bin/bash
MY_DIR="/home/charlie/github/bingAutomation-eclipse-workspace/bingAutomation/executable-jar"
cd $MY_DIR
java -jar bingSearch.jar | tee $MY_DIR/output.log
date >> output.log
