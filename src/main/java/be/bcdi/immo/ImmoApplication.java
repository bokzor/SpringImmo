package be.bcdi.immo;

import be.bcdi.immo.scrapers.ImmowebApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class ImmoApplication {

    @Autowired
    ImmowebApi immowebApi;

    public static void main(String[] args) {
        SpringApplication.run(ImmoApplication.class, args);
    }

    @PostConstruct
    void postConstruct() throws IOException {
        this.immowebApi.go();
    }

}
