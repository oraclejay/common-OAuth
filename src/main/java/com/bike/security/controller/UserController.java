package com.bike.security.controller;

import com.bike.security.model.DTO.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import com.bike.security.model.DTO.CreateUserRequest;
import com.bike.security.model.User;
import com.bike.security.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
@Tag(
        name = "Users",
        description = "APIs for user management"
)
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/testserver")
    public String login()
    {
        return "success login auth";

    }

    @Operation(
            summary = "Create a new user",
            description = "Creates a new user after validating that the email and phone number are unique."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "User created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "string", format = "uuid"),
                            examples = @ExampleObject(
                                    value = "550e8400-e29b-41d4-a716-446655440000"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email or phone already exists",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    value = "Email already exists"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            )
    })
    @PostMapping("/createuser")
    public ResponseEntity<?> createUser(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "User registration details",
            required = true,
            content = @Content(
                    mediaType = "application/json"
            )
    )
@RequestBody CreateUserRequest request) {
        if (request.getEmail() != null
                && userRepository.existsByEmail(request.getEmail())) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Email already exists");
        }
      if (userRepository.existsByPhone(request.getPhone())) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Phone already exists");
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        // TEMPORARY - see password section below
        user.setPasswordHash(request.getPassword());

        user.setProfileImage(request.getProfileImage());
        user.setGender(request.getGender());
        user.setDateOfBirth(request.getDateOfBirth());

        // role and status use entity defaults
        // CUSTOMER
        // ACTIVE

        User savedUser = userRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUser.getUserId());
    }

 @GetMapping("/me")
    public UserResponse home(Authentication authentication) {
        OAuth2AuthenticationToken oauthToken =
                (OAuth2AuthenticationToken) authentication;

        OAuth2User oauthUser = oauthToken.getPrincipal();
        System.out.println("Oauth2User " + oauthUser);

        String email = oauthUser.getAttribute("email");

        System.out.println("email which need to check "+ email);
        User user = userRepository
                .findByEmail(email) ;

        return new UserResponse(user.getFullName(),
                user.getEmail(),user.getPhone(),
                user.getProfileImage(),
                user.getGender(),
                user.getUserId().toString()
        );
    }

    @GetMapping("/profile-image")
    public ResponseEntity<byte[]> getProfileImage(Authentication authentication) {

        OAuth2AuthenticationToken oauthToken =
                (OAuth2AuthenticationToken) authentication;

        OAuth2User oauthUser = oauthToken.getPrincipal();
        System.out.println("Oauth2User " + oauthUser);

        String email = oauthUser.getAttribute("email");


        User user = userRepository.findByEmail(email);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(
                        user.getProfileImageType()
                ))
                .body(user.getActualImage());
    }


}
