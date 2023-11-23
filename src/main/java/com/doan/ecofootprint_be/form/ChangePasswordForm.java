package com.doan.ecofootprint_be.form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordForm {
    private String oldPassword;
    private String newPassword;
}
