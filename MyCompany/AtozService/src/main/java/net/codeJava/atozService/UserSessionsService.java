package net.codeJava.atozService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codeJava.atozEntity.UserSessions;
import net.codeJava.atozRepository.UserSessionsRepository;

@Service
public class UserSessionsService{
    @Autowired
    private UserSessionsRepository userSessionRepository;

    public void save(UserSessions userSession){
        userSessionRepository.save(userSession);
    }

}
