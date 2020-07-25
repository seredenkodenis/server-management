#!/bin/bash
pass=$1;
command=$(echo "$pass" | sudo -S apt-get install rkhunter);