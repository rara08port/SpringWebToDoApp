package com.example.demo.form;

import javax.validation.constraints.Size;

public class SignUpForm {
	
	@Size(min = 1, max = 20, message = "20文字以内で入力してください。")
	private String username;
	
	@Size(min = 1, max = 20, message = "20文字以内で入力してください。")
	private String password;
	
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
