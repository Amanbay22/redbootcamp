package kz.yertayev.redbootcamp.domain.repositories;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.Set;
import kz.yertayev.redbootcamp.domain.entities.AnnouncementEntity;
import kz.yertayev.redbootcamp.model.announcement.AnnouncementState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long> {
  @Query("select a from AnnouncementEntity a "
      + "where a.state = :state and a.expiredDate < :time")
  Set<AnnouncementEntity> findAllByStateAndExpiredDate(
      AnnouncementState state,
      LocalDateTime time);

  @Query("select a from AnnouncementEntity a where a.state = 'ACTIVE'")
  Set<AnnouncementEntity> findAllActive();

  @Query("select a from AnnouncementEntity a "
      + "where a.state = 'ACTIVE'"
      + "and ((:sellerEmail is null) or a.sellerEmail = :sellerEmail)"
      + "and ((:minPrice is null) or a.minPrice >= :minPrice)")
  Set<AnnouncementEntity> findAllActiveByFilter(
      String sellerEmail,
      Double minPrice
  );
}
