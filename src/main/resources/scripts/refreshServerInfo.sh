#!/bin/sh
uptime=$(uptime);
lastReboot=$(who -b);
lastShutdown=$(last shutdown);
avMemory=$(less /proc/meminfo | grep MemFree);
query=$(psql -U denis -d server -c "UPDATE system SET uptime='$uptime', reboot='$lastReboot', shutdown='$lastShutdown',av_memory='$avMemory' WHERE id=1");
