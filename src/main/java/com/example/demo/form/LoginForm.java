package com.example.demo.form;

import java.time.LocalDateTime;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class LoginForm {
	
	@Digits(integer = 1, fraction = 0)
    private int typeId;

    @NotBlank (message = "タイトルを入力してください。")
    @Size(min = 1, max = 20, message = "20文字以内で入力してください。")
    private String title;

    @NotBlank (message = "内容を入力してください。")
    @Size(min = 1, max = 50, message = "50文字以内で入力してください。")
    private String detail;

    @NotNull (message = "期限を設定してください。")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Future (message = "期限が過去に設定されています。")
    private LocalDateTime deadline;
	
	public LoginForm() {}

	public LoginForm(int typeId, String title, String detail, LocalDateTime deadline) {
		this.typeId = typeId;
		this.title = title;
		this.detail = detail;
		this.deadline = deadline;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}
	
	
	

}
