package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


/**
 * Created by nikita on 05.01.2017.
 */

@Component("loginUser")
@Scope("session")
public class AuthenticationBean {

    private String email;
    private String password;

    public AuthenticationBean() {
    }

    public AuthenticationBean(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        AuthenticationBean that = (AuthenticationBean) o;
//
//        if (email != null ? !email.equals(that.email) : that.email != null) return false;
//        if (password != null ? !password.equals(that.password) : that.password != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = email != null ? email.hashCode() : 0;
//        result = 31 * result + (password != null ? password.hashCode() : 0);
//        return result;
//    }

//    @Override
//    public String toString() {
//        return "AuthenticationBean{" +
//                "email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }
}
