package net.codeJava.atozMain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codeJava.atozEntity.UserSessions;
import net.codeJava.atozMain.repository.UserSessionsRepository;

@Service
public class UserSessionsService{
    @Autowired
    private UserSessionsRepository userSessionRepository;

    public void save(UserSessions userSession){
        userSessionRepository.save(userSession);
    }

}
