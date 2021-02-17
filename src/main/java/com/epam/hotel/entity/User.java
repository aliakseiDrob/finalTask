package com.epam.hotel.entity;

import com.epam.hotel.entity.enums.UserRole;
import com.epam.hotel.entity.enums.UserStatus;

import java.util.Objects;

public class User implements Identifier {
    private Long id;
    private String login;
    private String password;
    private UserRole role;
    private UserStatus status;

    public User(Long id, String login, String password, UserRole role, UserStatus status) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && role == user.role && status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role, status);
    }

}
