package org.paysdk.pay.repositories;

import org.paysdk.pay.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByTelegramId(String telegramId);
}
