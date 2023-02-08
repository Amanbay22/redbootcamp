package kz.yertayev.redbootcamp.domain.repositories;

import java.util.Optional;
import kz.yertayev.redbootcamp.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findByEmail(String email);
}
