package be.bcdi.immo.jobs;

import be.bcdi.immo.scrapers.ImmowebApi;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ImmowebJob implements Job {
 
    @Autowired
    private ImmowebApi immowebApi;
 
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            immowebApi.go();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}