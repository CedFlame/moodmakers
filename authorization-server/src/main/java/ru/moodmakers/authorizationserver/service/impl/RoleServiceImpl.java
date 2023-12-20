package ru.moodmakers.authorizationserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.stereotype.Service;
import ru.moodmakers.authorizationserver.domain.Role;
import ru.moodmakers.authorizationserver.repository.RoleRepository;
import ru.moodmakers.authorizationserver.service.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    public static final String DEFAULT_ROLE = "USER";

    private final RoleRepository repository;

    @Override
    public Role getByName(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }

        return repository.findByName(name).orElse(null);
    }

    @Override
    public Role getDefaultRole() {
        return getByName(DEFAULT_ROLE);
    }
}
