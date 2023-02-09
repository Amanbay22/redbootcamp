package kz.yertayev.redbootcamp.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

  @Bean
  public NewTopic topic() {
    return TopicBuilder.name("msg")
        .partitions(1)
        .replicas(1)
        .build();
  }

  @Bean
  public NewTopic topic1() {
    return TopicBuilder.name("msg1")
        .partitions(1)
        .replicas(1)
        .build();
  }

  @Bean
  public NewTopic topic2() {
    return TopicBuilder.name("msg2")
        .partitions(1)
        .replicas(1)
        .build();
  }
}
