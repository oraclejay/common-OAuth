package com.bike.security.model.DTO;

import com.bike.security.model.DTO.Gender;

public class UserResponse {

    private String userId;

    public UserResponse(String fullName, String email, String phone, String profileImage, Gender gender, String userId) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.profileImage = profileImage;
        this.gender = gender;
        this.userId=userId;
    }

    private String fullName;

    private String email;

    private String phone;

    private String profileImage;

    private Gender gender;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
