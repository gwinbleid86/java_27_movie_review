package kg.attractor.movie_review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true, chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class DirectorDto {
    private Long id;
    private String fullname;
}
