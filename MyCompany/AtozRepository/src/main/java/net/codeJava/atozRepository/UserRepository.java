package net.codeJava.atozRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import net.codeJava.atozEntity.User;
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("Select u from User u where u.enabled=1 and u.email= ?1")
    User findByEmail(String email);

    @Query("Select u from User u where u.enabled=1 and u.userName= ?1")
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    public User findByVerificationCode(String code);

    @Query("Select u from User u where u.enabled= ?1")
    List<User> findByActive(Boolean isEnabled);

    @Modifying
    @Query("update User set enabled=?1 where id=?2 ")
    int setUserEnabledStatus(boolean status, Long id);
}
