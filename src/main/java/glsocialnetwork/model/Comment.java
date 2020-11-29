package glsocialnetwork.model;

import java.time.Instant;

public class Comment {
    private Long id;
    private String text;
    private Post post;
    private Instant createdDate;
    private User user;
}
