package kz.yertayev.redbootcamp.services.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import kz.yertayev.redbootcamp.domain.entities.UserEntity;
import kz.yertayev.redbootcamp.domain.repositories.UserRepository;
import kz.yertayev.redbootcamp.model.UserDto;
import kz.yertayev.redbootcamp.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final ModelMapper modelMapper;

  @Override
  public UserDto register(UserDto userDto) {
    UserEntity userEntity = new UserEntity();
    UserEntity saveUser = userRepository.save(convertToEntity(userDto));
    log.info("User successfully registered: {}", saveUser);
    return convertToDto(userEntity);
  }

  @Override
  public List<UserDto> findAll() {
    return userRepository.findAll().stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
  }

  @Override
  public UserDto findByEmail(String email) {
    return convertToDto(userRepository.findByEmail(email)
        .orElseThrow(EntityNotFoundException::new));

  }

  @Override
  public UserDto findById(Long id) {
    return convertToDto(userRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new));
  }

  private UserDto convertToDto(UserEntity userEntity) {
    return modelMapper.map(userEntity, UserDto.class);
  }

  @SneakyThrows
  private UserEntity convertToEntity(UserDto userDto) {
    UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
    userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
    return userEntity;
  }

}
