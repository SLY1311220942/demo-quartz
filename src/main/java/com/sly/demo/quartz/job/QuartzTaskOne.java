package com.sly.demo.quartz.job;

import org.springframework.stereotype.Service;

/**
 * 定时任务
 * @author sly
 * @time 2019年3月17日
 */
@Service
public class QuartzTaskOne {
	
	public void sayHello() {
		System.out.println("hello this is job one!");
	}

}
