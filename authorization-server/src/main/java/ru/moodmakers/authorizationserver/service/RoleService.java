package ru.moodmakers.authorizationserver.service;

import ru.moodmakers.authorizationserver.domain.Role;

public interface RoleService {
    Role getByName(String name);
    Role getDefaultRole();
}
