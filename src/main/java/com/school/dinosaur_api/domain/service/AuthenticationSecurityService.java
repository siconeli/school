package com.school.dinosaur_api.domain.service;

import com.school.dinosaur_api.api.representationmodel.input.RegisterInput;
import com.school.dinosaur_api.domain.exception.BusinessException;
import com.school.dinosaur_api.domain.model.User;
import com.school.dinosaur_api.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class AuthenticationSecurityService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    @Transactional
    public void registerUser(RegisterInput registerInput) {
        if(userRepository.findByLogin(registerInput.getLogin()) != null) {
            throw new BusinessException("User already exists");
        }

        String encryptedPassword = passwordEncoder.encode(registerInput.getPassword());

        User newUser = new User(registerInput.getLogin(), encryptedPassword, registerInput.getRole());

        userRepository.save(newUser);
    }
}
