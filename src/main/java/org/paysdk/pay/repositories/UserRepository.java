package org.paysdk.pay.repositories;

import org.paysdk.pay.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByToken(String token);
}
