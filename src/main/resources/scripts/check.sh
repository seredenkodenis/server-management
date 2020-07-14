#!/bin/sh
pider=$1
name=$2
time=$(ps -p $pider -o etime=);
status=$(ps -p $pider -o stat=);
text=$(psql -U denis -d server -c "UPDATE proc SET uptime='$time' WHERE pid=$pider;");
echo $time.
echo $status.
echo $text
