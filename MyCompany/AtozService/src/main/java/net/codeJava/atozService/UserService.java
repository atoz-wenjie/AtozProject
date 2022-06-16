package net.codeJava.atozService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codeJava.atozEntity.User;
import net.codeJava.atozRepository.UserRepository;

//@Profile(value={"local","dev","prod"})
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> listAll(){
        return userRepository.findByActive(true);
    }

    public void save(User user){
        userRepository.save(user);
    }

    public String update(User user){
        userRepository.save(user);
        return "";
    }

    public void updateEnabled(boolean status, Long id){
        userRepository.setUserEnabledStatus(status,id);
    }

    public User get(Long id){
    	return userRepository.findById(id).get();
//        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id:"+id));
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
