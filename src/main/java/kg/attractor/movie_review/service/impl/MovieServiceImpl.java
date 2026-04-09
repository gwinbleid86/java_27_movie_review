package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dao.MovieDao;
import kg.attractor.movie_review.dto.DirectorDto;
import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.model.Movie;
import kg.attractor.movie_review.service.DirectorService;
import kg.attractor.movie_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;
    private final DirectorService directorService;

    @Override
    public List<MovieDto> getMovies() {
        List<Movie> list = movieDao.findAll();
        List<MovieDto> movies = new ArrayList<>();

        list.forEach(e -> {
            MovieDto movieDto = MovieDto.builder()
                    .title(e.getName())
                    .releaseYear(e.getReleaseYear())
                    .description(e.getDescription())
                    .build();

            DirectorDto directorDto = directorService.findById(e.getDirectorId());

            movieDto.setDirector(directorDto.fullname());

            movies.add(movieDto);
        });

        return movies;
    }

    @Override
    public void createMovie(MovieDto movieDto) {
        System.out.println(movieDto.toString());
    }
}
