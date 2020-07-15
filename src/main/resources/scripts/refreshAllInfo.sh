#!/bin/sh
pide=$1
name=$2
mem=$(ps -p $pide -o %mem= );
cpu=$(ps -p $pide -o %cpu= );
user=$(ps -p $pide -o user= );
time=$(ps -p $pide -o etime= );
vsz=$(ps -p $pide -o vsz= );
push=$(psql -U denis -d server -c "UPDATE proc SET uptime='$uptime', mem='$mem', cpu='$cpu', author='$user', vsz='$vsz'");
