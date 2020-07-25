#!/bin/sh
pass=$1;
partitions=$(ls /dev/sd*[a-z]);
delete=$(rm /home/denis/IdeaProjects/server-management/src/main/resources/results/diskReport.txt);
command=$(echo "$pass" | sudo -S badblocks -sv $partitions >> /home/denis/IdeaProjects/server-management/src/main/resources/results/diskReport.txt);
checkSmart=$(echo "$pass" | sudo -S smartctl -H $partitions | grep 'SMART overall-health self-assessment test result:');
request=$(psql -U denis -d server -c "UPDATE disk SET health_test='$checkSmart' WHERE id=1");
