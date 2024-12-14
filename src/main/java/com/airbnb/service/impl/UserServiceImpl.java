package com.airbnb.service.impl;

import com.airbnb.config.JwtUtil;
import com.airbnb.dto.request.user.LoginRequest;
import com.airbnb.dto.request.user.UserRegistrationRequest;
import com.airbnb.dto.response.user.UserResponse;
import com.airbnb.entity.User;
import com.airbnb.entity.UserToken;
import com.airbnb.exception.DuplicateEntityException;
import com.airbnb.exception.UserNotFoundException;
import com.airbnb.mapper.user.UserMapper;
import com.airbnb.repository.UserRepository;
import com.airbnb.repository.UserTokenRepository;
import com.airbnb.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserTokenRepository userTokenRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserMapper userMapper;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public void registerUser(UserRegistrationRequest request) {
        List<String> errors = validateUserRegistration(request);

        if (!errors.isEmpty()) {
            throw new DuplicateEntityException(errors);
        }

        User newUser = userMapper.toEntity(request);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        newUser.setPassword(encodedPassword);
        userRepository.save(newUser);
    }

    @Transactional
    @Override
    public String login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticateUser(loginRequest);
            String username = authentication.getName();

            return getTokenForUser(username)
                    .orElseGet(() -> createAndStoreNewToken(username));
        } catch (BadCredentialsException e) {
            log.error("Invalid username or password");
            throw new BadCredentialsException("Invalid username or password");
        }
    }


    @Override
    public UserResponse getUserById(Long userId) {
        User user = userRepository.findByIdWithBookings(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        return userMapper.toResponse(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAllUser();
    }

    /**
     * Validates the user registration request by checking if the username
     * and email already exist in the system.
     *
     * @param request the user registration request containing the username
     *                and email to be validated.
     * @return a list of error messages indicating validation failures, or
     * an empty list if no validation issues are found.
     */
    private List<String> validateUserRegistration(UserRegistrationRequest request) {
        List<String> errors = new ArrayList<>();

        if (userRepository.existsByUsername(request.getUsername())) {
            errors.add("Username already exists: " + request.getUsername());
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            errors.add("Email already exists: " + request.getEmail());
        }
        return errors;
    }

    /**
     * Authenticates a user based on the provided login request, using the username
     * and password credentials.
     *
     * @param loginRequest the login request containing the username and password
     *                     required for authentication.
     * @return an Authentication object representing the successfully authenticated
     * user, or throws an appropriate exception if authentication fails.
     */
    private Authentication authenticateUser(LoginRequest loginRequest) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
    }

    /**
     * Retrieves a valid token for the specified username if it exists and has not expired.
     *
     * @param username the username for which the token is to be retrieved
     * @return an {@link Optional} containing the token if a valid one exists; otherwise, an empty {@link Optional}
     */
    private Optional<String> getTokenForUser(String username) {
        return userTokenRepository.findByUsername(username)
                .filter(token -> token.getExpiresAt().isAfter(LocalDateTime.now(ZoneId.systemDefault()))) // Sử dụng LocalDateTime.now()
                .map(UserToken::getToken);
    }

    /**
     * Creates a new token for the given username, stores it in the database,
     * and returns the generated token.
     *
     * @param username the username for which the token is to be created
     * @return the newly generated token string
     */
    private String createAndStoreNewToken(String username) {
        userTokenRepository.softDeleteOldTokensByUsername(username);
        String newToken = jwtUtil.generateToken(username);
        UserToken userToken = UserToken.builder()
                .username(username)
                .token(newToken)
                .expiresAt(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).plusMinutes(15))
                .isDelete(false)
                .build();
        userTokenRepository.save(userToken);
        return newToken;
    }
}