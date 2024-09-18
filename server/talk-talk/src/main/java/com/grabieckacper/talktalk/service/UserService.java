package com.grabieckacper.talktalk.service;

import com.grabieckacper.talktalk.model.Profile;
import com.grabieckacper.talktalk.model.User;
import com.grabieckacper.talktalk.repository.UserRepository;
import com.grabieckacper.talktalk.request.CreateUserRequest;
import com.grabieckacper.talktalk.response.UserResponse;
import jakarta.persistence.EntityExistsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(final PasswordEncoder passwordEncoder, final UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found"));
    }

    @Transactional
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        if (userRepository.existsByEmail(createUserRequest.email())) {
            throw new EntityExistsException("User with email " + createUserRequest.email() + " already exists");
        }

        User user = new User();
        user.setEmail(createUserRequest.email());
        user.setPassword(passwordEncoder.encode(createUserRequest.password()));

        Profile profile = new Profile();
        profile.setFirstName(createUserRequest.firstName());
        profile.setLastName(createUserRequest.lastName());

        user.setProfile(profile);
        profile.setUser(user);

        // Automatically saves Profile by Cascade.ALL in User entity
        user = userRepository.save(user);

        return new UserResponse(user.getId(), user.getPassword(), user.getProfile().getId());
    }
}
