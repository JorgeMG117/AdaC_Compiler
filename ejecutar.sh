#!/bin/bash

rm pruebas/*

ant

directorio="doc/tests"

for i in $(ls $directorio)
do

    java -jar dist/adac_4.jar ${directorio}"/"${i} > pruebas/"${i%.*}".pcode
done

scp -r pruebas a801369@hendrix.cps.unizar.es:.