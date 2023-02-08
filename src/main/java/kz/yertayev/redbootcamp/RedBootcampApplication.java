package kz.yertayev.redbootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("kz.yertayev.redbootcamp")
public class RedBootcampApplication {

  public static void main(String[] args) {
    SpringApplication.run(RedBootcampApplication.class, args);
  }

}
