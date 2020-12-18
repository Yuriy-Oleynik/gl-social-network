package glsocialnetwork.controller;

import glsocialnetwork.dto.CommunityDto;
import glsocialnetwork.service.CommunityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class CommunityControllerTest {

    @InjectMocks
    CommunityController communityController;

    @Mock
    CommunityService communityService;

    @Test
    public void createCommunity() {
        CommunityDto community = CommunityDto.builder()
                .id(222L)
                .name("some community")
                .description("some description")
                .numberOfPosts(2)
                .build();
        ResponseEntity<CommunityDto> response = communityController.createCommunity(community);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void getAllCommunities() {
        ResponseEntity<List<CommunityDto>> response = communityController.getAllCommunities();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getCommunity() {
        Long id = 222L;
        ResponseEntity<CommunityDto> response = communityController.getCommunity(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
