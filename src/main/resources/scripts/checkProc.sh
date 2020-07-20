#!/bin/sh
pid=$1
email=$2
status=$(ps -p $pid -o etime=);
if [ -z "$status"]
then
    send=$(psql -U denis -d server -c "UPDATE proc SET email='send' WHERE pid=$pid;");
fi
