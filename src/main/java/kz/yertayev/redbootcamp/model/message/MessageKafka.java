package kz.yertayev.redbootcamp.model.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageKafka {
  private String announcementName;
  private Double price;
  private String sellerEmail;
  private String buyerEmail;
}
