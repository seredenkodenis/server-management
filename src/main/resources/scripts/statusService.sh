#!/bin/sh
name=$1
pass=$2
status=$(echo "$pass" |sudo -S systemctl status $name | grep Active);
command=$(psql -U denis -d server -c "UPDATE service SET status='$status'");
