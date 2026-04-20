package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.exception.NotFoundEntryException;
import kg.attractor.movie_review.exception.UserDataCreateException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDto> getAllUsers();

//    UserDto findById(int id) throws UserNotFoundException;

    UserDto findByEmail(String email) throws NotFoundEntryException;

    void create(UserDto userDto) throws UserDataCreateException;
}
