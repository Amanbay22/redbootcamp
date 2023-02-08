package kz.yertayev.redbootcamp.domain.repositories;

import kz.yertayev.redbootcamp.domain.entities.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long> {

}
