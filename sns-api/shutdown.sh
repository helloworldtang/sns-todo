#!/usr/bin/env bash
AppName="sns-api-1.0.0"
if ps -ef|grep $AppName|grep -v grep >/dev/null 2>&1 ; then
   ps -ef|grep $AppName|grep -v grep|awk '{print $2}'|xargs kill -9 >/dev/null 2>&1
   echo "success to shutdown  "$AppName
else
   echo " not exist! do nothing.  "
fi
