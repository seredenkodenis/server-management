#!/bin/sh
name=$1
pass=$2
stop=$(echo "$pass" |sudo -S systemctl stop $name);
