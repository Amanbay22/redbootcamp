package kz.yertayev.redbootcamp.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import kz.yertayev.redbootcamp.model.announcement.AnnouncementState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "announcements")
@NoArgsConstructor
public class AnnouncementEntity extends AbstractEntity {
  @NotNull
  private String name;
  @NotBlank
  private String description;
  @Min(value = 100)
  private Double minPrice;
  private Double activePrice;
  @NotNull
  private String imageUrl;
  private String sellerEmail;
  private String buyerEmail;
  private LocalDateTime expiredDate;
  @NotNull
  @Enumerated(EnumType.STRING)
  private AnnouncementState state;
}
