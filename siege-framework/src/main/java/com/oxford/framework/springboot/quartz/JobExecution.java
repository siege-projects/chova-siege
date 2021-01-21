package com.oxford.framework.springboot.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * 任务Job执行的业务逻辑
 * - DisallowConcurrentExecution注解使用一个数据库锁防止并发执行时数据被多次处理
 *
 * @author Chova
 * @date 2021/01/21
 */
@DisallowConcurrentExecution
public class JobExecution implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("任务明细:" + context.getJobDetail().getJobDataMap().get("name"));
        System.out.println(new Date() + "定时任务执行中...");
    }
}
