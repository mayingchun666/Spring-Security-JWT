package com.myc.boot.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 当前时间  年月日 时分秒
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        System.out.println(format + "++++++hello world");
    }
}
