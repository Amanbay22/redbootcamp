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
    log.info("Seller {} announcement was sell {}",
        kafka.getSellerEmail(),
        kafka.getAnnouncementName());
  }

  @KafkaListener(id = "group-2", topics = "msg1")
  @SneakyThrows
  public void sendNotificationEmailBuyer(String msg) {
    MessageKafka kafka = objectMapper.readValue(msg, MessageKafka.class);
    log.info("Buyer {} your successfully by announcement  {}",
        kafka.getBuyerEmail(),
        kafka.getAnnouncementName());

  }

  @KafkaListener(id = "group-3", topics = "msg2")
  @SneakyThrows
  public void sendNotificationEmailOutBid(String msg) {
    MessageKafka kafka = objectMapper.readValue(msg, MessageKafka.class);
    log.info("{} bid has been outbid name: {}, price: {}",
        kafka.getBuyerEmail(),
        kafka.getAnnouncementName(),
        kafka.getPrice());
  }
}
