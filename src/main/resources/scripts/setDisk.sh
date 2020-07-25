#!/bin/sh
pass=$1;
allPartitions=$(ls /dev/sd*);
partitions=$(ls /dev/sd*[a-z]);
device=$(echo "$pass" | sudo -S smartctl -s on -a $partitions | grep 'Device Model:');
capacity=$(echo "$pass" | sudo -S smartctl -s on -a $partitions | grep 'User Capacity:');
serial=$(echo "$pass" | sudo -S smartctl -s on -a $partitions | grep 'Serial Number:');
healthTest=$(echo "$pass" | sudo -S smartctl -s on -a $partitions | grep 'SMART overall-health self-assessment test result:');
requestToDb=$(psql -U denis -d server -c "INSERT INTO disk(id,device,serial_number,capacity,name_of_disk,partitions,health_test) values (1,'$device','$serial','$capacity','$patitions','$allPartitions','$healthTest')");
