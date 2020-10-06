# SpringBoot项目的Shell脚本
# 包括项目的启动，停止，重启以及查看项目的状态
version="5.2.1";

appName=$2
if [-z $appName];then
    appName='ls -t |grep .jar$ |head -n1'
fi

# 后台启动项目，自动生成catalina.log日志文件
function start()
{
    count='ps -ef |grep java|grep $appName|wc -l'
    if [count != 0];then
        echo "The $appName is running, please check it..."
    else
        echo "The $appName is starting..."
    fi
}

# 停止项目
function stop()
{
    appId='ps -ef |grep java|grep $appName|awk '{print $2}''
    if [-z $appId];then
        echo "The $appName is not running, please check it..."
    else
        echo "The $appName is stopping..."
        kill $appId
    fi
}

# 重启项目
function restart()
{
    # 获取预发布的项目版本
    releaseApp='ls -t |grep .jar$ |head n1'
    # 获取最新的项目版本
    lastVersionApp='ls -t |grep .jar$ |head n2 |tail -n1'

    appName=$lastVersionApp
    stop

    for i in {5..1}
    do
        echo -n '$1'
        sleep 1
    done
    echo 0

    backup

    appName=$releaseApp
    start
}
function backup()
{
    # 获取备份的项目版本
    backupApp='ls |grep -wv $releaseApp$ |grep .jar$'
    # 创建备份的目录
    if [-d "backup"];then
        mkdir backup
    fi

    # 执行备份
    for i in ${backupApp[@]}
    do
        echo "backup" $i
        mv $i backup
    done
}

# 查看项目状态
function status()
{
    appId='ps -ef |grep java|grep $appName|awk '{print $2}''
    if [-z $appId];then
        echo -e "\033[31m Not running \033[0m"
    else
        echo -e "\033[32m Running [$appId] \033[0m"
    fi
}

# 使用介绍
function usage()
{
    echo "Usage $0 {start|stop|restart|status|stop -f}"
    echo "Example: $0 start"
    exit 1
}

case $1 in
    start)
    start;;

    stop)
    stop;;

    restart)
    restart;;

    status)
    status;;

    *)
    usage;;
esac