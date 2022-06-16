package net.codeJava.atozRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.codeJava.atozEntity.UserSessions;

@Transactional
@Repository
public interface UserSessionsRepository extends JpaRepository<UserSessions,Long> {
}
