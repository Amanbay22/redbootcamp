package kz.yertayev.redbootcamp.services.impl;

import java.util.Set;
import kz.yertayev.redbootcamp.configuration.security.JwtService;
import kz.yertayev.redbootcamp.domain.entities.Roles;
import kz.yertayev.redbootcamp.domain.entities.UserEntity;
import kz.yertayev.redbootcamp.domain.repositories.UserRepository;
import kz.yertayev.redbootcamp.model.auth.AuthenticationRequest;
import kz.yertayev.redbootcamp.model.auth.AuthenticationResponse;
import kz.yertayev.redbootcamp.services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(AuthenticationRequest request) {
    UserEntity user = UserEntity.builder()
        .email(request.getEmail())
        .password(request.getPassword())
        .roles(Set.of(Roles.ROLE_USER))
        .build();

    repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
}
