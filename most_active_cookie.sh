#!/bin/bash

# Exit immediately if any command fails
set -e

# Compile the Java source (only if class file doesn't exist)
if [ ! -f src/Solution.class ]; then
  javac src/Solution.java
fi

# Run the program
java -cp src Solution "$@"