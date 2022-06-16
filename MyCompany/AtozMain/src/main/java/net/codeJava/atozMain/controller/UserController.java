package net.codeJava.atozMain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import net.codeJava.atozEntity.User;
import net.codeJava.atozService.UserService;

@Controller
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("api/update/{id}")
    private String updateUser2(@RequestBody Long id, User user){
       return userService.update(user);
    }

    @GetMapping(value="/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes ra,Model model){
        userService.updateEnabled(false, id);
        ra.addFlashAttribute("successMsg","User Deleted Successfully.");
        return ("redirect:/listing");
    }

}
