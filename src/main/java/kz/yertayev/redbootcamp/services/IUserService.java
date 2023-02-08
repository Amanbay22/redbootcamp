package kz.yertayev.redbootcamp.services;

import java.util.List;
import kz.yertayev.redbootcamp.model.UserDto;

public interface IUserService {
  UserDto register(UserDto user);

  List<UserDto> findAll();

  UserDto findByEmail(String email);

  UserDto findById(Long id);
}
