package net.codeJava.atozRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codeJava.atozEntity.AppExceptionLog;

@Repository
public interface AppExceptionRepository extends JpaRepository<AppExceptionLog,Long> {
}
