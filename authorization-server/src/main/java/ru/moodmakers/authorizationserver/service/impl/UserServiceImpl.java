package ru.moodmakers.authorizationserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.moodmakers.authorizationserver.domain.User;
import ru.moodmakers.authorizationserver.repository.UserRepository;
import ru.moodmakers.authorizationserver.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public User getByUsername(String username) {
        if (!StringUtils.hasText(username)) {
            return null;
        }

        return repository.findByUsername(username).orElse(null);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }
}
