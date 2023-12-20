package ru.moodmakers.authorizationserver.service;

import ru.moodmakers.authorizationserver.domain.User;

public interface UserService {

    User save(User user);
    User getByUsername(String username);

}
