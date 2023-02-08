package kz.yertayev.redbootcamp.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.yertayev.redbootcamp.model.message.MessageKafka;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventListenerService {

  private final ObjectMapper objectMapper;

  @KafkaListener(id = "group-1", topics = "msg")
  @SneakyThrows
  public void sendNotificationEmailSeller(String msg) {
    MessageKafka kafka = objectMapper.readValue(msg, MessageKafka.class);
    log.warn("Ypu announcement was sell {}", kafka.getAnnouncementName());
  }

  @KafkaListener(id = "group-2", topics = "msg1")
  @SneakyThrows
  public void sendNotificationEmailBuyer(String msg) {
    MessageKafka kafka = objectMapper.readValue(msg, MessageKafka.class);
    log.warn("Buyer your successfully by announcement  {}", kafka.getAnnouncementName());

  }
}
