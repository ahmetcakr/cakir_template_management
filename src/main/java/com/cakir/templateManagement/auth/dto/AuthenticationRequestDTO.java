package com.cakir.templateManagement.auth.dto;

import lombok.*;

@Setter
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDTO {

    private String email;
    private String password;
}
