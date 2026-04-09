package kg.attractor.movie_review.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {
    private Long id;
    private Integer rating;
    private String comment;
    private Long reviewer;
    private Long movieId;
}
