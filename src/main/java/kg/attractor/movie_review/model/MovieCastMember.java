package kg.attractor.movie_review.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieCastMember {
    private String role;
    private Long movieId;
    private Long castMemberId;
}
