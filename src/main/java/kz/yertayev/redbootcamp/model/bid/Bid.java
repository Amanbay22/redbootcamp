package kz.yertayev.redbootcamp.model.bid;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Bid {
  @NotBlank
  private Long announcementId;
  @NotBlank
  private Double price;
}
