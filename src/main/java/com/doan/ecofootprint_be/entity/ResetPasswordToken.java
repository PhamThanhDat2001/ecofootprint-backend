package com.doan.ecofootprint_be.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Reset_Password_Token")
public class ResetPasswordToken {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "token")
    private String token;

    @Column(name = "expiryDate")
    private Date expiryDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private  Users users;

    public ResetPasswordToken(String token, Users user) {
        this.token = token;
        this.users = user;

        // 1h
        expiryDate = new Date(System.currentTimeMillis() + 360000);
    }

}
