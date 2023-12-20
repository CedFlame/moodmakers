package ru.moodmakers.authorizationserver.security.socialLogin.mapper;

import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import ru.moodmakers.authorizationserver.domain.User;

public interface OidcUserMapper {
    OidcUser map(OidcUser oidcUser);
    OidcUser map(OidcIdToken idToken, OidcUserInfo userInfo, User user);
}
