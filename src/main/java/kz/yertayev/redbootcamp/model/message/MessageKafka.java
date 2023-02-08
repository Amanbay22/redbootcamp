package kz.yertayev.redbootcamp.model.message;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageKafka {
  private String announcementName;
  private String msg;
}
