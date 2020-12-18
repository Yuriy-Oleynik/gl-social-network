package glsocialnetwork.controller;

import glsocialnetwork.dto.PostRequest;
import glsocialnetwork.dto.PostResponse;
import glsocialnetwork.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
public class PostControllerTest {

    @InjectMocks
    PostController postController;

    @Mock
    PostService postService;

    @Test
    public void createPost() {
        PostRequest post = PostRequest.builder()
                .postId(222L)
                .postName("some name")
                .communityName("some community")
                .description("some description")
                .url("www.google.com")
                .build();
        doNothing().when(postService).save(post);
        ResponseEntity<Void> response = postController.createPost(post);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void getAllPosts() {
        ResponseEntity<List<PostResponse>> response = postController.getAllPosts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getPost() {
        Long id = 222L;
        ResponseEntity<PostResponse> response = postController.getPost(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getPostsByCommunity() {
        Long id = 222L;
        ResponseEntity<List<PostResponse>> response = postController.getPostsByCommunity(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getPostsByUsername() {
        String name = "username";
        ResponseEntity<List<PostResponse>> response = postController.getPostsByUsername(name);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deletePost() {
        Long id = 222L;
        ResponseEntity response = postController.deletePost(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
