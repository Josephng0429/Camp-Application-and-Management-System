#!/bin/bash
set -e
javac *.java -d bin
java -classpath bin LoginApplication
