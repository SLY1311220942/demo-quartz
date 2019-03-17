package com.sly.demo.quartz.job;

import org.springframework.stereotype.Component;

/**
 * 定时任务
 * @author sly
 * @time 2019年3月17日
 */
@Component
public class QuartzTaskTwo {
	
	public void sayHello() {
		System.out.println("hello this is job two!");
	}
}
