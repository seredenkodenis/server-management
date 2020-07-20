#!/bin/sh
$service=$1
status=$(echo "seredenko302003" | sudo -S systemctl status $service | grep "\<active\>");
if [ -z "$status"]
then
    send=$(psql -U denis -d server -c "UPDATE service SET email='send' WHERE name='$service';");
fi
