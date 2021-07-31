package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

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
    
    
    /**
     * ログイン画面を表示
     * @param model
     * @return resources/templates下のHTMLファイル名
     */
    @GetMapping
    public String task(TaskForm taskForm,Model model) {
    	model.addAttribute("taskForm",taskForm);
        model.addAttribute("title", "ログイン");
        return "user/login";
    }
    /*
    @GetMapping
    public String getLogin(SignUpForm signupForm,Model model) {
        model.addAttribute("title", "ログイン");
        return "user/login";
    }
    */
    /*
    @GetMapping
    public String task(TaskForm taskForm,Model model) {
        model.addAttribute("title", "ログイン");
        return "user/login";
    }*/
    
    
    /**
     * タスクデータを
     * @param taskForm
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/login")
    public String insert(@Valid @ModelAttribute TaskForm taskForm,BindingResult result, Model model) {
    	
    	
    		model.addAttribute("taskForm",taskForm);
            model.addAttribute("title", "ログイン（バリデーション）");
            return "user/login";
    	
        
    }
    /*
    @PostMapping("/login")
    public String insert(@Valid @ModelAttribute SignUpForm  signupForm,BindingResult result, Model model) {
    	
    	if(!result.hasErrors()) {
    		
        	User user = new User();
        	user.setUsername(signupForm.getUsername());
        	user.setPassword(signupForm.getPassword());
        	user.setAdmin_flg(0);
        	
        	
        	return "redirect:/login";
        	
    		
    	}else {
    		model.addAttribute("signupForm",signupForm);
            model.addAttribute("title", "ログイン（バリデーション）");
            return "user/login";
    	}
        
    }*/
    /*
    @PostMapping("/login")
    public String insert(@Valid @ModelAttribute TaskForm taskForm,BindingResult result, Model model) {
    	
    	if(!result.hasErrors()) {
    		
    		Task task = new Task();
        	task.setUserId(1);
        	task.setTypeId(taskForm.getTypeId());
        	task.setTitle(taskForm.getTitle());
        	task.setDetail(taskForm.getDetail());
        	task.setDeadline(taskForm.getDeadline());
        	
        	
        	return "redirect:/login";
        	
    		
    	}else {
    		model.addAttribute("taskForm",taskForm);
            model.addAttribute("title", "ログイン（バリデーション）");
            return "user/login";
    	}
        
    }*/
    

}
