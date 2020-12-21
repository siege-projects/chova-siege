package com.oxford.cron;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 任务调度器Scheduler
 *
 * @author Chova
 * @date 2020-12-16
 */
public class ExecuteScheduler {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // 创建任务调度器Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        // 创建JobDetail实例，并与指定的任务Job绑定
        JobDetail jobDetail = JobBuilder.newJob(ExecuteJob.class)
                .withIdentity("job")
                .build();

        // 创建触发器实例，每隔1s执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger")
                .startNow()
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(1)
                        .withRepeatCount(10))
                .build();

        // 执行任务
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("============================任务执行================================");
        scheduler.start();

        // 关闭任务
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println("============================任务结束================================");

    }
}
