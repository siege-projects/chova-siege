#!/bin/bash

version="1.0.1";

appName=$2
if [-z $appName];then
    appName='ls -t |grep .jar$ |head -nl'
fi

function start()
{
    count='ps -ef |grep java|grep $appName|wc -l'
    if [$count != 0];then
        echo "$appName is running, please check it..."
    else
        echo "The $appName is starting..."
        nohup java -jar ./$appName -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -Xms128M -Xmx512M | /usr/local/sbin/cronolog ./log/catalina-%Y-%m-%d.out >>/dev/null 2>&1 &
    fi
}

function stop()
{
    appId='ps -ef |grep java|grep $appName|awk '{print $2}''
    if [-z $appId];then
        echo "$appName is not running, please check it..."
    else
        echo "The $appName is stopping..."
        kill $appId
    fi
}

function restart()
{
    # 获取发布的版本
    releaseApp='ls -t |grep .jar$ |head -nl'

    # 获取最新的版本
    lastVersionApp='ls -t |grep .jar$ |head -n2 |tail -nl'

    appName=$lastVersionApp
    stop
    for i in {5..1}
    do
        echo -n "$i"
        sleep 1
    done
    echo 0

    backup

    appName=$releaseApp
    start
}

function backup()
{
    # 获取备份的版本
    backupApp='ls |grep -wv $releaseApp$ |grep .jar$'

    # 创建备份目录
    if [! -d "backup"];then
        mkdir backup
    fi

    # 备份
    for i in ${backupApp[@]}
    do
        echo "backup" $i
        mv $i backup
    done
}

function status()
{
    appId='ps -ef |grep java|grep $appName|awk '{print $2}''
    if [-z $appId];then
        echo -e "\033[31m Not running \033[0m"
    else
        echo -e "\033[32m Running [$appId] \033[0m"
    fi
}

function usage()
{
    echo "Usage: $0 {start|stop|restart|status|stop -f}"
    exit 1
}

case $1 in
    start)
    start;;

    stop)
    stop;;

    restart)
    restart;;

    *)
    usage;;
esac