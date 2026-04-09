package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.DirectorDto;
import lombok.SneakyThrows;

public interface DirectorService {
    @SneakyThrows
    DirectorDto findById(long id);
}
