package com.doan.ecofootprint_be.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "result_eco_footprint")
public class ResultEcoFootprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private  String username;
    @Column(name = "result")
    private BigDecimal result;

    @Column(name = "total_results")
    private BigDecimal total_results;

    @Column(name = "user_id")
    private String user_id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private Users users;
}
