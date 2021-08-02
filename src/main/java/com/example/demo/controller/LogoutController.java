package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.LoginForm;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	@Autowired
    HttpSession session;
	
	 /**
     * ログイン画面を表示
     * @param model
     * @return resources/templates下のHTMLファイル名
     */
    @GetMapping
    public String logout(LoginForm loginForm,Model model) {
    	//model.addAttribute("taskForm",taskForm);
    	session.removeAttribute("userId");
    	session.removeAttribute("username");
    	
        model.addAttribute("title", "ログイン");
        //session.setAttribute("sessionMessage","test Message session");
        return "user/login";
    }

}
