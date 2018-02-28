#!/bin/sh
./gradlew build -x findbugsMain -x findbugsTest -x test deploy
