package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.task.TaskForm;
import com.example.demo.entity.Task;
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
    public String getSignUp(Model model) {

        //Taskのリストを取得する
        //List<Task> taskList = taskService.findAll();

        //model.addAttribute("taskList", taskList);
        model.addAttribute("title", "ユーザ登録");

        return "user/signup";
    }
    
    @PostMapping("/signup")
    public String postSignUp(@ModelAttribute SignUpForm signupForm,Model model) {
    	
    	System.out.println(signupForm.getUsername());
    	System.out.println(signupForm.getPassword());
    	User user = new User();
    	user.setUsername(signupForm.getUsername());
    	user.setPassword(signupForm.getPassword());
    	user.setAdmin_flg(0);
    	int count=10;
    	//count = userService.findRegisterUser(user);
    	System.out.println(count);
    	
		
    	if(count==0) {
    		System.out.println("ユーザ登録");
    		//userService.insert(user);
    		//return "task/index";
    		return "redirect:/task";
    	}

    	
    	model.addAttribute("title", "ユーザ登録");
    	
    	//return "redirect:/task/index";
    	 return "user/signup";
    }
    
    

}
