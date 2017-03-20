#!/usr/bin/env bash

echo "Hello World!"
echo "PID: $$"
echo "PPID: $PPID"
echo "UID: $UID"

BASEDIR=$(cd `dirname $0`; cd ..; pwd)

echo $BASEDIR

PIDFILE="current.pid"

if [ ! -f "$PIDFILE" ]; then
    echo "service not running...exit"
    exit 0
fi

PID=`cat $PIDFILE`

echo "Stopping service..."

kill -9 $PID

rm $PIDFILE

echo "Stop success"
