#!/bin/bash
pass=$1;
delete=$(rm /home/denis/IdeaProjects/server-management/src/main/resources/results/checkRootKit.txt);
command=$(echo "$pass" | sudo -S rkhunter -c --enable all --disable none --rwo >> /home/denis/IdeaProjects/server-management/src/main/resources/results/checkRootKit.txt);