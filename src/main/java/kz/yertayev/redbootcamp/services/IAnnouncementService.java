package kz.yertayev.redbootcamp.services;

import java.util.Set;
import kz.yertayev.redbootcamp.model.announcement.AnnouncementDto;

public interface IAnnouncementService {
  void createAnnouncement(AnnouncementDto dto);
  Set<AnnouncementDto> getAnnouncements();
  void takeOffAnnouncement(Long id);
}
