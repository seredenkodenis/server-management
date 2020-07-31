#!/bin/sh
sudo su - postgres <<EOF
pg_dumpall > postgres_db.bak
exit