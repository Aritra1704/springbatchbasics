package io.arpaul.ytspringbatch.repository;

import io.arpaul.ytspringbatch.payload.UserAddress;
import io.arpaul.ytspringbatch.payload.UserState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStateRepository extends JpaRepository<UserState, Long> {
}
