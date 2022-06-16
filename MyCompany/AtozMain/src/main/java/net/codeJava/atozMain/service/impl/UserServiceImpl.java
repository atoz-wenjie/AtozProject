package net.codeJava.atozMain.service.impl;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import net.codeJava.atozEntity.User;
import net.codeJava.atozMain.dto.UserDto;
import net.codeJava.atozService.UserService;

@Service
public class UserServiceImpl {
	
	private Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserService userService;
	
	@SuppressWarnings("unused")
	public boolean saveUser(UserDto dto) throws Exception {
		log.info("saveUser"+dto.toString());
		User user = new User();
		user.setUserName(dto.getUserName());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setEnabled(true);
		
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedPassword= encoder.encode(dto.getPassword());
		user.setPassword(encodedPassword);
		userService.save(user);
		
		return true;
	}

	public UserDto findUserById(Long id) throws Exception{
		log.info("findUserById:"+id);
		
		User user = userService.get(id);
		if(user != null) {
			return new UserDto(user);
		}
		return null;
	}
}
