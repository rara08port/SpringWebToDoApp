package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.demo.app.task.TaskForm;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.form.LoginForm;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }
    
    @Autowired
    HttpSession session;
    
    
    /**
     * ログイン画面を表示
     * @param model
     * @return resources/templates下のHTMLファイル名
     */
    @GetMapping
    public String task(LoginForm loginForm,Model model) {
    	
        model.addAttribute("title", "ログイン");
       
        return "user/login";
    }
    
    
    /**
     * タスクデータを
     * @param taskForm
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/login")
    public String insert(@Valid @ModelAttribute LoginForm loginForm,BindingResult result, Model model) {
    	
    	User user = new User();
    	user.setUsername(loginForm.getUsername());
    	user.setPassword(loginForm.getPassword());
    	user.setAdmin_flg(0);
    	int count=10;
    	count = userService.findLoginUserCheck(user);
    	
    	if(!result.hasErrors()) {
    			
    		if(count!=0) {		
    			User userData = new User();
    			userData = userService.findLoginUser(user);	
        		session.setAttribute("userId",userData.getId());
        		session.setAttribute("username", userData.getUsername());
        		
        		return "redirect:/task";
        	}
    		
        	model.addAttribute("loginForm", loginForm);
        	model.addAttribute("title", "ユーザ未登録");
        	return "user/login";
        	       	
    		
    	}else {
    		
    		model.addAttribute("loginForm",loginForm);
            model.addAttribute("title", "ログイン（バリデーション）");
            return "user/login";
    	}
        
    }
    
  

}
