//package com.doan.ecofootprint_be.dto;
//
//public class JwtResponse {
//    private String token;
//
//
//    public JwtResponse(String token) {
//        this.token = token;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//
//}


package com.doan.ecofootprint_be.dto;

import com.doan.ecofootprint_be.entity.Users;

import java.util.Date;

public class JwtResponse {
    private String token;
    private int id;
    private String username;
    private String email;
    private String fullname;
//    private Users.GENDER gender;
    private String gender;
    private String address;
    private Date birthday;
    private String phone;
    private String avatarUrl;
    private Users.UserStatus status;
    private Users.ROLE role;
    public JwtResponse() {
    }

    // Updated constructor to accept Users object
    public JwtResponse(String token, Users user) {
        this.token = token;
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.fullname = user.getFullname();
        this.gender = user.getGender();
        this.address = user.getAddress();
        this.birthday = user.getBirthday();
        this.phone = user.getPhone();
        this.status = user.getStatus();
        this.avatarUrl = user.getAvatarUrl();
        this.role = user.getRole();
    }

    public Users.ROLE getRole() {
        return role;
    }

    public void setRole(Users.ROLE role) {
        this.role = role;
    }
    // Getters and setters for all fields
    // ...

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

//    public Users.GENDER getGender() {
//        return gender;
//    }
    public String getGender() {
        return gender;
    }

//    public void setGender(Users.GENDER gender) {
//        this.gender = gender;
//}
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Users.UserStatus getStatus() {
        return status;
    }

    public void setStatus(Users.UserStatus status) {
        this.status = status;
    }

    // Remove other constructor as it's not needed
}