package kz.yertayev.redbootcamp.services.impl;

import java.util.Set;
import kz.yertayev.redbootcamp.domain.entities.AnnouncementEntity;
import kz.yertayev.redbootcamp.domain.repositories.AnnouncementRepository;
import kz.yertayev.redbootcamp.model.announcement.AnnouncementDto;
import kz.yertayev.redbootcamp.model.announcement.AnnouncementState;
import kz.yertayev.redbootcamp.model.bid.Bid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.*;

class AnnouncementServiceImplTest {

  @Mock
  AnnouncementRepository announcementRepository;
  @Mock
  ModelMapper modelMapper;
  @InjectMocks
  AnnouncementServiceImpl announcementServiceImpl;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @Transactional
  void testCreateAnnouncement() {
    announcementServiceImpl.createAnnouncement(
        new AnnouncementDto(Long.valueOf(1), "name", "description", Double.valueOf(101), "imageUrl",
            "sellerEmail", AnnouncementState.ACTIVE));
  }

  @Test
  void testGetAnnouncements() {
    when(announcementRepository.findAllActive()).thenReturn(Set.of(new AnnouncementEntity()));

    Set<AnnouncementDto> result = announcementServiceImpl.getAnnouncements();
    Assertions.assertEquals(
        Set.of(new AnnouncementDto(Long.valueOf(1), "name", "description", Double.valueOf(0),
            "imageUrl", "sellerEmail", AnnouncementState.ACTIVE)), result);
  }

  @Test
  @Transactional
  void testTakeBid() {
    announcementServiceImpl.takeBid(new Bid());
  }

}

