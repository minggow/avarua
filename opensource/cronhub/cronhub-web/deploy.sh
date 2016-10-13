#!/bin/bash
#pid_file=webapp.pid
#kill -9 `cat $pid_file`
mvn clean -Ptest -Djetty.port=8078 -Djetty.scanIntervalSeconds=0 jetty:run >> /ROOT/logs/cronhub_8078/cronhub_8078.log 2>&1
#echo $!>${pid_file}
echo "webapp running on pid $pid"
