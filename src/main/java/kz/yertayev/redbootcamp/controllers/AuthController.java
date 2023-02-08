package kz.yertayev.redbootcamp.controllers;

import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import kz.yertayev.redbootcamp.model.auth.AuthenticationRequest;
import kz.yertayev.redbootcamp.model.auth.AuthenticationResponse;
import kz.yertayev.redbootcamp.model.error.ApiError;
import kz.yertayev.redbootcamp.services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final IAuthService authService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(authService.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(authService.authenticate(request));
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ApiError> handleContentNotAllowedException(EntityNotFoundException unfe) {
    List<String> errors = Collections.singletonList(unfe.getMessage());

    return new ResponseEntity<>(new ApiError(errors), HttpStatus.NO_CONTENT);
  }
}
