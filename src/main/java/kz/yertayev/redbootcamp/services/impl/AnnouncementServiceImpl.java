package kz.yertayev.redbootcamp.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityExistsException;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import kz.yertayev.redbootcamp.domain.entities.AnnouncementEntity;
import kz.yertayev.redbootcamp.domain.entities.UserEntity;
import kz.yertayev.redbootcamp.domain.repositories.AnnouncementRepository;
import kz.yertayev.redbootcamp.model.announcement.AnnouncementDto;
import kz.yertayev.redbootcamp.model.announcement.AnnouncementState;
import kz.yertayev.redbootcamp.model.bid.Bid;
import kz.yertayev.redbootcamp.model.message.MessageKafka;
import kz.yertayev.redbootcamp.services.IAnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements IAnnouncementService {

  private final AnnouncementRepository announcementRepository;
  private final ModelMapper modelMapper;


  @Override
  @Transactional
  public void createAnnouncement(AnnouncementDto dto) {
    dto.setState(AnnouncementState.ACTIVE);
    announcementRepository.save(convertToEntity(dto));
  }

  @Override
  public Set<AnnouncementDto> getAnnouncements() {
    return announcementRepository.findAllActive().stream()
        .map(this::convertToDto)
        .collect(Collectors.toSet());
  }

  @Override
  @SneakyThrows
  @Transactional(isolation = Isolation.READ_COMMITTED)
  public void takeBid(Bid bid) {
    AnnouncementEntity entity = announcementRepository.findById(bid.getAnnouncementId())
        .orElseThrow(EntityExistsException::new);
    entity.setActivePrice(bid.getPrice());
    entity.setExpiredDate(LocalDateTime.now().plusMinutes(1));
  }

  private AnnouncementDto convertToDto(AnnouncementEntity entity) {


    return modelMapper.map(entity, AnnouncementDto.class);
  }

  @SneakyThrows
  private AnnouncementEntity convertToEntity(AnnouncementDto dto) {
    AnnouncementEntity entity = modelMapper.map(dto, AnnouncementEntity.class);
    UserEntity principal = (UserEntity) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();

    entity.setSellerEmail(principal.getEmail());
    return entity;
  }
}
