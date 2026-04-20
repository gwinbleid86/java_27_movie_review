package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dao.UserDao;
import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.exception.NotFoundEntryException;
import kg.attractor.movie_review.exception.UserDataCreateException;
import kg.attractor.movie_review.exception.UserNotFoundException;
import kg.attractor.movie_review.model.Authority;
import kg.attractor.movie_review.model.Role;
import kg.attractor.movie_review.model.User;
import kg.attractor.movie_review.repository.UserRepository;
import kg.attractor.movie_review.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.getAllUsers();

        List<UserDto> result = new ArrayList<>();

        users.forEach(e -> {
            UserDto user = UserDto.builder()
                    .email(e.getEmail())
                    .username(e.getUsername())
                    .password(e.getPassword())
                    .build();
            result.add(user);
        });

        return result;
    }

    @Override
    public UserDto findByEmail(String email) throws NotFoundEntryException {
        User user = userDao.findByEmailNamed(email)
                .orElseThrow(() -> new NotFoundEntryException("User not found"));

        return UserDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    @Override
    public void create(UserDto userDto) throws UserDataCreateException {
        try {
            User user = new User();
            user.setEmail(userDto.getEmail());
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());

            userDao.create(user);
        } catch (SQLException e) {
            throw new UserDataCreateException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(UserNotFoundException::new);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    private List<String> getPrivileges(Collection<Role> roles) {
        List<String> privileges = new ArrayList<>();
        List<Authority> collection = new ArrayList<>();

        for (Role role : roles) {
            privileges.add(role.getRoleName());
            collection.addAll(role.getAuthorities());
        }
        for (Authority item : collection) {
            privileges.add(item.getAuthorityName());
        }
        return privileges;
    }

}
