#!/bin/bash

# class_name=$1
dir=$1

echo "work dir is $dir"
# echo "class name is $class_name"

if test "$dir" != ""
  then
    cd "$dir" || exit 1
    echo "cd $dir"
fi

echo "current in $PWD"

find . -name "*.java" > "./sources.txt"

javac -d . @sources.txt

jar cvf "spark-udf.jar" "ink"
