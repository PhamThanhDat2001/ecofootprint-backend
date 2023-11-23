package com.doan.ecofootprint_be.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgetPasswordForm {
    private String token;
    private String newPassword;
}
