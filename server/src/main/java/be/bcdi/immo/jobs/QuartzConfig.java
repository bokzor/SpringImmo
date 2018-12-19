package be.bcdi.immo.jobs;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

//@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob().ofType(ImmowebJob.class)
                .storeDurably()
                .build();
    }


    @Bean
    public Trigger trigger() {
        return TriggerBuilder.newTrigger().forJob(jobDetail())
                .withSchedule(simpleSchedule().repeatForever().withIntervalInHours(24))
                .build();
    }

}
