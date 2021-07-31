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
    public String getSignUp(SignUpForm signupForm,Model model) {
    	System.out.println("getSignUp");

        //Taskのリストを取得する
        //List<Task> taskList = taskService.findAll();

        //model.addAttribute("taskList", taskList);
    	model.addAttribute("signupForm", signupForm);
        model.addAttribute("title", "ユーザ登録");

        return "user/signup";
    }
    
    @PostMapping("/signup")
    public String postSignUp(@Validated @ModelAttribute SignUpForm signupForm,
    		BindingResult result,
    		Model model) {
    	
    	User user = new User();
    	user.setUsername(signupForm.getUsername());
    	user.setPassword(signupForm.getPassword());
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
    	System.out.println(result.hasErrors());
    	System.out.println(result.getFieldError("username"));
    	System.out.println(result.getFieldError("password"));
    	//fields.hasErrors('username');
    	System.out.println(result.getFieldErrorCount());
    	System.out.println(result.getFieldErrors("username"));
    	
    	if (!result.hasErrors()) {  	
    		System.out.println("ユーザ登録");
    		//userService.insert(user);
    		//return "task/index";
    		return "redirect:/task";
        } else {
        	System.out.println("項目エラー");
        	model.addAttribute("signupForm", signupForm);
        	System.out.println(signupForm.getUsername());
        	System.out.println(signupForm.getPassword());
        	model.addAttribute("title", "ユーザ登録再");
        	
        	 return "user/signup";
            
        }
    	/*
    	if (!result.hasErrors()) {
    		Task task = new Task();
    		task.setId(taskId);
        	//task.setUserId(1);
        	task.setTypeId(taskForm.getTypeId());
        	task.setTitle(taskForm.getTitle());
        	task.setDetail(taskForm.getDetail());
        	task.setDeadline(taskForm.getDeadline());

        	taskService.update(task);
        	
        	redirectAttributes.addFlashAttribute("complete", "変更が完了しました");

        	return "redirect:/task/" + taskId;
        } else {
            model.addAttribute("taskForm", taskForm);
            model.addAttribute("title", "タスク一覧");
            return "task/index";
            
        }
        */
    	
    }
    
    

}
