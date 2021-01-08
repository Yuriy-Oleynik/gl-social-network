package glsocialnetwork.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import glsocialnetwork.dto.PostRequest;
import glsocialnetwork.dto.PostResponse;
import glsocialnetwork.model.Community;
import glsocialnetwork.model.Post;
import glsocialnetwork.model.User;
import glsocialnetwork.model.Vote;
import glsocialnetwork.model.VoteType;
import glsocialnetwork.repository.CommentRepository;
import glsocialnetwork.repository.VoteRepository;
import glsocialnetwork.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

import static glsocialnetwork.model.VoteType.DOWNVOTE;
import static glsocialnetwork.model.VoteType.UPVOTE;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;

    @Value("${aws.credentials.image-url}")
    private String awsUrl;


    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "community", source = "community")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "imageKey", expression = "java(addAwsUrl(postRequest))")
    public abstract Post map(PostRequest postRequest, Community community, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "communityName", source = "community.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    @Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
    @Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
    @Mapping(target = "imageKey", source = "imageKey")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

    boolean isPostUpVoted(Post post) {
        return checkVoteType(post, UPVOTE);
    }

    boolean isPostDownVoted(Post post) {
        return checkVoteType(post, DOWNVOTE);
    }

    private boolean checkVoteType(Post post, VoteType voteType) {
        if (authService.isLoggedIn()) {
            Optional<Vote> voteForPostByUser =
                    voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,
                            authService.getCurrentUser());
            return voteForPostByUser.filter(vote -> vote.getVoteType().equals(voteType))
                    .isPresent();
        }
        return false;
    }

    String addAwsUrl(PostRequest postRequest) {
        return awsUrl + postRequest.getImageKey();
    }
}
