package com.epam.hotel.entity.dto;

import com.epam.hotel.entity.Identifier;
import com.epam.hotel.entity.enums.UserRole;
import com.epam.hotel.entity.enums.UserStatus;

import java.util.Objects;

public class UserDto implements Identifier {
    private Long id;
    private String login;
    private UserRole role;
    private UserStatus status;

    public UserDto(Long id, String login, UserRole role, UserStatus status) {
        this.id = id;
        this.login = login;
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
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(login, userDto.login) && role == userDto.role && status == userDto.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, role, status);
    }
}
