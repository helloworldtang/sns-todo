#!/usr/bin/env bash
#20 3 * * * /opt/tool/backup_data.sh >>/opt/tool/logs/backup_data.log 2>&1
DIR=/home/backup
FILE_NAME=`date +%y%m%d%H`
FILE_NAME=${DIR}/front_end_${FILE_NAME}.tar.gz
echo start backup to ${FILE_NAME} at `date`
cd /opt/project
tar zcf  ${FILE_NAME} todoList/
echo finish backup at `date`
find /opt/backup/ -mtime +30 -type f
find /opt/backup/ -mtime +30 -type f |xargs rm -f