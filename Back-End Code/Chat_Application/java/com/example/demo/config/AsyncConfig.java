package com.example.demo.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class AsyncConfig {
	
	@Bean(name="CollabTaskExecution")
	public Executor collabTaskExecutor() {
		ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
		
		exec.setCorePoolSize(5);
		exec.setMaxPoolSize(20);
		exec.setQueueCapacity(100);
		exec.setThreadNamePrefix("collab-");
		exec.initialize();
		return exec;
	}

}
