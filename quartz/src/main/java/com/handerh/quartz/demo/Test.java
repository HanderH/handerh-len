package com.handerh.quartz.demo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.*;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.*;

/**
 * @author handerh
 * @date 2021/01/29
 */
public class Test {

    public static void main(String[] args) throws SchedulerException {
        //创建一个scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getContext().put("skey", "svalue");

        //创建一个Trigger
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .usingJobData("t1", "tv1")
                .withSchedule(simpleSchedule().withIntervalInSeconds(3)
                        .repeatForever()).build();
        trigger.getJobDataMap().put("t2", "tv2");

        //创建一个job
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .usingJobData("j1", "jv1")
                .withIdentity("myjob", "mygroup").build();
        job.getJobDataMap().put("j2", "jv2");

        //注册trigger并启动scheduler
        scheduler.scheduleJob(job,trigger);
        scheduler.start();


        trigger = (SimpleTrigger) newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(new Date())                     // some Date
                .forJob("job1", "group1")                 // identify job with name, group strings
                .build();
        trigger = newTrigger()
                .withIdentity("trigger3", "group1")
                .startAt(new Date())  // if a start time is not given (if this line were omitted), "now" is implied
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .withRepeatCount(10)) // note that 10 repeats will give a total of 11 firings
                .forJob(job) // identify job with handle to its JobDetail itself
                .build();

        trigger = (SimpleTrigger) newTrigger()
                .withIdentity("trigger5", "group1")
                .startAt(futureDate(5, DateBuilder.IntervalUnit.MINUTE)) // use DateBuilder to create a date in the future
                .forJob("myjob") // identify job with its JobKey
                .build();

        trigger = newTrigger()
                .withIdentity("trigger7", "group1")
                .withSchedule(simpleSchedule()
                        .withIntervalInMinutes(5)
                        .repeatForever())
                .endAt(dateOf(22, 0, 0))
                .build();

        trigger = newTrigger()
                .withIdentity("trigger8") // because group is not specified, "trigger8" will be in the default group
                .startAt(evenHourDate(null)) // get the next even-hour (minutes and seconds zero ("00:00"))
                .withSchedule(simpleSchedule()
                        .withIntervalInHours(2)
                        .repeatForever())
                // note that in this example, 'forJob(..)' is not called which is valid
                // if the trigger is passed to the scheduler along with the job
                .build();

        scheduler.scheduleJob(job,trigger);
    }
}
