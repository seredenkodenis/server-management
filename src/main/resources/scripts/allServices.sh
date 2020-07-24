#!/bin/sh
delete=$(rm /home/denis/IdeaProjects/server-management/src/main/resources/results/allServices.txt);
command=$(service --status-all >> /home/denis/IdeaProjects/server-management/src/main/resources/results/allServices.txt);
