#!/bin/sh
delete=$(rm /home/denis/IdeaProjects/server-management/src/main/resources/results/failedServices.txt);
command=$(systemctl --type=service --state=failed >> /home/denis/IdeaProjects/server-management/src/main/resources/results/failedServices.txt);
