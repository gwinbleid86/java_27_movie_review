package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.exception.NotFoundEntryException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    List<MovieDto> getMovies(Pageable pageable);

    void createMovie(MovieDto movieDto);

    MovieDto findById(Long id) throws NotFoundEntryException;
}
