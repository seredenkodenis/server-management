#!/bin/sh
cpuName=$(lscpu | grep name);
arch=$(arch);
mem=$(less /proc/meminfo | grep MemTotal);
distrVersion=$(uname -r);
nuclearVers=$(uname -a);
import=$(psql -U denis -d server -c "UPDATE system SET cpu_name='$cpuName', arch='$arch', memory='$mem', distr_version='$distrVersion', nuclear_vers='$nuclearVers' WHERE id=1");
