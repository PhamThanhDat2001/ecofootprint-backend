package com.doan.ecofootprint_be.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "eco_footprint_log")
public class EcoFootprintLog {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "log_time")
    private Date logTime;

    @Column(name = "footprint_type")
    private  String footprintType;

    @Column(name = "log_description")
    private  String logDescription;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
}
