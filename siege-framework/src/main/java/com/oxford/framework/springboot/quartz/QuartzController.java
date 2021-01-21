package com.oxford.framework.springboot.quartz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 定时任务Quartz控制层Controller
 *
 * @author Chova
 * @date 2021/01/21
 */
@Tag(name = "定时任务")
@RestController
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    private Scheduler scheduler;

    /**
     * 创建定时任务
     *
     * @param no 根据单号启动定时任务
     * @return Object 启动成功信息
     * @throws Exception 启动任务失败抛出异常
     */
    @Operation(summary = "定时任务_创建")
    @ResponseBody
    @PostMapping("/start")
    public Object start(@RequestParam("no") String no) throws Exception {

        // 设置任务的开始时间为当前时间6s之后
        Date start = new Date(System.currentTimeMillis() + 6 * 1000);

        /*
         * 通过JobBuilder.newJob()方法获取到当前Job的具体实现,然后通过链式调用添加任务明细
         * 		- 固定的Job实现
         * 		- 如果是动态实现,根据不同的类来创建Job
         * 			- 比如((Job)Class.forName("com.oxford.job.DynamicJob").newInstance()).getClass()
         * 			- 则当前Job的具体实现:JobBuilder.newJob(((Job)Class.forName("com.oxford.job.DynamicJob")).getClass())
         */
        JobDetail jobDetail = JobBuilder.newJob(JobExecution.class)
                /*
                 * usingJobData可以以k-v的形式给当前JobDetail添加参数
                 * 可以通过链式调用usingJobData,传入多个参数
                 * 在Job实现类中,可以通过JobExecutionContext.getJobDetail().getJobDataMap().get("name")获取参数的值
                 */
                .usingJobData("name", "oxford")
                // 添加Job的认证信息
                .withIdentity(no)
                // 任务明细创建完成.执行生效
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                /*
                 * usingJobData可以以k-v的形式给当前JobDetail添加参数
                 * 可以通过链式调用usingJobData,传入多个参数
                 * 在Job实现类中,可以通过JobExecutionContext.getJobDetail().getJobDataMap().get("name")获取参数的值
                 */
                .usingJobData("no", no)
                // 添加Trigger的认证信息
                .withIdentity(no)
                // 开始执行时间
                .startAt(start)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        // 每隔2s执行一次
                        .withIntervalInSeconds(2)
                        // 执行6次
                        .withRepeatCount(6))
                // 触发器创建完成.执行生效
                .build();

        // 根据任务明细和设置的任务触发器添加定时任务
        scheduler.scheduleJob(jobDetail, trigger);
        if (!scheduler.isShutdown()) {
            scheduler.start();
        }

        System.out.println("定时任务已启动.");
        return "ok";
    }

    /**
     * 停止定时任务
     *
     * @param no 根据单号停止定时任务
     * @return Object 停止成功信息
     * @throws SchedulerException 停止任务失败抛出异常
     */
    @Operation(summary = "定时任务_停止")
    @PutMapping("/pause")
    @ResponseBody
    public Object shutDown(@RequestParam("no") String no) throws SchedulerException {
        // 根据触发器认证信息停止触发器任务
        scheduler.pauseTrigger(TriggerKey.triggerKey(no));

        System.out.println("定时任务已停止.");
        return "ok";
    }

    /**
     * 恢复停止的定时任务
     *
     * @param no 根据单号恢复已经停止的定时任务
     * @return Object 恢复成功信息
     * @throws SchedulerException 恢复停止的任务失败抛出异常
     */
    @Operation(summary = "定时任务_恢复")
    @PutMapping("/resume")
    @ResponseBody
    public Object resume(@RequestParam("no") String no) throws SchedulerException {
        // 根据触发器认证信息恢复停止的触发器任务
        scheduler.resumeTrigger(TriggerKey.triggerKey(no));

        System.out.println("停止的定时任务已恢复");
        return "ok";
    }

    /**
     * 删除定时任务
     *
     * @param no 根据单号删除定时任务
     * @return Object 删除成功信息
     * @throws SchedulerException 删除任务失败抛出异常
     */
    @Operation(summary = "定时任务_删除")
    @DeleteMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam("no") String no) throws SchedulerException {
        // 先对触发器任务进行暂停
        scheduler.pauseTrigger(TriggerKey.triggerKey(no));
        // 然后移除触发器任务
        scheduler.unscheduleJob(TriggerKey.triggerKey(no));
        // 最后删除定时任务
        scheduler.deleteJob(JobKey.jobKey(no));

        System.out.println("定时任务已删除");
        return "ok";
    }
}
