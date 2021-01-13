package com.oxford.cluster.cron.dynamic;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 动态任务调度器Scheduler
 *
 * @author Chova
 * @date 2020/12/20
 */
public class ExecuteScheduler {
    public static void main(String[] args) throws SchedulerException {
        // 创建任务调度器
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        // 创建指定的JobDetail实例，并与指定的Job相绑定
        JobDetail jobDetail = JobBuilder.newJob(ExecuteJob.class)
                .withIdentity("job")
                .build();

        // 创建触发器实例， 每隔1s执行一次，执行10次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger")
                .startNow()
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(1)
                        .withRepeatCount(10))
                .build();

        // 将任务添加到触发器中
        scheduler.scheduleJob(jobDetail, trigger);

        // 更新触发器
        System.out.println("============================更新触发器================================");
        TriggerKey triggerKey = TriggerKey.triggerKey("newTrigger");
        scheduler.addJob(jobDetail, true);
        scheduler.rescheduleJob(triggerKey, trigger);

        // 暂停任务
        System.out.println("============================暂停任务================================");
        JobKey pauseJobKey = JobKey.jobKey("job");
        scheduler.pauseJob(pauseJobKey);

        // 恢复任务
        System.out.println("============================恢复任务================================");
        JobKey resumeJobKey = JobKey.jobKey("job");
        scheduler.resumeJob(resumeJobKey);

        // 立即执行任务
        System.out.println("============================立即执行任务================================");
        JobKey triggerJobKey = JobKey.jobKey("job");
        scheduler.triggerJob(triggerJobKey);

        // 删除任务
        System.out.println("============================删除任务================================");
        JobKey deleteJobKey = JobKey.jobKey("job");
        scheduler.deleteJob(deleteJobKey);
    }
}
