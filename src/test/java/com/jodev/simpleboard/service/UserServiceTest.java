package com.jodev.simpleboard.service;

import com.jodev.simpleboard.domain.RoleType;
import com.jodev.simpleboard.domain.User;
import com.jodev.simpleboard.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void 회원가입() {
        User user = User.builder()
                .email("test@test.com")
                .name("tester")
                .password("test_password")
                .role(RoleType.USER)
                .build();


        Long userId = userService.register(user);
        assertEquals(user, userRepository.findById(userId).get());
    }

}