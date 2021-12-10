package com.minhien.testapp.model;

public class NguoiDung {
    String userId, password, email, birthday;

    public NguoiDung() {
    }

    public NguoiDung(String userId, String password, String email, String birthday) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
