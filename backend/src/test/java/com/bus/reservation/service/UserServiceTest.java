package com.bus.reservation.service;

import com.bus.reservation.entity.User;
import com.bus.reservation.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void saveDelegatesToRepository() {
        User u = new User();
        u.setId(1L);
        u.setEmail("a@b.com");
        when(userRepository.save(u)).thenReturn(u);

        User saved = userService.save(u);

        assertThat(saved).isSameAs(u);
        verify(userRepository).save(u);
    }
}

