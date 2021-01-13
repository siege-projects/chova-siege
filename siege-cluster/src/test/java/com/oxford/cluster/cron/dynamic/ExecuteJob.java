package com.oxford.cluster.cron.dynamic;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 动态任务Job
 *
 * @author Chova
 * @date 2020/12/20
 */
public class ExecuteJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getMergedJobDataMap();
        System.out.println("Job:" + data.getString("job"));
    }
}
