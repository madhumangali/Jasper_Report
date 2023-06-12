package com.jasper_report.config;

import lombok.Data;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

@Data
public class JobCompletionListener extends JobExecutionListenerSupport {

    long max;

    long min;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
        }
        jobExecution.getJobId().toString();

//        Reader.max=jobExecution.getJobParameters().getLong("max");
//        Reader.min=jobExecution.getJobParameters().getLong("min");
//        new Writer(jobExecution.getJobParameters().getLong("min"),jobExecution.getJobParameters().getLong("max"),jobExecution.getJobId().toString());
//        System.out.println(max=jobExecution.getJobParameters().getLong("max"));
//        System.out.println(min=jobExecution.getJobParameters().getLong("min"));
    }

}