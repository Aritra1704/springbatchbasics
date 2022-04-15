package io.arpaul.ytspringbatch.controllers;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/load")
public class LoadController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @GetMapping
    public BatchStatus loadUsers() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));

        JobParameters parameters = new JobParameters();
        Calendar calendar = Calendar.getInstance();
        long startTime = calendar.getTimeInMillis();
        System.out.println("Batch started");
        JobExecution jobExecution = jobLauncher.run(job, parameters);
        jobExecution.getStepExecutions().stream().map(stepExecution -> {
            System.out.println("StepExecution: "+stepExecution.getStepName());
            return stepExecution;
        });

        System.out.println("JobExecution: "+jobExecution.getStatus());
        long endTime = calendar.getTimeInMillis();

        System.out.println("Batch is running..");
        while(jobExecution.isRunning()) {
            System.out.println("...");
        }
        System.out.println("Batch running complete in: "+(endTime - startTime));
        return jobExecution.getStatus();
    }
}
