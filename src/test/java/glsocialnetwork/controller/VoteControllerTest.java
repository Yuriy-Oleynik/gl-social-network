package glsocialnetwork.controller;

import glsocialnetwork.dto.VoteDto;
import glsocialnetwork.model.VoteType;
import glsocialnetwork.service.VoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
public class VoteControllerTest {

    @InjectMocks
    VoteController voteController;

    @Mock
    VoteService voteService;

    @Test
    public void vote() {
        VoteDto vote = VoteDto.builder()
                .postId(222L)
                .voteType(VoteType.UPVOTE)
                .build();
        doNothing().when(voteService).vote(vote);
        ResponseEntity<Void> response = voteController.vote(vote);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
