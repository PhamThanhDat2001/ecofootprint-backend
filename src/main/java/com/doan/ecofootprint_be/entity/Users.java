package com.doan.ecofootprint_be.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username", unique = true)
    private  String username;

    @Column(name = "email", unique = true)
    private  String email;

    @Column(name = "password")
    private  String password;

    @Column(name = "fullname")
    private  String fullname;

    @Column(name = "gender")
    private  GENDER gender;

    @Column(name = "address")
    private  String address;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "phone")
    private  String phone;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "`status`", nullable = false)
    private UserStatus status = UserStatus.NOT_ACTIVE;
    public Users(String userName, String email, String password) {
        this.username = userName;
        this.email = email;
        this.password = password;
    }
    private boolean isActive;
    public enum UserStatus {
        NOT_ACTIVE, ACTIVE;
    }
    public enum GENDER{
    Male, Female, Unknown;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ROLE role = ROLE.USER;


    public enum ROLE {
        USER, ADMIN
    }
//    @OneToMany(mappedBy = "users")
//    private List<EcoFootprintLog> ecoFootprintLogs;
//    @OneToMany(mappedBy = "users")
//    private List<EnergyConsumption> energyConsumptions;
//    @OneToMany(mappedBy = "users")
//    private List<FoodConsumption> foodConsumptions;
//    @OneToMany(mappedBy = "users")
//    private List<GreenEnergyUsage> greenEnergyUsages;
//    @OneToMany(mappedBy = "users")
//    private List<RandomDailyActivities> randomDailyActivities;
//    @OneToMany(mappedBy = "users")
//    private List<Transportation> transportation;
//    @OneToMany(mappedBy = "users")
//    private List<Waste> wastes;
//    @OneToMany(mappedBy = "users")
//    private List<WaterConsumption> waterConsumptions;
}
