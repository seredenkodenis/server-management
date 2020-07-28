#!/bin/sh
lcoation=$1
delete=$(rm $lcoation);
command=$(service --status-all >> $lcoation);
