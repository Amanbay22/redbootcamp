package kz.yertayev.redbootcamp.model;

import java.util.HashSet;
import java.util.Set;
import kz.yertayev.redbootcamp.domain.entities.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private Long userId;
  private String email;
  private String password;
  private Set<Roles> roles = new HashSet<>();
}
