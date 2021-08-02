package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.LoginForm;

@Controller
@RequestMapping("/top")
public class TopController {
	
	/**
     * ログイン画面を表示
     * @param model
     * @return resources/templates下のHTMLファイル名
     */
    @GetMapping
    public String top(Model model) {
    	//model.addAttribute("taskForm",taskForm);
    	
        model.addAttribute("title", "ログイン");
        //session.setAttribute("sessionMessage","test Message session");
        return "top/top";
    }

}
