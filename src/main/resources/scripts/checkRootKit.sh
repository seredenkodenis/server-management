#!/bin/bash
pass=$1;
loc=$2;
delete=$(rm $loc);
command=$(echo "$pass" | sudo -S rkhunter -c --enable all --disable none --rwo >> $loc);
