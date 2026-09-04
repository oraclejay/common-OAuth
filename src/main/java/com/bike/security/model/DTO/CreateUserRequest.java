package com.bike.security.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import com.bike.security.model.DTO.Gender;

import java.time.LocalDate;

public class CreateUserRequest {
    @Schema(
            description = "User's full name",
            example = "John Doe",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String fullName;

    @Schema(
            description = "User's email address",
            example = "john.doe@example.com"
    )
    private String email;

    @Schema(
            description = "User's phone number",
            example = "+919876543210",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String phone;

    @Schema(
            description = "User's password",
            example = "StrongPassword@123",
            requiredMode = Schema.RequiredMode.REQUIRED,
            format = "password"
    )
    private String password;


    @Schema(
            description = "Profile image URL",
            example = "https://example.com/images/john.jpg"
    )
    private String profileImage;


    @Schema(
            description = "User's gender",
            example = "MALE"
    )
    private Gender gender;

    @Schema(
            description = "User's date of birth",
            example = "1995-06-15"
    )
    private LocalDate dateOfBirth;


    // Getters and Setters

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
