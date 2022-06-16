package net.codeJava.atozMain.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import  java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import net.codeJava.atozEntity.BankTransaction;
import net.codeJava.atozEntity.User;
import net.codeJava.atozMain.dto.UserDto;
import net.codeJava.atozMain.service.impl.UserServiceImpl;
import net.codeJava.atozService.BankTransactionService;
import net.codeJava.atozService.UserService;

@Slf4j
@Controller
public class IndexController {
	
	private Logger log = Logger.getLogger(IndexController.class);
	
	@Autowired
    private UserServiceImpl userServiceImpl;
	@Autowired
    private UserService userService;
	@Autowired
    private BankTransactionService bankTransactionService;
	
    @GetMapping(value = {"/"})
    public  String loginPage(){
    	log.trace("A TRACE Message");
    	log.debug("A DEBUG Message");
    	log.info("An INFO Message");
    	log.warn("A WARN Message");
        log.error("An ERROR Message");
        return "login";
    }
    
    @RequestMapping(value="/save", method=RequestMethod.POST)    
    public ModelAndView save(@ModelAttribute User user)  
    {    
    ModelAndView modelAndView = new ModelAndView();    
    modelAndView.setViewName("testing");        
    modelAndView.addObject("user", user);      
    return modelAndView;    
    }    
    
    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){
        if(StringUtils.hasLength(user.getFirstName()) && StringUtils.hasLength(user.getPassword())){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            user.setLoginTime(dtf.format(now));
            session.setAttribute("loginUser",user);
            return  "redirect:/main";
        }
        else{
            model.addAttribute("msg","Incorrect Login Details!");
            if(StringUtils.hasLength(user.getUserName())) {
                model.addAttribute("uname",user.getUserName());
            }
            return "login";
        }
    }

    @PostMapping("/reset")
    public  String reset(User user, HttpSession session, Model model){
        if(StringUtils.hasLength(user.getUserName()) && "123456".equals(user.getPassword())){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            user.setLoginTime(dtf.format(now));
            log.info("info date={}"+dtf.format(now));
            session.setAttribute("loginUser",user);
            return  "redirect:/main";
        }
        else{
            model.addAttribute("msg","Incorrect Login Details!");
            if(StringUtils.hasLength(user.getUserName())) {
                model.addAttribute("uname",user.getUserName());
            }
            return "login";
        }
    }

    @GetMapping(value={"/main"})
    public String pageStart(HttpSession session, Model model){
        return  "main";
    }

    @GetMapping("/forgetPassword")
    public String forgotPassword(){
        return "forgotPassword";
    }

    @GetMapping("/addnew")
    public String generalForm(Model model){
        model.addAttribute("userDto", new UserDto());
        return "general";
    }

    @RequestMapping("/listing")
    public String listing(Model model){
        List<User> userList=userService.listAll();
        model.addAttribute("userList",userList);
        return "table/data";
    }

    @GetMapping("/viewNew/{id}")
    public String generalViewForm(@PathVariable("id") Long id, Model model,HttpServletRequest request) throws Exception{
//    	try {
    		String u1 = request.getRequestURI();
//    		System.out.println("ul:"+u1);
    		
    		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    		if (principal instanceof UserDetails) {
    		  String username = ((UserDetails)principal).getUsername();
//    		  System.out.println("123:"+username);
    		} else {
    		  String username = principal.toString();
//    		  System.out.println("456");
    		}
    		
    		UserDto userDto = userServiceImpl.findUserById(id);
            model.addAttribute("userDto",userDto);
            return "generalView";
//		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
//			throw e;
//			appExceptionLogService.saveLog(e.getMessage(),1L, new Date(),PathEnum.MAIN.getPageId());
//		}
    }

    @GetMapping("/editNew/{id}")
    public String generalEditForm(@PathVariable("id") Long id, Model model){
        User user=userService.get(id);
        model.addAttribute("user",user);
        return "generalEdit";
    }

    @GetMapping("/deleteNew/{id}")
    public String deleteNew(@PathVariable("id") Long id, RedirectAttributes ra){
        userService.delete(id);
//        userService.updateEnabled(false,id);
        ra.addFlashAttribute("successMsg","User Deleted Successfully.");
        return "redirect:/listing";
    }

    @RequestMapping(value="/saveUser", method = {RequestMethod.POST})
    public String saveUser(@ModelAttribute("userDto") UserDto userDto) throws Exception{
    	boolean isSuccess = userServiceImpl.saveUser(userDto);
    	if(isSuccess) {
    		log.info("persist user is successfully");
    	}
        return "redirect:/listing";
    }

    
    @RequestMapping(value="/updateUser", method = {RequestMethod.POST})
    public String updateUser(@ModelAttribute("user") User user){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedPassword= encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(false);
        return "main";
    }
}
