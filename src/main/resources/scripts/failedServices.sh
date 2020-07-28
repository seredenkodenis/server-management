#!/bin/sh
loc=$2;
delete=$(rm $loc);
command=$(systemctl --type=service --state=failed >> $loc);
