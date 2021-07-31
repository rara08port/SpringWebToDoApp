package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.demo.entity.User;
import com.example.demo.form.SignUpForm;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class SignUpController {
	
	
	private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * ユーザ登録画面を表示
     * @param model
     * @return resources/templates下のHTMLファイル名
     */
    @GetMapping("/signup")
    public String getSignUp(SignUpForm signUpForm,Model model) {
    	
    	model.addAttribute("signUpForm", signUpForm);
        model.addAttribute("title", "ユーザ登録");

        return "user/signup";
    }
    
    @PostMapping("/signup")
    public String postSignUp(@Validated @ModelAttribute SignUpForm signUpForm,
    		BindingResult result,
    		Model model) {
    	
    	User user = new User();
    	user.setUsername(signUpForm.getUsername());
    	user.setPassword(signUpForm.getPassword());
    	user.setAdmin_flg(0);
    	int count=10;
    	count = userService.findRegisterUser(user);
    
    	
		
    	/*if(count==0) {
    		System.out.println("ユーザ登録");
    		//userService.insert(user);
    		//return "task/index";
    		return "redirect:/task";
    	}
    	model.addAttribute("signupForm", signupForm);
    	model.addAttribute("title", "ユーザ登録");
    	//return "redirect:/task/index";
    	 return "user/signup";
    	 */
    	
    	
    	if (!result.hasErrors()) {  	
    		System.out.println("ユーザ登録");
    		//userService.insert(user);
    		//return "task/index";
    		return "redirect:/task";
        } else {
        	
        	model.addAttribute("signuUpForm", signUpForm);
        	model.addAttribute("title", "ユーザ登録再");
        	
        	 return "user/signup";
            
        }
    	
    }
    
    

}
