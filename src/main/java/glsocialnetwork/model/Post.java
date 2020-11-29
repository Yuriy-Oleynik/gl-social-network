package glsocialnetwork.model;

import java.time.Instant;

public class Post {
    private Long postId;
    private String postName;
    private String url;
    private String description;
    private Integer voteCount = 0;
    private User user;
    private Instant createdDate;
    private Community community;
}
