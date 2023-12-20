package ru.moodmakers.authorizationserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.moodmakers.authorizationserver.domain.Authority;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
}
