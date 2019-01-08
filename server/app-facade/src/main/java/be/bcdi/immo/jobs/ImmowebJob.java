package be.bcdi.immo.jobs;

import be.bcdi.immo.model.ImmoProperty;
import be.bcdi.immo.repositories.ImmoPropertyRepository;
import be.bcdi.immo.scrapers.ImmowebApi;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ImmowebJob implements Job {

    @Autowired
    private ImmowebApi immowebApi;

    @Autowired
    private ImmoPropertyRepository immoPropertyRepository;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            List<ImmoProperty> properties = immowebApi.go();
            this.immoPropertyRepository.saveAll(properties);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}