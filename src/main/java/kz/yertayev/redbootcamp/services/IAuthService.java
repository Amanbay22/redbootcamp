package kz.yertayev.redbootcamp.services;

import kz.yertayev.redbootcamp.model.auth.AuthenticationRequest;
import kz.yertayev.redbootcamp.model.auth.AuthenticationResponse;

public interface IAuthService {
  AuthenticationResponse register(AuthenticationRequest request);

  AuthenticationResponse authenticate(AuthenticationRequest request);
}
