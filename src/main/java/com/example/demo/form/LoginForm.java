package com.example.demo.form;

import java.time.LocalDateTime;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class LoginForm {
	
	@NotBlank (message = "ユーザ名を入力してください。")
	@Size(min = 1, max = 20, message = "20文字以内で入力してください。")
	private String username;
	
	@NotBlank (message = "パスワードを入力してください。")
	@Size(min = 1, max = 20, message = "20文字以内で入力してください。")
	private String password;
	
	public LoginForm() {}

	public LoginForm(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
