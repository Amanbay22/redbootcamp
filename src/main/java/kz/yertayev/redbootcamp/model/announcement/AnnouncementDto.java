package kz.yertayev.redbootcamp.model.announcement;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
  @NotBlank
  private String imageUrl;
  private String sellerEmail;
  private AnnouncementState state;
}
