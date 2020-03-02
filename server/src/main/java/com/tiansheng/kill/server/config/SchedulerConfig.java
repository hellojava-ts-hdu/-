package com.tiansheng.kill.server.config;/**
 * Created by Administrator on 2019/6/29.
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * @作者 itsheng
 * @GitHub https://github.com/hellojava-ts-hdu
 * @博客 http://itsheng.top:8082/
 * @时间  2020/3/2  22:10
定时任务多线程处理的通用化配置
 */

@Configuration
public class SchedulerConfig implements SchedulingConfigurer{

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
    }
}