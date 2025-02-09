package com.example.authenticationservice.dto.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequestDto {
    private String username;
    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;
}
