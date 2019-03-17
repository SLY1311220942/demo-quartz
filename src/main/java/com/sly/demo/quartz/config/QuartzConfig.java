package com.sly.demo.quartz.config;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.sly.demo.quartz.job.QuartzTaskOne;
import com.sly.demo.quartz.job.QuartzTaskTwo;

/**
 * quartz配置类
 * 
 * @author sly
 * @time 2019年3月17日
 */
@Configuration
public class QuartzConfig {

	/**
	 * 配置任务一
	 * 
	 * @param quartzTask QuartzTask为需要执行的任务
	 * @return
	 */
	@Bean(name = "quartzJobOne")
	public MethodInvokingJobDetailFactoryBean detailFactoryBean(QuartzTaskOne quartzTaskOne) {
		MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
		// 是否并发执行
		jobDetail.setConcurrent(false);
		// 设置任务的名字
		jobDetail.setName("quartzJobOne");
		// 设置任务的分组，在多任务的时候使用
		jobDetail.setGroup("quartzJobGroup");
		// 需要执行的对象
		jobDetail.setTargetObject(quartzTaskOne);
		// 非常重要 执行QuartzTask类中的需要执行方法
		jobDetail.setTargetMethod("sayHello");
		return jobDetail;
	}

	/**
	 * 定时触发器一
	 * 
	 * @param quartzJobOne 任务
	 * @return
	 */
	@Bean(name = "jobOneTrigger")
	public CronTriggerFactoryBean cronJobTrigger(JobDetail quartzJobOne) {
		CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
		tigger.setJobDetail(quartzJobOne);
		// cron表达式，每1分钟执行一次
		tigger.setCronExpression("0 0/1 * * * ?");
		tigger.setName("jobOneTrigger");
		return tigger;
	}

	/**
	 * 定时任务二
	 * 
	 * @param quartzTaskTwo
	 * @return
	 * @author sly
	 * @time 2019年3月17日
	 */
	@Bean(name = "quartzJobTwo")
	public MethodInvokingJobDetailFactoryBean sayHelloJobDetail(QuartzTaskTwo quartzTaskTwo) {
		MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
		// 是否并发执行
		jobDetail.setConcurrent(false);
		// 设置任务的名字
		jobDetail.setName("quartzTaskTwo");
		// 设置任务的分组，在多任务的时候使用
		jobDetail.setGroup("quartzJobGroup");
		// 需要执行的对象
		jobDetail.setTargetObject(quartzTaskTwo);
		// 非常重要 执行QuartzTask类中的需要执行方法
		jobDetail.setTargetMethod("sayHello");
		return jobDetail;
	}

	/**
	 * 定时触发器二
	 * 
	 * @param quartzJobTwo 任务
	 * @return
	 */
	@Bean(name = "jobTwoTrigger")
	public CronTriggerFactoryBean sayHelloJobTrigger(JobDetail quartzJobTwo) {
		CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
		tigger.setJobDetail(quartzJobTwo);
		// cron表达式，每10秒执行一次
		tigger.setCronExpression("0/10 * * * * ?");
		tigger.setName("quartzJobTwo");
		return tigger;
	}

	/**
	 * 调度工厂
	 * 
	 * @param jobTrigger 触发器
	 * @return
	 */
	@Bean(name = "scheduler")
	public SchedulerFactoryBean schedulerFactory(Trigger jobOneTrigger, Trigger jobTwoTrigger) {
		SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
		// 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
		factoryBean.setOverwriteExistingJobs(true);
		// 延时启动，应用启动1秒后
		factoryBean.setStartupDelay(1);
		// 注册触发器
		factoryBean.setTriggers(jobOneTrigger, jobTwoTrigger);
		return factoryBean;
	}

}
