package io.arpaul.ytspringbatch.repository;

import io.arpaul.ytspringbatch.payload.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
