package kz.yertayev.redbootcamp.model.error;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BidException extends RuntimeException {

  public BidException(String message) {
    super(message);
  }
}
