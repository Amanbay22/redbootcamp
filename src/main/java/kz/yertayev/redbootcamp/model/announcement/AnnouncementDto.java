package kz.yertayev.redbootcamp.model.announcement;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDto {

  private Long id;
  @NotBlank
  private String name;
  @NotBlank
  private String description;
  @Min(value = 100)
  private Double minPrice;
  private Double activePrice;
  @NotBlank
  private String imageUrl;
  private String userEmail;
  private AnnouncementState state;
}
