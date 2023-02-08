package kz.yertayev.redbootcamp.services.impl;

import java.util.Set;
import kz.yertayev.redbootcamp.domain.entities.AnnouncementEntity;
import kz.yertayev.redbootcamp.domain.entities.UserEntity;
import kz.yertayev.redbootcamp.domain.repositories.AnnouncementRepository;
import kz.yertayev.redbootcamp.model.UserDto;
import kz.yertayev.redbootcamp.model.announcement.AnnouncementDto;
import kz.yertayev.redbootcamp.model.announcement.AnnouncementState;
import kz.yertayev.redbootcamp.services.IAnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements IAnnouncementService {

  private final AnnouncementRepository announcementRepository;
  private final ModelMapper modelMapper;
  
  @Override
  public void createAnnouncement(AnnouncementDto dto) {
    dto.setState(AnnouncementState.ACTIVE);
    announcementRepository.save(convertToEntity(dto));
  }

  @Override
  public Set<AnnouncementDto> getAnnouncements() {
    return null;
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
