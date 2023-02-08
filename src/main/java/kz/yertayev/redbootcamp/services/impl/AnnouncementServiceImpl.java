package kz.yertayev.redbootcamp.services.impl;

import java.util.Set;
import java.util.stream.Collectors;
import kz.yertayev.redbootcamp.domain.entities.AnnouncementEntity;
import kz.yertayev.redbootcamp.domain.entities.UserEntity;
import kz.yertayev.redbootcamp.domain.repositories.AnnouncementRepository;
import kz.yertayev.redbootcamp.model.announcement.AnnouncementDto;
import kz.yertayev.redbootcamp.model.announcement.AnnouncementState;
import kz.yertayev.redbootcamp.services.IAnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
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
    return announcementRepository.findAll().stream()
        .map(this::convertToDto)
        .collect(Collectors.toSet());
  }

  @Override
  public void takeBid(Long id, double price) {

  }

  @Override
  public void takeOffAnnouncement(Long id) {

  }

  private AnnouncementDto convertToDto(AnnouncementEntity entity) {


    return modelMapper.map(entity, AnnouncementDto.class);
  }

  @SneakyThrows
  private AnnouncementEntity convertToEntity(AnnouncementDto dto) {
    AnnouncementEntity entity = modelMapper.map(dto, AnnouncementEntity.class);
    UserEntity principal = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    entity.setUserEmail(principal.getEmail());
    return entity;
  }
}
