package kz.yertayev.redbootcamp.controllers.api.cabinet;

import jakarta.persistence.EntityNotFoundException;
import java.security.SignatureException;
import java.util.Collections;
import java.util.List;
import kz.yertayev.redbootcamp.model.announcement.AnnouncementDto;
import kz.yertayev.redbootcamp.model.error.ApiError;
import kz.yertayev.redbootcamp.services.IAnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/announcement")
public class AnnouncementController {
  private final IAnnouncementService announcementService;

  @PreAuthorize("hasAuthority('ROLE_USER')")
  @PostMapping("/")
  public ResponseEntity<Void> create(@RequestBody AnnouncementDto dto) {
    announcementService.createAnnouncement(dto);
    return ResponseEntity.ok().build();
  }

  @ExceptionHandler(SignatureException.class)
  public ResponseEntity<ApiError> handleContentNotAllowedException(SignatureException unfe) {
    List<String> errors = Collections.singletonList(unfe.getMessage());

    return new ResponseEntity<>(new ApiError(errors), HttpStatus.UNAUTHORIZED);
  }
}
