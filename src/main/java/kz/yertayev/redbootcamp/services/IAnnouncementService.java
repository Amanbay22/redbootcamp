package kz.yertayev.redbootcamp.services;

import java.util.Set;
import kz.yertayev.redbootcamp.model.announcement.AnnouncementDto;
import kz.yertayev.redbootcamp.model.bid.Bid;

public interface IAnnouncementService {
  void createAnnouncement(AnnouncementDto dto);

  Set<AnnouncementDto> getAnnouncements();

  Set<AnnouncementDto> getAnnouncementsByFilter(
      String sellerEmail,
      Double minPrice
  );

  void takeBid(Bid bid);
}
