package com.bookstore.be.auth;

import com.bookstore.be.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private int cardId;
    private String cardNumber;
    private int subscriptionId;
    private Role role;
    private String username;
    private String password;

}
