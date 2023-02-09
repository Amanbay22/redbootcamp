package kz.yertayev.redbootcamp.controllers.api.cabinet;

import java.security.SignatureException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import kz.yertayev.redbootcamp.model.announcement.AnnouncementDto;
import kz.yertayev.redbootcamp.model.bid.Bid;
import kz.yertayev.redbootcamp.model.error.ApiError;
import kz.yertayev.redbootcamp.model.error.BidException;
import kz.yertayev.redbootcamp.services.IAnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @PreAuthorize("hasAuthority('ROLE_USER')")
  @PostMapping("/bid")
  public ResponseEntity<Void> takeBid(@RequestBody Bid bid) {
    announcementService.takeBid(bid);
    return ResponseEntity.ok().build();
  }

  @PreAuthorize("hasAuthority('ROLE_USER')")
  @GetMapping("/")
  public ResponseEntity<Set<AnnouncementDto>> getAll() {
    return ResponseEntity.ok(announcementService.getAnnouncements());
  }

  @PreAuthorize("hasAuthority('ROLE_USER')")
  @GetMapping("/filter")
  public ResponseEntity<Set<AnnouncementDto>> getAllWithFilters(
        @RequestParam(required = false) String sellerEmail,
      @RequestParam(required = false) Double minPrice
  ) {
    return ResponseEntity.ok(
        announcementService.getAnnouncementsByFilter(
            sellerEmail,
            minPrice)
    );
  }

  @ExceptionHandler(BidException.class)
  public ResponseEntity<ApiError> handleContentNotAllowedException(BidException e) {
    List<String> errors = Collections.singletonList(e.getMessage());

    return new ResponseEntity<>(new ApiError(errors), HttpStatus.BAD_REQUEST);
  }
}
