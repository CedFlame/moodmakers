package ru.moodmakers.authorizationserver.security.socialLogin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.moodmakers.authorizationserver.domain.Role;
import ru.moodmakers.authorizationserver.domain.User;
import ru.moodmakers.authorizationserver.service.impl.RoleServiceImpl;
import ru.moodmakers.authorizationserver.service.impl.UserServiceImpl;

import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserServiceOAuth2UserHandler implements Consumer<OidcUser> {
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    @Override
    public void accept(OidcUser user) {

        CustomOidcUser oidcUser = (CustomOidcUser)user;

        if (oidcUser.getId() == null
                && this.userService.getByUsername(user.getName()) == null
        ) {
            Collection<GrantedAuthority> grantedAuthorities = (Collection<GrantedAuthority>)oidcUser.getAuthorities();
            User localUser = oidcUser.toInstantUser();
            Role defaultRole = roleService.getDefaultRole();

            if (defaultRole != null) {
                localUser.setRoles(Set.of(defaultRole));
            }

            this.userService.save(localUser);

            if (!CollectionUtils.isEmpty(localUser.getRoles())) {
                Set<? extends GrantedAuthority> authorities = localUser.getRoles().stream()
                        .flatMap(role -> role.getAuthorities().stream()
                                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                        )
                        .collect(Collectors.toSet());

                grantedAuthorities.addAll(authorities);
            }

            oidcUser.setId(localUser.getId());
        }
    }
}
