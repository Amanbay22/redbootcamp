package kz.yertayev.redbootcamp.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Set;
import kz.yertayev.redbootcamp.domain.entities.AnnouncementEntity;
import kz.yertayev.redbootcamp.domain.repositories.AnnouncementRepository;
import kz.yertayev.redbootcamp.model.announcement.AnnouncementState;
import kz.yertayev.redbootcamp.model.message.MessageKafka;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerService {

  private final JdbcTemplate jdbcTemplate;
  private final AnnouncementRepository announcementRepository;
  private final KafkaTemplate<Integer, String> template;
  private final ObjectMapper objectMapper;

  @Scheduled(fixedDelayString = "30000")
  @Transactional
  @SneakyThrows
  public void expireOrders() {
    var now = LocalDateTime.now();

    var announcements =
        announcementRepository.findAllByStateAndExpiredDate(AnnouncementState.ACTIVE, now);

    for (AnnouncementEntity an : announcements) {
      MessageKafka kafka = MessageKafka.builder()
          .announcementName(an.getName())
          .price(an.getActivePrice())
          .sellerEmail(an.getSellerEmail())
          .buyerEmail(an.getBuyerEmail())
          .build();

      template.send("msg", objectMapper.writeValueAsString(kafka));
      template.send("msg1", objectMapper.writeValueAsString(kafka));

      an.setState(AnnouncementState.ARCHIVE);
    }

  }
}

