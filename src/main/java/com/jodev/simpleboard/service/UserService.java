package com.jodev.simpleboard.service;

import com.jodev.simpleboard.domain.User;
import com.jodev.simpleboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long register(User user){
        validationDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    public void validationDuplicateUser(User user){
        List<User> findUser = userRepository.findByEmail(user.getEmail());
        if (!findUser.isEmpty()) {
            throw new IllegalStateException("Already Registered User");
        }
    }

}
