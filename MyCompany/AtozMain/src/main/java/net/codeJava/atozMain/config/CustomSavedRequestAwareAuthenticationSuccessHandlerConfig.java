package net.codeJava.atozMain.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import net.codeJava.atozEntity.UserSessions;

public class CustomSavedRequestAwareAuthenticationSuccessHandlerConfig extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private UserSessions userSessions;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        super.onAuthenticationSuccess(request, response, authentication);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
//        userSessions.setLoginTime(dtf.format(now));
    }
}
