package com.oxford.cron;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 任务Job
 *
 * @author Chova
 * @date 2020/12/16
 */
public class ExecuteJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 执行打印时间的任务
        String printTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("Print Time:" + printTime + "ExecuteJob - " + new Random().nextInt(100));
    }
}
