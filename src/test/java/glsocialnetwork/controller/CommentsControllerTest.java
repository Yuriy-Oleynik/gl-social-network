package glsocialnetwork.controller;

import glsocialnetwork.dto.CommentsDto;
import glsocialnetwork.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
public class CommentsControllerTest {

    @InjectMocks
    CommentsController commentsController;

    @Mock
    CommentService commentService;

    @Test
    public void createComment() {
        CommentsDto comment = CommentsDto.builder()
                .userName("username")
                .id(222L)
                .postId(222L)
                .createdDate(Instant.now())
                .text("some message")
                .build();
        doNothing().when(commentService).save(comment);
        ResponseEntity<Void> response = commentsController.createComment(comment);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void getAllCommentsForPost() {
        Long postId = 222L;
        ResponseEntity<List<CommentsDto>> response = commentsController.getAllCommentsForPost(postId);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void getAllCommentsForUser() {
        String userName = "username";
        ResponseEntity<List<CommentsDto>> response = commentsController.getAllCommentsForUser(userName);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
