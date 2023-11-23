package com.doan.ecofootprint_be.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BodyLogin {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
