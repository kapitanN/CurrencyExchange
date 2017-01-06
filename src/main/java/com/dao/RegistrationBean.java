package com.dao;

/**
 * Created by nikita on 04.01.2017.
 */
public class RegistrationBean {

    private String name;
    private String lastName;
    private String email;
    private String password;

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistrationBean registrationBean = (RegistrationBean) o;

        if (name != null ? !name.equals(registrationBean.name) : registrationBean.name != null) return false;
        if (lastName != null ? !lastName.equals(registrationBean.lastName) : registrationBean.lastName != null) return false;
        if (email != null ? !email.equals(registrationBean.email) : registrationBean.email != null) return false;
        return password != null ? password.equals(registrationBean.password) : registrationBean.password == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
