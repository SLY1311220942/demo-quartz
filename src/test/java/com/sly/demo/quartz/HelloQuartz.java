package com.sly.demo.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * job类
 * @author sly
 * @time 2019年3月17日
 */
public class HelloQuartz implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("HelloQuartz");

	}

}
