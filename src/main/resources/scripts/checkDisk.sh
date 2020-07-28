#!/bin/sh
pass=$1;
partition=$2;
location=$3;
delete=$(rm $location);
testSmart=$(echo "$pass"| sudo -S smartctl -s on -a $partition | grep "Available");
if [ -z "$testSmart"]
then
    request=$(psql -U denis -d server -c "UPDATE disk SET health_test='can't use smartctl' WHERE id=1");
    command=$(echo "$pass" | sudo -S badblocks -v $partition > $location);
else
    command=$(echo "$pass" | sudo -S badblocks -sv $partition >> $location);
    checkSmart=$(echo "$pass" | sudo -S smartctl -H $partition | grep 'SMART overall-health self-assessment test result:');
    request=$(psql -U denis -d server -c "UPDATE disk SET health_test='$checkSmart' WHERE id=1");
fi
