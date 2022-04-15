package io.arpaul.ytspringbatch.repository;

import io.arpaul.ytspringbatch.payload.User;
import io.arpaul.ytspringbatch.payload.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, String> {
}
