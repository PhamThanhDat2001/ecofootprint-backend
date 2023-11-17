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
@Table(name = "`Registration_User_Token`")
public class RegistrationUserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", unique = true, nullable = false)
    private int id;

    @Column(name = "`token`", nullable = false, length = 36, unique = true)
    private String token;

    @OneToOne()
    @JoinColumn(nullable = false, name = "user_id")
    private Users users;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiryDate", nullable = false)
    private Date expiryDate;
    public RegistrationUserToken(String token, Users user) {
        this.token = token;
        this.users = user;

        // 1h
        expiryDate = new Date(System.currentTimeMillis() + 1);
    }
}
