#!/usr/bin/env bash
set -e

function main {
    case $1 in
        "ct" )
	contract;;
	"cs" )
	coffee-service;;
    esac
}

function contract {
    cd ./coffee-api-challenge
    bundle exec rake pacto:validate['http://localhost:4567','contracts']
    cd -
}

function coffee-service {
    cd ./coffee-service
    ./gradlew bootrun
}

main $@
exit 0
