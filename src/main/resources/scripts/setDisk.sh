#!/bin/sh
pass=$1;
partition=$2;
testSmart=$(echo "$pass" | sudo -S smartctl -s on -a $partition | grep "Available");
if [ -z "$testSmart"]
then
    device=$(echo "$pass" | sudo -S smartctl -s on -a $partition | grep 'Product:');
    capacity=$(echo "$pass" | sudo -S smartctl -s on -a $partition | grep 'User Capacity:');
    serial=$(echo "$pass" |sudo -S udevadm info --query=all --name=$partition | grep 'ID_SERIAL');
    not=$(echo "we can not do this");
    requestToDb=$(psql -U denis -d server -c "INSERT INTO disk(id,device,serial_number,capacity,partitions,health_test) values (1,'$device','$serial','$capacity','$patition','$not');");

else
device=$(echo "$pass" | sudo -S smartctl -s on -a $partition | grep 'Device Model:');
        capacity=$(echo "$pass" | sudo -S smartctl -s on -a $partition | grep 'User Capacity:');
        serial=$(echo "$pass" | sudo -S smartctl -s on -a $partition | grep 'Serial Number:');
        healthTest=$(echo "$pass" | sudo -S smartctl -s on -a $partition | grep 'SMART overall-health self-assessment test result:');
        requestToDb=$(psql -U denis -d server -c "INSERT INTO disk(id,device,serial_number,capacity,partitions,health_test) values (1,'$device','$serial','$capacity','$patition','$healthTest')");

fi
