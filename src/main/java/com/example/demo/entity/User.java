package com.example.demo.entity;

public class User {
    private int id;
    private String username;
    private String password;
    private int admin_flg;

    public int getId() {
        return id;
    }

	public void setId(int id) {
        this.id = id;
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
    
    public int getAdmin_flg() {
		return admin_flg;
	}

	public void setAdmin_flg(int admin_flg) {
		this.admin_flg = admin_flg;
	}


}
