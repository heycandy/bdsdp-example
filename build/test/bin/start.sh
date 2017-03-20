#!/usr/bin/env bash

echo "Hello World!"
echo "PID: $$"
echo "PPID: $PPID"
echo "UID: $UID"

BASEDIR=$(cd `dirname $0`; cd ..; pwd)

echo $BASEDIR
echo "Starting service..."

LOGFILE="nohup.out"

nohup java -cp .;$BASEDIR/lib/*;$BASEDIR/conf she.candyliu.demo.Main > $LOGFILE 2>&1 &

PIDFILE="current.pid"

echo $! > $PIDFILE
echo "Start success"

